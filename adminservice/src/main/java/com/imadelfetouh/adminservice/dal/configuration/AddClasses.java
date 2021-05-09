package com.imadelfetouh.adminservice.dal.configuration;

import com.imadelfetouh.adminservice.dal.ormmodel.Profile;
import com.imadelfetouh.adminservice.dal.ormmodel.User;
import org.hibernate.cfg.Configuration;

public class AddClasses {

    private static final AddClasses addClasses = new AddClasses();

    private AddClasses() {

    }

    public static AddClasses getInstance() {
        return addClasses;
    }

    public void setClasses(Configuration configuration) {
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Profile.class);
    }
}
