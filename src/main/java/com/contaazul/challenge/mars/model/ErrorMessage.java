package com.contaazul.challenge.mars.model;

/**
 *
 * @author Wesklei Migliorini
 */
public class ErrorMessage {

    private String code;
    private String error;

    public ErrorMessage(String error) {
        this.code = error;
    }

    public ErrorMessage(String error, String message) {
        this.code = error;
        this.error = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return code + " " + error;
    }
}
