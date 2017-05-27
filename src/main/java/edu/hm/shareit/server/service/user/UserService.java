package edu.hm.shareit.server.service.user;

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

public interface UserService {

    /*User getUser(String token);
    User updateUser(User user);
    List<User> getUsers(User user);*/

    User getUser(String token, String userId);
    User updateUser(String token, String userId, User user);
    List<User> getUsers(String token);

}
