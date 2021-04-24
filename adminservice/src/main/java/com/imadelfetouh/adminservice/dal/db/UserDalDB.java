package com.imadelfetouh.adminservice.dal.db;

import com.imadelfetouh.adminservice.dal.configuration.Executer;
import com.imadelfetouh.adminservice.dal.configuration.SessionType;
import com.imadelfetouh.adminservice.dal.queryexecuter.AddUserExecuter;
import com.imadelfetouh.adminservice.dal.queryexecuter.CheckUsernameExecuter;
import com.imadelfetouh.adminservice.dal.queryexecuter.DeleteUserExecuter;
import com.imadelfetouh.adminservice.dal.queryexecuter.GetUsersExecuter;
import com.imadelfetouh.adminservice.dalinterface.UserDal;
import com.imadelfetouh.adminservice.model.dto.NewUserDTO;
import com.imadelfetouh.adminservice.model.dto.UserDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import com.imadelfetouh.adminservice.rabbit.RabbitProducer;
import com.imadelfetouh.adminservice.rabbit.producer.AddUserProducer;
import com.imadelfetouh.adminservice.rabbit.producer.DeleteUserProducer;
import com.imadelfetouh.adminservice.security.PasswordHash;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDalDB implements UserDal {

    @Override
    public ResponseModel<List<UserDTO>> getUsers() {
        Executer<List<UserDTO>> executer = new Executer<>(SessionType.READ);
        return executer.execute(new GetUsersExecuter());
    }

    @Override
    public ResponseModel<Void> addUser(NewUserDTO newUserDTO) {
        Executer<Void> executer = new Executer<>(SessionType.READ);
        ResponseModel<Void> responseModel = executer.execute(new CheckUsernameExecuter(newUserDTO.getUsername()));

        if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            String password = PasswordHash.getInstance().hash(newUserDTO.getPassword());
            newUserDTO.setPassword(password);

            Executer<Void> executerRegister = new Executer<>(SessionType.WRITE);
            responseModel = executerRegister.execute(new AddUserExecuter(newUserDTO));
            if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
                RabbitProducer rabbitProducer = new RabbitProducer();
                rabbitProducer.produce(new AddUserProducer(newUserDTO));
            }
        }

        return responseModel;
    }

    @Override
    public ResponseModel<Void> deleteUser(String userId) {
        Executer<Void> executer = new Executer<>(SessionType.WRITE);
        ResponseModel<Void> responseModel = executer.execute(new DeleteUserExecuter(userId));

        if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            RabbitProducer rabbitProducer = new RabbitProducer();
            rabbitProducer.produce(new DeleteUserProducer(userId));
        }

        return responseModel;
    }
}
