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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Standardni kalkulator.
 * @author Ivana
 */
public class CalculatorGUI extends JPanel{
    private JPanel spremnik;
    private JPanel unos;
    private JTabbedPane tab;
    private JScrollPane sp;
    private JTextField ekran;
    private boolean start = true;
    private double rezultat=0.0;
    private String zadnjaBinarnaOperacija="=";
    private String zadnjaUnarnaOperacija="";
    /**
     * Raspoređujemo gumbe i aktiviramo ActionListeners.
     * @author Ivana
     */
    public CalculatorGUI(){
        unos=new JPanel();
        
        this.setLayout(new BorderLayout());
        unos.setLayout(new BorderLayout());
        
        ekran = new JTextField();
        ekran.setSize(800, 100);
        ekran.setPreferredSize(new Dimension(800,100));
        ekran.setEnabled(true);
        ekran.setFont(ekran.getFont().deriveFont(Font.BOLD, 28f));
        add(ekran, BorderLayout.NORTH);
        spremnik=new JPanel();
        spremnik.setLayout(new GridLayout(4,9));
        
        ActionListener pisanje = new AkcijaPisanja();
        ActionListener brisanje = new AkcijaBrisanja();
        ActionListener bin_naredba = new AkcijaBinarneOperacije();
        ActionListener unar_naredba = new AkcijaUnarneOperacije();
        unos.add(ekran, BorderLayout.NORTH);
        
        String[] zaglavlje={"Funkcija"};
        DefaultTableModel tm=new DefaultTableModel(zaglavlje,0);
    
        dodajGumb("7",pisanje); dodajGumb("8",pisanje);
        dodajGumb("9",pisanje); dodajGumb("/",bin_naredba);
        dodajGumb("sin",unar_naredba); dodajGumb("cos",unar_naredba);
        dodajGumb("tg",unar_naredba); dodajGumb("ctg",unar_naredba);
        dodajGumb("x^2",unar_naredba);
        
        dodajGumb("4",pisanje); dodajGumb("5",pisanje);
        dodajGumb("6",pisanje); dodajGumb("*",bin_naredba);
        dodajGumb("arcsin",unar_naredba); dodajGumb("arccos",unar_naredba);
        dodajGumb("arctg",unar_naredba); dodajGumb("arcctg",unar_naredba);
        dodajGumb("√x",unar_naredba);
        
        dodajGumb("1",pisanje); dodajGumb("2",pisanje);
        dodajGumb("3",pisanje); dodajGumb("-",bin_naredba);
        dodajGumb("log(x)",unar_naredba); dodajGumb("ln(x)",unar_naredba);
        dodajGumb("D",brisanje); dodajGumb("C",brisanje);
        dodajGumb("π",pisanje);
        
        dodajGumb("0",pisanje); dodajGumb(".",pisanje);
        dodajGumb("=",bin_naredba); dodajGumb("+",bin_naredba);
        dodajGumb("10^x",unar_naredba); dodajGumb("e^x",unar_naredba);
        dodajGumb("CE",brisanje); dodajGumb("%",unar_naredba);
        dodajGumb("e",pisanje);
        
        
        unos.add(spremnik, BorderLayout.CENTER);
        
        tab=new JTabbedPane();
        tab.add("Unos",unos);
        this.add(tab);
        
    }
    /**
     * ActionListener odgovoran za operacije pisanja.
     * @author Ivana
     */
    private class AkcijaPisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            if(start){
                ekran.setText("");
                start=false;
            }
            if("π".equals(unos)){
                String pi=Double.toString(Math.PI);
                ekran.setText(ekran.getText()+pi);
            }else if("e".equals(unos)){
                String const_e=Double.toString(Math.E);
                ekran.setText(ekran.getText()+const_e);
            }else
                ekran.setText(ekran.getText()+unos);
        }
    }
    /**
     * ActionListener odgovoran za operacije brisanja.
     * @author Ivana
     */
    private class AkcijaBrisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            switch (unos) {
                case "D":
                    String str = ekran.getText();
                    if("".equals(str))
                        break;
                    if(str.length()>1)
                        ekran.setText(str.substring(0, str.length()-1));
                    else 
                        ekran.setText("");
                    break;
                case "CE":
                    ekran.setText("");
                    break;
                case "C":
                    start = true;
                    rezultat=0.0;
                    zadnjaBinarnaOperacija="=";
                    zadnjaUnarnaOperacija="";
                    ekran.setText("");
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * Metoda za dodavanje gumbi.
     * @param oznaka tekst gumba
     * @param slusac ACtionListener
     * @author Ivana
     */
    private void dodajGumb(String oznaka, ActionListener slusac){
        JButton gumb = new JButton(oznaka);
        
        gumb.addActionListener(slusac);
        spremnik.add(gumb);
    }
    /**
     * ActionListener odgovoran za binarne operacije.
     * @author Ivana
     */
    private class AkcijaBinarneOperacije implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            String operacija = event.getActionCommand();
            if(start){
                if(operacija.equals("-")){
                    ekran.setText(operacija);
                    start=false;
                }
                else 
                    zadnjaBinarnaOperacija=operacija;
            }
            else{
                try{
                    racunaj(Double.parseDouble(ekran.getText()));
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(spremnik, "Niste unesli broj.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                zadnjaBinarnaOperacija=operacija;
                start=true;
            }
        }
        
        public void racunaj(double x){
            switch (zadnjaBinarnaOperacija) {
                case "+":
                    rezultat+=x;
                    break;
                case "-":
                    rezultat-=x;
                    break;
                case "*":
                    rezultat*=x;
                    break;
                case "/":
                    rezultat/=x;
                    break;
                case "=":
                    rezultat=x;
                    break;
                default:
                    break;
            }
            ekran.setText(""+rezultat);
        }
    }
    /**
     * ActionListener odgovoran za unarne operacije.
     * @author Ivana
     */
    private class AkcijaUnarneOperacije implements ActionListener{
        double unaryResult=rezultat;
        @Override
        public void actionPerformed(ActionEvent event) {
            String operacija = event.getActionCommand();
            zadnjaUnarnaOperacija=operacija;
            try{
                racunaj(Double.parseDouble(ekran.getText()));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(spremnik, "Niste unesli broj.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            start=true;
        }
        
        public void racunaj(double x){
            switch (zadnjaUnarnaOperacija) {
                case "sin":
                    unaryResult=Math.sin(x);
                    break;
                case "arcsin":
                    unaryResult=Math.asin(x);
                    break;
                case "cos":
                    unaryResult=Math.cos(x);
                    break;
                case "arccos":
                    unaryResult=Math.acos(x);
                    break;
                case "tg":
                    unaryResult=Math.tan(x);
                    break;
                case "arctg":
                    unaryResult=Math.atan(x);
                    break;
                case "ctg":
                    unaryResult=1/Math.tan(x);
                    break;
                case "arcctg":
                    unaryResult=1/Math.atan(x);
                    break;
                case "1/x":
                    unaryResult=1/x;
                    break;
                case "ln(x)":
                    unaryResult=Math.log(x);
                    break;
                case "log(x)":
                    unaryResult=Math.log10(x);
                    break;
                case "10^x":
                    unaryResult=Math.pow(10,x);
                    break;
                case "e^x":
                    unaryResult=Math.pow(Math.E, x);
                    break;
                case "%":
                    unaryResult=x/100;
                    break;
                case "x^2":
                    unaryResult=x*x;
                    break;
                case "√x":
                    unaryResult=Math.sqrt(x);
                    break;
                default:
                    break;
            }
            switch (zadnjaBinarnaOperacija) {
                case "+":
                    rezultat+=unaryResult;
                    break;
                case "-":
                    rezultat-=unaryResult;
                    break;
                case "*":
                    rezultat*=unaryResult;
                    break;
                case "/":
                    rezultat/=unaryResult;
                    break;
                case "=":
                    rezultat=unaryResult;
                    break;
                default:
                    break;
            }
            ekran.setText(""+rezultat);
        }
    }
}
