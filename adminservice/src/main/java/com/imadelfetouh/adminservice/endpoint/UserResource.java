package com.imadelfetouh.adminservice.endpoint;

import com.google.gson.Gson;
import com.imadelfetouh.adminservice.dalinterface.UserDal;
import com.imadelfetouh.adminservice.model.dto.NewUserDTO;
import com.imadelfetouh.adminservice.model.dto.UserDTO;
import com.imadelfetouh.adminservice.model.response.ResponseModel;
import com.imadelfetouh.adminservice.model.response.ResponseType;
import com.mchange.v2.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("user")
public class UserResource {

    private static final Logger logger = Logger.getLogger(UserResource.class.getName());

    @Autowired
    private UserDal userDal;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers() {

        logger.info("get user request made");

        ResponseModel<List<UserDTO>> responseModel = userDal.getUsers();

        if(responseModel.getResponseType().equals(ResponseType.EMPTY)) {
            return ResponseEntity.noContent().build();
        }
        else if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            return ResponseEntity.ok().body(responseModel.getData());
        }

        return ResponseEntity.status(500).build();
    }

    @PostMapping()
    public ResponseEntity<Void> addUser(@RequestParam("user") String user, @RequestParam("photo") MultipartFile multipartFile) {

        logger.info("add user request made");

        Gson gson = new Gson();

        NewUserDTO newUserDTO = gson.fromJson(user, NewUserDTO.class);

        String photo = UUID.randomUUID().toString() + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        newUserDTO.setPhoto(photo);

        ResponseModel<Void> responseModel = userDal.addUser(newUserDTO);

        if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            uploadPhoto(multipartFile, photo);
            return ResponseEntity.ok().build();
        }
        else if(responseModel.getResponseType().equals(ResponseType.USERNAMEALREADYINUSE)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(500).build();

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {

        logger.info("delete user request made");

        ResponseModel<Void> responseModel = userDal.deleteUser(userId);

        if(responseModel.getResponseType().equals(ResponseType.CORRECT)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(500).build();

    }

    private void uploadPhoto(MultipartFile multipartFile, String photo) {
        try {
            String folder = "D:/imageskwetter/";
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + photo);
            Files.write(path, bytes);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
