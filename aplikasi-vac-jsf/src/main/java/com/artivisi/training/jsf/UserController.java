/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.jsf;

import com.artivisi.training.dao.UserDao;
import com.artivisi.training.domain.User;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author endy
 */
@Controller
public class UserController {
    
    @Autowired private UserDao userDao;
    
    private List<User> daftarUser;
    
    @PostConstruct
    public void refreshDataUser(){
        daftarUser = userDao.cariSemuaUser(0, userDao.hitungSemuaUser().intValue());
    }
    
    // getter 

    public List<User> getDaftarUser() {
        return daftarUser;
    }
    
}
