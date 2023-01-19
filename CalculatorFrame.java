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
 *
 * @author Ivana
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
    
    public CalculatorFrame(){
        setTitle("Kalkulator");
        stan = new CalculatorGUI();
        stan.setSize(800,400);
        stan.setPreferredSize(new Dimension(800,400));
        //naknadno ga je potrebno postaviti, za sada neka bude prazan
        grafovi=new GraphingGUI();
        grafovi.setSize(800,400);
        grafovi.setPreferredSize(new Dimension(800,400));
        
        //naknadno ga je potrebno postaviti, za sada neka bude prazan
        pol=new JPanel();
        pol.setSize(800,400);
        pol.setPreferredSize(new Dimension(800,400));
        
        //prazan spremnik
        glavni=new JPanel();
        glavni.setSize(800,400);
        glavni.setPreferredSize(new Dimension(800,400));
        
        glavni.setLayout(cl);
        //nazive mozemo kasnije promijeniti, ja sam stavila za sada
        glavni.add(stan, "Standardni kalkulator");
        glavni.add(grafovi,"Grafički kalkulator");
        glavni.add(pol,"Polinomi");
        cl.show(glavni, "Standardni kalkulator");
        
        add(glavni);
        pack();
        
        menubar=new JMenuBar();
        this.setJMenuBar(menubar);
        odaberi=new JMenu("Menu");
        menubar.add(odaberi);
        
        standardni=new JMenuItem("Standardni kalkulator");
        graficki=new JMenuItem("Grafički kalkulator");
        polinomi=new JMenuItem("Polinomi");
        
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
                cl.show(glavni, "Polinomi");
            }
        });
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==standardni){
            System.out.println("Odabrali ste standardni kalkulator."); 
        }else if(e.getSource()==graficki){
            System.out.println("Odabrali ste grafički kalkulator.");
        }
    }
    
}
