package com.imadelfetouh.adminservice.dal.db;

import com.imadelfetouh.adminservice.dal.configuration.Executer;
import com.imadelfetouh.adminservice.dal.configuration.SessionType;
import com.imadelfetouh.adminservice.dal.queryexecuter.ChangeRoleExecuter;
import com.imadelfetouh.adminservice.dalinterface.RoleDal;
import com.imadelfetouh.adminservice.model.dto.ChangeRoleDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import com.imadelfetouh.adminservice.rabbit.RabbitProducer;
import com.imadelfetouh.adminservice.rabbit.producer.AddUserProducer;
import com.imadelfetouh.adminservice.rabbit.producer.ChangeRoleProducer;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDalDB implements RoleDal {

    @Override
    public ResponseModel<Void> changeRole(ChangeRoleDTO changeRoleDTO) {
        Executer<Void> executer = new Executer<>(SessionType.WRITE);
        ResponseModel<Void> responseModel = executer.execute(new ChangeRoleExecuter(changeRoleDTO));

        if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            RabbitProducer rabbitProducer = new RabbitProducer();
            rabbitProducer.produce(new ChangeRoleProducer(changeRoleDTO));
        }

        return responseModel;
    }
}
