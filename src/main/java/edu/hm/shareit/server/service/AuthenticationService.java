package edu.hm.shareit.server.service;

import edu.hm.shareit.server.model.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/26/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public interface AuthenticationService {

    boolean validateToken(String token) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    String authorizeUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    void removeToken(String token);

}
