/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GUIinterfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Sučelje s implementacijama pomoćnih metoda u polinomnom kalkulatoru, glavna uloga je stvaranje kompaktnijeg koda u polinomnom kalkulatoru
 * @author Ivana
 */
public interface PolynomialInterface {
    /**
     * Čišćenje baze podataka na početku korištenja
     * @param url link na bazu podataka kalkulatora
     * @author Ivana
     */
    public default void setUpDatabase(String url){
        String sql1 ="DELETE FROM Polinomi;";
        String sql2 ="VACUUM;";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {}
        Connection conn=null;
        ResultSet result = null; 
        try{
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql1);
            stmt.execute(sql2);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    };
    /**
     * Postavljanje nekih početnih vrijednosti za ekrane polinomnog kalkulatora
     * @param ekran1 prostor za unos prvog polinoma
     * @param ekran2 prostor za unos drugog polinoma
     * @param display prostor za prikaz rezultata raznih operacija
     * @author Ivana
     */
    public default void setUpDisplaysDesign(JTextField ekran1, JTextField ekran2, JTextField display, JPanel unos, JPanel spremnik) {
        ekran1.setText("");
        ekran1.setSize(770, 50);
        ekran1.setPreferredSize(new Dimension(770,50));
        ekran1.setEnabled(true);
        ekran1.setFont(ekran1.getFont().deriveFont(Font.BOLD, 28f));
        
        ekran2.setText("");
        ekran2.setSize(770, 50);
        ekran2.setPreferredSize(new Dimension(770,50));
        ekran2.setEnabled(true);
        ekran2.setFont(ekran2.getFont().deriveFont(Font.BOLD, 28f));
        
        display.setText("");
        display.setSize(770, 50);
        display.setPreferredSize(new Dimension(770,50));
        display.setEnabled(true);
        display.setFont(display.getFont().deriveFont(Font.BOLD, 28f));
        
        JLabel jLabel1 = new JLabel("Prvi polinom:");
        JLabel jLabel2 = new JLabel("Drugi polinom:");
        JLabel jLabel3 = new JLabel("Rezultat:");
        
        unos.add(jLabel1);
        unos.add(ekran1);
        unos.add(jLabel2);
        unos.add(ekran2);
        unos.add(jLabel3);
        unos.add(display);
        
        spremnik.setSize(770, 400);
        spremnik.setPreferredSize(new Dimension(770,400));
        spremnik.setLayout(new BorderLayout());
    }
    
    public default void setUpVisuals(JPanel spremnik, JTextField jTextField1, JButton jButton1, JButton jButton2, JButton jButton3, JButton jButton4, JButton jButton5, JButton jButton6, JRadioButton jRadioButton1, JRadioButton jRadioButton2, JRadioButton jRadioButton3, JRadioButton jRadioButton4, JRadioButton jRadioButton5) {
        ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        ButtonGroup buttonGroup2 = new javax.swing.ButtonGroup();
        JLabel jLabel4 = new javax.swing.JLabel();
        JLabel jLabel5 = new javax.swing.JLabel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JLabel jLabel7 = new javax.swing.JLabel();
        
        jTextField1.setText("");
        jTextField1.setEnabled(true);
        
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("π");
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("e");

        jLabel4.setText("Posebne vrijednosti:");

        jLabel5.setText("Polinomne operacije:");
        
        jLabel6.setText("Unesite vrijednost u kojoj");

        jLabel7.setText("želite evaluirati funkciju:");
        
        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setText("zbrajanje");
        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("oduzimanje");
        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("množenje");
        
        jButton1.setText("spremi");
        jButton1.setToolTipText("spremanje prvog polinoma");
        jButton2.setText("evaluiraj");
        jButton2.setToolTipText("evaluacija prvog polinoma");
        jButton3.setText("graf");
        jButton3.setToolTipText("graf prvog polinoma");
        jButton4.setText("derivacija");
        jButton4.setToolTipText("derivacija prvog polinoma");
        jButton5.setText("dohvati");
        jButton6.setText("memorija");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(spremnik);
        spremnik.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jLabel4)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel5)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jRadioButton5)
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    //.addComponent(jRadioButton5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }
}
