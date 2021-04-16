package com.imadelfetouh.adminservice.dal.configuration;

import com.imadelfetouh.adminservice.model.response.ResponseModel;
import org.hibernate.Session;

public interface QueryExecuter<T> {

    ResponseModel<T> executeQuery(Session session);
}
