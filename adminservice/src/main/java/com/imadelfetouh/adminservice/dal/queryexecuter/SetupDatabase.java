package com.imadelfetouh.adminservice.dal.queryexecuter;

import com.imadelfetouh.adminservice.dal.configuration.QueryExecuter;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import org.hibernate.Session;

public class SetupDatabase implements QueryExecuter<Void> {
    @Override
    public ResponseModel<Void> executeQuery(Session session) {
        return new ResponseModel<>();
    }
}
