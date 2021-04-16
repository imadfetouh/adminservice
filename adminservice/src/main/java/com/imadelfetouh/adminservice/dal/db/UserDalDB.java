package com.imadelfetouh.adminservice.dal.db;

import com.imadelfetouh.adminservice.dal.configuration.Executer;
import com.imadelfetouh.adminservice.dal.configuration.SessionType;
import com.imadelfetouh.adminservice.dal.queryexecuter.AddUserExecuter;
import com.imadelfetouh.adminservice.dal.queryexecuter.DeleteUserExecuter;
import com.imadelfetouh.adminservice.dal.queryexecuter.GetUsersExecuter;
import com.imadelfetouh.adminservice.dalinterface.UserDal;
import com.imadelfetouh.adminservice.model.dto.NewUserDTO;
import com.imadelfetouh.adminservice.model.dto.UserDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
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
        Executer<Void> executer = new Executer<>(SessionType.WRITE);
        return executer.execute(new AddUserExecuter(newUserDTO));
    }

    @Override
    public ResponseModel<Void> deleteUser(String userId) {
        Executer<Void> executer = new Executer<>(SessionType.WRITE);
        return executer.execute(new DeleteUserExecuter(userId));
    }
}
