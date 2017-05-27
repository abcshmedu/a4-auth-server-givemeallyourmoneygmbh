package edu.hm.shareit.server.Data;

import edu.hm.shareit.server.model.Identity;
import edu.hm.shareit.server.model.Token;
import edu.hm.shareit.server.model.User;
import edu.hm.shareit.server.model.UserCredentials;

import java.util.*;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/27/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public class Data {

    //UserId, tokenlist
    private static Map<String, List<Token>> tokenlistOfUser = new HashMap<>();

    //userid, user
    private static Map<String, User> usersByUserID = new HashMap<String, User>(){
        {
            put("1",new User("1","lisa","Hallo123",true));
            put("2",new User("2","peter","wert1234"));

        }
    };

    private static Map<String, String> userIdByName = new HashMap<String, String>(){
        {
            put("lisa","1");
            put("peter","2");

        }
    };



    //token, userId
    private static Map<String, String> userByToken = new HashMap<>();



    public static Token findToken(String token){
        if(userByToken.containsKey(token)){

            final String userId = userByToken.get(token);
            //final User user = userByToken.get(token);
            if(tokenlistOfUser.containsKey(userId)){
                final List<Token> tokenList = tokenlistOfUser.get(userId);
                for (Token item:tokenList) {
                    if(item.getToken().equals(token)){
                        return item;

                    }
                }

            }

        }
        return null;

    }

    public static boolean validateUser(UserCredentials user){

        String userId = userIdByName.get(user.getUserName());

        if(usersByUserID.containsKey(userId)){

            //password correct?
            if(user.getPassword().equals(usersByUserID.get(userId).getPassword())){
                return true;
            }

        }
        return false;
    }


    public static boolean validateUser(User user){


        if(usersByUserID.containsKey(user.getUserId())){

            //password correct?
            if(user.getPassword().equals(usersByUserID.get(user.getUserId()).getPassword())){
                return true;
            }

        }
        return false;
    }

    public static void removeToken(String token) {
        Token item = Data.findToken(token);

        if(item != null){
            String userId =userByToken.get(token);
            userByToken.remove(token);
            final List<Token> tokenList = tokenlistOfUser.get(userId);
            tokenList.remove(item);

        }
    }


    public static void addToken(User user, Token token, String result) {



        if(tokenlistOfUser.containsKey(user.getUserId())) {
            List<Token> list = tokenlistOfUser.get(user.getUserId());
            list.add(token);
        }
        else{

            List<Token> list = new ArrayList<>();
            list.add(token);
            tokenlistOfUser.put(user.getUserId(), list);
        }

        userByToken.put(result,user.getUserId());


    }

    public static User getUser(String token) {
        String userId = userByToken.get(token);
        return usersByUserID.get(userId);

    }

    public static User updateUser(User user) {
        List<User> list = getUsers();

        User modified = null;
        for (User item :list) {
            if(item.getUserId().equals(user.getUserId())){

                item.setUserName(user.getUserName());
                item.setPassword(user.getPassword());
                modified =item;
            }

        }
        return modified;

    }

    public static List<Identity> getIdentities() {
        List<Identity> users  = new ArrayList<>();

        final Set<Map.Entry<String, User>> entries = usersByUserID.entrySet();

        for (Map.Entry<String,User> item: entries) {
            User user = item.getValue();
            users.add(new Identity(user.getUserName(),user.getIsAdmin(),user.getUserId()));
        }

        return users;
    }
    public static List<User> getUsers() {
        List<User> users  = new ArrayList<>();

        final Set<Map.Entry<String, User>> entries = usersByUserID.entrySet();

        for (Map.Entry<String,User> item: entries) {
            users.add(item.getValue());
        }

        return users;
    }
    public static User getUser(UserCredentials userCredentials) {
        String userId = userIdByName.get(userCredentials.getUserName());

        if(usersByUserID.containsKey(userId)){

            //password correct?
            if(userCredentials.getPassword().equals(usersByUserID.get(userId).getPassword())){
                return usersByUserID.get(userId);
            }

        }

        return null;
    }

    public static User getUserById(String userId) {
        return usersByUserID.get(userId);
    }
}
