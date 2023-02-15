/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HelperClasses;

import Grapher.Expressions.Function;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
 * Pop-up prozor koji omogućuje pristup memoriji kalkulatora iz grafičkog kalkulatora.
 * @author Ivana
 */
public class popUp extends JFrame implements ActionListener{
    Popup po;
    String url;
    String povratnaVrijednost="";
    ArrayList<String> iskoristenaImena = new ArrayList<>();
    JTextField ekran;
    String evaluateAt;
    double evaluatedFunction;
    JPanel f;
    JPanel p;
    PopupFactory pf;
    String evaluatedFunctionF;
    /**
     * Konstruktor u kojem se postavljaju komponente pop-up prozora i stvara se poveznica s grafičkim kalkualtorom.
     * @param f prozor roditelj
     * @param url lokacija baze podataka koja igra ulogu memorije kalkulatora
     * @param iskoristenaImena imena koja se već nalaze u bazi podataka
     * @param ekran JTextField za prikaz rezultata u prozoru f
     * @param eA vrijednost u kojoj je funkcija evaluirana
     * @param eF vrijednost funkcije evaluirane u eA
     * @author Ivana
     */
    public popUp(JPanel f, String url, ArrayList<String> iskoristenaImena, JTextField ekran, String eA, double eF, String evaluatedFunctionF)
    {   
        this.evaluatedFunctionF = evaluatedFunctionF;
        this.f=f;
        this.url=url;
        this.iskoristenaImena=iskoristenaImena;
        this.ekran=ekran;
        evaluateAt=eA; evaluatedFunction=eF;
        
        pf = new PopupFactory();
 
        JLabel l = new JLabel("Meni");
 
        JButton b19 = new JButton("Prikaži");
        JButton b20 = new JButton("Dohvati");
        JButton b21 = new JButton("Spremi");
        JButton b22 = new JButton("Odustani");
 
        b19.addActionListener(this);
        b20.addActionListener(this);
        b21.addActionListener(this);
        b22.addActionListener(this);
 
        p = new JPanel();
 
        p.add(l);
        p.add(b19);
        p.add(b20);
        p.add(b21);
        p.add(b22);
 
        p.setLayout(new GridLayout(5, 1));
 
        po = pf.getPopup(f, p, 180, 100);
        po.show();
 
    }
 /**
  * Obrada pritiska pojedinog gumba na pop-up prozoru.
  * @param e događaj
  * @author Ivana
  */
    public void actionPerformed(ActionEvent e)
    {
        String d = e.getActionCommand();
        if (d.equals("Prikaži")) {
            po.hide();
            String output ="Ime"+"\t"+"Funkcija"+"\t"+"Tocka_evaluacije"+"\t"+"Rezultat"+"\n";
            try{
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex){
            }
            String sql ="SELECT Ime, Funkcija, Tocka_evaluacije, Rezultat FROM Funkcije";
            Connection conn=null;
            ResultSet result = null; 
            try{
               conn = DriverManager.getConnection(url);
               Statement stmt = conn.createStatement();
               result = stmt.executeQuery(sql);
            }catch(SQLException ev){
               System.out.println(ev.getMessage());
            }
            try {
                while(result.next()){
                    String ime=result.getString("Ime");
                    String funkcija=result.getString("Funkcija");
                    String tocka_eval =result.getString("Tocka_evaluacije");
                    String res = result.getString("Rezultat");
                    if(ime!=null)
                        output+=ime+"\t"+funkcija+"\t"+tocka_eval+"\t"+"\t"+res+"\n";
                }
            }    
            catch (SQLException ex){}
            JOptionPane.showMessageDialog(f, new JTextArea(output));
            po = pf.getPopup(f, p, 180, 100);
        }else if (d.equals("Dohvati")){
            String trazeni = JOptionPane.showInputDialog(f, "Koju evaluiranu vrijednost želite dohvatiti?", "Dohvaćanje evaluirane vrijednosti", JOptionPane.QUESTION_MESSAGE);
            try{
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex){
            }
            String sql ="SELECT Ime, Funkcija, Tocka_evaluacije, Rezultat FROM Funkcije";
            Connection conn=null;
            ResultSet result = null; 
            try{
               conn = DriverManager.getConnection(url);
               Statement stmt = conn.createStatement();
               result = stmt.executeQuery(sql);
            }catch(SQLException ev){
               JOptionPane.showMessageDialog(f, ev.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
               return;
            }
            try {
                while(result.next()){
                    String ime=result.getString("Ime");
                    if(ime.equals(trazeni)){
                        String funkcija=result.getString("Funkcija");
                        String tocka_eval =result.getString("Tocka_evaluacije");
                        povratnaVrijednost += result.getString("Rezultat");
                        ekran.setText(ekran.getText()+povratnaVrijednost);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(f, "Ne postoji spremljena vrijednost s imenom "+trazeni+".", "Greška", JOptionPane.ERROR_MESSAGE);
            }catch (SQLException ex){}
            po.hide();
            
            po = pf.getPopup(f, p, 180, 100);
        }else if (d.equals("Spremi")){
            if(evaluateAt==null || "".equals(evaluateAt)){
                JOptionPane.showMessageDialog(f, "Operacija koju ste odabrali se nije izvršila jer niste unesli točku evaluacije.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            po.hide();
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {}
            String name = "";
            while(true){
                name = JOptionPane.showInputDialog(f, "Pod kojim imenom želite spremiti Evaluiranu vrijednost funkcije?", "Spremanje evaluirane vrijednosti", JOptionPane.QUESTION_MESSAGE);
                if(name==null)
                    return;
                if(!iskoristenaImena.contains(name)){
                    iskoristenaImena.add(name);
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(f, "Već ste spremili nešto pod tim imenom. Odaberite drugo ime.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
            String sql ="INSERT INTO Funkcije (Ime, Funkcija, Tocka_evaluacije, Rezultat) VALUES ('"+name+"','"+evaluatedFunctionF+"','"+evaluateAt+"','"+evaluatedFunction+"');";
            Connection conn=null;
            ResultSet result = null; 
            try{
                conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }catch(SQLException ev){
               JOptionPane.showMessageDialog(f, ev.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
               return;
            }
            po = pf.getPopup(f, p, 180, 100);
        }else if(d.equals("Odustani")){
            po.hide();
            po = pf.getPopup(f, p, 180, 100);
        }
    }
}