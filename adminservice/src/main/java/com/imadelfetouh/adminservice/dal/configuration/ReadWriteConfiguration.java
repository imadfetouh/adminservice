package com.imadelfetouh.adminservice.dal.configuration;

import org.hibernate.cfg.Environment;
import java.util.Properties;

public class ReadWriteConfiguration {

    private static final ReadWriteConfiguration readWriteConfiguration = new ReadWriteConfiguration();
    private Properties properties;

    private ReadWriteConfiguration() {
        properties = new Properties();

        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.USER, System.getenv("MYSQL_USER"));
        properties.put(Environment.PASS, System.getenv("MYSQL_PASS"));
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MariaDBDialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.C3P0_MIN_SIZE, "1");
        properties.put(Environment.C3P0_MAX_SIZE, "2");
        properties.put(Environment.C3P0_ACQUIRE_INCREMENT, "1");
        properties.put(Environment.C3P0_TIMEOUT, "1800");
    }

    public static ReadWriteConfiguration getInstance() {
        return readWriteConfiguration;
    }

    public Properties getProperties() {
        return properties;
    }
}
