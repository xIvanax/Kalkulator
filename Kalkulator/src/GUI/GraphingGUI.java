/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Dorotea
 */
public class GraphingGUI extends JPanel{ 
    private JPanel spremnik;
    private JPanel unos;
    private JPanel nacrtaj;
    private JTextField ekran;
    JTabbedPane tab;
    JButton jednako = new JButton();
    
    //obrisi
    private boolean start = true;
    private ArrayList<String> funkcija;
    private String zadnjaBinarnaOperacija="=";
    private String zadnjaUnarnaOperacija="";
    
    public GraphingGUI(){
        spremnik=new JPanel();
        nacrtaj=new JPanel();
        unos=new JPanel();
        unos.setLayout(new BorderLayout());
        
        ekran = new JTextField();
        ekran.setSize(800, 100);
        ekran.setPreferredSize(new Dimension(800,100));
        ekran.setEnabled(true);
        ekran.setFont(ekran.getFont().deriveFont(Font.BOLD, 28f));
        unos.add(ekran, BorderLayout.NORTH);
        
        spremnik.setLayout(new GridLayout(6,7));
        
        ActionListener pisanje = new GraphingGUI.AkcijaPisanja();
        ActionListener brisanje = new GraphingGUI.AkcijaBrisanja();
        ActionListener bin_naredba = new GraphingGUI.AkcijaBinarneOperacije();
        ActionListener unar_naredba = new GraphingGUI.AkcijaUnarneOperacije();
    
        //koristim gumbe kao u CalculatorGUI, uz neke promijene i dodatke
        //potrebno je dodati gumbe za: (, ), x, y, e, pi, x^y, ceiling, floor, sqrtx, proizvoljni korijen od x, x^2
        //x^2 sam stavila jer nemam bolje ideje, a bio mi je potreban jos jedan gumb
        
        //dodatno priliko implementacije provijeri jesu li unarana ili binarna operacija, za svaki slucaj
        dodajGumb("⌈x⌉",unar_naredba); dodajGumb("⌊x⌋",unar_naredba);
        //ne mogu stavit da bude oznaka za korijen cijela?? izbor: √x ili x^(1/2)
        dodajGumb("√x",unar_naredba); dodajGumb("x^(1/y)",bin_naredba);
        dodajGumb("π",pisanje); dodajGumb("e",pisanje); dodajGumb("C",brisanje);
        
        dodajGumb("x^2",unar_naredba); dodajGumb("1/x",unar_naredba);
        dodajGumb("|x|",unar_naredba); dodajGumb("(",pisanje);
        dodajGumb(")",pisanje); dodajGumb("D",brisanje); dodajGumb("CE",brisanje);
        
        dodajGumb("x^y",bin_naredba); dodajGumb("7",pisanje);
        dodajGumb("8",pisanje); dodajGumb("9",pisanje);
        dodajGumb("/",bin_naredba); dodajGumb("sin",unar_naredba); dodajGumb("arcsin",unar_naredba);
        
        dodajGumb("10^x",unar_naredba); dodajGumb("4",pisanje);
        dodajGumb("5",pisanje); dodajGumb("6",pisanje);
        dodajGumb("*",bin_naredba); dodajGumb("cos",unar_naredba); dodajGumb("arccos",unar_naredba);
        
        dodajGumb("logx",unar_naredba); dodajGumb("1",pisanje);
        dodajGumb("2",pisanje); dodajGumb("3",pisanje);
        dodajGumb("-",bin_naredba); dodajGumb("tg",unar_naredba); dodajGumb("arctg",unar_naredba);
        
        dodajGumb("ln",unar_naredba); dodajGumb("0",pisanje);
        dodajGumb(".",pisanje); dodajGumb("=",bin_naredba);
        dodajGumb("+",bin_naredba); dodajGumb("ctg",unar_naredba); dodajGumb("arcctg",unar_naredba);
        
        unos.add(spremnik, BorderLayout.CENTER);
        tab=new JTabbedPane();
        tab.add("Unos",unos);
        tab.add("Graf",nacrtaj);
        //ne uspijevam namjestiti da se jtabbedpane nalazi preko cijelog frame-a te da se dinamicki resize-a
        this.add(tab);
    }
    
