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
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * GUI made by hand
 * @author Ivana
 */
public class CalculatorGUI extends JPanel{
    private JPanel spremnik;
    private JPanel memorija;
    private JPanel unos;
    private JTabbedPane tab;
    private JScrollPane sp;
    private JTextField ekran;
    private boolean start = true;
    private double rezultat=0.0;
    private String zadnjaBinarnaOperacija="=";
    private String zadnjaUnarnaOperacija="";
    private Vector<String> vec;
    private JTable tablica;
    JButton jednako = new JButton();
    /**
     * @Ivana
     * u konstruktoru raspoređujemo gumbe i aktiviramo ActionListeners
     */
    public CalculatorGUI(){
        unos=new JPanel();
        memorija=new JPanel();
        
        this.setLayout(new BorderLayout());
        unos.setLayout(new BorderLayout());
        memorija.setLayout(new BorderLayout());
        
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
        unos.add(ekran, BorderLayout.NORTH);
        
        String[] zaglavlje={"Funkcija"};
        DefaultTableModel tm=new DefaultTableModel(zaglavlje,0);
        tablica=new JTable(tm);
        tablica.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        sp=new JScrollPane(tablica);
    
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
        unos.add(spremnik, BorderLayout.CENTER);
        
        tab=new JTabbedPane();
        tab.add("Unos",unos);
        tab.add("Memorija",sp);
        this.add(tab);
        
        tablica.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                ekran.setText(tablica.getValueAt(tablica.getSelectedRow(), 0).toString());
            }
        });
    }
    /**
     * @Ivana
     * InputTipkovnice potreban je za registriranje brojeva unesenih putem tipkovnice
     * umjesto putem gumbi
     */
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
        /**
         * @Ivana
         * sljedeca je funkcija pokusaj implementacije funkcije da je pritisak tipke enter
         * ekvivalentan pritisku gumba za jednakost, ali ne radi
         * @param event 
         */
        @Override
        public void keyPressed(KeyEvent event) {
            if(event.getKeyChar()=='\n'){
                if(start){
                //System.out.println("Sad je start true");
                zadnjaBinarnaOperacija="=";
                }
                else{
                    
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
    /**
     * @Ivana
     * implementacija tipki D, CE i C
     */
    private class AkcijaBrisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            switch (unos) {
                case "D"://brise se samo zadnja znamenka
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
    /**
     * @Ivana
     * metoda za dodavanje gumbi
     * @param oznaka
     * @param slusac 
     */
    private void dodajGumb(String oznaka, ActionListener slusac){
        JButton gumb = new JButton(oznaka);
        if(oznaka=="=")
            jednako=gumb;
        gumb.addActionListener(slusac);
        spremnik.add(gumb);
    }
    /**
     * @Ivana
     * implementacija binarnih operacija
     */
    private class AkcijaBinarneOperacije implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            String operacija = event.getActionCommand();
            if(start){
                //System.out.println("Sad je start true");
                if(operacija.equals("-")){
                    ekran.setText(operacija);
                    start=false;
                }else if(operacija.endsWith("=")){
                    vec=new Vector<>();
                    vec.add(Double.toString(rezultat));
                    DefaultTableModel tm=(DefaultTableModel) tablica.getModel();
                    tm.addRow(vec);
                }
                else zadnjaBinarnaOperacija=operacija;
            }
            else{
                //System.out.println("Sad je start false i promijenit cu ga true");
                racunaj(Double.parseDouble(ekran.getText()));
                if(operacija.endsWith("=")){
                    vec=new Vector<>();
                    vec.add(Double.toString(rezultat));
                    DefaultTableModel tm=(DefaultTableModel) tablica.getModel();
                    tm.addRow(vec);
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
     * @Ivana
     * implementacija unarnih opracija
     */
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
