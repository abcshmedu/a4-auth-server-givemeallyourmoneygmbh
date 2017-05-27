package edu.hm.shareit.server.service.auth;

import javax.ws.rs.core.Response.Status;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, Maximilian, MarkusKral
 * Date 5/26/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public enum AuthenticationServiceResult {
    SUCCESS(200, Status.OK,"Accepted"),
    UNAUTHERIZED(401,Status.UNAUTHORIZED,"User ID, password, or both, are incorrect."),
    FORBIDDEN(403,Status.FORBIDDEN,"");

    private final String detail;
    private final Status status;
    private final int code;


    AuthenticationServiceResult(int code, Status status, String detail) {
        this.code = code;
        this.status = status;
        this.detail = detail;
    }

    /**
     * Returns the description of the error or empty String.
     *
     * @return String.
     */

    public String getDetail() {
        return detail;
    }

    /**
     * Returns response status codes.
     *
     * @return Status code.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Response status codes.
     *
     * @return int status code
     */

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "MediaServiceResult{"
                + "message='" + detail + '\''
                + ", status=" + status
                + ", code=" + code
                + "} " + super.toString();
    }
}