    //ove su funkcije samo kopirane iz file-a CalculatorGUI
    //kasnije cu ih prepraviti, a sada su mi samo potrebne kako bih mogla pokrenuti aplikaciju
    //zakomentirane su neke linije da se ne pojavljuje greska, kasnije prepravljamo
    private void dodajGumb(String oznaka, ActionListener slusac){
        JButton gumb = new JButton(oznaka);
        if(oznaka=="=")
            jednako=gumb;
        gumb.addActionListener(slusac);
        spremnik.add(gumb);
    }
    
    private class AkcijaPisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            if(start){
                ekran.setText("");
                start=false;
            }
            ekran.setText(ekran.getText()+unos);
        }
    }
    
    private class AkcijaBrisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            switch (unos) {
                case "D":
                    String str = ekran.getText();
                    //nema unosa pa nemamo što brisati
                    if("".equals(str))
                        break;
                    //na ekran stavljam broj bez zadnje znamenke
                    if(str.length()>1)
                        ekran.setText(str.substring(0, str.length()-1));
                    else 
                        ekran.setText("");
                    break;
                case "CE":
                    //brišem cijeli zadnjji entry
                    ekran.setText("");
                    break;
                case "C":
                    //brišem cijeli input kalkulatora (back to square one)
                    start = true;
                    //rezultat=0.0;
                    zadnjaBinarnaOperacija="=";
                    zadnjaUnarnaOperacija="";
                    ekran.setText("");
                    break;
                default:
                    break;
            }
        }
    }
    private class AkcijaBinarneOperacije implements ActionListener{
        
        //ovaj dio treba prepraviti, za sada je kopija funkcije iz CalculatorGUI
        @Override
        public void actionPerformed(ActionEvent event) {
            String operacija = event.getActionCommand();
            if(start){
                //System.out.println("Sad je start true");
                if(operacija.equals("-")){
                    ekran.setText(operacija);
                    start=false;
                }
                else zadnjaBinarnaOperacija=operacija;
            }
            else{
                //System.out.println("Sad je start false i promijenit cu ga true");
                racunaj(Double.parseDouble(ekran.getText()));
                zadnjaBinarnaOperacija=operacija;
                start=true;
            }
        }
        
        public void racunaj(double x){
            switch (zadnjaBinarnaOperacija) {
                case "+":
                    //rezultat+=x;
                    break;
                case "-":
                    //rezultat-=x;
                    break;
                case "*":
                    //rezultat*=x;
                    break;
                case "/":
                    //rezultat/=x;
                    break;
                case "=":
                    //rezultat=x;
                    break;
                default:
                    break;
            }
            //ekran.setText(""+rezultat);
        }
    }
    
    private class AkcijaUnarneOperacije implements ActionListener{
        //double unaryResult=rezultat;
        @Override
        public void actionPerformed(ActionEvent event) {
            String operacija = event.getActionCommand();
            zadnjaUnarnaOperacija=operacija;
            racunaj(Double.parseDouble(ekran.getText()));
            start=true;
        }
        
        public void racunaj(double x){
            switch (zadnjaUnarnaOperacija) {
                case "sin":
                    //unaryResult=Math.sin(x);
                    break;
                case "arcsin":
                    //unaryResult=Math.asin(x);
                    break;
                case "cos":
                    //unaryResult=Math.cos(x);
                    break;
                case "arccos":
                    //unaryResult=Math.acos(x);
                    break;
                case "tg":
                    //unaryResult=Math.tan(x);
                    break;
                case "arctg":
                    //unaryResult=Math.atan(x);
                    break;
                case "ctg":
                    //unaryResult=1/Math.tan(x);
                    break;
                case "arcctg":
                    //unaryResult=1/Math.atan(x);
                    break;
                case "1/x":
                    //unaryResult=1/x;
                    break;
                case "ln(x)":
                    //unaryResult=Math.log(x);
                    break;
                case "log(x)":
                    //unaryResult=Math.log10(x);
                    break;
                case "10^x":
                    //unaryResult=Math.pow(10,x);
                    break;
                case "e^x":
                    //unaryResult=Math.pow(Math.E, x);
                    break;
                case "%":
                    //unaryResult=x/100;
                    break;
                default:
                    break;
            }
            switch (zadnjaBinarnaOperacija) {
                case "+":
                    //rezultat+=unaryResult;
                    break;
                case "-":
                   // rezultat-=unaryResult;
                    break;
                case "*":
                    //rezultat*=unaryResult;
                    break;
                case "/":
                    //rezultat/=unaryResult;
                    break;
                case "=":
                    //rezultat=unaryResult;
                    break;
                default:
                    break;
            }
            //ekran.setText(""+rezultat);
        }
    }
}