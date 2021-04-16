package com.imadelfetouh.adminservice.dalinterface;

import com.imadelfetouh.adminservice.model.dto.ChangeRoleDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;

public interface RoleDal {

    ResponseModel<Void> changeRole(ChangeRoleDTO changeRoleDTO);
}
