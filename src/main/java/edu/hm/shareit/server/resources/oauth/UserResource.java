package edu.hm.shareit.server.resources.oauth;

import edu.hm.shareit.server.model.Identity;
import edu.hm.shareit.server.model.User;
import edu.hm.shareit.server.service.user.UserServiceImp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/27/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

@Path("/user")
public class UserResource {

    UserServiceImp serviceImp = new UserServiceImp();

    @Path("/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(@HeaderParam("authorization") String token, @PathParam("userid") String userid) {

        Response response = Response.status(Response.Status.UNAUTHORIZED).build();


        try {
            //String token,String userId
            final User user = serviceImp.getUser(token,userid);
            System.out.println(user);
            if(user != null)
                response = Response.ok(user).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();

        }


        return response;

    }


    @Path("/{userid}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateUser(@HeaderParam("authorization") String token, @PathParam("userid") String userid, User user) {

        Response response = Response.status(Response.Status.UNAUTHORIZED).build();

        try {
            //String token, String userId, User user
            final User userResult = serviceImp.updateUser(token, userid, user);
            if(userResult != null)
                response = Response.ok(userResult).build();

        } catch (Exception e) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }


        return response;

    }


    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUsers(@HeaderParam("authorization") String token) {

        Response response = Response.status(Response.Status.UNAUTHORIZED).build();

        try {
            final List<Identity> users = serviceImp.getUsers(token);
            if(users!=null)
                response = Response.ok(users).build();

        } catch (Exception e) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }


        return response;

    }




}
