/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

import com.artivisi.training.dao.RoleDao;
import com.artivisi.training.domain.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> semuaRole(){
        List<Role> hasil = roleDao.cariSemuaRole();
        for (Role role : hasil) {
            role.setDaftarPermission(null);
            role.setDaftarUser(null);
        }
        return hasil;
    }
}
