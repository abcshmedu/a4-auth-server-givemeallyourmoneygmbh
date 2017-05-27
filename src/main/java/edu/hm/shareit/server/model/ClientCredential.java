package edu.hm.shareit.server.model;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/26/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public class ClientCredential {
    private String clientId;
    private String clientSecret;

    /**
     *
     * @param clientId The client ID.
     * @param clientSecret The client secret string.
     */
    public ClientCredential(String clientId, String clientSecret) {
        super();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     *
     * @return The client ID.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     *
     * @return The client secret.
     */
    public String getClientSecret() {
        return clientSecret;
    }

}
