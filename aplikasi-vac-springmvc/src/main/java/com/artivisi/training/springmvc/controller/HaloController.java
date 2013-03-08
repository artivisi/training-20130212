/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author endy
 */
@Controller
public class HaloController {
    
    @Autowired
    private JposServer jposServer;
    
    @RequestMapping("/halo")
    public ModelMap halo(@RequestParam(required=false) String nama){
        ModelMap mm = new ModelMap();
        
        if(nama == null){
            nama = "undefined";
        }
        
        mm.addAttribute("nama", nama);
        return mm;
    }
    
    @RequestMapping("/jpos")
    @ResponseBody
    public Map<String, String> jumlahKoneksi(){
        Map<String, String> data = new HashMap<String, String>();
        data.put("jumlahKoneksi", String.valueOf(jposServer.jumlahKoneksi()));
        data.put("counter", jposServer.counter());
        return data;
    }
}
