package edu.hm.shareit.server.model;

import java.security.SecureRandom;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/26/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public class Token {

    private String token;
    private long createMillis;
    private String userId;
    private long expire;
    private byte randombytes[];

    private static SecureRandom random = new SecureRandom();

    public Token(String token, long createMillis, String userId, long expire) {
        this.token = token;
        this.createMillis = createMillis;
        this.userId = userId;
        this.expire = expire;

        randombytes = new byte[30];
        random.nextBytes(randombytes);
    }

    public String getToken() {
        return token;
    }

    public long getCreateMillis() {
        return createMillis;
    }

    public String getUserId() {
        return userId;
    }

    public long getExpire() {
        return expire;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public byte[] getRandombytes() {
        return randombytes;
    }

    /*public void setBytes(byte[] bytes) {
        this.randombytes = bytes;
    }*/

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", createMillis=" + createMillis +
                ", userId='" + userId + '\'' +
                ", expire=" + expire +
                '}';
    }
}
