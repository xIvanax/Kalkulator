/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Grapher.Expressions.Function;
import Grapher.Parser.ExpressionParser;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Ivana
 */
public class PolynomialGUI extends JPanel{
    private JPanel spremnik;
    private JPanel unos;
    private JPanel prikaz;
    private String textBox;
    private IntegratedDrawFunctionScreen nacrtaj;
    /**
     * sad imamo tri ekrana (jedan za prvi polinom, jedan za drugi polinom i jedan za display rezultata)
     * @Ivana
     * */
    public JTextField ekran1;
    public JTextField ekran2;
    public JTextField display;
    public JLabel jLabel1;
    public JLabel jLabel2;
    public JLabel jLabel3;
    //polyOp vrijednost 0 je zbrajanje, 1 oduzimanje, 2 množenje
    private int polyOp;
    //tu cu spremati clanove polinoma
    private ArrayList<String> clanovi = new ArrayList<>();
    
    
    private Function function;
    private ExpressionParser parser;
    JTabbedPane tab;
    
    private boolean start = true;
    private ArrayList<String> funkcija;
    private String zadnjaBinarnaOperacija="=";
    private String screen="";
    
    private double evaluatedFunction;
    private String evaluateAt="";
    
    public PolynomialGUI(){
        nacrtaj=new IntegratedDrawFunctionScreen();
        unos=new JPanel();
        prikaz=new JPanel();
        
        //omogucava resize
        this.setLayout(new BorderLayout());
        unos.setLayout(new FlowLayout(FlowLayout.LEFT));
        prikaz.setLayout(new BorderLayout());
        
        //slicno kao u CalCulatorGUI implementiramo unos funkcije koju crtamo
        //napravljene su neke promjene, npr. nema vise %, te su dodane nove mogucnosti
        ekran1 = new JTextField();
        ekran1.setText("");
        ekran1.setSize(770, 50);
        ekran1.setPreferredSize(new Dimension(770,50));
        ekran1.setEnabled(true);
        ekran1.setFont(ekran1.getFont().deriveFont(Font.BOLD, 28f));
        
        ekran2 = new JTextField();
        ekran2.setText("");
        ekran2.setSize(770, 50);
        ekran2.setPreferredSize(new Dimension(770,50));
        ekran2.setEnabled(true);
        ekran2.setFont(ekran2.getFont().deriveFont(Font.BOLD, 28f));
        
        display = new JTextField();
        display.setText("");
        display.setSize(770, 50);
        display.setPreferredSize(new Dimension(770,50));
        display.setEnabled(true);
        display.setFont(display.getFont().deriveFont(Font.BOLD, 28f));
        
        jLabel1=new JLabel("Prvi polinom:");
        jLabel2=new JLabel("Drugi polinom:");
        jLabel3=new JLabel("Rezultat:");
        
        unos.add(jLabel1);
        unos.add(ekran1);
        unos.add(jLabel2);
        unos.add(ekran2);
        unos.add(jLabel3);
        unos.add(display);
        
        spremnik=new JPanel();
        spremnik.setSize(770, 170);
        spremnik.setPreferredSize(new Dimension(770,170));
        /**
         * ja sam dodala tu gumbe za spremanje polinoma, deriviranje i potenciranje sam da od nekud krenem,
         * vidim da imamo zaseban 
         */
        spremnik.setLayout(new GridLayout(4,7));
        
        ActionListener pisanje = new PolynomialGUI.AkcijaPisanja();
        ActionListener brisanje = new PolynomialGUI.AkcijaBrisanja();
        ActionListener bin_naredba = new PolynomialGUI.AkcijaBinarneOperacije();
        
        dodajGumb("7",pisanje);
        dodajGumb("8",pisanje); 
        dodajGumb("9",pisanje);
        dodajGumb("/",bin_naredba);
        dodajGumb("(",pisanje);
        dodajGumb(")",pisanje);
        dodajGumb("D",brisanje);
        
        
        dodajGumb("4",pisanje);
        dodajGumb("5",pisanje); 
        dodajGumb("6",pisanje);
        dodajGumb("*",bin_naredba);
        dodajGumb("π",pisanje); 
        dodajGumb("e",pisanje); 
        dodajGumb("C",brisanje);
        
        dodajGumb("1",pisanje);
        dodajGumb("2",pisanje); 
        dodajGumb("3",pisanje);
        dodajGumb("-",bin_naredba); 
        dodajGumb("x",pisanje);
        dodajGumb("x^y",bin_naredba);
        dodajGumb("x^(1/y)",bin_naredba);
       
        //obrisan gumb CE jer nema smisla u ovom kontekstu/dizajnu kalkulatora (ja mislim, ak ti mislis da ima
        //lako ga vratim - sad sam ga zamijenila s gumbom eval pomocu kojeg cemo napravit evaluation funckije
        //zapravo ce funkcionirat isto kao "=" u obicnom kalkulatoru
        /**
         * ovaj komentar iznad napisala
         * @Ivana
         */
        
        dodajGumb("0",pisanje);
        dodajGumb(".",pisanje);
        dodajGumb("eval",bin_naredba);
        dodajGumb("+",bin_naredba);
        dodajGumb("derivative",bin_naredba); 
        /**
         * pritiskom na gumb "poly op" se zbrajaju, oduzimaju ili množe polinomi
         */
        dodajGumb("poly op",bin_naredba);
        dodajGumb("draw",bin_naredba);   
        
        
        unos.add(spremnik);
        prikaz.add(nacrtaj, BorderLayout.CENTER);
        
        tab=new JTabbedPane();
        tab.add("Unos",unos);
        tab.add("Graf",prikaz);
        /**
         * kaze da ovo nije sigurna operacija, ali ne znam gdje drugdje staviti (moglo
         * bi bit problema s testovima, a on je to stavio u main, al ja ne znam kak to stavit u main)
         * @Ivana
         */
        new Thread(nacrtaj).start();
        
        this.add(tab);
    }
    
