package edu.hm.shareit.server.resources.oauth;

import edu.hm.shareit.server.model.UserCredentials;
import edu.hm.shareit.server.service.auth.AuthenticationServiceImp;
import sun.misc.BASE64Decoder;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.StringTokenizer;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/26/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

@Path("/oauth2")
public class AuthenticationResource {

    private final AuthenticationServiceImp authservice = new AuthenticationServiceImp();

    @Path("/authorize")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@HeaderParam("authorization") String authString) {

        Response response = Response.status(Response.Status.UNAUTHORIZED).build();

        try {

            String usernameAndPassword = "";
            // Header is in the format "Basic 5tyc0uiDat4"
            // We need to extract data before decoding it back to original string
            String[] authParts = authString.split("\\s+");
            String authInfo = authParts[1];
            // Decode the data back to original string
            byte[] bytes = null;
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
            usernameAndPassword = new String(bytes);



            final StringTokenizer tokenizer = new StringTokenizer(
                    usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

                String token = authservice.authorizeUser(new UserCredentials(username,password));

                if(token.isEmpty())
                    response = Response.status(Response.Status.UNAUTHORIZED).build();
                else
                    response= Response.ok(token).build();


        } catch (Exception e) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }


        return response;

    }

    @Path("/token")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkToken(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {


        Response response = Response.status(Response.Status.UNAUTHORIZED).build();
        try {

            String token = authorizationHeader;
            if(!token.isEmpty()) {
                //Check token
                boolean result = authservice.validateToken(token);


                // Return the token on the response
                if(result)
                    response = Response.ok(Response.Status.ACCEPTED).build();
            }

        } catch (Exception e) {
            return response;
        }


        return response;

    }

    @Path("/logout")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {


        Response response = Response.status(Response.Status.UNAUTHORIZED).build();
        try {

            String token = authorizationHeader;
            if(!token.isEmpty()) {

                authservice.removeToken(token);
                response = Response.ok(Response.Status.OK).build();
            }

        } catch (Exception e) {
            return response;
        }


        return response;

    }

}
