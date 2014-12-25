/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import logger.GeneralLogger;

/**
 *
 * @author group12
 */
public class UtilsAnnotation {

    public final static Map<Class<?>, Class<?>> map = new HashMap<>();

    static {
        map.put(boolean.class, Boolean.class);
        map.put(byte.class, Byte.class);
        map.put(short.class, Short.class);
        map.put(char.class, Character.class);
        map.put(int.class, Integer.class);
        map.put(long.class, Long.class);
        map.put(float.class, Float.class);
        map.put(double.class, Double.class);
    }

    public static String capitalizeFirstLetter(String s) {
        char[] stringArray = s.toCharArray();
        stringArray[0] = Character.toUpperCase(stringArray[0]);
        return new String(stringArray);
    }

    public static List<Field> getRequiredFields(Class clazz) throws NoSuchFieldException {
        List<Field> requiredFields = new LinkedList<>();

        while (clazz != Object.class && clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(RequiredParameterAnnotation.class)) {
                    requiredFields.add(field);
                }
            }

            clazz = clazz.getSuperclass();
        }

        return requiredFields;
    }

    public static boolean containClass(List<?> list, Class c) {
        boolean found = false;
        for (Object o : list) {
            if (o.getClass().equals(c)) {
                found = true;
            }
        }

        return found;
    }

    public static void removeElementsByClass(List<?> list, Class c) {
        Iterator<?> i = list.iterator();
        while (i.hasNext()) {
            Object o = i.next();

            if (o.getClass().equals(c)) {
                i.remove();
            }
        }
    }
    
    public static void createClassByRequiredParameters(Object instance) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class c = instance.getClass();
        List<Field> requiredFields = UtilsAnnotation.getRequiredFields(c);

        for (Field requireField : requiredFields) {
            RequiredParameterAnnotation requiredParameterAnnotation = requireField.getAnnotation(RequiredParameterAnnotation.class);

            Object value = JOptionPane.showInputDialog(requiredParameterAnnotation.description() + " - "
                    + requiredParameterAnnotation.type());

            Class typeOfField = requireField.getType();
            if (typeOfField.isPrimitive()) {
                Class tempTypeOfField = UtilsAnnotation.map.get(requireField.getType());

                try {
                    Constructor converted = tempTypeOfField.getConstructor(String.class);

                    value = converted.newInstance(value);
                } catch (Exception exc) {
                    GeneralLogger.logger.log(Level.OFF, "Invalid type to set the new parameter");
                }
            }

            Method setter = c.getMethod("set" + UtilsAnnotation.capitalizeFirstLetter(requireField.getName()), typeOfField);
            setter.invoke(instance, value);
        }
    }
}