    private void dodajGumb(String oznaka, ActionListener slusac){
        JButton gumb = new JButton(oznaka);
        gumb.addActionListener(slusac);
        spremnik.add(gumb);
    }
    /**
     * @Ivana
     * @return što je trenutno na ekran1u
     */
    public String readScreen(){
        if(this.ekran1==null)
            return "";
        return ekran1.getText();
    }
    
    private class AkcijaPisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String unos=event.getActionCommand();
            if(start){
                textBox=ekran1.getText();
                start=false;
            }
            
            ekran1.setText(ekran1.getText()+unos);
            
            if(unos.equals("π")){
                String pi=Double.toString(Math.PI);
                screen+=pi;
                ekran1.setText(screen);
                textBox=screen;
            }else if(unos.equals("e")){
                String const_e=Double.toString(Math.E);
                screen+=const_e;
                ekran1.setText(screen);
                textBox=screen;
            }else if(unos.equals("=")){
                function=parser.parse(textBox);
                    if(function==null){
			textBox="";
                    }
                screen="";
            }else{
                screen+=unos;
                ekran1.setText(screen);
                
                textBox=screen;
            }
        }
    }
    private class AkcijaBrisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            switch (unos) {
                case "D":
                    //brise se samo zadnji znak
                    String str = ekran1.getText();
                    //nema unosa pa nemamo što brisati:
                    if("".equals(str))
                        break;
                    //na ekran1 stavljam sadrzaj ekran1a bez zadnjeg znaka i updateam "screen":
                    if(str.length()>1){
                        ekran1.setText(str.substring(0, str.length()-1));
                        screen=ekran1.getText();
                    }else{
                        ekran1.setText("");
                        screen="";
                    }
                    //System.out.println("Nakon pritiske gumba D screen="+screen);
                    break;
                case "C":
                    //brišem cijeli input kalkulatora (back to square one)
                    start = true;
                    //rezultat=0.0;
                    zadnjaBinarnaOperacija="=";
                    screen="";
                    ekran1.setText("");
                    break;
                default:
                    break;
            }
        }
    }
    
    private class AkcijaBinarneOperacije implements ActionListener{  
        @Override
        public void actionPerformed(ActionEvent event) {
            String op=event.getActionCommand();
            String operacija=operation(op);
            
            /**
             * nisam htjela dirat ovo sa startom jer nisam više sigurna jel nam to uopće treba ovdje 
             * (to je naslijeđeno iz standardnog kalkulatora)
             * ovaj kod što slijedi odnosi se na obradu odabrane operacije zbrajanja, oduzimanja ili množenej polinoma
             * @author Ivana
             */
            if("poly op".equals(operacija)){
                ArrayList<String> clanovi1 = new ArrayList<>();
                ArrayList<String> clanovi2 = new ArrayList<>();
                    
                clanovi.clear();
                clanovi1 = dohvati(ekran1.getText());
                //sam kopiram u pomocnu listu jer se inače prebrišu
                ArrayList<String> clanoviCopy = new ArrayList<>();
                for(String i:clanovi1)
                    clanoviCopy.add(i);
                    
                clanovi.clear();
                clanovi2 = dohvati(ekran2.getText());
                
                if(polyOp==0){
                    ArrayList<String> clanoviRes = polyAdd(clanoviCopy, clanovi2);
                    String res="";
                    if(clanoviRes.isEmpty()==false)
                        res+=clanoviRes.get(0);
                    clanoviRes.remove(0);
                    for(String clan:clanoviRes)
                        if(clan.charAt(0)!='-')
                            res+="+"+clan;
                        else
                            res+=clan;
                    System.out.println(res);
                    display.setText(res);
                }else if(polyOp==1){
                    ArrayList<String> reverse = new ArrayList<>();
                    for(String i:clanovi2){
                        if(i.length()>0)
                            if(i.charAt(0)=='-')
                                reverse.add(i.substring(1));
                            else
                                reverse.add("-"+i);
                    }
                    
                    ArrayList<String> clanoviRes = polyAdd(clanoviCopy, reverse);
                    String res="";
                    if(clanoviRes.isEmpty()==false)
                        res+=clanoviRes.get(0);
                    clanoviRes.remove(0);
                    for(String clan:clanoviRes)
                        if(clan.charAt(0)!='-')
                            res+="+"+clan;
                        else
                            res+=clan;
                    System.out.println(res);
                    display.setText(res);
                }else{
                    ArrayList<String> clanoviRes = polyMulti(clanoviCopy, clanovi2);
                    String res="";
                    if(clanoviRes.isEmpty()==false)
                        res+=clanoviRes.get(0);
                    clanoviRes.remove(0);
                    for(String clan:clanoviRes)
                        if(clan.charAt(0)!='-')
                            res+="+"+clan;
                        else
                            res+=clan;
                    System.out.println(res);
                    display.setText(res);
                }
            }else
            if(start){
                //System.out.println("Sad je start true");
                if(operacija.equals("-") && "".equals(ekran1.getText())){
                    ekran1.setText(operacija);
                    start=false;
                    screen="-";
                }else{
                    zadnjaBinarnaOperacija=operacija;
                    
                    //System.out.println("flag 3");
                    if(zadnjaBinarnaOperacija.equals("=")){
                        //System.out.println("flag 1");
                        
                        textBox=ekran1.getText();
                        function=parser.parse(textBox);
                            if(function==null){
                                textBox="";
                            }
                        
                        screen="";
                    }else{
                        ekran1.setText(ekran1.getText()+zadnjaBinarnaOperacija);
                        //System.out.println("***ekran1="+ekran1.getText());
                        screen=ekran1.getText();
                    }
                }
            }else{
                start=true;
                //Sad je start false i promijenit ce se u true
                //System.out.println("start je false i screen = "+screen);
                zadnjaBinarnaOperacija=operacija;
                
                if(zadnjaBinarnaOperacija.equals("=")){
                    //System.out.println("screen nakon pritiska ="+screen);
                   textBox=ekran1.getText();
                   function=parser.parse(textBox);
                        if(function==null){
                            textBox="";
                        }
                   screen="";
                }else{
                    ekran1.setText(ekran1.getText()+zadnjaBinarnaOperacija);
                    //System.out.println("** **ekran1="+ekran1.getText());
                    screen=ekran1.getText();
                }
            }
        }
        
        public String operation(String operacija){
            switch(operacija) {
                case "+":
                    return "+";
                case "-":
                    return "-";
                case "*":
                    return "*";
                case "/":
                    return "/";
                case "draw":
                    return "=";
                case "x^y":
                    return "^";
                case "x^(1/y)":
                    return "^(1/";
                case "eval":
                    evaluateAt = JOptionPane.showInputDialog(spremnik, "Unesite x:", "Evaluacija funkcije", JOptionPane.QUESTION_MESSAGE);
                    Function f=parser.parse(textBox);
                    evaluatedFunction=f.evaluateAt(Double.parseDouble(evaluateAt), 0.0, 0.0);
                    String fja = "f(x) = " + ekran1.getText();
                    String output = fja + "\n" + "f("+evaluateAt+")="+evaluatedFunction;
                    System.out.println("f("+evaluateAt+")="+evaluatedFunction);
                    JOptionPane.showMessageDialog(spremnik, output, "Rezultat evaluacije", JOptionPane.INFORMATION_MESSAGE);
                case "derivative":
                    String ulaz=ekran1.getText();
                    String izlaz=deriviraj(ulaz);
                    ekran1.setText(izlaz);
                    if(izlaz.length()>1)
                        if(izlaz.charAt(izlaz.length()-1)=='+' || izlaz.charAt(izlaz.length()-1)=='-'){
                            ekran1.setText(izlaz.substring(0,izlaz.length()-1));
                        }else{
                            ekran1.setText(izlaz);
                        }
                case "poly op":
                    String[] options = {"zbrajanje polinoma", "oduzimanje polinoma", "množenje polinoma"};
                    polyOp = JOptionPane.showOptionDialog(null, "Odaberite operaciju koju želite izvršiti nad unesenim polinomima:",
                "Opcije",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    //System.out.println(x);
                    return "poly op";
            }
            return "";
        }
        
        /**
         * @Ivana
         * funkcija za dohvacanje clanova u polinomu
         * @param ulaz polinom
         * @return lista clanova polinoma
         */
        public ArrayList<String> dohvati(String ulaz){
            int locationPlus=scanFromRight(ulaz,'+');
            int locationMinus=scanFromRight(ulaz,'-');
            if(locationPlus>locationMinus && locationPlus!=-1){
                String left=ulaz.substring(0,locationPlus);
                String right=ulaz.substring(locationPlus+1,ulaz.length());
                clanovi.add(right);
                dohvati(left);
            }else{
                if(locationMinus!=-1){
                    if(locationMinus==0){
                        String right=ulaz.substring(locationMinus+1,ulaz.length());
                        clanovi.add("-"+right);
                    }else{
                        String left=ulaz.substring(0,locationMinus);
                        String right=ulaz.substring(locationMinus+1,ulaz.length());
                        
                        if(left.isEmpty()){
                            clanovi.add(right);
                        }
                        else{
                            clanovi.add("-"+right);
                            dohvati(left);
                        }
                    } 
                }else{
                    clanovi.add(ulaz);
                }
            }
            return clanovi;
        }
        /**
         * 
         * funkcija vraca informacije o koeficijentu, eksponentu i je li eksponent pozitivan (1) ili je negativan/razlomak (0)
         * @param expr1 
         * @return polje double: na indeksu 0 je koeficijent, 1 je eksponent, 2 je li eksponent pozitivan
         * @author Ivana
         */
        public double[] coefAndExp(String expr1){
            double[] ret = new double[3];
            double coef=0, exp=0, konstanta=0;
            //prvo odredujem koliko iznosi koeficijent
            if(expr1.contains("*")){//dakle sadrzi i *x
                ret[0]=Double.parseDouble(expr1.split("\\*")[0]);
            }else if(expr1.contains("x") && !(expr1.contains("*"))){//ako je koeficijent jednak 1
                ret[0]=1;
            }else if(!(expr1.contains("x") && !(expr1.contains("*")))){//ako je jednak konstanti
                ret[0]=Double.parseDouble(expr1);
                konstanta=1;
            }
            if(konstanta==0){//ako nije konstanta zanima me koji je eksponent
                int jednako=-1;//provjeravam ima li jednak broj otvorenih i zatvorenih zagrada
                if(expr1.contains("(") && expr1.contains(")"))
                    jednako=1;
                else if(!expr1.contains("(") && !expr1.contains(")"))
                    jednako = 0;
                if(jednako==-1){
                    System.out.println("Niste zatvorili zagradu!");
                }
                //slucaj kad imamo zagrade
                if(jednako==1){
                    ret[2]=0;
                    int start=expr1.indexOf("(");
                    int finish=expr1.indexOf(")");

                    String izmeduZagrada = expr1.substring(start+1, finish);
                    
                    int q=-1;
                    if(expr1.substring(start+1, finish).contains(("/"))){
                        q=expr1.substring(start+1, finish).indexOf("/");
                    }

                    int negative=0;//provjera je li eksponent negativan
                    if(expr1.substring(start+1, finish).contains(("-")))
                        negative=1;

                        //slucaj kad je eksponent razlomak:
                    if(q!=-1){
                        double brojnik=Double.parseDouble(izmeduZagrada.split("/")[0]);
                        double nazivnik=Double.parseDouble(izmeduZagrada.split("/")[1]);

                        if(negative==1){
                            exp=-(brojnik/nazivnik);
                        }else if(negative==0){
                            exp=brojnik/nazivnik;
                        }
                    }else{//slucaj kad eksponent nije razlomak:
                        if(negative==1){
                            exp=-(Double.parseDouble(expr1.substring(start+1, finish)));
                        }else{
                            exp=Double.parseDouble(expr1.substring(start+1, finish));
                        }
                    }
                }else{//slucaj kad nemamo zagrade u eskponentu
                    ret[2]=1;
                    if(expr1.contains("^")){//x^nesto
                        exp=Double.parseDouble(expr1.split("\\^")[1]);
                    }else{//samo je napisan x, dakle nema potenciju, pa stavljamo da je eksponent jednak 1
                        exp=1;
                    }
                }
            }
            ret[1]=exp;
            return ret;
        }
        /**
         * fja kao argumente uzima clanove dva polinoma i zbraja te polinome
         * @param poly1 clanovi prvog polinoma
         * @param poly2 clanovi drugog polinoma
         * @return rezultat zbrajanja poly1 i poly2 u obliku liste clanova
         * @author Ivana
         */
        public ArrayList<String> polyAdd(ArrayList<String> poly1, ArrayList<String> poly2){
            String res="";//tu cemo spremiti rezultatntni polinom
            ArrayList<String> clanoviRes = new ArrayList<String>(); //tu cemo spremat clanove reuzltantnog polinoma koje cemo onda prepisati u res
            double coef=0, exp=0, konstanta=0;
            double coef2=0, exp2=0, konstanta2=0;
            int flag=0; //bit ce 1 ako nadem podudaranje nekih koeficijenata
            int eksponentJeRazlomakIliNegativan=0;
            
            //System.out.println("clanovi poly1: "+poly1);
            //System.out.println("clanovi poly2: "+poly2);
            for(String expr1:poly1){
                //određujem koeficijent i eksponent za clan expr1 polinoma poly1
                double info[] = new double[3];
                info = coefAndExp(expr1);
                coef=info[0];
                exp=info[1];
                //sad istu stvar radim za drugi polinom
                for(String expr2:poly2){
                    double info2[] = new double[3];
                    info2 = coefAndExp(expr2);
                    coef2=info2[0];
                    exp2=info2[1];
                //sad znam koeficijente i eksponente svakog clana polinoma
                //provjeravam imamo li podudaranje eksponenata i ako imamo onda zbrajam eksponente i dodajem u novi polinom
                    if(exp==exp2){
                        flag=1; //pronasla sam podudaranje eksponenta exp iz prvog polinoma s nekim iz drugog
                        double newCoef=coef+coef2;
                        
                        if(eksponentJeRazlomakIliNegativan==1){
                            clanoviRes.add(Double.toString(newCoef)+"*x^("+exp+")");
                            eksponentJeRazlomakIliNegativan=0;
                        }
                        else{
                            clanoviRes.add(Double.toString(newCoef)+"*x^"+exp+"");
                        }
                    }
                }
                    if(flag==0){//nisam nasla nijedno poklapanje pa ubacujem clan prvog polinoma u rezultat takav kakav je
                        //na kraju jos moram provjeriti trebam li ubaciti neke clanove iz drugog polinoma u rezultat
                        if(exp==0)
                            clanoviRes.add(Double.toString(coef));
                        else if(coef!=1)
                                if(info[2]==1)
                                    clanoviRes.add(coef+"*x^"+exp);
                                else
                                    clanoviRes.add(coef+"*x^("+exp+")");
                            else
                                if(info[2]==1)
                                    clanoviRes.add("x^"+exp);
                                else
                                    clanoviRes.add("x^("+exp+")");
                    }else
                        flag=0;
            }
            //sad opet sve to isto radim sam sa clanoviRes umjesto poly1
            flag=0; 
            konstanta=0; konstanta2=0;
            for(String expr1:poly2){
                double info[] = new double[3];
                info = coefAndExp(expr1);
                coef=info[0];
                exp=info[1];
                for(String expr2:clanoviRes){
                    double info2[] = new double[3];
                    info2 = coefAndExp(expr2);
                    exp2=info2[1];
                    if(exp==exp2){
                        flag=1; 
                    }
                }
                if(flag==0){
                if(exp==0)
                    clanoviRes.add(Double.toString(coef));
                    else if(coef!=1)
                            if(info[2]==1)
                                clanoviRes.add(coef+"*x^"+exp);
                            else
                                clanoviRes.add(coef+"*x^("+exp+")");
                        else
                            if(info[2]==1)
                                clanoviRes.add("x^"+exp);
                            else
                                clanoviRes.add("x^("+exp+")");
                }else
                    flag=0;
            }
            
            return clanoviRes;
        }
        /**
         * funkcija uzima dva polinoma reprezentirana preko svoje liste clanova i vraca njihov umnozak u obliku liste clanova
         * @param poly1 clanovi prvog polinoma
         * @param poly2 clanovi drugog polinoma
         * @return rezultat mnozenja u obliku liste clanova
         * @author Ivana
         */
        public ArrayList<String> polyMulti(ArrayList<String> poly1, ArrayList<String> poly2){
            ArrayList<String> resClanovi = new ArrayList<>();
            ArrayList<ArrayList<String>> svi = new ArrayList<>();
            double coef, exp, coef2, exp2;
            for(String expr1:poly1){
                double info[] = new double[3];
                info = coefAndExp(expr1);
                coef=info[0];
                exp=info[1];
                ArrayList<String> help = new ArrayList<>();
                for(String expr2:poly2){
                    double info2[] = new double[3];
                    info2 = coefAndExp(expr2);
                    coef2=info2[0];
                    exp2=info2[1];
                    String str;
                    //System.out.println("mnozim "+expr1+" i "+expr2);
                    //System.out.println("koef i exp od expr1 "+coef+" i "+exp);
                    //System.out.println("koef i exp od expr2 "+coef2+" i "+exp2);
                    if(exp*exp2<0)
                        if((coef*coef2)!=1.0)
                            str = (coef*coef2) + "*x^("+(exp+exp2)+")";
                        else
                            str = "x^("+(exp+exp2)+")";
                    else
                        if((coef*coef2)!=1.0)
                            str = (coef*coef2) + "*x^"+(exp+exp2);
                        else
                            str = "x^("+(exp+exp2)+")";
                    //System.out.println("dobivam "+str);
                    help.add(str);
                }
                svi.add(help);
            }
            for(String i:svi.get(0))
                resClanovi.add(i);
            
            //System.out.println("resClanovi="+resClanovi);
            svi.remove(0);
            for(ArrayList<String> i:svi){
                resClanovi=polyAdd(i,resClanovi);
                //System.out.println("resClanovi="+resClanovi);
            }
            return resClanovi;
        }
        /**
        *@author Dorotea
        * Sljedeća funkcija će po konstrukciji biti slična funkciji doOrderOfOperations iz EXPressionParser-a
        * Vraćat će String koji će sadržavati derivaciju unesenog polinoma
        **/
        public String deriviraj(String ulaz){
            String rezultat="";
            
            int location=scanFromRight(ulaz,'+');
            if(location!=-1){
                String left=ulaz.substring(0,location);
                String right=ulaz.substring(location+1,ulaz.length());
                rezultat+=deriviraj(left)+"+"+Der(right);
            }else{
                location=scanFromRight(ulaz,'-');
                if(location!=-1){
                    if(location==0){
                        String right=ulaz.substring(location+1,ulaz.length());
                        rezultat+="-"+Der(right);
                    }else{
                        String left=ulaz.substring(0,location);
                        String right=ulaz.substring(location+1,ulaz.length());
                        if(left.isEmpty()){
                            rezultat+=Der(right);
                        }
                        else{
                            rezultat+=deriviraj(left)+"-"+Der(right);
                        }
                    } 
                }else{
                    rezultat+=Der(ulaz);
                }
            }
            return rezultat;
        }
        
        public int scanFromRight(String ulaz,char token){
            int openParentheses=0;
            for(int i=ulaz.length()-1; i>=0; i--){
                if(ulaz.charAt(i)==')'){
                       openParentheses++;
                }else if(ulaz.charAt(i)=='('){
                    openParentheses--;
                }else if(ulaz.charAt(i)==token && openParentheses==0){
                    return i;
                }
            }
            return -1;
        }
        
        public String Der(String expr){
            String res="";
            double coef=0, exp=0;
            //prvo odredujem koliko iznosi koeficijent
            int location=0;
            if(expr.contains("*")){//dakle sadrzi i *x
                location=expr.indexOf("*");
                coef=Double.parseDouble(expr.substring(0, location));
            }else if(expr.contains("x") && !(expr.contains("*"))){//ako je koeficijent jednak 1
                coef=1;
            }else if(!(expr.contains("x") && !(expr.contains("*")))){//ako je jednak konstanti
                coef=Double.parseDouble(expr);
                return res; //derivacija konstante je 0, pa ostavljam da string ostaje prazan
            }

            int parenthesis=0, jednako=0;//provjeravam ima li jednak broj otvorenih i zatvorenih zagrada
            if(expr.contains("(") || expr.contains(")"))
                parenthesis=1;

            for(int i=0; i<expr.length(); i++){
                if(i==')'){
                    jednako++;
                }else if(i=='('){
                    jednako--;
                }     
            }

            if(jednako!=0){
                System.out.println("Niste zatvorili sve zagrade!");
            }

            /**
            *@author Dorotea
            * Ako je eksponent negativan ili je zapisan u obliku kvocjenta onda mora sadrzavati zagrade
            * Ako je eksponent negativan, onda se mora nalaziti unutar zagrada
            **/
            //slucaj kad imamo zagrade
            if(parenthesis==1){
                int start=expr.indexOf("(");
                int finish=expr.indexOf(")");
                
                String izmeduZagrada = expr.substring(start+1, finish);
                System.out.println("izmedu zagrada je "+izmeduZagrada);
                int q=-1;
                if(expr.substring(start+1, finish).contains(("/"))){
                    q=expr.substring(start+1, finish).indexOf("/");
                    System.out.println("Prepoznao sam da je u eksponentu razlomak");
                    System.out.println("znak / nalazi se na indeksu "+q);
                }
                
                int negative=0;//provjera je li eksponent negativan
                if(expr.substring(start+1, finish).contains(("-")))
                    negative=1;
                
                //slucaj kad je eksponent razlomak:
                if(q!=-1){
                    System.out.println("Određujem brojnik i nazivnik");
                    //double brojnik=Double.parseDouble(expr.substring(start+1,q));
                    //double nazivnik=Double.parseDouble(expr.substring(q+1,finish));
                    double brojnik=Double.parseDouble(izmeduZagrada.split("/")[0]);
                    double nazivnik=Double.parseDouble(izmeduZagrada.split("/")[1]);
                    System.out.println("brojnik="+brojnik);
                    System.out.println("nazivnik="+nazivnik);
                    if(negative==1){
                        exp=-(brojnik/nazivnik);
                    }else if(negative==0){
                        exp=brojnik/nazivnik;
                    }
                }else{//slucaj kad eksponent nije razlomak:
                    if(negative==1){
                        System.out.println("Prepoznao sam da je eksponent negativan pa mu sad dodajem minus i sad exp= "+exp);
                        exp=-(Double.parseDouble(expr.substring(start+1, finish)));
                    }else{
                        exp=Double.parseDouble(expr.substring(start+1, finish));
                    }
                }
            }else{//slucaj kad nemamo zagrade u eskponentu
                if(expr.contains("^")){//x^nesto
                    int p=expr.indexOf("^");
                    exp=Double.parseDouble(expr.substring(p+1,expr.length()));
                }else{//samo je napisann x, dakle nema potenciju, pa stavljamo da je eksponent jednak 1
                    exp=1;
                }
            }

            //sada kada su odredeni koficijent i eksponent mozemo 'derivirati'
            res+=Double.toString(coef*exp);
            if(exp==1){
                return res;
            }else{
                double novi=exp-1;
                if(novi<0)
                    res+="*x^("+Double.toString(novi)+")";
                else
                    res+="*x^"+Double.toString(novi);
            }

            return res;
        }

        
    }
    
    public class IntegratedDrawFunctionScreen extends JPanel implements MouseWheelListener, /*KeyListener,*/ Runnable{
        public static final int WIDTH = 800;
	public static final int HEIGHT = 400;

	private BufferedImage buff;
	private Graphics2D g2d;
	
	//private ExpressionParser parser;
	//private Function function;
	
	private double windowX, windowY, windowWidth, windowHeight;
	private Point mousePt;
	
	//private String textBox;
	
	public IntegratedDrawFunctionScreen() {
            addMouseWheelListener(this);
            //addKeyListener(this);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mousePt = e.getPoint();
                    repaint();
                }
            });
            
            this.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    int dx = e.getX() - mousePt.x;
                    int dy = e.getY() - mousePt.y;
                    windowX -= dx / (double)WIDTH * windowWidth;
                    windowY += dy / (double)HEIGHT * windowHeight;
                    mousePt = e.getPoint();
                    repaint();
                }
            });
            setFocusable(true);
            requestFocusInWindow();
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setMinimumSize(new Dimension(WIDTH, HEIGHT));
            setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
            buff = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            g2d = buff.createGraphics();
		
		parser = new ExpressionParser();
		textBox = "";
		function = parser.parse(textBox);
		
		windowX = 0.0;
		windowY = 0.0;
		windowHeight = 2.0;
		windowWidth = windowHeight * WIDTH / HEIGHT;
            }
	
	// Time variables
	private double yVar=0.0;	// Constantly increasing
	private double zVar=0.0;	// Cycles smoothly from -1 to 1
	private synchronized void updateDT(double dt) {
		yVar += dt;
		zVar = Math.sin(yVar);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);

		synchronized (this) {
			List<Double> xs = new ArrayList<>();
			List<Double> ys = new ArrayList<>();
			
			for (int x = 0; x < WIDTH; x++) {
				double xx = toRealX(x);
				
				double yy = 0.0;
				if (function != null) yy = function.evaluateAt(xx, yVar, zVar);
                                String check = Double.toString(yy);
				double scaledX = x;
				double scaledY = toScreenY(yy);
				scaledY = Math.min(Math.max(scaledY, -5), HEIGHT + 5);
                                //
				//funkcija evaluirana u x je yy
                                if(!"Infinity".equals(check)){
                                    xs.add(scaledX);
                                    ys.add(scaledY);
                                }
			}
			
			int[] xa = new int[xs.size()];
			int[] ya = new int[ys.size()];
			for (int i = 0; i < xa.length; i++) {
				xa[i] = xs.get(i).intValue();
			}
			for (int i = 0; i < ya.length; i++) {
				ya[i] = ys.get(i).intValue();
			}
			
			g2d.setColor(Color.BLACK);
			int xAxisY = toScreenY(0.0);
			g2d.drawLine(0, xAxisY, WIDTH, xAxisY);
			int yAxisX = toScreenX(0.0);
			g2d.drawLine(yAxisX, 0, yAxisX, HEIGHT);
			
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(new Color(50, 50, 180));
			g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
			g2d.drawPolyline(xa, ya, xa.length);

			g2d.setFont(new Font("courier new", Font.ITALIC, 40));
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, HEIGHT - g2d.getFontMetrics().getHeight(), WIDTH, HEIGHT);
			g2d.setColor(Color.BLACK);
			g2d.drawString("f(x) = " + textBox, 0.0f, HEIGHT - 10.0f);
			
			g2d.drawString("x", 0, xAxisY - 10);
			g2d.drawString("y", yAxisX + 10, g2d.getFontMetrics().getHeight() - 20);
		}
		
		g.drawImage(buff, 0, 0, null);
	}
	
	@Override
	public void run() {
		boolean running = true;
		
		long oldTime = 0;
		double dt = 0.0;
		
		while (running) {
			long newTime = System.nanoTime();
			dt = (newTime - oldTime) / 1000000000.0;
			oldTime = newTime;
			
			updateDT(dt);
			repaint();
			
			try {
                            Thread.sleep(1);
			} catch (InterruptedException e) {
                            e.printStackTrace();
			}
		}
	}

	private double bottom() {
		return windowY - halfWindowHeight();
	}
	
	private double right() {
		return windowX - halfWindowWidth();
	}
	
	private double toRealX(int screenX) {
		return screenX / (double)WIDTH * windowWidth + right();
	}
	
	private double toRealY(int screenY) {
		return (HEIGHT - screenY) / (double)HEIGHT * windowHeight + bottom();
	}
	
	private int toScreenX(double realX) {
		return (int) ((realX - right()) / windowWidth * WIDTH);
	}
	
	private int toScreenY(double realY) {
		return HEIGHT - (int) ((realY - bottom()) / windowHeight * HEIGHT);
	}
	
	private double halfWindowWidth() {
		return windowWidth / 2.0;
	}
	
	private double halfWindowHeight() {
		return windowHeight / 2.0;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double scale = Math.pow(1.15, e.getPreciseWheelRotation());
		double mxReal = toRealX(e.getX());
		double myReal = toRealY(e.getY());
		double sx = (windowX - mxReal) / windowWidth;
		double sy = (windowY - myReal) / windowHeight;
		windowWidth *= scale;
		windowHeight *= scale;
		windowX = mxReal + sx * windowWidth;
		windowY = myReal + sy * windowHeight;
	}
    }
}    

