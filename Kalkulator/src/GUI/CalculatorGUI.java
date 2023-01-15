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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * GUI made by hand
 * @author Ivana
 */
public class CalculatorGUI extends JPanel{
    private JPanel spremnik;
    private JTextField ekran;
    private boolean start = true;
    private double rezultat=0.0;
    private String zadnjaBinarnaOperacija="=";
    private String zadnjaUnarnaOperacija="";
    JButton jednako = new JButton();
    public CalculatorGUI(){
        setLayout(new BorderLayout());
        ekran = new JTextField();
        ekran.setSize(800, 100);
        ekran.setPreferredSize(new Dimension(800,100));
        //watch out
        ekran.setEnabled(true);
        ekran.setFont(ekran.getFont().deriveFont(Font.BOLD, 28f));
        add(ekran, BorderLayout.NORTH);
        spremnik=new JPanel();
        spremnik.setLayout(new GridLayout(4,8));
        
        ActionListener pisanje = new AkcijaPisanja();
        ActionListener brisanje = new AkcijaBrisanja();
        ActionListener bin_naredba = new AkcijaBinarneOperacije();
        ActionListener unar_naredba = new AkcijaUnarneOperacije();
        KeyListener s_tipkovnice = new InputTipkovnice();
        ekran.addKeyListener(s_tipkovnice);
    
        dodajGumb("7",pisanje); dodajGumb("8",pisanje);
        dodajGumb("9",pisanje); dodajGumb("/",bin_naredba);
        dodajGumb("sin",unar_naredba); dodajGumb("cos",unar_naredba);
        dodajGumb("tg",unar_naredba); dodajGumb("ctg",unar_naredba);
        
        dodajGumb("4",pisanje); dodajGumb("5",pisanje);
        dodajGumb("6",pisanje); dodajGumb("*",bin_naredba);
        dodajGumb("arcsin",unar_naredba); dodajGumb("arccos",unar_naredba);
        dodajGumb("arctg",unar_naredba); dodajGumb("arcctg",unar_naredba);
        
        dodajGumb("1",pisanje); dodajGumb("2",pisanje);
        dodajGumb("3",pisanje); dodajGumb("-",bin_naredba);
        dodajGumb("log(x)",unar_naredba); dodajGumb("ln(x)",unar_naredba);
        dodajGumb("D",brisanje); dodajGumb("C",brisanje);
        
        dodajGumb("0",pisanje); dodajGumb(".",pisanje);
        dodajGumb("=",bin_naredba); dodajGumb("+",bin_naredba);
        dodajGumb("10^x",unar_naredba); dodajGumb("e^x",unar_naredba);
        dodajGumb("CE",brisanje); dodajGumb("%",unar_naredba);
        add(spremnik, BorderLayout.CENTER);
    }
    private class InputTipkovnice implements KeyListener{
        String unos="";
        @Override
        public void keyTyped(KeyEvent event) {
            unos += event.getKeyChar();
            System.out.println("Unos: "+unos);
            if(start){
                ekran.setText("");
                start=false;
            }
        }

        @Override
        public void keyPressed(KeyEvent event) {
            //System.out.println(event.paramString());
            /**
             * registriramo da je pritisnut backspace
             */
            if(event.getKeyCode()==8){
                String str = ekran.getText();
                    //nema unosa pa nemamo što brisati
                    if("".equals(str))
                        return;
                    //na ekran stavljam broj bez zadnje znamenke
                    if(str.length()>1)
                        ekran.setText(str.substring(0, str.length()));
                    else 
                        ekran.setText("");
            }else if(event.getKeyCode()==10){
                /**
                 * ovo zasad ne radi, uopće ne registrira pritisak enter-a kao keyPressed neg sam kao  keyTped :/
                 */
                if(start){
                //System.out.println("Sad je start true");
                zadnjaBinarnaOperacija="=";
                }
                else{
                    String rezultat = ekran.getText();
                    ekran.setText(""+rezultat);
                    zadnjaBinarnaOperacija="=";
                    start=true;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent event) {
        }
        
    }
    private class AkcijaPisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            if(start){
                //System.out.println("sad je start true");
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
    
    private void dodajGumb(String oznaka, ActionListener slusac){
        JButton gumb = new JButton(oznaka);
        if(oznaka=="=")
            jednako=gumb;
        gumb.addActionListener(slusac);
        spremnik.add(gumb);
    }
    
    private class AkcijaBinarneOperacije implements ActionListener{

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
        private class AkcijaUnarneOperacije implements ActionListener{
            double unaryResult=rezultat;
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
