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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author endy
 */
@Controller
public class UserController {
    
    @Autowired private UserDao userDao;
    @Autowired private RoleDao roleDao;
    
    
    @RequestMapping("/rest/user/{id}")
    @ResponseBody
    public User cariUserById(@PathVariable Integer id){
        User user = userDao.cariById(id);
        user.getRole().setDaftarPermission(null);
        user.getRole().setDaftarUser(null);
        user.setDaftarEmail(null);
        user.setDaftarTelepon(null);
        return user;
    }
    
    @RequestMapping(value = "/rest/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void simpanUser(@RequestBody @Valid User u){
        userDao.save(u);
    }
    
    @RequestMapping(value = "/rest/user/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Integer id, @RequestBody @Valid User u){
        User ux = userDao.cariById(id);
        if(ux == null){
            throw new IllegalStateException();
        }
        u.setId(ux.getId());
        userDao.save(u);
    }
    
    @RequestMapping(value = "/rest/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusUser(@PathVariable Integer id){
        User ux = userDao.cariById(id);
        if(ux == null){
            throw new IllegalStateException();
        }
        userDao.delete(ux);
    }
    
    @RequestMapping(value = "/rest/user", method = RequestMethod.GET)
    @ResponseBody
    public List<User> daftarUserRest(){
        List<User> hasilQuery = userDao.cariSemuaUser(0, userDao.hitungSemuaUser().intValue());
        for (User user : hasilQuery) {
            // supaya tidak kena lazy loading, putuskan relasi
            user.getRole().setDaftarPermission(null);
            user.getRole().setDaftarUser(null);
            user.setDaftarEmail(null);
            user.setDaftarTelepon(null);
        }
        return hasilQuery;
    }
    
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
    
    @ModelAttribute("daftarRole")
    public List<Role> daftarRole(){
        return roleDao.cariSemuaRole();
    }
    
    @RequestMapping(value="/master/user/form", method= RequestMethod.GET)
    public ModelMap tampilkanForm(@RequestParam(required=false) Integer id){
        
        User u = userDao.cariById(id);
        if(u == null){
            u = new User();
        }
        
        ModelMap mm = new ModelMap();
        mm.addAttribute("user", u);
        return mm;
    }
    
    @RequestMapping(value="/master/user/form", method= RequestMethod.POST)
    public String prosesForm(@ModelAttribute @Valid User u, BindingResult errors, SessionStatus status){
        if(errors.hasErrors()){
            return "/master/user/form";
        }
        userDao.save(u);
        status.setComplete();
        return "redirect:list";
    }
}
