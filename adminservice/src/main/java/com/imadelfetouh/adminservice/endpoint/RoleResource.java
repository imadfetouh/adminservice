package com.imadelfetouh.adminservice.endpoint;

import com.imadelfetouh.adminservice.dalinterface.RoleDal;
import com.imadelfetouh.adminservice.model.dto.ChangeRoleDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role")
public class RoleResource {

    @Autowired
    private RoleDal roleDal;

    @PutMapping
    public ResponseEntity<Void> deleteUser(@RequestBody ChangeRoleDTO changeRoleDTO) {

        ResponseModel<Void> responseModel = roleDal.changeRole(changeRoleDTO);

        if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(500).build();

    }
}
