/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author endy
 */
public class Permission {
    private Integer id;
    private String action;
    private String keterangan;
    private transient Boolean selected = Boolean.FALSE;

    @JsonIgnore
    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    
}
