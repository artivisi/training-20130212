/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.demo;

import com.artivisi.training.dao.ProdukDao;
import com.artivisi.training.domain.Produk;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author endy
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx 
                = new ClassPathXmlApplicationContext("konfig-spring.xml");
        
        //ProdukDao pd = ctx.getBean("pd"); // cari object by id
        ProdukDao pd = ctx.getBean(ProdukDao.class); // cari object by tipe class
        
        Produk p = new Produk();
        p.setKode("P-001");
        p.setNama("Produk 001");
        
        pd.simpan(p);
    }
}
