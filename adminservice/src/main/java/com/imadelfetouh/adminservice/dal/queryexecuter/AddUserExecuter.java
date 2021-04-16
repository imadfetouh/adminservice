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

        String profileId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        Profile profile = new Profile(profileId, "bio", "Helmond", "fontys.nl");

        User user = new User(userId, newUserDTO.getUsername(), newUserDTO.getPassword(), Role.USER, "new.jpg", profile);

        session.persist(profile);
        session.persist(user);

        session.getTransaction().commit();

        responseModel.setResponseType(ResponseType.CORRECT);

        return responseModel;
    }
}
