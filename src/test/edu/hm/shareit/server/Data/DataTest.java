package edu.hm.shareit.server.Data;

import edu.hm.shareit.server.model.Token;
import edu.hm.shareit.server.model.User;
import edu.hm.shareit.server.model.UserCredentials;
import edu.hm.shareit.server.service.auth.AuthenticationService;
import edu.hm.shareit.server.service.auth.AuthenticationServiceImp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/27/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public class DataTest {

    String token ="";
    UserCredentials userCredentialsLisa = new UserCredentials("lisa","Hallo123");
    UserCredentials userCredentialsPeter = new UserCredentials("peter","wert1234");
    AuthenticationService authservice  = new AuthenticationServiceImp();
    @Before
    public void before() throws Exception {


        token = authservice.authorizeUser(new UserCredentials("lisa","Hallo123"));

        assertNotNull(token);
    }


    @Test
    public void findToken() throws Exception {

        assertNotNull(token);

        Token obj = Data.findToken(token);
        assertNotNull(obj);

    }

    @Test
    public void getUser() throws Exception {
        final User user = Data.getUser(token);
        assertNotNull(user);

    }

    @Test
    public void getOtherUser() throws Exception {
        String tokenPeter = authservice.authorizeUser(userCredentialsPeter);
        final User lisa = Data.getUser(token);
        assertNotNull(lisa);

        final User peter = Data.getUser(userCredentialsPeter);
        assertNotNull(peter);

    }


}