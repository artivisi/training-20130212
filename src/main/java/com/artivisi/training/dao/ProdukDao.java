/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.dao;

import com.artivisi.training.domain.Produk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author endy
 */
public class ProdukDao {
    private DataSource dataSource;
    
    public ProdukDao(){
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("belajar");
        ds.setUser("belajar");
        ds.setPassword("java");
        dataSource = ds;
    }
    
    public void simpan(Produk p) throws Exception {
        String sql = "insert into t_produk (kode, nama) values (?,?)";
        Connection koneksi = dataSource.getConnection();
        PreparedStatement ps = koneksi.prepareStatement(sql);
        ps.setString(1, p.getKode());
        ps.setString(2, p.getNama());
        ps.executeUpdate();
        koneksi.close();
    }
    
    public void hapus(Produk p) throws Exception {
        String sql = "delete from t_produk where id = ?";
        Connection koneksi = dataSource.getConnection();
        PreparedStatement ps = koneksi.prepareStatement(sql);
        ps.setInt(1, p.getId());
        ps.executeUpdate();
        koneksi.close();
    }
    
    public Produk cariById(Integer id){
        return null;
    }
}
