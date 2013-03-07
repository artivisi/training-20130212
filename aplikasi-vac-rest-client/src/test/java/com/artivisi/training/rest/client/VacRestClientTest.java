/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.rest.client;

import com.artivisi.training.rest.domain.Role;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author endy
 */
public class VacRestClientTest {
    
    private VacRestClient client = new VacRestClient();
    
    @Test
    public void testSemuaRole() {
        List<Role> hasil = client.semuaRole();
        System.out.println("Jumlah record : "+hasil.size());
        for (Role role : hasil) {
            System.out.println("Kode : "+role.getKode());
        }
    }
    
    @Test
    public void testCariRoleById(){
        Role r = client.cariRoleById(1);
        System.out.println("Kode : "+r.getKode());
        System.out.println("Nama : "+r.getNama());
        assertEquals("admin", r.getKode());
    }
}