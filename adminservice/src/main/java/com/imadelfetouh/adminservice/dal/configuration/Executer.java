package com.imadelfetouh.adminservice.dal.configuration;

import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Executer<T> extends SessionHelper {

    private static final Logger logger = Logger.getLogger(Executer.class.getName());

    public Executer(SessionType sessionType) {
        super(sessionType);
    }

    public ResponseModel<T> execute(QueryExecuter<T> queryExecuter) {

        ResponseModel<T> responseModel = new ResponseModel<>();

        try {
            responseModel = queryExecuter.executeQuery(getSession());
        }
        catch (Exception e) {
            logger.severe(e.getMessage());
            rollback();
            responseModel.setResponseType(ResponseType.ERROR);
        }
        finally {
            closeSession();
        }

        return responseModel;
    }
}
