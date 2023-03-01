/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package mz.com.talhosystem.screen;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mz.com.talhosystem.dal.ModelConecction;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author CUSTODIO
 */
public class ScreenUser extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form ScreenUser
     */
    public ScreenUser() {
        initComponents();
        conexao = ModelConecction.conector();
    }

    // ================================================ Create user on db ===================================================
    private void create() {
        String sql = "INSERT INTO users (name, pass) VALUES (?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUserName.getText());
            pst.setString(2, txtUserPass.getText());
            // validate the fields
            if (txtUserName.getText().isEmpty() || txtUserPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos campos obrigatorios!");
            } else {
                //Insere dados na tabela usuario com os dados do formulario
                int add = pst.executeUpdate();
                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    clean();
                } else {
                    JOptionPane.showMessageDialog(null, "Verifica a sua conexão com  a internet, provavelmente o seu pedido não foi processado!");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // ==============================================END Create user on db ===================================================
    //================================================ Search user on db =====================================================
    private void search_user() {
        String sql = "SELECT id as Id, name as Nome , pass as Senha  FROM users  WHERE name like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUserSearch.getText() + "%");
            rs = pst.executeQuery();
            tblUser.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //===========================================  End Search user on db =====================================================
    //============================================= set field when click on the table ========================================
    private void set_fields() {
        int setar = tblUser.getSelectedRow();
        txtUsrId.setText(tblUser.getModel().getValueAt(setar, 0).toString());
        txtUserName.setText(tblUser.getModel().getValueAt(setar, 1).toString());
        txtUserPass.setText(tblUser.getModel().getValueAt(setar, 2).toString());
        //dasenable add button
        btnUserAdd.setEnabled(false);
        // Enable update and delete button
        btnUserUpd.setEnabled(true);
        btnUserDel.setEnabled(true);
    }

    //===========================================End set field when clicked on the table ========================================
    //============================================ Update user ==============================================================
    private void update() {
        String sql = "UPDATE users SET name = ?, pass = ? WHERE id = ? ";
        try {
            //pst = conexao.prepareStatement(sql);
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUserName.getText());
            pst.setString(2, txtUserPass.getText());
            pst.setString(3, txtUsrId.getText());
            if (txtUserName.getText().isEmpty() || txtUserPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos campos obrigatorios!");
            } else {
                //Update users table
                int up = pst.executeUpdate();
                if (up > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                    clean();
                } else {
                    JOptionPane.showMessageDialog(null, "Verifica a sua conexão com  a internet, provavelmente o seu pedido não foi processado!");

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //===========================================END Update user ==============================================================
    //=========================================== clean input method ======================================================
    private void clean() {
        txtUserName.setText(null);
        txtUserPass.setText(null);
        txtUsrId.setText(null);
        //dasenable add button
        btnUserAdd.setEnabled(true);
        // Enable update and delete button
        btnUserUpd.setEnabled(false);
        btnUserDel.setEnabled(false);
    }

    //==========================================End clean input method ====================================================
    //=========================================== Delete user on db ======================================================
    private void delete() {
        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM users WHERE id = ? ";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsrId.getText());
                int del = pst.executeUpdate();
                if (del > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    clean();
                } else {
                    JOptionPane.showMessageDialog(null, "Verifica a sua conexão com  a internet, provavelmente o seu pedido não foi processado!");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    //=========================================FIM Delete user on db ======================================================
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUserPass = new javax.swing.JTextField();
        btnUserAdd = new javax.swing.JButton();
        btnUserUpd = new javax.swing.JButton();
        btnUserDel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtUsrId = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtUserSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Usuários");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(860, 580));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("*Nome ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("*Senha");

        btnUserAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mz/com/talhosystem/icon/create.png"))); // NOI18N
        btnUserAdd.setToolTipText("Criar usuario");
        btnUserAdd.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUserAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserAddActionPerformed(evt);
            }
        });

        btnUserUpd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mz/com/talhosystem/icon/update.png"))); // NOI18N
        btnUserUpd.setToolTipText("Atualizar");
        btnUserUpd.setEnabled(false);
        btnUserUpd.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUserUpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserUpdActionPerformed(evt);
            }
        });

        btnUserDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mz/com/talhosystem/icon/delete.png"))); // NOI18N
        btnUserDel.setToolTipText("Apagar usuario");
        btnUserDel.setEnabled(false);
        btnUserDel.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUserDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Id");

        txtUsrId.setEditable(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txtUserSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserSearchActionPerformed(evt);
            }
        });
        txtUserSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserSearchKeyReleased(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mz/com/talhosystem/icon/search.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("* Campos obrigatorios");

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Senha"
            }
        ));
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        tblUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblUserKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblUser);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUserSearch)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btnUserUpd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(btnUserDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserPass, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsrId, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsrId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUserPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserUpd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        setBounds(0, 0, 797, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserSearchActionPerformed

    private void tblUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblUserKeyReleased

    }//GEN-LAST:event_tblUserKeyReleased

    private void txtUserSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserSearchKeyReleased
        // Call search method for find a user
        search_user();
    }//GEN-LAST:event_txtUserSearchKeyReleased

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        // set fields when click on the table
        set_fields();
    }//GEN-LAST:event_tblUserMouseClicked

    private void btnUserUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserUpdActionPerformed
        // Call update method 
        update();
    }//GEN-LAST:event_btnUserUpdActionPerformed

    private void btnUserAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserAddActionPerformed
        // Insert new user on the system
        create();
    }//GEN-LAST:event_btnUserAddActionPerformed

    private void btnUserDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserDelActionPerformed
        // Delete user from db
        delete();
    }//GEN-LAST:event_btnUserDelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUserAdd;
    private javax.swing.JButton btnUserDel;
    private javax.swing.JButton btnUserUpd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtUserPass;
    private javax.swing.JTextField txtUserSearch;
    private javax.swing.JTextField txtUsrId;
    // End of variables declaration//GEN-END:variables
}
