/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.jsf;

import com.artivisi.training.dao.RoleDao;
import com.artivisi.training.dao.UserDao;
import com.artivisi.training.domain.Role;
import com.artivisi.training.domain.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.ListDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author endy
 */
@Controller
@Scope(value="session")
public class UserController {
    
    @Autowired private UserDao userDao;
    @Autowired private RoleDao roleDao;
    
    private List<User> daftarUser;
    private List<Role> daftarRole;
    private User currentUser;
    private ListDataModel<User> listDataModelUser;
    
    @PostConstruct
    public void refreshDataUser(){
        daftarUser = userDao.cariSemuaUser(0, userDao.hitungSemuaUser().intValue());
        listDataModelUser = new ListDataModel<User>(daftarUser);
        daftarRole = roleDao.cariSemuaRole();
    }
    
    private void resetCurrentUser(){
        currentUser = new User();
        Role r = new Role();
        currentUser.setRole(r);
    }
    
    public String tambah(){
        resetCurrentUser();
        return "form?faces-redirect=true";
    }
    
    public String edit(){
        currentUser = listDataModelUser.getRowData();
        return "form?faces-redirect=true";
    }
    
    public String delete(){
        currentUser = listDataModelUser.getRowData();
        userDao.delete(currentUser);
        resetCurrentUser();
        refreshDataUser();
        return "list?faces-redirect=true";
    }
    
    public String simpan(){
        userDao.save(currentUser);
        resetCurrentUser();
        refreshDataUser();
        return "list?faces-redirect=true";
    }
    
    // getter 

    public ListDataModel<User> getListDataModelUser(){
        return listDataModelUser;
    }
    
    public List<User> getDaftarUser() {
        return daftarUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<Role> getDaftarRole() {
        return daftarRole;
    }
    
    
    
}
