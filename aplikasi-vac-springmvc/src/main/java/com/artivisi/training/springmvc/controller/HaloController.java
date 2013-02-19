/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author endy
 */
@Controller
public class HaloController {
    
    @RequestMapping("/halo")
    public ModelMap halo(@RequestParam(required=false) String nama){
        ModelMap mm = new ModelMap();
        
        if(nama == null){
            nama = "undefined";
        }
        
        mm.addAttribute("nama", nama);
        return mm;
    }
}
