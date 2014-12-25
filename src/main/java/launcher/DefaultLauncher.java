/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import annotations.UtilsAnnotation;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import samples.TSP.TSP;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import problem.ProblemFactory;
import samples.min1D.Min1D;

/**
 *
 * @author group12
 */
public class DefaultLauncher implements ActionListener {
    private JFrame jFrame = new JFrame("Launcher");
    private JButton actionButton = new JButton("CONFIGURE");
    private JLabel description = new JLabel("Choose your problem :");
    private JButton backButton = new JButton("BACK");
    private JPanel contentPane = new JPanel(new FlowLayout());
    private JComboBox launcherList = new JComboBox();
    private JLabel error = new JLabel("");
    
    private List<Class<? extends Launcher>> problems;
    private Class<? extends Launcher> defaultLauncher;
    private String[] args;
    private boolean gui = true;
    
    private Launcher launcher = null;
    
    private void chooseLauncherFrame() {
        launcherList.removeAllItems();
        for(Class c : problems) {
            launcherList.addItem(c.getSimpleName());
        }

        actionButton.setName("CONFIGURE");
        description.setText("Choose your problem :");
        backButton.setVisible(false);
    }
    
    private void createLauncherFrame() {
        jFrame.setLayout(new BorderLayout(10, 10));
        
        jFrame.add(description, BorderLayout.NORTH);
        contentPane.add(launcherList);
        
        actionButton.addActionListener(this);
        backButton.addActionListener(this);
        backButton.setName("BACK");
        
        contentPane.add(actionButton);
        contentPane.add(backButton);
        
        this.chooseLauncherFrame();
        jFrame.add(contentPane, BorderLayout.CENTER);
        jFrame.add(error, BorderLayout.SOUTH);
        jFrame.setVisible(true);
        jFrame.setSize(400, 150);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void configureLauncherFrame(String problem) {
        Iterator<Class<? extends Launcher>> iterator = problems.iterator();
        boolean found = false;
        while (iterator.hasNext() && !found) {
            Class c = iterator.next();
            if(c.getSimpleName().equals(problem)) {
                try {
                    Object instance = c.newInstance();
                    UtilsAnnotation.createClassByRequiredParameters(instance);
                    
                    launcher = (Launcher)instance;
                    Class problemFactoryClass = launcher.getProblemFactory().getClass();
                    Method[] methods = problemFactoryClass.getMethods();
                    
                    int count = 0;
                    this.launcherList.removeAllItems();
                    for(Method m : methods) {
                        if(m.getName().endsWith(launcher.getProblemPrefix())) {
                            count++;
                            this.launcherList.addItem(m.getName().replaceFirst(launcher.getProblemPrefix(), ""));
                        }
                    }

                    if(count <= 0) {
                        launcher.lauch();
                        this.chooseLauncherFrame();
                    }
                    else {
                        this.description.setText("Choose one implementation :");
                        this.actionButton.setText("LAUNCH");
                        this.actionButton.setName("LAUNCH");

                        this.backButton.setVisible(true);
                    }
                    
                }
                catch (Exception e) {
                    error.setText("ERROR OCCURED : " + e.getMessage());
                }
            }
        }
    }
    private void launchByParameters() throws InstantiationException, IllegalAccessException {
        try {
            Iterator<Class<? extends Launcher>> iterator = problems.iterator();
            boolean found = false;
            while (iterator.hasNext() && !found) {
                Class c = iterator.next();
                if (c.getSimpleName().equals(args[0])) {
                    this.launcher = (Launcher)c.newInstance();
                    if (args.length == 2) {
                        this.launcher.setFactoryName(args[1]);
                    } else if (args.length == 3) {
                        this.launcher = (Launcher)c.getConstructor(String.class, int.class).newInstance(args[1], Integer.valueOf(args[2]));
                    } else {
                        this.launcher = (Launcher)c.newInstance();
                    }
                    found = true;
                }
            }
            
            this.launcher.lauch();
        } catch (Exception e) {
            defaultLauncher.newInstance();
        }
    }
    
    public DefaultLauncher(List<Class<? extends Launcher>> problems, String[] args, Class<? extends Launcher> defaultLauncher, boolean gui) {
        if(problems != null && args != null && defaultLauncher != null) {
            try {
                this.problems = problems;
                this.defaultLauncher = defaultLauncher;
                this.args = args;
                this.gui = gui;

                if (args.length > 0 && args[0] != null) {
                    this.launchByParameters();
                } else if(problems.size() > 0 && gui) {
                    this.createLauncherFrame();
                }
                else {
                    defaultLauncher.newInstance();
                }
            } catch (Exception exc) {
                System.out.println("Software was unable to run a launcher");
                exc.printStackTrace();
            }
            
        }
        else {
            throw new IllegalArgumentException("Default launcher can not have a null parameter");
        }
    }

    public static void main(String[] args) {
        List<Class<? extends Launcher>> problems = new LinkedList<>();

        problems.add(Min1D.class);
        problems.add(TSP.class);
        DefaultLauncher defaultLauncher = new DefaultLauncher(problems, args, Min1D.class, true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton button = (JButton)e.getSource();
            switch(button.getName()) {
                case "CONFIGURE":
                    this.configureLauncherFrame((String)this.launcherList.getSelectedItem());
                    break;
                case "LAUNCH":
                    if(this.launcher != null) {
                        this.launcher.setFactoryName((String)this.launcherList.getSelectedItem());
                        this.launcher.lauch();
                    }
                    break;
                case "BACK":
                    this.chooseLauncherFrame();
                    break;
            }
        }
    }
}
