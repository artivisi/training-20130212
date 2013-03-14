/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.swing.ui.master.tablemodel;

import com.artivisi.training.rest.domain.Permission;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author adi
 */
public class PermissionTableModel 
    extends AbstractTableModel {
    
    private List<Permission> permissions = new ArrayList<Permission>();
    private String[] HEADER = {"X", "Permission"};
    private Boolean editable = Boolean.FALSE;

    public PermissionTableModel(List<Permission> permissions, Boolean editable) {
        this.permissions = permissions;
        this.editable = editable;
    }
    
    @Override
    public int getRowCount() {
        return permissions.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Permission p = permissions.get(rowIndex);
        switch(columnIndex){
            case 0: return p.getSelected();
            case 1: return p.getAction();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return HEADER[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0: return Boolean.class;
            default: return String.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Permission p = permissions.get(rowIndex);
        switch(columnIndex){
            case 0: p.setSelected((Boolean)aValue);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return editable;
            default: return false;
        }
    }
    
}
