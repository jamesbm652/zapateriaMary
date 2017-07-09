/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JOptionPane;
import BL.BL_Logueo;
import BL.BL_Usuario;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author oscal
 */
public class Menu_Principal extends javax.swing.JFrame {

    /**
     * Creates new form Menu_Principal
     */
    public Menu_Principal() {
        initComponents();       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labSalir = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelBtnRevisar = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelBtnReportes = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panelBtnInventario = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelBtnAbonos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelBtnFacturar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelBtnAdministrar = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");
        setBackground(new java.awt.Color(175, 201, 201));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1370, 725));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 57, 66));
        jPanel2.setMinimumSize(new java.awt.Dimension(700, 74));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 74));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labSalir.setBackground(new java.awt.Color(175, 201, 201));
        labSalir.setForeground(new java.awt.Color(204, 204, 204));
        labSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir.png"))); // NOI18N
        labSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labSalirMouseEntered(evt);
            }
        });
        jPanel2.add(labSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 30, -1, -1));

        jLabel2.setBackground(new java.awt.Color(196, 196, 196));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(237, 237, 237));
        jLabel2.setText("Zapatería Mary");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 360, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 100));

        panelBtnRevisar.setBackground(new java.awt.Color(0, 105, 120));
        panelBtnRevisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnRevisarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnRevisarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnRevisarMouseExited(evt);
            }
        });
        panelBtnRevisar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Revisar Facturas");
        panelBtnRevisar.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/revisarFactura.png"))); // NOI18N
        panelBtnRevisar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jPanel1.add(panelBtnRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, 280, 160));

        panelBtnReportes.setBackground(new java.awt.Color(0, 239, 239));
        panelBtnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnReportesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnReportesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnReportesMouseExited(evt);
            }
        });
        panelBtnReportes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Reporte Ventas");
        panelBtnReportes.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/reportes.png"))); // NOI18N
        panelBtnReportes.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        jPanel1.add(panelBtnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 390, 280, 160));

        panelBtnInventario.setBackground(new java.awt.Color(0, 151, 167));
        panelBtnInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnInventarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnInventarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnInventarioMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Inventario");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/inventarioIcon.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnInventarioLayout = new javax.swing.GroupLayout(panelBtnInventario);
        panelBtnInventario.setLayout(panelBtnInventarioLayout);
        panelBtnInventarioLayout.setHorizontalGroup(
            panelBtnInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnInventarioLayout.createSequentialGroup()
                .addGroup(panelBtnInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnInventarioLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel9))
                    .addGroup(panelBtnInventarioLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel3)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        panelBtnInventarioLayout.setVerticalGroup(
            panelBtnInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnInventarioLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(34, 34, 34))
        );

        jPanel1.add(panelBtnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 280, 160));

        panelBtnAbonos.setBackground(new java.awt.Color(0, 159, 175));
        panelBtnAbonos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnAbonosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnAbonosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnAbonosMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Abonos");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/abonar.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnAbonosLayout = new javax.swing.GroupLayout(panelBtnAbonos);
        panelBtnAbonos.setLayout(panelBtnAbonosLayout);
        panelBtnAbonosLayout.setHorizontalGroup(
            panelBtnAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAbonosLayout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addGroup(panelBtnAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnAbonosLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(95, 95, 95))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnAbonosLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(111, 111, 111))))
        );
        panelBtnAbonosLayout.setVerticalGroup(
            panelBtnAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnAbonosLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(32, 32, 32))
        );

        jPanel1.add(panelBtnAbonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, 280, 160));

        panelBtnFacturar.setBackground(new java.awt.Color(38, 198, 218));
        panelBtnFacturar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnFacturarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnFacturarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnFacturarMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Facturar");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/facturar.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnFacturarLayout = new javax.swing.GroupLayout(panelBtnFacturar);
        panelBtnFacturar.setLayout(panelBtnFacturarLayout);
        panelBtnFacturarLayout.setHorizontalGroup(
            panelBtnFacturarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnFacturarLayout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(94, 94, 94))
            .addGroup(panelBtnFacturarLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnFacturarLayout.setVerticalGroup(
            panelBtnFacturarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnFacturarLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(37, 37, 37))
        );

        jPanel1.add(panelBtnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 280, 160));

        panelBtnAdministrar.setBackground(new java.awt.Color(128, 222, 234));
        panelBtnAdministrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnAdministrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBtnAdministrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBtnAdministrarMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Administrar Usuarios");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/settings.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnAdministrarLayout = new javax.swing.GroupLayout(panelBtnAdministrar);
        panelBtnAdministrar.setLayout(panelBtnAdministrarLayout);
        panelBtnAdministrarLayout.setHorizontalGroup(
            panelBtnAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAdministrarLayout.createSequentialGroup()
                .addGroup(panelBtnAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnAdministrarLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel14))
                    .addGroup(panelBtnAdministrarLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelBtnAdministrarLayout.setVerticalGroup(
            panelBtnAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnAdministrarLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(33, 33, 33))
        );

        jPanel1.add(panelBtnAdministrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 390, 280, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void labSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labSalirMouseEntered
        labSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_labSalirMouseEntered

    private void labSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labSalirMouseClicked
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_labSalirMouseClicked

    private void panelBtnInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnInventarioMouseClicked
        this.dispose();
        new Inventario(null).setVisible(true);
    }//GEN-LAST:event_panelBtnInventarioMouseClicked

    private void panelBtnInventarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnInventarioMouseEntered
        panelBtnInventario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBtnInventario.setBackground(new Color(0,166,183));
    }//GEN-LAST:event_panelBtnInventarioMouseEntered

    private void panelBtnFacturarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnFacturarMouseClicked
        this.dispose();
        new Facturar().setVisible(true);
    }//GEN-LAST:event_panelBtnFacturarMouseClicked

    private void panelBtnFacturarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnFacturarMouseEntered
       panelBtnFacturar.setCursor(new Cursor(Cursor.HAND_CURSOR));
       panelBtnFacturar.setBackground(new Color(34,181,200));
    }//GEN-LAST:event_panelBtnFacturarMouseEntered

    private void panelBtnReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnReportesMouseClicked
        BL_Logueo lg = new BL_Logueo();
        if(lg.isAdmin()){
            this.dispose();
            new ReporteVentas().setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "No tienes los permisos para ingresar\n a esta opción, favor comunicar al\nadministrador para solicitar el acceso","Acceso denegado",JOptionPane.WARNING_MESSAGE,new ImageIcon("src/recursos/warning.png"));
        }
        
    }//GEN-LAST:event_panelBtnReportesMouseClicked

    private void panelBtnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnReportesMouseEntered
        panelBtnReportes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBtnReportes.setBackground(new Color(0,214,214));
    }//GEN-LAST:event_panelBtnReportesMouseEntered

    private void panelBtnRevisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnRevisarMouseClicked
        this.dispose();
        new RevisarFacturas().setVisible(true);
    }//GEN-LAST:event_panelBtnRevisarMouseClicked

    private void panelBtnRevisarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnRevisarMouseEntered
        panelBtnRevisar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBtnRevisar.setBackground(new Color(0,134,158));
    }//GEN-LAST:event_panelBtnRevisarMouseEntered

    private void panelBtnAdministrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAdministrarMouseClicked
        BL_Logueo lg = new BL_Logueo();
        if(lg.isAdmin()){
            this.dispose();
            new AdministrarUsuarios().setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "No tienes los permisos para ingresar\n a esta opción, favor comunicar al\nadministrador para solicitar el acceso","Acceso denegado",JOptionPane.WARNING_MESSAGE,new ImageIcon("src/recursos/warning.png"));
        }
    }//GEN-LAST:event_panelBtnAdministrarMouseClicked

    private void panelBtnAdministrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAdministrarMouseEntered
        panelBtnAdministrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBtnAdministrar.setBackground(new Color(93,212,228));
    }//GEN-LAST:event_panelBtnAdministrarMouseEntered

    private void panelBtnInventarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnInventarioMouseExited
        panelBtnInventario.setBackground(new Color(0,151,167));
    }//GEN-LAST:event_panelBtnInventarioMouseExited

    private void panelBtnFacturarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnFacturarMouseExited
        panelBtnFacturar.setBackground(new Color(38,198,218));
    }//GEN-LAST:event_panelBtnFacturarMouseExited

    private void panelBtnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnReportesMouseExited
        panelBtnReportes.setBackground(new Color(0,239,239));
    }//GEN-LAST:event_panelBtnReportesMouseExited

    private void panelBtnRevisarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnRevisarMouseExited
        panelBtnRevisar.setBackground(new Color(0,105,120));
    }//GEN-LAST:event_panelBtnRevisarMouseExited

    private void panelBtnAdministrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAdministrarMouseExited
        panelBtnAdministrar.setBackground(new Color(128,222,234));
    }//GEN-LAST:event_panelBtnAdministrarMouseExited

    private void panelBtnAbonosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAbonosMouseExited
        panelBtnAbonos.setBackground(new Color(0,159,175));
    }//GEN-LAST:event_panelBtnAbonosMouseExited

    private void panelBtnAbonosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAbonosMouseEntered
        panelBtnAbonos.setBackground(new Color(0,180,198));
    }//GEN-LAST:event_panelBtnAbonosMouseEntered

    private void panelBtnAbonosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAbonosMouseClicked
        this.dispose();
        new Abonar().setVisible(true);
    }//GEN-LAST:event_panelBtnAbonosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labSalir;
    private javax.swing.JPanel panelBtnAbonos;
    private javax.swing.JPanel panelBtnAdministrar;
    private javax.swing.JPanel panelBtnFacturar;
    private javax.swing.JPanel panelBtnInventario;
    private javax.swing.JPanel panelBtnReportes;
    private javax.swing.JPanel panelBtnRevisar;
    // End of variables declaration//GEN-END:variables
}
