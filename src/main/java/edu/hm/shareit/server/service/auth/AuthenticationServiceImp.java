package edu.hm.shareit.server.service.auth;

import edu.hm.shareit.server.Data.Data;
import edu.hm.shareit.server.model.Token;
import edu.hm.shareit.server.model.User;
import edu.hm.shareit.server.model.UserCredentials;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/26/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public class AuthenticationServiceImp  implements AuthenticationService {

    private static long TIME = 1000*60*5; //%Minuten

     public AuthenticationServiceImp() {

    }

    public boolean validateToken(String token) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        boolean result = false;

        Token item = Data.findToken(token);

        if (item != null) {
            if(item.getToken().equals(token)){


                if(item.getExpire() > System.currentTimeMillis()) {
                    result = true;
                }

            }
        }



        return result;
    }



    public String authorizeUser(UserCredentials userCredentials) throws UnsupportedEncodingException, NoSuchAlgorithmException {


        //exist user?
        if(Data.validateUser(userCredentials)) {
            User user = Data.getUser(userCredentials);
            return createToken(user);
        }

        return "";

    }


    private String createToken(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final long currentTimeMillis = System.currentTimeMillis();
        Token token = new Token("", currentTimeMillis, user.getUserId() ,currentTimeMillis+ TIME);


        String result="";
        boolean valid = Data.validateUser(user);
        if(valid){
            result = getToken(user,token);
            token.setToken(result);

            Data.addToken(user, token,result );
        }

        return result;
    }

    private String getToken(User user, Token token) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-512");

        String text = "{\"userId\":" + token.getUserId() +
                "\"expires\":" +token.getExpire()+
                "\"admin\":" +user.getIsAdmin() +
                "\"random\":" +token.getRandombytes().toString() +
                "}";

        md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();


        String encodeString = Base64.getEncoder().encodeToString(digest);

        return encodeString;
    }

    public void removeToken(String token) {

        Data.removeToken(token);

    }

}
