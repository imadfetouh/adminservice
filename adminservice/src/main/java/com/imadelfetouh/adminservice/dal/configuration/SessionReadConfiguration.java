package com.imadelfetouh.adminservice.dal.configuration;

import com.imadelfetouh.adminservice.dal.ormmodel.Profile;
import com.imadelfetouh.adminservice.dal.ormmodel.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class SessionReadConfiguration {

    private final static SessionReadConfiguration sessionReadConfiguration = new SessionReadConfiguration();
    private final ReadWriteConfiguration readWriteConfiguration;
    private final SessionFactory sessionFactory;

    public SessionReadConfiguration() {
        readWriteConfiguration = ReadWriteConfiguration.getInstance();
        Configuration configuration = new Configuration();

        Properties properties = readWriteConfiguration.getProperties();
        configuration.addProperties(properties);
        configuration.getProperties().put(Environment.URL, "jdbc:mysql://"+System.getenv("ADMINSERVICE_MYSQL_REPLICA_HOST")+":"+System.getenv("ADMINSERVICE_MYSQL_REPLICA_PORT")+"/adminservice");

        AddClasses.getInstance().setClasses(configuration);

        sessionFactory = configuration.configure().buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public static SessionReadConfiguration getInstance() {
        return sessionReadConfiguration;
    }
}
