/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

/**
 *
 * @author endy
 */
public class ObjectTidakDitemukanException extends RuntimeException {

    public ObjectTidakDitemukanException(String message) {
        super(message);
    }
    
}
