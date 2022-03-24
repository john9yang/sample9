package logging;

import ClassLoaderTest.AgentPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JExample {
    public static void main(String[] args) {
        //Creating the Logger object
        Logger logger = LoggerFactory.getLogger(AgentPath.class);
        //Logging the information
        logger.info("Hi This is my first SLF4J program");

        //package name
        Logger logger1 = LoggerFactory.getLogger("logging");
        logger1.warn("logging log");
    }
}
