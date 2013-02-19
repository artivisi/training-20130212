/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

import com.artivisi.training.dao.RoleDao;
import com.artivisi.training.dao.UserDao;
import com.artivisi.training.domain.Role;
import com.artivisi.training.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author endy
 */
@Controller
public class UserController {
    
    @Autowired private UserDao userDao;
    
    @RequestMapping("/master/user/list")
    public ModelMap daftarUser(@RequestParam(required=false)Integer start, 
        @RequestParam(required=false)Integer rows){
        
        if(start == null){
            start = 0;
        }
        
        if(rows == null){
            rows = 10;
        }
        
        // query ke database
        List<User> hasilQuery = userDao.cariSemuaUser(start, rows);
        
        // masukkan ke modelmap
        ModelMap mm = new ModelMap();
        mm.addAttribute("dataUser", hasilQuery);
        return mm;
    }
}
