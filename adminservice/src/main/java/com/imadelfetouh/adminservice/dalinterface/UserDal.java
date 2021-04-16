package com.imadelfetouh.adminservice.dalinterface;

import com.imadelfetouh.adminservice.model.dto.NewUserDTO;
import com.imadelfetouh.adminservice.model.dto.UserDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;

import java.util.List;

public interface UserDal {

    ResponseModel<List<UserDTO>> getUsers();

    ResponseModel<Void> addUser(NewUserDTO newUserDTO);

    ResponseModel<Void> deleteUser(String userId);
}
