/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.swing.ui.master.tablemodel;

import com.artivisi.training.rest.domain.Role;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author adi
 */
public class MasterRoleTableModel 
    extends AbstractTableModel {
    
    private List<Role> listRole = new ArrayList<Role>();
    private String[] HEADER = {"KODE","NAMA"};

    public MasterRoleTableModel(List<Role> listRole) {
        this.listRole = listRole;
    }
    
    @Override
    public int getRowCount() {
        return listRole.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public String getColumnName(int column) {
        return HEADER[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Role r = listRole.get(rowIndex);
        switch(columnIndex){
            case 0: return r.getKode();
            case 1: return r.getNama();
            default: return "";
        }
    }
    
}
