/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.ws.metro;

import com.artivisi.training.dao.RoleDao;
import com.artivisi.training.domain.Role;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author endy
 */
@WebService(serviceName = "HaloService")
public class HaloService extends SpringBeanAutowiringSupport {
    
    @Autowired
    private RoleDao roleDao;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "cariSemuaRole")
    public List<Role> cariSemuaRole() {
        List<Role> hasil = roleDao.cariSemuaRole();
        
        for (Role role : hasil) {
            role.setDaftarPermission(null);
            role.setDaftarUser(null);
        }
        
        return hasil;
    }
}
