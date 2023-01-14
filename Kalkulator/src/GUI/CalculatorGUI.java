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
    public CalculatorGUI(){
        setLayout(new BorderLayout());
        ekran = new JTextField();
        ekran.setSize(800, 100);
        ekran.setPreferredSize(new Dimension(800,100));
        ekran.setEnabled(false);
        ekran.setFont(ekran.getFont().deriveFont(Font.BOLD, 28f));
        add(ekran, BorderLayout.NORTH);
        spremnik=new JPanel();
        spremnik.setLayout(new GridLayout(4,8));
        
        ActionListener pisanje = new AkcijaPisanja();
        ActionListener bin_naredba = new AkcijaBinarneOperacije();
        ActionListener unar_naredba = new AkcijaUnarneOperacije();
    
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
        dodajGumb("?",bin_naredba); dodajGumb("?",bin_naredba);
        
        dodajGumb("0",pisanje); dodajGumb(".",pisanje);
        dodajGumb("=",bin_naredba); dodajGumb("+",bin_naredba);
        dodajGumb("10^x",unar_naredba); dodajGumb("e^x",unar_naredba);
        dodajGumb("?",bin_naredba); dodajGumb("?",bin_naredba);
        add(spremnik, BorderLayout.CENTER);
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
    
    private void dodajGumb(String oznaka, ActionListener slusac){
        JButton gumb = new JButton(oznaka);
        gumb.addActionListener(slusac);
        spremnik.add(gumb);
    }
    
    private class AkcijaBinarneOperacije implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            String operacija = event.getActionCommand();
            if(start){
                if(operacija.equals("-")){
                    ekran.setText(operacija);
                    start=false;
                }
                else zadnjaBinarnaOperacija=operacija;
            }
            else{
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
            
            if(start){
                /*if(operacija.equals("-")){
                    ekran.setText(operacija);
                    start=false;
                }
                else*/ 
                zadnjaUnarnaOperacija=operacija;
                
                ekran.setText(operacija);
            }
            else{
                racunaj(Double.parseDouble(ekran.getText()));
                zadnjaUnarnaOperacija=operacija;
                start=true;
            }
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
                    unaryResult=1/Math.log(x);
                    break;
                case "log(x)":
                    unaryResult=1/Math.log10(x);
                    break;
                case "10^x":
                    unaryResult=1/Math.pow(10,x);
                    break;
                case "e^x":
                    unaryResult=1/Math.pow(Math.E, x);
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
