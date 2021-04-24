package com.imadelfetouh.adminservice.dal.queryexecuter;

import com.imadelfetouh.adminservice.dal.configuration.QueryExecuter;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class CheckUsernameExecuter implements QueryExecuter<Void> {

    private String username;

    public CheckUsernameExecuter(String username) {
        this.username = username;
    }

    @Override
    public ResponseModel<Void> executeQuery(Session session) {
        ResponseModel<Void> responseModel = new ResponseModel<>();

        Query query = session.createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);

        try{
            query.getSingleResult();
            responseModel.setResponseType(ResponseType.USERNAMEALREADYINUSE);
        }
        catch (NoResultException e) {
            responseModel.setResponseType(ResponseType.CORRECT);
        }

        return responseModel;
    }
}
