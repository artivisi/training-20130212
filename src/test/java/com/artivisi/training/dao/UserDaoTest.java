/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.dao;

import com.artivisi.training.domain.Role;
import com.artivisi.training.domain.User;
import java.io.FileInputStream;
import java.sql.Connection;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        u.setPassword("123");
        u.setRole(r);
        
        Assert.assertNull(u.getId()); // sebelum disave, id null
        userDao.save(u);
        Assert.assertNotNull(u.getId()); // setelah disave, id terisi
        
        // update record
        u.setPassword("456");
        userDao.save(u);
        
        User ux = userDao.cariByUsername("endy");
        Assert.assertNotNull(ux);
        Assert.assertEquals("456", ux.getPassword());
        
        
        // delete record
        userDao.delete(ux);
        Assert.assertNull(userDao.cariByUsername("endy"));
    }
}
