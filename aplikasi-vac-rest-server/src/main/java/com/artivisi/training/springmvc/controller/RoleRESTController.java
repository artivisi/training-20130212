/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

import com.artivisi.training.dao.RoleDao;
import com.artivisi.training.domain.Permission;
import com.artivisi.training.domain.Role;
import com.artivisi.training.domain.User;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author endy
 */
@Controller
public class RoleRESTController {
    private static final String URL_ALL_ROLE = "/role";
    private static final String URL_ALL_ROLE_COUNT = "/role/count/";
    private static final String URL_ROLE_BY_ID = "/role/{id}";

    @Autowired private RoleDao roleDao;
    
    @RequestMapping(value = URL_ALL_ROLE_COUNT, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Long> countSemuaRole(){
        Long count = roleDao.countSemuaRole();
        
        Map<String, Long> mapCounter = new HashMap<String, Long>();
        mapCounter.put("total", count);
        return mapCounter;
    }
    
    @RequestMapping(value = URL_ALL_ROLE, method = RequestMethod.GET)
    @ResponseBody
    public List<Role> semuaRole(
            @RequestParam(required=false) String nama, 
            @RequestParam(required=false) Integer start, 
            @RequestParam(required=false) Integer rows){
        List<Role> hasil ;
        
        if(start==null || rows==null){
            start=0;
            rows=roleDao.countSemuaRole().intValue();
        }
        
        if(StringUtils.hasText(nama)) {
            hasil = roleDao.cariRoleByNama(nama, start, rows);
        } else {
            hasil = roleDao.cariSemuaRole(start, rows);
        }
        
        for (Role role : hasil) {
//            role.setDaftarPermission(null);
            role.setDaftarUser(null);
        }
        return hasil;
    }
    
    @RequestMapping(value = URL_ROLE_BY_ID, method = RequestMethod.GET)
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
    
    
    @RequestMapping(value = URL_ALL_ROLE, method = RequestMethod.POST)
    public void simpan(@RequestBody @Valid Role r, HttpServletRequest request, HttpServletResponse response){
        roleDao.save(r);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, r.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(value = URL_ROLE_BY_ID, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Role r){
        Role rx = roleDao.cariById(id);
        if(rx == null){
            throw new ObjectTidakDitemukanException("Role dengan id "+id+" tidak ditemukan");
        }
        roleDao.save(r);
    }
    
    @RequestMapping(value = URL_ROLE_BY_ID, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hapus(@PathVariable Integer id){
        Role r = roleDao.cariById(id);
        if(r == null){
            throw new ObjectTidakDitemukanException("Role dengan id "+id+" tidak ditemukan");
        }
        roleDao.hapus(r);
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
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectTidakDitemukanException.class)
    public void handleObjectTidakDitemukanException(){}
}
