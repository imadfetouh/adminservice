package com.imadelfetouh.adminservice.startup;

import com.imadelfetouh.adminservice.dal.configuration.Executer;
import com.imadelfetouh.adminservice.dal.configuration.SessionType;
import com.imadelfetouh.adminservice.dal.queryexecuter.SetupDatabase;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class Hibernate implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Executer<Void> executer = new Executer<>(SessionType.WRITE);
        executer.execute(new SetupDatabase());
    }
}
