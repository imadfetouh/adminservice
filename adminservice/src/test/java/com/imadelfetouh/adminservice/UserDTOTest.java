package com.imadelfetouh.adminservice;

import com.imadelfetouh.adminservice.dal.ormmodel.Role;
import com.imadelfetouh.adminservice.model.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserDTOTest {

    @Test
    void testConstructor() {
        UserDTO userDTO = new UserDTO("u123", Role.USER.name(), "imad", "imad.jpg", "Hello", "Helmond", "imad.nl");

        Assertions.assertEquals("u123", userDTO.getUserId());
        Assertions.assertEquals("USER", userDTO.getRole());
        Assertions.assertEquals("imad", userDTO.getUsername());
        Assertions.assertEquals("imad.jpg", userDTO.getPhoto());
        Assertions.assertEquals("Hello", userDTO.getBio());
        Assertions.assertEquals("Helmond", userDTO.getLocation());
        Assertions.assertEquals("imad.nl", userDTO.getWebsite());
    }
}
