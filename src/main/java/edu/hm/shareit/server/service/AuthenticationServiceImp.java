package edu.hm.shareit.server.service;

import edu.hm.shareit.server.model.Token;
import edu.hm.shareit.server.model.User;

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

public class AuthenticationServiceImp implements AuthenticationService {

    private static long TIME = 90000;

    //private static Map< User, Token> tokens = new HashMap<>();

    //user, tokenlist
    private static Map<User, List<Token>> tokenlistOfUser = new HashMap<>();

    //userid, user
    private static Map<String, User> usersByUserID = new HashMap<String, User>(){
        {
            put("lisa",new User("lisa","Hallo123"));
            put("peter",new User("peter","wert1234"));

        }
    };

    //token, user
    private static Map<String, User> userByToken = new HashMap<>();
    //hate noch token leichen .. etc


    public AuthenticationServiceImp() {

    }

    public boolean validateToken(String token) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        boolean result = false;

        Token item = findToken(token);

        if (item != null) {
            if(item.getToken().equals(token)){


                if(item.getExpire() > System.currentTimeMillis()) {
                    result = true;
                }

            }
        }

        /*if(userByToken.containsKey(token)){
            final User user = userByToken.get(token);
            if(tokenlistOfUser.containsKey(user)){
                Token removeToken = null;
                final List<Token> tokenList = tokenlistOfUser.get(user);
                for (Token item:tokenList) {
                    if(item.getToken().equals(token)){
                        if(item.getExpire() > System.currentTimeMillis()) {
                            result = true;
                        }
                        else {
                            removeToken = item;
                        }
                    }
                }
                if(!result){
                    userByToken.remove(token);
                    tokenList.remove(removeToken);
                }
            }
        }*/


        return result;
    }



    public String authorizeUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        //exist user?
        if(usersByUserID.containsKey(user.getEmail())){

            //password correct?
            if(user.getPassword().equals(usersByUserID.get(user.getEmail()).getPassword())){

                return createToken(user);
            }

        }
        return "";

    }


    private String createToken(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final long currentTimeMillis = System.currentTimeMillis();
        Token token = new Token("", currentTimeMillis, user.getEmail(),  currentTimeMillis+ TIME);

        String result = getToken(user,token);
        token.setToken(result);

        if(tokenlistOfUser.containsKey(user)) {
            List<Token> list = tokenlistOfUser.get(user);
            list.add(token);
        }
        else{

            List<Token> list = new ArrayList<>();
            list.add(token);
            tokenlistOfUser.put(user, list);
        }

        userByToken.put(result,user);


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

        boolean result = false;

        Token item = findToken(token);

        if(item != null){
            User user =userByToken.get(token);
            userByToken.remove(token);
            final List<Token> tokenList = tokenlistOfUser.get(user);
            tokenList.remove(item);
        }

    }

    private Token findToken(String token){
        if(userByToken.containsKey(token)){

            final User user = userByToken.get(token);
            if(tokenlistOfUser.containsKey(user)){
                Token removeToken = null;
                final List<Token> tokenList = tokenlistOfUser.get(user);
                for (Token item:tokenList) {
                    if(item.getToken().equals(token)){
                        return item;

                    }
                }

            }

        }
       return null;

    }
}
