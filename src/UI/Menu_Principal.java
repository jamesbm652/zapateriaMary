/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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

        btn_Inventario = new javax.swing.JButton();
        btn_Facturar = new javax.swing.JButton();
        btn_ReporteVentas = new javax.swing.JButton();
        lab_TituloMenu = new javax.swing.JLabel();
        btn_RevisarFacturas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_Inventario.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btn_Inventario.setText("Inventario");
        btn_Inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InventarioActionPerformed(evt);
            }
        });

        btn_Facturar.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btn_Facturar.setText("Facturar");
        btn_Facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FacturarActionPerformed(evt);
            }
        });

        btn_ReporteVentas.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btn_ReporteVentas.setText("Reporte de Ventas");
        btn_ReporteVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReporteVentasActionPerformed(evt);
            }
        });

        lab_TituloMenu.setFont(new java.awt.Font("Yu Gothic UI", 1, 40)); // NOI18N
        lab_TituloMenu.setText("Menu Principal");

        btn_RevisarFacturas.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btn_RevisarFacturas.setText("Revisar Facturas");
        btn_RevisarFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RevisarFacturasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 16)); // NOI18N
        jLabel1.setText("Seleccione una opción:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(lab_TituloMenu))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_RevisarFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ReporteVentas))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btn_Inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_Facturar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel1)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lab_TituloMenu)
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Inventario)
                    .addComponent(btn_Facturar))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_RevisarFacturas)
                    .addComponent(btn_ReporteVentas))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_InventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InventarioActionPerformed
        this.dispose();
        Inventario inventario = new Inventario();
        inventario.setVisible(true);
    }//GEN-LAST:event_btn_InventarioActionPerformed

    private void btn_ReporteVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReporteVentasActionPerformed
        this.dispose();
        ReporteVentas reporte = new ReporteVentas();
        reporte.setVisible(true);
    }//GEN-LAST:event_btn_ReporteVentasActionPerformed

    private void btn_FacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FacturarActionPerformed
        this.dispose();
        Facturar facturar = new Facturar();
        facturar.setVisible(true);
    }//GEN-LAST:event_btn_FacturarActionPerformed

    private void btn_RevisarFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RevisarFacturasActionPerformed
        this.dispose();
        RevisarFacturas revisar = new RevisarFacturas();
        revisar.setVisible(true);
    }//GEN-LAST:event_btn_RevisarFacturasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Facturar;
    private javax.swing.JButton btn_Inventario;
    private javax.swing.JButton btn_ReporteVentas;
    private javax.swing.JButton btn_RevisarFacturas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lab_TituloMenu;
    // End of variables declaration//GEN-END:variables
}
