/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.dao;

import com.artivisi.training.domain.Permission;
import com.artivisi.training.domain.Role;
import com.artivisi.training.domain.User;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author endy
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:konfig-spring.xml")
public class UserDaoTest {
    @Autowired private UserDao userDao;
    @Autowired private DataSource dataSource;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Before
    public void resetIsiDatabase() throws Exception{
        Connection conn = dataSource.getConnection();
        
        DatabaseOperation.CLEAN_INSERT
                .execute(new DatabaseConnection(conn), 
                new FlatXmlDataSetBuilder()
                .build(new FileInputStream("src/test/resources/sample-data.xml")));
        
        conn.close();
    }
    
    @Test
    public void testCreateUpdateDelete(){
        Role r = new Role();
        r.setId(1);
        
        User u = new User();
        u.setUsername("endy");
        u.setPassword("12345678");
        u.setRole(r);
        
        Assert.assertNull(u.getId()); // sebelum disave, id null
        userDao.save(u);
        Assert.assertNotNull(u.getId()); // setelah disave, id terisi
        
        // update record
        u.setPassword("45678321");
        userDao.save(u);
        
        User ux = userDao.cariByUsername("endy");
        Assert.assertNotNull(ux);
        Assert.assertEquals("45678321", ux.getPassword());
        
        
        // delete record
        userDao.delete(ux);
        Assert.assertNull(userDao.cariByUsername("endy"));
    }
    
    @Test
    public void testHitungSemuaUser(){
        Assert.assertEquals(new Long(1), userDao.hitungSemuaUser());
    }
    
    @Test
    public void testHitungUserByRole(){
        Role admin = new Role();
        admin.setId(1);
        
        Role staff = new Role();
        staff.setId(2);
        
        Assert.assertEquals(new Long(1), userDao.hitungUserByRole(admin));
        Assert.assertEquals(new Long(0), userDao.hitungUserByRole(staff));
    }
    
    @Test
    public void testCariUserByRole(){
        Role admin = new Role();
        admin.setId(1);
        
        Role staff = new Role();
        staff.setId(2);
        
        List<User> hasilAdmin = userDao.cariUserByRole(admin, 0, 100);
        Assert.assertEquals(new Integer(1), new Integer(hasilAdmin.size()));
        
        User u = hasilAdmin.get(0);
        Assert.assertEquals("dadang", u.getUsername());
        Assert.assertEquals("admin", u.getRole().getKode());
        
        List<User> hasilStaff = userDao.cariUserByRole(staff, 0, 100);
        Assert.assertEquals(new Integer(0), new Integer(hasilStaff.size()));
        
    }
    
    @Test
    public void testLazyLoading(){
        User u = userDao.cariByUsername("dadang");
        Assert.assertNotNull(u);
        System.out.println("Role : "+u.getRole().getNama());
        
        System.out.println("Daftar Permission User dadang : ");
        for(Permission p : u.getRole().getDaftarPermission()){
            logger.debug("Action : "+p.getAction());
        }
    }
    
    @Test
    public void testDeleteEmail(){
        User u = userDao.cariByUsername("dadang");
        Assert.assertNotNull(u);
        Assert.assertEquals(new Integer(2), new Integer(u.getDaftarEmail().size()));
        
        u.getDaftarEmail().remove(1); // remove email kedua
        userDao.save(u);
        
        User ux = userDao.cariByUsername("dadang");
        Assert.assertNotNull(ux);
        Assert.assertEquals(new Integer(1), new Integer(ux.getDaftarEmail().size()));
        
        for(String email : ux.getDaftarEmail()){
            logger.debug("Email : "+email);
        }
    }
    
    @Test
    public void testAddEmail(){
        User u = userDao.cariByUsername("dadang");
        Assert.assertNotNull(u);
        Assert.assertEquals(new Integer(2), new Integer(u.getDaftarEmail().size()));
        
        u.getDaftarEmail().add("dadang@yahoo.com"); 
        userDao.save(u);
        
        User ux = userDao.cariByUsername("dadang");
        Assert.assertNotNull(ux);
        Assert.assertEquals(new Integer(3), new Integer(ux.getDaftarEmail().size()));
        
        for(String email : ux.getDaftarEmail()){
            logger.debug("Email : "+email);
        }
    }
    
    @Test
    public void testDeleteUser() throws Exception {
        User u = userDao.cariByUsername("dadang");
        Assert.assertNotNull(u);
        userDao.delete(u);
        
        User ux = userDao.cariByUsername("dadang");
        Assert.assertNull(ux);
        
        String sqlEmail = "select count(*) from t_user_email";
        Connection conn = dataSource.getConnection();
        
        ResultSet rs = conn.createStatement().executeQuery(sqlEmail);
        Assert.assertTrue(rs.next());
        Long hasil = rs.getLong(1);
        
        Assert.assertEquals(new Long(0), hasil);
        
        conn.close();
    }
}
