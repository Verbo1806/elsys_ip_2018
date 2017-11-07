package org.elsys.ip.servlet.models;

public class User {

    private long id;
    private String name;
    private String email;
    private String password;

    public User(long id, String name, String mail, String password) {
        this.id=id;
        this.name = name;
        this.email= mail;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setMail(String email) {
        this.email = email;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPass() {
        return password;
    }
    public void setPass(String pass) {
        this.password = pass;
    }

}

