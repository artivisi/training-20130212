/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.dao;

import com.artivisi.training.domain.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author endy
 */
@Repository @Transactional
public class RoleDao {
    @PersistenceContext private EntityManager entityManager;
    
    public Role cariById(Integer id){
        Role r = entityManager.find(Role.class, id);
        
        // supaya tidak kena LazyInitializationException
        r.getDaftarUser().size();
        r.getDaftarPermission().size();
        
        return r;
    }

    public List<Role> cariSemuaRole(Integer start, Integer rows) {
        if(start==null) start = 0;
        if(rows==null) rows = 20;
        
        return entityManager.createQuery("select r from Role r order by r.nama")
                .setFirstResult(start)
                .setMaxResults(rows)
                .getResultList();
    }
    
    public Long countSemuaRole() {
        return (Long) entityManager.createQuery("select count(r) from Role r")
                .getSingleResult();
    }

    public void save(Role r) {
        if(r.getId() == null){
            entityManager.persist(r);
        } else {
            entityManager.merge(r);
        }
    }

    public void hapus(Role r) {
        entityManager.remove(entityManager.find(Role.class, r.getId()));
    }

    public List<Role> cariRoleByNama(String nama, Integer start, Integer rows) {
        if(start==null) start = 0;
        if(rows==null) rows = 20;
        
        return entityManager.createQuery("select r from Role r where r.nama like :nama order by r.nama")
                .setParameter("nama", "%"+nama+"%")
                .setFirstResult(start)
                .setMaxResults(rows)
                .getResultList();
    }
}
