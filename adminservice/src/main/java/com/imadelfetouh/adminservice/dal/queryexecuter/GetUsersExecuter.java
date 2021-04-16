package com.imadelfetouh.adminservice.dal.queryexecuter;

import com.imadelfetouh.adminservice.dal.configuration.QueryExecuter;
import com.imadelfetouh.adminservice.model.dto.UserDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class GetUsersExecuter implements QueryExecuter<List<UserDTO>> {

    @Override
    public ResponseModel<List<UserDTO>> executeQuery(Session session) {
        ResponseModel<List<UserDTO>> responseModel = new ResponseModel<>();

        Query query = session.createQuery("SELECT new com.imadelfetouh.adminservice.model.dto.UserDTO(u.userId, u.username, u.photo, u.profile.bio, u.profile.location, u.profile.website) FROM User u");

        List<UserDTO> userDTOS = query.getResultList();

        responseModel.setData(userDTOS);

        responseModel.setResponseType((userDTOS.isEmpty()) ? ResponseType.EMPTY : ResponseType.CORRECT);

        return responseModel;
    }
}
