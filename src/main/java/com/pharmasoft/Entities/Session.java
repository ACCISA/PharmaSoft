package com.pharmasoft.Entities;

/**
 * This class stores the jwt token. Everytime an API Call is made it will verify this token and respond accordingly.
 * If the token is expired or invalid, the user will be redirected to the login form, which will create a new token.
 */
public class Session {
    /**
     * JWT token as a String
     */
    private String token;

    public static Session cur_session;

    public Session(String token){
        this.token = token;
        cur_session = this;
    }

    public String getToken(){
        return this.token;
    }

}
