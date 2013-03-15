/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.rest.client;

import com.artivisi.training.rest.domain.Permission;
import com.artivisi.training.rest.domain.Role;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author endy
 */
public class VacRestClient {
    private String serverUrl = "http://localhost:8080/aplikasi-vac-rest-server/rest/role";
    private String serverUrlPermission = 
            "http://localhost:8080/aplikasi-vac-rest-server/rest/permission";
    
    private RestTemplate restTemplate = new RestTemplate();
    
    public List<Role> semuaRole(){
        ParameterizedTypeReference<List<Role>> roleType
                = new ParameterizedTypeReference<List<Role>>() {};
        List<Role> hasil = restTemplate
                .exchange(serverUrl, HttpMethod.GET, HttpEntity.EMPTY, roleType)
                .getBody();
        return hasil;
    }
    
    public Role cariRoleById(Integer id){
        return restTemplate.getForObject(serverUrl+"/"+id, Role.class);
    }
    
    public void hapus(Role r){
        restTemplate.delete(serverUrl+"/"+r.getId());
    }
    
    public Role simpan(Role r){
        try {
            Thread.sleep(10000);
            
            if(r.getId() == null){
                URI location = restTemplate.postForLocation(serverUrl, r);
                return restTemplate.getForObject(location, Role.class);
            } else {
                restTemplate.put(serverUrl+"/"+r.getId(), r);
                return r;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(VacRestClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Permission> semuaPermission(){
        ParameterizedTypeReference<List<Permission>> permissions
                = new ParameterizedTypeReference<List<Permission>>() {};
        List<Permission> hasil = restTemplate
                .exchange(serverUrlPermission, HttpMethod.GET, HttpEntity.EMPTY, permissions)
                .getBody();
        return hasil;
    }
}
