package edu.hm.shareit.server.service;

import edu.hm.shareit.server.model.UserCredentials;
import edu.hm.shareit.server.service.auth.AuthenticationService;
import edu.hm.shareit.server.service.auth.AuthenticationServiceImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * AuthenticationServiceImp Tester.
 *
 * @author <Authors name>
 * @since <pre>May 27, 2017</pre>
 * @version 1.0
 */
public class AuthenticationServiceImpTest {


    UserCredentials userCredentialsLisa = new UserCredentials("lisa","Hallo123");
    UserCredentials userCredentialsPeter = new UserCredentials("peter","wert1234");
    AuthenticationService authservice  = new AuthenticationServiceImp();


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: validateToken(String token)
     *
     */
    @Test
    public void testValidateToken() throws Exception {
        //TODO: Test goes here...
    }

    /**
     *
     * Method: authorizeUser(User user)
     *
     */
    @Test
    public void testAuthorizeUser() throws Exception {
        //TODO: Test goes here...
    }


    /**
     *
     * Method: createToken(User user)
     *
     */
    @Test
    public void testCreateToken() throws Exception {

        //TODO: Test goes here...

    }

    /**
     *
     * Method: getToken(User user, Token token)
     *
     */
    @Test
    public void testGetToken() throws Exception {}


    @Test
    public void testRemoveToken() throws Exception {
        String tokenPeter = authservice.authorizeUser(userCredentialsPeter);
        assertNotNull(tokenPeter);

        boolean validationStatus = authservice.validateToken(tokenPeter);
        assertTrue(validationStatus);
        authservice.removeToken(tokenPeter);

        validationStatus= authservice.validateToken(tokenPeter);
        assertFalse(validationStatus);



    }
}
