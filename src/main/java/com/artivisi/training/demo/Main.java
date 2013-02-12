/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.demo;

import com.artivisi.training.dao.ProdukDao;
import com.artivisi.training.domain.Produk;
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author endy
 */
public class Main {
    public static void main(String[] args) throws Exception {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("belajar");
        ds.setUser("belajar");
        ds.setPassword("java");
        
        ProdukDao pd = new ProdukDao();
        pd.setDataSource(ds); // inject melalui setter
        
        Produk p = new Produk();
        p.setKode("P-001");
        p.setNama("Produk 001");
        
        pd.simpan(p);
    }
}
