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
public class Role {
    
    private Integer id;
    
    private String kode;
    private String nama;
    
    private List<Permission> daftarPermission 
            = new ArrayList<Permission>();
    
    private List<User> daftarUser 
            = new ArrayList<User>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<Permission> getDaftarPermission() {
        return daftarPermission;
    }

    public void setDaftarPermission(List<Permission> daftarPermission) {
        this.daftarPermission = daftarPermission;
    }

    public List<User> getDaftarUser() {
        return daftarUser;
    }

    public void setDaftarUser(List<User> daftarUser) {
        this.daftarUser = daftarUser;
    }
    
}
