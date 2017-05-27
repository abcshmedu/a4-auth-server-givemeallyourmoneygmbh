package edu.hm.shareit.server.service.user;

import edu.hm.shareit.server.model.Identity;
import edu.hm.shareit.server.model.User;
import edu.hm.shareit.server.model.UserCredentials;
import edu.hm.shareit.server.service.auth.AuthenticationService;
import edu.hm.shareit.server.service.auth.AuthenticationServiceImp;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/27/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public class UserServiceImpTest {

    UserService service = new UserServiceImp();
    AuthenticationService authenticationService = new AuthenticationServiceImp();


    UserCredentials userCredentialsLisa = new UserCredentials("lisa","Hallo123");
    UserCredentials userCredentialsPeter = new UserCredentials("peter","wert1234");
    /*
        User getUser(String token, String userId);
        User updateUser(String token, String userId, User user);
        List<User> getUsers(String token);
     */
    @Test
    public void getUser() throws Exception {
        String tokenLisa = authenticationService.authorizeUser(userCredentialsLisa);
        User lisa = service.getUser(tokenLisa, "1");
        User peter = service.getUser(tokenLisa, "2");

        assertNotNull(lisa);
        assertNotNull(peter);


        String tokenPeter = authenticationService.authorizeUser(userCredentialsPeter);
        lisa = service.getUser(tokenPeter, "1");
        peter = service.getUser(tokenPeter, "2");

        assertNull(lisa);
        assertNotNull(peter);
    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void getUsers() throws Exception {

        String tokenLisa = authenticationService.authorizeUser(userCredentialsLisa);
        List<Identity> all = service.getUsers(tokenLisa);

        assertNotNull(all);


        String tokenPeter = authenticationService.authorizeUser(userCredentialsPeter);
        all = service.getUsers(tokenPeter);


        assertNull(all);


    }

}