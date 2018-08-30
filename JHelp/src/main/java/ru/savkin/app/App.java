package ru.savkin.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring app
 * @version 1.0
 * @author Dmitry Savkin
 */
public class App {

    /**
     * Loads xml config file
     */
    public void launcher() {
        new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    /**
     * Entry point
     * @param args not nessessary for this version
     */
    public static void main(String[] args) {
        App app = new App();
        app.launcher();
    }
}
