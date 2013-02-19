/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author endy
 */
@Controller
public class HaloController {
    
    @RequestMapping("/halo")
    public ModelMap halo(){
        ModelMap mm = new ModelMap();
        mm.addAttribute("nama", "Endy");
        return mm;
    }
}
