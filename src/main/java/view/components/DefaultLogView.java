/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import logger.LoggerEvent;
import logger.LoggerListener;

/**
 *
 * @author group12
 */
public class DefaultLogView extends JPanel implements LoggerListener {

    private JTextArea jTextArea;
    private JScrollPane jScrollPane;

    public DefaultLogView(int rows, int columns) {
        this.jTextArea = new JTextArea(rows, columns);
        this.jScrollPane = new JScrollPane(jTextArea);

        this.add(this.jScrollPane);
        this.revalidate();
    }

    @Override
    public void logAdded(LoggerEvent e) {
        if (e != null) {
            this.jTextArea.append(e.getRecord().getMessage() + System.getProperty("line.separator"));
        }
    }
}
