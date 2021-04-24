package com.imadelfetouh.adminservice.dal.queryexecuter;

import com.imadelfetouh.adminservice.dal.configuration.QueryExecuter;
import com.imadelfetouh.adminservice.dal.ormmodel.Profile;
import com.imadelfetouh.adminservice.dal.ormmodel.Role;
import com.imadelfetouh.adminservice.dal.ormmodel.User;
import com.imadelfetouh.adminservice.model.dto.NewUserDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import org.hibernate.Session;
import java.util.UUID;

public class AddUserExecuter implements QueryExecuter<Void> {

    private NewUserDTO newUserDTO;

    public AddUserExecuter(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }

    @Override
    public ResponseModel<Void> executeQuery(Session session) {
        ResponseModel<Void> responseModel = new ResponseModel<>();

        if(newUserDTO.getUserId() == null) {
            newUserDTO.setUserId(UUID.randomUUID().toString());
        }

        if(newUserDTO.getProfile().getProfileId() == null) {
            newUserDTO.getProfile().setProfileId(UUID.randomUUID().toString());
        }

        Profile profile = new Profile(newUserDTO.getProfile().getProfileId(), newUserDTO.getProfile().getBio(), newUserDTO.getProfile().getLocation(), newUserDTO.getProfile().getWebsite());

        User user = new User(newUserDTO.getUserId(), newUserDTO.getUsername(), newUserDTO.getPassword(), Role.valueOf(newUserDTO.getRole()), newUserDTO.getPhoto(), profile);

        session.persist(profile);
        session.persist(user);

        session.getTransaction().commit();

        responseModel.setResponseType(ResponseType.CORRECT);

        return responseModel;
    }
}
