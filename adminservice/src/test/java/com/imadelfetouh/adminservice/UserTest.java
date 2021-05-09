package com.imadelfetouh.adminservice;

import com.imadelfetouh.adminservice.dal.configuration.Executer;
import com.imadelfetouh.adminservice.dal.configuration.SessionType;
import com.imadelfetouh.adminservice.dal.db.UserDalDB;
import com.imadelfetouh.adminservice.dal.ormmodel.Role;
import com.imadelfetouh.adminservice.dal.queryexecuter.SetupDatabase;
import com.imadelfetouh.adminservice.model.dto.NewUserDTO;
import com.imadelfetouh.adminservice.model.dto.ProfileDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import org.junit.jupiter.api.*;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {

    @BeforeAll
    static void setupDatabase() {
        Executer<Void> executer = new Executer<>(SessionType.WRITE);
        executer.execute(new SetupDatabase());
    }

    @Test
    @Order(1)
    void addUserCorrect() {
        UserDalDB userDalDB = new UserDalDB();

        NewUserDTO newUserDTO = new NewUserDTO("u123", "imad", "imad", Role.USER.name(), "imad.jpg", new ProfileDTO("p123", "Hello", "Helmond", "imad.nl"));

        ResponseModel<Void> responseModel = userDalDB.addUser(newUserDTO);
        Assertions.assertEquals(ResponseType.CORRECT, responseModel.getResponseType());

        String url = "http://localhost:8081/auth/signin";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("username", "imad");
        map.add("password", "imad");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());

    }

    @Test
    @Order(2)
    void deleteUserCorrect() throws InterruptedException {
        UserDalDB userDalDB = new UserDalDB();
        ResponseModel<Void> responseModel = userDalDB.deleteUser("u123");

        Assertions.assertEquals(ResponseType.CORRECT, responseModel.getResponseType());

        Thread.sleep(2000);

        String url = "http://localhost:8081/auth/signin";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("username", "imad");
        map.add("password", "imad");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = null;

        try{
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        }
        catch (HttpClientErrorException e) {
            Assertions.assertEquals(400, e.getStatusCode().value());
        }
        
    }
}
