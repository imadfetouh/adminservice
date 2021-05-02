package com.imadelfetouh.adminservice.endpoint;

import com.google.gson.Gson;
import com.imadelfetouh.adminservice.dal.ormmodel.Role;
import com.imadelfetouh.adminservice.dalinterface.RoleDal;
import com.imadelfetouh.adminservice.model.dto.ChangeRoleDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("role")
public class RoleResource {

    private static final Logger logger = Logger.getLogger(RoleResource.class.getName());

    @Autowired
    private RoleDal roleDal;

    @PutMapping()
    public ResponseEntity<Void> deleteUser(@RequestParam("role") String role) {

        logger.info("Change role request with body: " + role);

        Gson gson = new Gson();

        ChangeRoleDTO changeRoleDTO = gson.fromJson(role, ChangeRoleDTO.class);

        ResponseModel<Void> responseModel = roleDal.changeRole(changeRoleDTO);

        if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(500).build();

    }
}
