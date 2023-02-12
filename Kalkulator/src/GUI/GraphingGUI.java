/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUIinterfaces.GraphingInterface;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Grapher.Expressions.Function;
import Grapher.Parser.ExpressionParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Dorotea
 */
public class GraphingGUI extends JPanel implements GraphingInterface{ 
    private final String url="jdbc:sqlite:C:\\Users\\Ivana\\Desktop\\Java_projekt\\Kalkulator_12_veljace\\Kalkulator\\Kalkulator\\baza\\baza.db";
    private ArrayList<String> iskoristenaImena = new ArrayList<>();
    
    private JPanel spremnik;
    private JPanel unos;
    private JPanel prikaz;
    private JPanel memorija;
    private String textBox;
    private IntegratedDrawFunctionScreen nacrtaj;
    public JTextField ekran;
    private Function function;
    private ExpressionParser parser;
    private JTabbedPane tab;
    private JScrollPane sp;
    
    private boolean start = true;
    private ArrayList<String> funkcija;
    private String zadnjaBinarnaOperacija="=";
    private String zadnjaUnarnaOperacija="";
    private String screen="";
    private Vector<String> vec;
    
    private double evaluatedFunction;
    private String evaluateAt="";
    
    public GraphingGUI(){
        //nema se sta testirat:
        setUpDatabase(url);
        
        nacrtaj=new IntegratedDrawFunctionScreen();
        unos=new JPanel();
        prikaz=new JPanel();
        memorija=new JPanel();
        
        //omogucava resize
        this.setLayout(new BorderLayout());
        unos.setLayout(new BorderLayout());
        prikaz.setLayout(new BorderLayout());
        memorija.setLayout(new BorderLayout());
        
        //slicno kao u CalCulatorGUI implementiramo unos funkcije koju crtamo
        //napravljene su neke promjene, npr. nema vise %, te su dodane nove mogucnosti
        ekran = new JTextField();
        ekran.setText("");
        ekran.setSize(800, 100);
        ekran.setPreferredSize(new Dimension(800,100));
        ekran.setEnabled(true);
        ekran.setFont(ekran.getFont().deriveFont(Font.BOLD, 28f));
        
        unos.add(ekran, BorderLayout.NORTH);
        spremnik=new JPanel();
        
        /**
         * ja sam dodala tu gumbe za spremanje polinoma, deriviranje i potenciranje sam da od nekud krenem,
         * vidim da imamo zaseban 
         */
        spremnik.setLayout(new GridLayout(6,7));
        
        ActionListener pisanje = new GraphingGUI.AkcijaPisanja();
        ActionListener brisanje = new GraphingGUI.AkcijaBrisanja();
        ActionListener bin_naredba = new GraphingGUI.AkcijaBinarneOperacije();
        ActionListener unar_naredba = new GraphingGUI.AkcijaUnarneOperacije();
        ActionListener spremi_naredba = new GraphingGUI.AkcijaSpremanja();
        ActionListener doh_naredba = new GraphingGUI.AkcijaUzimanja();
        ActionListener mem = new GraphingGUI.AkcijaPregledaMemorije();
        
        dodajGumb("⌈x⌉",unar_naredba); dodajGumb("⌊x⌋",unar_naredba);
        dodajGumb("√x",unar_naredba); dodajGumb("x^(1/y)",bin_naredba);
        dodajGumb("π",pisanje); dodajGumb("e",pisanje); 
        dodajGumb("C",brisanje);
        
        dodajGumb("x",pisanje); dodajGumb("1/x",unar_naredba);
        dodajGumb("|x|",unar_naredba); dodajGumb("(",pisanje);
        dodajGumb(")",pisanje); dodajGumb("D",brisanje); 
        //obrisan gumb CE jer nema smisla u ovom kontekstu/dizajnu kalkulatora (ja mislim, ak ti mislis da ima
        //lako ga vratim - sad sam ga zamijenila s gumbom eval pomocu kojeg cemo napravit evaluation funckije
        //zapravo ce funkcionirat isto kao "=" u obicnom kalkulatoru
        
        /**
         * ovaj komentar iznad napisala
         * @Ivana
         */
        dodajGumb("eval",bin_naredba);
        
        dodajGumb("x^y",bin_naredba); dodajGumb("7",pisanje);
        dodajGumb("8",pisanje); dodajGumb("9",pisanje);
        dodajGumb("/",bin_naredba); dodajGumb("sin",unar_naredba); 
        dodajGumb("arcsin",unar_naredba);
        
        dodajGumb("10^x",unar_naredba); dodajGumb("4",pisanje);
        dodajGumb("5",pisanje); dodajGumb("6",pisanje);
        dodajGumb("*",bin_naredba); dodajGumb("cos",unar_naredba); 
        dodajGumb("arccos",unar_naredba);
        
        dodajGumb("logx",unar_naredba); dodajGumb("1",pisanje);
        dodajGumb("2",pisanje); dodajGumb("3",pisanje);
        dodajGumb("-",bin_naredba); dodajGumb("tg",unar_naredba); 
        dodajGumb("arctg",unar_naredba);
        //preimenovala sam gumb "=" u "draw"
        /**
         * ovaj komentar iznad napisala
         * @Ivana
         */
        dodajGumb("lnx",unar_naredba); dodajGumb("0",pisanje);
        dodajGumb(".",pisanje); dodajGumb("draw",bin_naredba);
        dodajGumb("+",bin_naredba); dodajGumb("ctg",unar_naredba); 
        dodajGumb("arcctg",unar_naredba);
        
        unos.add(spremnik, BorderLayout.CENTER);
        prikaz.add(nacrtaj, BorderLayout.CENTER);
        tab=new JTabbedPane();
        tab.add("Unos",unos);
        tab.add("Graf",prikaz);
        tab.add("Memorija",sp);
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
     * @return što je trenutno na ekranu
     */
    public String readScreen(){
        if(this.ekran==null)
            return "";
        return ekran.getText();
    }
    
    private class AkcijaPisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String unos=event.getActionCommand();
            if(start){
                //ovo nam vise ne treba jer je dizajn drukciji nego kod standardnog kalkulatora,
                //ne vidi se u svakom trenu samo jedna znamenka nego cijeli niz znakova
                //ekran.setText("");
                /**
                * ovaj komentar iznad napisala
                * @Ivana
                */
                textBox=ekran.getText();
                //System.out.println("sad je start true i textbox= "+textBox);
                start=false;
            }
            
            //System.out.println("*unos="+unos+" ekran="+ekran.getText());
            //updateanje ekrana kao prije:
            ekran.setText(ekran.getText()+unos);
            //System.out.println("**unos="+unos+" ekran="+ekran.getText());
            
            if(unos.equals("π")){
                String pi=Double.toString(Math.PI);
                screen+=pi;
                ekran.setText(screen);
                textBox=screen;
            }else if(unos.equals("e")){
                String const_e=Double.toString(Math.E);
                screen+=const_e;
                ekran.setText(screen);
                textBox=screen;
            }else if(unos.equals("=")){
                function=parser.parse(textBox);
                    if(function==null){
			textBox="";
                    }
                screen="";
            }else{
                screen+=unos;
                ekran.setText(screen);
                textBox=screen;
            }
            
            /*if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (textBox.length() > 0) {
				textBox = textBox.substring(0, textBox.length() - 1);
			}
		} else if (Character.isLetterOrDigit(e.getKeyChar()) || e.getKeyChar() == '^' || e.getKeyChar() == '-' ||
				e.getKeyChar() == '+' || e.getKeyChar() == '*' || e.getKeyChar() == '/' || e.getKeyChar() == '(' ||
				e.getKeyChar() == ')' || e.getKeyChar() == '%' || e.getKeyChar() == ',' || e.getKeyChar() == '.') {
			textBox += e.getKeyChar();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			function = parser.parse(textBox);
			if (function == null) {
				textBox = "";
			}
		}*/
        }
    }
    private class AkcijaBrisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            switch (unos) {
                case "D":
                    //brise se samo zadnji znak
                    String str = ekran.getText();
                    //nema unosa pa nemamo što brisati:
                    if("".equals(str))
                        break;
                    //na ekran stavljam sadrzaj ekrana bez zadnjeg znaka i updateam "screen":
                    if(str.length()>1){
                        ekran.setText(str.substring(0, str.length()-1));
                        screen=ekran.getText();
                    }else{
                        ekran.setText("");
                        screen="";
                    }
                    //System.out.println("Nakon pritiske gumba D screen="+screen);
                    break;
                case "C":
                    //brišem cijeli input kalkulatora (back to square one)
                    start = true;
                    //rezultat=0.0;
                    zadnjaBinarnaOperacija="=";
                    zadnjaUnarnaOperacija="";
                    screen="";
                    ekran.setText("");
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
            if(start){
                //System.out.println("Sad je start true");
                if(operacija.equals("-") && "".equals(ekran.getText())){
                    ekran.setText(operacija);
                    start=false;
                    screen="-";
                }else{
                    zadnjaBinarnaOperacija=operacija;
                    
                    if(zadnjaBinarnaOperacija.equals("=")){
                        textBox=ekran.getText();
                        function=parser.parse(textBox);
                            if(function==null){
                                textBox="";
                            }
                        
                        screen="";
                    }else{
                        ekran.setText(ekran.getText()+zadnjaBinarnaOperacija);
                        //System.out.println("***ekran="+ekran.getText());
                        screen=ekran.getText();
                    }
                }
            }else{
                start=true;
                zadnjaBinarnaOperacija=operacija;
                
                if(zadnjaBinarnaOperacija.equals("=")){
                    //System.out.println("screen nakon pritiska ="+screen);
                   textBox=ekran.getText();
                   function=parser.parse(textBox);
                        if(function==null){
                            textBox="";
                        }
                    
                    screen="";
                }else{
                    ekran.setText(ekran.getText()+zadnjaBinarnaOperacija);
                    //System.out.println("** **ekran="+ekran.getText());
                    screen=ekran.getText();
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
                    String fja = "f(x) = " + ekran.getText();
                    String output = fja + "\n" + "f("+evaluateAt+")="+evaluatedFunction;
                    System.out.println("f("+evaluateAt+")="+evaluatedFunction);
                    JOptionPane.showMessageDialog(spremnik, output, "Rezultat evaluacije", JOptionPane.INFORMATION_MESSAGE);
            }
            return "";
        }
    }
    
    private class AkcijaUnarneOperacije implements ActionListener{
        //double unaryResult=rezultat;
        @Override
        public void actionPerformed(ActionEvent event) {
            String op=event.getActionCommand();
            String operacija=operation(op);
            zadnjaUnarnaOperacija=operacija;
            screen+=zadnjaUnarnaOperacija;
            ekran.setText(screen);
        }
        
        public String operation(String operacija){
            switch(operacija){
                case "sin":
                    return "sin(";
                case "arcsin":
                    return "asin(";
                case "cos":
                    return "cos(";
                case "arccos":
                    return "acos(";
                case "tg":
                    return "tan(";
                case "arctg":
                    return "atan(";
                case "ctg":
                    return "cot(";
                case "arcctg":
                    return "arcctan(";
                case "1/x":
                    return "1/(";
                case "lnx":
                    return "ln(";
                case "logx":
                    return "log(";
                case "10^x":
                    return "10^(";
                case "e^x":
                    return "e^(";
                case "|x|":
                    return "abs(";
                case "⌈x⌉":
                    return "ceil(";
                case "⌊x⌋":
                    return "floor(";
                case "√x":
                    return "sqrt(";  
                default:
                    return "";
            }
        }
    }
    
        private class AkcijaUzimanja implements ActionListener{
            @Override
        public void actionPerformed(ActionEvent event) {
            String trazeni = JOptionPane.showInputDialog(spremnik, "Koju evaluiranu vriejdnost želite dohvatiti?", "Dohvaćanje evaluirane vrijednosti", JOptionPane.QUESTION_MESSAGE);
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
            }catch(SQLException e){
               System.out.println(e.getMessage());
            }
            try {
                while(result.next()){
                    String ime=result.getString("Ime");
                    if(ime.equals(trazeni)){
                        String funkcija=result.getString("Funkcija");
                        String tocka_eval =result.getString("Tocka_evaluacije");
                        String res = result.getString("Rezultat");
                        ekran.setText(ekran.getText()+tocka_eval);
                    }
                }    
            }catch (SQLException ex){}
        }
    }
        
    private class AkcijaSpremanja implements ActionListener{
            @Override
        public void actionPerformed(ActionEvent event) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {}
            String name = "";
            while(true){
                name = JOptionPane.showInputDialog(spremnik, "Pod kojim imenom želite spremiti Evaluiranu vrijednost funkcije?", "Spremanje evaluirane vrijednosti", JOptionPane.QUESTION_MESSAGE);
                if(name==null)
                    return;
                if(!iskoristenaImena.contains(name)){
                    iskoristenaImena.add(name);
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(spremnik, "Već ste spremili nešto pod tim imenom. Odaberite drugo ime.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
            //kod polinoma cu zasad omoguciti sam spremanje polinoma, ne i njegove evaluirane vrijednosti 
            String sql ="INSERT INTO Funkcije (Ime, Funkcija, Tocka_evaluacije, Rezultat) VALUES ('"+name+"','"+ekran.getText()+"','"+evaluateAt+"','"+evaluatedFunction+"');";
            Connection conn=null;
            ResultSet result = null; 
            try{
                conn = DriverManager.getConnection(url);
                System.out.println("Connection established");
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }catch(SQLException e){
               System.out.println(e.getMessage());
            }
        }
    }
    //nema se sta testirat?
    class AkcijaPregledaMemorije implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String output ="Ime"+"\t"+"Funkcija"+"\t"+"Tocka_evaluacije"+"\t"+"Rezultat"+"\n";
        System.out.println(output);
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
                        output+=ime+"\t"+funkcija+"\t"+tocka_eval+"\t"+res+"\n";
                }
            }    
            catch (SQLException ex){}
            JOptionPane.showMessageDialog(spremnik, new JTextArea(output));
    }
    }
    /**
     * pokušaj integracije grafa i unosa
     * @Ivana
     */
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