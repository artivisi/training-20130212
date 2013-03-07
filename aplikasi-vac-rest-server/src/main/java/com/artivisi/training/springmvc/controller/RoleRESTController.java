/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

import com.artivisi.training.dao.RoleDao;
import com.artivisi.training.domain.Permission;
import com.artivisi.training.domain.Role;
import com.artivisi.training.domain.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author endy
 */
@Controller
public class RoleRESTController {
    @Autowired private RoleDao roleDao;
    
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> semuaRole(){
        List<Role> hasil = roleDao.cariSemuaRole();
        for (Role role : hasil) {
            role.setDaftarPermission(null);
            role.setDaftarUser(null);
        }
        return hasil;
    }
    
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Role cariRoleById(@PathVariable Integer id){
        Role r = roleDao.cariById(id);
        for (User user : r.getDaftarUser()) {
            user.setDaftarEmail(null);
            user.setDaftarTelepon(null);
            user.setRole(null);
        }
        return r;
    }
    
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> informasiAplikasi(HttpServletRequest req){
        Map<String, String> hasil = new HashMap<String, String>();
        hasil.put("username", "endy");
        hasil.put("versi", "1.1.2");
        hasil.put("ip", req.getRemoteAddr());
        return hasil;
    }
}
