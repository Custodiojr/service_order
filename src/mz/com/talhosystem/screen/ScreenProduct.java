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
public class ScreenProduct extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form ScreenProduct
     */
    public ScreenProduct() {
        initComponents();
        conexao = ModelConecction.conector();
    }

    // ================================================ Create user on db ===================================================
    private void create() {
        String sql = "INSERT INTO products (name) VALUES (?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNameProd.getText());

            // validate the fields
            if (txtNameProd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos campos obrigatorios!");
            } else {
                //Insere dados na tabela usuario com os dados do formulario
                int add = pst.executeUpdate();
                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    txtNameProd.setText(null);
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
    private void search_product() {
        String sql = "SELECT product_id as Id, name as Nome   FROM products  WHERE name like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtpesq.getText() + "%");
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //===========================================  End Search product on db =====================================================
    //============================================ Update product =============================================================
    private void update() {
        String sql = "UPDATE products SET name = ? WHERE product_id = ? ";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(2, txtProdID.getText());
            pst.setString(1, txtNameProd.getText());

            if (txtNameProd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos campos obrigatorios!");
            } else {
                //Update users table
                int up = pst.executeUpdate();
                if (up > 0) {
                    JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
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
    //============================================= set field when click on the table ========================================
    private void set_fields() {
        int setar = jTable1.getSelectedRow();
        txtProdID.setText(jTable1.getModel().getValueAt(setar, 0).toString());
        txtNameProd.setText(jTable1.getModel().getValueAt(setar, 1).toString());
        //dasenable add button
        btnAdd.setEnabled(false);
        // Enable update and delete button
        btnUp.setEnabled(true);
        btnRem.setEnabled(true);
    }

    //===========================================End set field when clicked on the table ========================================
    //=========================================== clean input method ======================================================
    private void clean() {
        txtNameProd.setText(null);
        txtProdID.setText(null);
        //dasenable add button
        btnAdd.setEnabled(true);
        // Enable update and delete button
        btnUp.setEnabled(false);
        btnRem.setEnabled(false);
    }

    //==========================================End clean input method ====================================================
    //=========================================== Delete user on db ======================================================
    private void delete() {
        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM products WHERE product_id = ? ";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtProdID.getText());
                int del = pst.executeUpdate();
                if (del > 0) {
                    JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtpesq = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNameProd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUp = new javax.swing.JButton();
        btnRem = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtProdID = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Produtos");

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ProductID", "Product Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtpesq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpesqKeyPressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mz/com/talhosystem/icon/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpesq, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(75, 75, 75))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtpesq))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nome do produto");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mz/com/talhosystem/icon/create.png"))); // NOI18N
        btnAdd.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mz/com/talhosystem/icon/update.png"))); // NOI18N
        btnUp.setEnabled(false);
        btnUp.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });

        btnRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mz/com/talhosystem/icon/delete.png"))); // NOI18N
        btnRem.setEnabled(false);
        btnRem.setPreferredSize(new java.awt.Dimension(80, 80));
        btnRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("ProdID");

        txtProdID.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNameProd, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProdID, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btnRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtProdID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNameProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        setBounds(0, 0, 844, 522);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtpesqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpesqKeyPressed
        // TODO add your handling code here:
        search_product();
    }//GEN-LAST:event_txtpesqKeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        set_fields();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpActionPerformed

    private void btnRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnRemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRem;
    private javax.swing.JButton btnUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtNameProd;
    private javax.swing.JTextField txtProdID;
    private javax.swing.JTextField txtpesq;
    // End of variables declaration//GEN-END:variables
}
