/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.swing.ui.master.panel;

import com.artivisi.training.rest.domain.Permission;
import com.artivisi.training.rest.domain.Role;
import com.artivisi.training.swing.App;
import com.artivisi.training.swing.ui.master.tablemodel.MasterRoleTableModel;
import com.artivisi.training.swing.ui.master.tablemodel.PermissionTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.springframework.util.StringUtils;

/**
 *
 * @author adi
 */
public class MasterRolePanel extends javax.swing.JPanel {

    public static final String PANEL_NAME = "Master Role Panel";
    private static MasterRolePanel masterRolePanel;
    private Role role;
    private List<Role> listRole = new ArrayList<Role>();
    private List<Permission> permission = new ArrayList<Permission>();

    public static MasterRolePanel getMasterRolePanel() {
        if(masterRolePanel == null){
            masterRolePanel = new MasterRolePanel();
        }
        return masterRolePanel;
    }
    
    /**
     * Creates new form MasterUserPanel
     */
    public MasterRolePanel() {
        initComponents();
        loadDataToTable();
        enableButton(true, false, false, false, true);
        tableListRole.getSelectionModel()
                .addListSelectionListener(new TableSelection());
    }
    
    private void loadDataToTable(){
        listRole = App.getVacRestClient().semuaRole();
        tableListRole.setModel(
                new MasterRoleTableModel(listRole));
        
        permission = App.getVacRestClient().semuaPermission();
        tableListPermission.setModel(
                new PermissionTableModel(permission));
    }
    
    private void enableButton(
            boolean add, boolean edit,
            boolean save, boolean delete,
            boolean exit){
        btnAdd.setEnabled(add);
        btnEdit.setEnabled(edit);
        btnDelete.setEnabled(delete);
        btnSave.setEnabled(save);
        btnExit.setEnabled(exit);
    }
    
    private void enableTextField(boolean active){
        txtKode.setEnabled(active);
        txtNama.setEnabled(active);
    }
    
    private boolean validateForm(){
        if(!StringUtils.hasText(txtKode.getText())){
            JOptionPane.showMessageDialog(
                    this, 
                    "Kode harus diisi", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(!StringUtils.hasText(txtNama.getText())){
            JOptionPane.showMessageDialog(
                    this, 
                    "Nama harus diisi", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNama = new javax.swing.JTextField();
        txtKode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableListPermission = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableListRole = new javax.swing.JTable();
        btnExit = new javax.swing.JButton();

        btnAdd.setText("Add");
        btnAdd.setEnabled(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNama.setEnabled(false);

        txtKode.setEnabled(false);

        jLabel2.setText("Nama");

        jLabel1.setText("Kode");

        jLabel3.setText("Daftar Permission");

        tableListPermission.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "x", "Permission"
            }
        ));
        tableListPermission.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableListPermission);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNama)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableListRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Nama"
            }
        ));
        jScrollPane1.setViewportView(tableListRole);

        btnExit.setText("Exit");
        btnExit.setEnabled(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnSave)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        App.getMainFrame().getMainTabpane().remove(this);
        masterRolePanel = null;
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(validateForm()){
            if(role == null){
                role = new Role();
            }

            role.setKode(txtKode.getText());
            role.setNama(txtNama.getText());
            role.setDaftarPermission(getCheckedPermission());

            App.getVacRestClient().simpan(role);
            role = null;
            loadDataToTable();

            txtKode.setText("");
            txtNama.setText("");

            enableButton(true, false, false, false, true);
            enableTextField(false);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private List<Permission> getCheckedPermission(){
        List<Permission> checkedPermissions
                = new ArrayList<Permission>();
        
        for (Permission p : permission) {
            System.out.println("Checked " + p.getSelected());
            if(p.getSelected()){
                checkedPermissions.add(p);
            }
        }
        
        return checkedPermissions;
    }
    
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(role != null){
            int ret = JOptionPane.showConfirmDialog(
                    this, 
                    "Apakah anda yakin untuk menghapus role " + role.getKode(), 
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if(ret == JOptionPane.YES_OPTION){
                App.getVacRestClient().hapus(role);
            }
        } else {
            JOptionPane.showMessageDialog(
                    this, 
                    "Tidak ada data yang akan di hapus", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        loadDataToTable();
        txtKode.setText("");
        txtNama.setText("");
        enableButton(true, false, false, false, true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
       enableButton(false, false, true, false, true);
       enableTextField(true);
       role = null;
       
       permission = App.getVacRestClient().semuaPermission();
       tableListPermission.setModel(new PermissionTableModel(permission));
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        enableButton(false, false, true, false, true);
        enableTextField(true);
    }//GEN-LAST:event_btnEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableListPermission;
    private javax.swing.JTable tableListRole;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables

    private class TableSelection 
        implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(tableListRole.getSelectedRow() >= 0){
                role = listRole.get(
                        tableListRole.getSelectedRow());
                txtKode.setText(role.getKode());
                txtNama.setText(role.getNama());
                
                enableButton(false, true, false, true, true);
                enableTextField(false);
            }
        }
        
    }
    
}
