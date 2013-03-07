/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.rest.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author endy
 */
public class User {
    private Integer id;
    
    private String username;
    
    private String password;
    
    private List<String> daftarEmail 
            = new ArrayList<String>();
    private List<Phone> daftarTelepon 
            = new ArrayList<Phone>();
    
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<String> getDaftarEmail() {
        return daftarEmail;
    }

    public void setDaftarEmail(List<String> daftarEmail) {
        this.daftarEmail = daftarEmail;
    }

    public List<Phone> getDaftarTelepon() {
        return daftarTelepon;
    }

    public void setDaftarTelepon(List<Phone> daftarTelepon) {
        this.daftarTelepon = daftarTelepon;
    }
    
    
}
