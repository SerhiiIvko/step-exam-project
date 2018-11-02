package com.step.ivko.web.listener;

import com.step.ivko.config.PropertiesManager;
import com.step.ivko.dao.DBManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

public class ApplicationLifecycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties applicationProperties = PropertiesManager.getInstance().getApplicationProperties();
        DBManager.getInstance().initialize(applicationProperties);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBManager.getInstance().stopDb();
    }
}