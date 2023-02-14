/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GUIinterfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Sučelje za implementaciju dijelova koda iz GraphingGUI u svrhu kompaktnosti koda za grafički kalkulator.
 * @author Ivana
 */
public interface GraphingInterface {
    /**
     * Čišćenje baze podataka na početku korištenja
     * @param url link na bazu podataka koja igra ulogu memorije kalkulatora
     * @author Ivana
     */
    public default void setUpDatabase(String url){
        String sql1 ="DELETE FROM Funkcije;";
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
     * Postavljanje rasporeda gumbi i njihovih ACtionListener-a.
     * @param ekran JTextField za prikaz rezultata u grafičkom kalkulatoru
     * @param unos JPanel na koji smještamo ekran
     * @param spremnik JPanel na koji smještamo gumbe
     * @param tab JTabbedPane na koji smještamo područje za crtanje i računanje
     * @param pisanje ActionListener za obradu akcije pisanja na ekran
     * @param brisanje ActionListener za obradu akcije brisanja sadržaja ekrana
     * @param bin_naredba ActionListener za obradu akcije pozia binarne naredbe
     * @param unar_naredba ActionListener za obradu akcije poziva unarne naredbe
     * @param mem ActionListener za obradu akcije poziva pop-up prozora za rad s memorijom kalkualtora
     * @param eval_naredba ActionListener za obradu evaluacije funkcije u zadanoj točki
     * @author Ivana
     */
    public default void setUpButtons(JTextField ekran, JPanel unos, JPanel spremnik, JTabbedPane tab, ActionListener pisanje,ActionListener brisanje,ActionListener bin_naredba,ActionListener unar_naredba,ActionListener mem, ActionListener eval_naredba){
        ekran.setText("");
        ekran.setSize(800, 100);
        ekran.setPreferredSize(new Dimension(800,100));
        ekran.setEnabled(true);
        ekran.setFont(ekran.getFont().deriveFont(Font.BOLD, 28f));
        
        unos.add(ekran, BorderLayout.NORTH);
        
        dodajGumb("⌈x⌉",unar_naredba, spremnik); dodajGumb("⌊x⌋",unar_naredba, spremnik);
        dodajGumb("memory",mem, spremnik); dodajGumb("x^(1/y)",bin_naredba, spremnik);
        dodajGumb("π",pisanje, spremnik); dodajGumb("e",pisanje, spremnik); 
        dodajGumb("C",brisanje, spremnik);
        
        dodajGumb("x",pisanje, spremnik); dodajGumb("1/x",unar_naredba, spremnik);
        dodajGumb("|x|",unar_naredba, spremnik); dodajGumb("(",pisanje, spremnik);
        dodajGumb(")",pisanje, spremnik); dodajGumb("D",brisanje, spremnik); 
        
        dodajGumb("eval",eval_naredba, spremnik);
        
        dodajGumb("x^y",bin_naredba, spremnik); dodajGumb("7",pisanje, spremnik);
        dodajGumb("8",pisanje, spremnik); dodajGumb("9",pisanje, spremnik);
        dodajGumb("/",bin_naredba, spremnik); dodajGumb("sin",unar_naredba, spremnik); 
        dodajGumb("arcsin",unar_naredba, spremnik);
        
        dodajGumb("10^x",unar_naredba, spremnik); dodajGumb("4",pisanje, spremnik);
        dodajGumb("5",pisanje, spremnik); dodajGumb("6",pisanje, spremnik);
        dodajGumb("*",bin_naredba, spremnik); dodajGumb("cos",unar_naredba, spremnik); 
        dodajGumb("arccos",unar_naredba, spremnik);
        
        dodajGumb("logx",unar_naredba, spremnik); dodajGumb("1",pisanje, spremnik);
        dodajGumb("2",pisanje, spremnik); dodajGumb("3",pisanje, spremnik);
        dodajGumb("-",bin_naredba, spremnik); dodajGumb("tg",unar_naredba, spremnik); 
        dodajGumb("arctg",unar_naredba, spremnik);
        
        dodajGumb("lnx",unar_naredba, spremnik); dodajGumb("0",pisanje, spremnik);
        dodajGumb(".",pisanje, spremnik); dodajGumb("draw",bin_naredba, spremnik);
        dodajGumb("+",bin_naredba, spremnik); dodajGumb("ctg",unar_naredba, spremnik); 
        dodajGumb("arcctg",unar_naredba, spremnik);
    }
    /**
     * Funkcija za postavljanje naziva i ActionListener-a gumba.
     * @param oznaka tekst gumba
     * @param slusac ActionListener gumba
     * @param spremnik JPanel na koji smještamo gumb
     * @author Ivana
     */
    public default void dodajGumb(String oznaka, ActionListener slusac, JPanel spremnik){
        JButton gumb = new JButton(oznaka);
        gumb.addActionListener(slusac);
        spremnik.add(gumb);
    }
}
