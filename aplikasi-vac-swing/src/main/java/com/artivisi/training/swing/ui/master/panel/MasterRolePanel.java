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
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.springframework.util.StringUtils;

/**
 *
 * @author adi
 */
public class MasterRolePanel extends javax.swing.JPanel 
    implements PropertyChangeListener {

    public static final String PANEL_NAME = "Master Role Panel";
    private static MasterRolePanel masterRolePanel;
    private Role role;
    private List<Role> listRole = new ArrayList<Role>();
    private List<Permission> permission = new ArrayList<Permission>();
    private WorkerSaveRole workerSaveRole;
    private WorkerQueryRole workerQueryRole;
    private Integer rows = 5;
    
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
        initPaging();
        loadDataToTable();
        enableButton(true, false, false, false, true);
        
        tableListRole.getSelectionModel()
                .addListSelectionListener(new TableSelection());
        renderCheckboxHeader();
    }
    
    public void renderCheckboxHeader(){
        TableColumn tc = tableListPermission.getColumnModel().getColumn(0);  
        tc.setCellEditor(tableListPermission.getDefaultEditor(Boolean.class));  
        tc.setCellRenderer(tableListPermission.getDefaultRenderer(Boolean.class));  
        tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
    }
    
    private void initPaging(){
        //start konfigurasi untuk paging
        Long count = App.getVacRestClient().countSemuaRole();
        
        Long hasilBagi = count/rows;
        long page = Math.round(hasilBagi); 
        
        if ((count%rows) > 0) {
            page = page + 1;
        }
        if(page==0){page=1;}
        labelCount.setText(String.valueOf(count));
        spinnerPaging.setModel(new SpinnerNumberModel(1, 1, page, 1));
        labelMaxPage.setText("  pages : " + String.valueOf(page));
        //end konfigurasi untuk paging
    }
    
    private void loadDataToTable(){
        Double hal = (Double) spinnerPaging.getModel().getValue();
        Integer start = (hal.intValue() - 1) * rows;
        
        listRole = App.getVacRestClient().semuaRole(start, rows);
        tableListRole.setModel(
                new MasterRoleTableModel(listRole));
        
        permission = App.getVacRestClient().semuaPermission();
        tableListPermission.setModel(
                new PermissionTableModel(permission, Boolean.FALSE));
        renderCheckboxHeader();
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
        btnRefresh = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        labelCount = new javax.swing.JLabel();
        spinnerPaging = new javax.swing.JSpinner();
        labelMaxPage = new javax.swing.JLabel();

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
        tableListPermission.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNama)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        tableListRole.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tableListRole);

        btnExit.setText("Exit");
        btnExit.setEnabled(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        jLabel4.setText("Total Data : ");
        jToolBar1.add(jLabel4);

        labelCount.setMaximumSize(new java.awt.Dimension(14, 14));
        labelCount.setPreferredSize(new java.awt.Dimension(50, 50));
        jToolBar1.add(labelCount);

        spinnerPaging.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerPagingStateChanged(evt);
            }
        });
        jToolBar1.add(spinnerPaging);

        labelMaxPage.setMaximumSize(new java.awt.Dimension(14, 14));
        labelMaxPage.setPreferredSize(new java.awt.Dimension(100, 100));
        jToolBar1.add(labelMaxPage);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
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
                    .addComponent(btnExit)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        App.getMainFrame().getMainTabpane().remove(this);
        masterRolePanel = null;
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(workerSaveRole != null && !workerSaveRole.isDone()){
            workerSaveRole.cancel(true);
            workerSaveRole = null;
        }
        
        workerSaveRole = new WorkerSaveRole();
        workerSaveRole.addPropertyChangeListener(this);
        workerSaveRole.execute();
        
        btnSave.setEnabled(false);
        App.getMainFrame().getjProgressBar1().setIndeterminate(true);
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
       tableListPermission.setModel(new PermissionTableModel(permission, Boolean.TRUE));
       renderCheckboxHeader();
       
       txtKode.requestFocus();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        enableButton(false, false, true, false, true);
        enableTextField(true);
        tableListPermission.setModel(new PermissionTableModel(permission, Boolean.TRUE));
        renderCheckboxHeader();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        tableListRole.setModel(new MasterRoleTableModel(new ArrayList<Role>()));
        
        if(workerQueryRole != null && !workerQueryRole.isDone()){
            workerQueryRole.cancel(true);
            workerQueryRole = null;
        }
        
        workerQueryRole = new WorkerQueryRole();
        workerQueryRole.addPropertyChangeListener(this);
        workerQueryRole.execute();
        
        btnRefresh.setEnabled(false);
        App.getMainFrame().getjProgressBar1().setIndeterminate(true);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void spinnerPagingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerPagingStateChanged
        loadDataToTable();
    }//GEN-LAST:event_spinnerPagingStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelCount;
    private javax.swing.JLabel labelMaxPage;
    private javax.swing.JSpinner spinnerPaging;
    private javax.swing.JTable tableListPermission;
    private javax.swing.JTable tableListRole;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables

    class WorkerQueryRole extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
