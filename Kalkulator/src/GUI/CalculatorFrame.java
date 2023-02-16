/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Konstrukcija prozora za kalkulatore
 * @author Dorotea, Ivana
 */
public class CalculatorFrame extends JFrame implements ActionListener{
    JMenuBar menubar;
    JMenu odaberi;
    JMenuItem standardni;
    JMenuItem graficki;
    JMenuItem polinomi;
    CardLayout cl=new CardLayout();
    private JPanel glavni; //glavni spremnik
    private JPanel grafovi; //graficki prikaz unesenih funkcija
    private JPanel stan; //standardni kalkulator
    private JPanel pol;
    /**
     * Inicijalizacija komponenti
     * @author Dorotea
     */
    public CalculatorFrame(){
        setTitle("Kalkulator");
        stan = new CalculatorGUI();
        stan.setSize(800,800);
        stan.setPreferredSize(new Dimension(800,800));
        
        grafovi=new GraphingGUI();
        grafovi.setSize(800,800);
        grafovi.setPreferredSize(new Dimension(800,800));
        
        pol=new PolynomialGUI();
        pol.setSize(800,1000);
        pol.setPreferredSize(new Dimension(800,1000));
        
        //prazan spremnik, ciji cemo layout postaviti na CardLayout
        glavni=new JPanel();
        glavni.setSize(800,800);
        glavni.setPreferredSize(new Dimension(800,800));
        
        glavni.setLayout(cl);
        glavni.add(stan, "Standardni kalkulator");
        glavni.add(grafovi,"Grafički kalkulator");
        glavni.add(pol,"Polinomni kalkulator");
        cl.show(glavni, "Standardni kalkulator");
        
        add(glavni);
        
        menubar=new JMenuBar();
        this.setJMenuBar(menubar);
        odaberi=new JMenu("Menu");
        menubar.add(odaberi);
        
        standardni=new JMenuItem("Standardni kalkulator");
        graficki=new JMenuItem("Grafički kalkulator");
        polinomi=new JMenuItem("Polinomni kalkulator");
       
        odaberi.add(standardni);
        odaberi.addSeparator();
        odaberi.add(graficki);
        odaberi.addSeparator();
        odaberi.add(polinomi);
              
        standardni.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(glavni, "Standardni kalkulator");
            }
        });
        graficki.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(glavni, "Grafički kalkulator");
            }
        });
        polinomi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(glavni, "Polinomni kalkulator");
            }
        });
        
        this.setResizable(false);
        pack();
    }
    /**
     * Obrada događaj.
     * @param e događaj
     * @author Dorotea
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==standardni){
            //System.out.println("Odabrali ste standardni kalkulator."); 
        }else if(e.getSource()==graficki){
            //System.out.println("Odabrali ste grafički kalkulator.");
        }else if(e.getSource()==pol){
            //System.out.println("Odabrali ste polinomni kalkulator.");
        }
    }
    
}