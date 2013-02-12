/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.demo;

import com.artivisi.training.dao.ProdukDao;
import com.artivisi.training.domain.Produk;

/**
 *
 * @author endy
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ProdukDao pd = new ProdukDao();
        
        Produk p = new Produk();
        p.setKode("P-001");
        p.setNama("Produk 001");
        
        pd.simpan(p);
    }
}