//            Random random = new Random();
            int progress = 0;
            //Initialize progress property.
            setProgress(0);
            
            Double hal = (Double) spinnerPaging.getModel().getValue();
            Integer start = (hal.intValue() - 1) * rows;
            List<Role> roles = App.getVacRestClient().semuaRole(start, rows);
            
            //Sleep for at least one second to simulate "startup".
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {
                System.out.println("ERROR " + ignore.getMessage());
            }
            
            int i = 0;
            
            while(i<roles.size()){
                System.out.println("Perulangan Role " + roles.get(i).getKode());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignore) {
                    System.out.println("ERROR " + ignore.getMessage());
                }
                
                Integer counter = ((i+1) * 100) / roles.size();
                
                progress = counter;
                setProgress(Math.min(progress, 100));
                tableListRole.setModel(new MasterRoleTableModel(roles.subList(0, 0+i)));
                i++;
            }
            
            return null;
        }

        /*
         * Executed in event dispatch thread
         */
        public void done() {
            btnRefresh.setEnabled(true);
        }

    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange : " + evt.getPropertyName());
        if("progress" == evt.getPropertyName()){
            int progress = (Integer) evt.getNewValue();
            App.getMainFrame().getjProgressBar1().setIndeterminate(false);
            App.getMainFrame().getjProgressBar1().setValue(progress);
        }
    }

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
                
                refreshTablePermission(role.getDaftarPermission());
            }
        }
        
    }
    
    private void refreshTablePermission(
            List<Permission> selectedList){
        permission = App.getVacRestClient().semuaPermission();
        
        for (Permission p1 : selectedList) {
            for (Permission p : permission) {
                if(p.getId().equals(p1.getId())){
                    p.setSelected(Boolean.TRUE);
                    break;
                }
            }
        }
        
        tableListPermission.setModel(
                new PermissionTableModel(permission, Boolean.FALSE));
        renderCheckboxHeader();
    }
    
    
    class CheckBoxHeader extends JCheckBox
            implements TableCellRenderer, MouseListener {

        protected CheckBoxHeader rendererComponent;
        protected int column;
        protected boolean mousePressed = false;

        public CheckBoxHeader(ItemListener itemListener) {
            rendererComponent = this;
            rendererComponent.addItemListener(itemListener);
        }

        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (table != null) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                    rendererComponent.setForeground(header.getForeground());
                    rendererComponent.setBackground(header.getBackground());
                    rendererComponent.setFont(header.getFont());
                    rendererComponent.setHorizontalAlignment(SwingConstants.CENTER);
                    rendererComponent.setHorizontalTextPosition(SwingConstants.CENTER);
                    header.addMouseListener(rendererComponent);
                }
            }
            setColumn(column);
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            return rendererComponent;
        }

        protected void setColumn(int column) {
            this.column = column;
        }

        public int getColumn() {
            return column;
        }

        protected void handleClickEvent(MouseEvent e) {
            if (mousePressed) {
                mousePressed = false;
                JTableHeader header = (JTableHeader) (e.getSource());
                JTable tableView = header.getTable();
                TableColumnModel columnModel = tableView.getColumnModel();
                int viewColumn = columnModel.getColumnIndexAtX(e.getX());
                int column = tableView.convertColumnIndexToModel(viewColumn);

                if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
                    doClick();
                }
            }
        }

        public void mouseClicked(MouseEvent e) {
            handleClickEvent(e);
            ((JTableHeader) e.getSource()).repaint();
        }

        public void mousePressed(MouseEvent e) {
            mousePressed = true;
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
    
    class MyItemListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = tableListPermission.getRowCount(); x < y; x++) {
                tableListPermission.setValueAt(new Boolean(checked), x, 0);
            }
        }
    }
    
    class WorkerSaveRole extends SwingWorker<Role, Void> {

        @Override
        protected void done() {
            role = null;
            loadDataToTable();

            txtKode.setText("");
            txtNama.setText("");

            enableButton(true, false, false, false, true);
            enableTextField(false);
            App.getMainFrame().getjProgressBar1().setIndeterminate(false);
        }
        
        @Override
        protected Role doInBackground() throws Exception {
            if(validateForm()){
                if(role == null){
                    role = new Role();
                }

                role.setKode(txtKode.getText());
                role.setNama(txtNama.getText());
                role.setDaftarPermission(getCheckedPermission());

                App.getVacRestClient().simpan(role);
            }
            return role;
        }
        
    }
    
    
}
