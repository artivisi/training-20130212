/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

import com.artivisi.training.dao.PermissionDao;
import com.artivisi.training.domain.Permission;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author adi
 */
@Controller
public class PermissionRESTController {
    
    @Autowired private PermissionDao permissionDao;
    
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    @ResponseBody
    public List<Permission> semuaPermission(){
        List<Permission> hasil ;
        hasil = permissionDao.cariSemuaPermission();
        return hasil;
    }
}
