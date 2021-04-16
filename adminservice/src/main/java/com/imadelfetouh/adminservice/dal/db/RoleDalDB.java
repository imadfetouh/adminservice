package com.imadelfetouh.adminservice.dal.db;

import com.imadelfetouh.adminservice.dal.configuration.Executer;
import com.imadelfetouh.adminservice.dal.configuration.SessionType;
import com.imadelfetouh.adminservice.dal.queryexecuter.ChangeRoleExecuter;
import com.imadelfetouh.adminservice.dalinterface.RoleDal;
import com.imadelfetouh.adminservice.model.dto.ChangeRoleDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDalDB implements RoleDal {

    @Override
    public ResponseModel<Void> changeRole(ChangeRoleDTO changeRoleDTO) {
        Executer<Void> executer = new Executer<>(SessionType.WRITE);
        return executer.execute(new ChangeRoleExecuter(changeRoleDTO));
    }
}
