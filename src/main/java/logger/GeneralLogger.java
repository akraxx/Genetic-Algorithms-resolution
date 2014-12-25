/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.util.logging.Logger;

/**
 *
 * @author group12
 */
public class GeneralLogger {

    public static final Logger logger = Logger.getLogger(GeneralLogger.class.getName());
    public static final GeneralHandler generalHandler = new GeneralHandler();

    static {
        logger.addHandler(generalHandler);
    }
}
