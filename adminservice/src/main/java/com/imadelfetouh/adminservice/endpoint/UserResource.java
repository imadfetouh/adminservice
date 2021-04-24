package com.imadelfetouh.adminservice.endpoint;

import com.imadelfetouh.adminservice.dalinterface.UserDal;
import com.imadelfetouh.adminservice.model.dto.NewUserDTO;
import com.imadelfetouh.adminservice.model.dto.UserDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserResource {

    @Autowired
    private UserDal userDal;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers() {
        ResponseModel<List<UserDTO>> responseModel = userDal.getUsers();

        if(responseModel.getResponseType().equals(ResponseType.EMPTY)) {
            return ResponseEntity.noContent().build();
        }
        else if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            return ResponseEntity.ok().body(responseModel.getData());
        }

        return ResponseEntity.status(500).build();
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody NewUserDTO newUserDTO) {

        ResponseModel<Void> responseModel = userDal.addUser(newUserDTO);

        if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            return ResponseEntity.ok().build();
        }
        else if(responseModel.getResponseType().equals(ResponseType.USERNAMEALREADYINUSE)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(500).build();

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {

        ResponseModel<Void> responseModel = userDal.deleteUser(userId);

        if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(500).build();

    }
}
