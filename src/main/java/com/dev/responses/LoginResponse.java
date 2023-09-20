package com.dev.responses;



public class LoginResponse  extends BasicResponse{

    private String firstname;
    private String email;
    private int admin;

    private int id;

    public LoginResponse(boolean success, Integer errorCode, String email,String firstname ,int admin , int id) {
        super(success, errorCode);
        this.firstname = firstname;
        this.email= email;
        this.admin = admin;
        this.id=id;
    }

    public LoginResponse( String email,String firstname , int admin , int id) {
        this.firstname = firstname;
        this.email=email;
        this.admin = admin;
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
