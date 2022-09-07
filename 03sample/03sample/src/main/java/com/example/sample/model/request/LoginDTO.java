package com.example.sample.model.request;

import com.example.sample.model.entity.LoginEntity;

public class LoginDTO {

    private Integer id;
    private String name;
    private String password;

    public LoginEntity toEntity(){
        return new LoginEntity(id, name, password);
    }

    public LoginDTO(){ }

    public LoginDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public LoginDTO(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
