/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.dao;

import com.artivisi.training.domain.Role;
import com.artivisi.training.domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author endy
 */
@Repository @Transactional
public class UserDao {
    @PersistenceContext private EntityManager entityManager;
    
    public void save(User u){
        if(u.getId() == null){
            entityManager.persist(u);
        } else {
            entityManager.merge(u);
        }
    }
    
    public void delete(User u){
        User ux = entityManager.find(User.class, u.getId());
        entityManager.remove(ux);
    }
    
    public List<User> cariSemuaUser(Integer start, Integer rows){
        return entityManager
                .createQuery("select u from User u order by u.username")
                .setFirstResult(start)
                .setMaxResults(rows)
                .getResultList();
    }
    
    public Long hitungSemuaUser(){
        return (Long) entityManager
                .createQuery("select count(u) from User u")
                .getSingleResult();
    }
    
    public User cariByUsername(String username){
        try {
            return (User) entityManager
                .createQuery("select u from User u where u.username = :username")
                .setParameter("username", username)
                .getSingleResult();
        } catch (NoResultException err){ // query tidak menghasilkan, gpp
            return null;
        }
    }
    
    public List<User> cariUserByRole(Role role, Integer start, Integer rows){
        return entityManager
                .createQuery("select u from User u where u.role.id = :roleId order by u.username")
                .setParameter("roleId", role.getId())
                .setFirstResult(start)
                .setMaxResults(rows)
                .getResultList();
    }
    
    public Long hitungUserByRole(Role role){
        return (Long) entityManager
                .createQuery("select count(u) from User u where u.role.id = :roleId")
                .setParameter("roleId", role.getId())
                .getSingleResult();
    }
}
