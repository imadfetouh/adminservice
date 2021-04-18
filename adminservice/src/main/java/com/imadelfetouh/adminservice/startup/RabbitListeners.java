package com.imadelfetouh.adminservice.startup;

import com.imadelfetouh.adminservice.rabbit.thread.AddUserThread;
import com.imadelfetouh.adminservice.rabbit.thread.ChangeRoleThread;
import com.imadelfetouh.adminservice.rabbit.thread.DeleteUserThread;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Profile("!test")
@Component
public class RabbitListeners implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Executor executor = Executors.newScheduledThreadPool(3);

        AddUserThread addUserThread = new AddUserThread();
        executor.execute(addUserThread);

        DeleteUserThread deleteUserThread = new DeleteUserThread();
        executor.execute(deleteUserThread);

        ChangeRoleThread changeRoleThread = new ChangeRoleThread();
        executor.execute(changeRoleThread);
    }
}
