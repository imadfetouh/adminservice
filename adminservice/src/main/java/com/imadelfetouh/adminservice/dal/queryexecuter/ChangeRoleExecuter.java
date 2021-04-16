package com.imadelfetouh.adminservice.dal.queryexecuter;

import com.imadelfetouh.adminservice.dal.configuration.QueryExecuter;
import com.imadelfetouh.adminservice.dal.ormmodel.Role;
import com.imadelfetouh.adminservice.model.dto.ChangeRoleDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import org.hibernate.Session;

import javax.persistence.Query;

public class ChangeRoleExecuter implements QueryExecuter<Void> {

    private ChangeRoleDTO changeRoleDTO;

    public ChangeRoleExecuter(ChangeRoleDTO changeRoleDTO) {
        this.changeRoleDTO = changeRoleDTO;
    }

    @Override
    public ResponseModel<Void> executeQuery(Session session) {
        ResponseModel<Void> responseModel = new ResponseModel<>();

        Query query = session.createQuery("UPDATE User u SET u.role = :role WHERE u.userId = :userId");
        query.setParameter("role", Role.valueOf(changeRoleDTO.getRole()));
        query.setParameter("userId", changeRoleDTO.getUserId());

        query.executeUpdate();

        session.getTransaction().commit();

        responseModel.setResponseType(ResponseType.CORRECT);

        return responseModel;
    }
}
