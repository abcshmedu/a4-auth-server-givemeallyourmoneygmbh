package edu.hm.shareit.server.service.user;

import edu.hm.shareit.server.Data.Data;
import edu.hm.shareit.server.model.Identity;
import edu.hm.shareit.server.model.User;

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

public class UserServiceImp implements UserService {

    @Override
    public User getUser(String token,String userId) {
        User userByToken = Data.getUser(token);

        if(userByToken.getUserId().equals(userId) || userByToken.getIsAdmin()) {

            return Data.getUserById(userId);
        }


        return null;
    }

    @Override
    public User updateUser(String token, String userId, User user) {
        User userByToken = Data.getUser(token);

        if(userByToken.getIsAdmin()){
            return Data.updateUser(user);
        }

        if(userByToken.equals(userId) && userByToken.equals(user.getUserId()))
            return Data.updateUser(user);

        return null;
    }

    @Override
    public List<Identity> getUsers(String token) {
        User userByToken = Data.getUser(token);

        if(userByToken.getIsAdmin())
            return Data.getIdentities();

        return null;
    }
}
