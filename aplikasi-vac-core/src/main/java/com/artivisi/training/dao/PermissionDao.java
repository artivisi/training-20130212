/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.dao;

import com.artivisi.training.domain.Permission;
import com.artivisi.training.domain.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adi
 */

@Repository @Transactional
public class PermissionDao {
    @PersistenceContext private EntityManager entityManager;
    
    public List<Permission> cariSemuaPermission() {
        return entityManager.createQuery("select p from Permission p order by p.action")
                .getResultList();
    }
}
