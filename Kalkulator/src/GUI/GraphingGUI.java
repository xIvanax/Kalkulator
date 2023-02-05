/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Grapher.DrawFunctionScreen;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

/**
 *
 * @author Dorotea
 */
public class GraphingGUI extends JPanel{ 
    private JPanel spremnik;
    private JPanel unos;
    private JPanel prikaz;
    private String textBox;
   
    /**
     * @Ivana
     */
    //private DrawFunctionScreen nacrtaj;
    private IntegratedDrawFunctionScreen nacrtaj;
    
    public JTextField ekran;
    private Function function;
    private ExpressionParser parser;
    
    /**
     * @Dorotea
     */
    JTabbedPane tab;
    JButton jednako = new JButton();
    
    //obrisi
    private boolean start = true;
    private ArrayList<String> funkcija;
    private String zadnjaBinarnaOperacija="=";
    private String zadnjaUnarnaOperacija="";
    private String screen="";
    
    public GraphingGUI(){
        /**
         * @Ivana
         */
        //sljedeca linija je ok rjesenje
        //nacrtaj = new DrawFunctionScreen();
        
        nacrtaj=new IntegratedDrawFunctionScreen();
        unos=new JPanel();
        prikaz=new JPanel();
        
        //omogucava resize
        this.setLayout(new BorderLayout());
        unos.setLayout(new BorderLayout());
        prikaz.setLayout(new BorderLayout());
        
        //slicno kao u CalCulatorGUI implementiramo unos funkcije koju crtamo
        //napravljene su neke promijene, npr. nema vise %, te su dodane nove mogucnosti
        ekran = new JTextField();
        ekran.setText("");
        ekran.setSize(800, 100);
        ekran.setPreferredSize(new Dimension(800,100));
        ekran.setEnabled(true);
        ekran.setFont(ekran.getFont().deriveFont(Font.BOLD, 28f));
        
        unos.add(ekran, BorderLayout.NORTH);
        spremnik=new JPanel();
        spremnik.setLayout(new GridLayout(7,7));
        
        ActionListener pisanje = new GraphingGUI.AkcijaPisanja();
        ActionListener brisanje = new GraphingGUI.AkcijaBrisanja();
        ActionListener bin_naredba = new GraphingGUI.AkcijaBinarneOperacije();
        ActionListener unar_naredba = new GraphingGUI.AkcijaUnarneOperacije();
        
        /**
         * @Dorotea
         */
        //x^2 sam stavila jer nemam bolje ideje, a bio mi je potreban jos jedan gumb
        
        //dodatno prilikom implementacije provjeri jesu li unarna ili binarna operacija, za svaki slucaj
        dodajGumb("⌈x⌉",unar_naredba); dodajGumb("⌊x⌋",unar_naredba);
        //ne mogu stavit da bude oznaka za korijen cijela?? izbor: √x ili x^(1/2)
        dodajGumb("√x",unar_naredba); dodajGumb("x^(1/y)",bin_naredba);
        dodajGumb("π",pisanje); dodajGumb("e",pisanje); 
        dodajGumb("C",brisanje);
        
        dodajGumb("x^2",unar_naredba); dodajGumb("1/x",unar_naredba);
        dodajGumb("|x|",unar_naredba); dodajGumb("(",pisanje);
        dodajGumb(")",pisanje); dodajGumb("D",brisanje); 
        dodajGumb("CE",brisanje);
        
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
        
        dodajGumb("lnx",unar_naredba); dodajGumb("0",pisanje);
        dodajGumb(".",pisanje); dodajGumb("=",bin_naredba);
        dodajGumb("+",bin_naredba); dodajGumb("ctg",unar_naredba); 
        dodajGumb("arcctg",unar_naredba);
        
        /**
         * @Ivana
         * ? znaci da je taj gumb prazan
         */
        //mozemo npr. maknut x^2 i sgrt(x) jer vec immo opcenite potencije i korijene 
        //umijeto njih stavit x i y
        dodajGumb("x",pisanje); dodajGumb("y",pisanje);
        dodajGumb("?",pisanje); dodajGumb("?",pisanje);
        dodajGumb("?",pisanje); dodajGumb("?",pisanje);
        dodajGumb("?",pisanje);
        
        /**
         * @Dorotea
         */
        unos.add(spremnik, BorderLayout.CENTER);
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
        /**
         * @Dorotea
         */
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
    /**
     * @Ivana
     * @return što je trenutno na ekranu
     */
    public String readScreen(){
        if(this.ekran==null)
            return "";
        return ekran.getText();
    }
    //originalna AkcijaPisanja
    /*private class AkcijaPisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            if(start){
                ekran.setText("");
                start=false;
            }
            ekran.setText(ekran.getText()+unos);
        }
    }*/
    /**
     * moj pokušaj funkcije AkcijaPisanja za integraciju s crtanjem grafa
     * @Ivana
     */
    private class AkcijaPisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String unos=event.getActionCommand();
            if(start){
                ekran.setText("");
                textBox=ekran.getText();
                start=false;
            }
            
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
                    //brise se samo zadnja znamenka
                    //nema unosa pa nemamo što brisati
                    if("".equals(screen))
                        break;
                    //na ekran stavljam broj bez zadnje znamenke
                    if(screen.length()>1){
                        screen=screen.substring(0,screen.length()-1);
                        ekran.setText(screen);
                    }else{
                        ekran.setText("");
                    }
                    break;
                case "CE":
                    //brišem cijeli zadnji entry
                    //nisam sigurna kako da ovo popravim
                    ekran.setText("");
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
        //ovaj dio treba prepraviti, za sada je kopija funkcije iz CalculatorGUI
        @Override
        public void actionPerformed(ActionEvent event) {
            String op=event.getActionCommand();
            String operacija=operation(op);
            if(start){
                //System.out.println("Sad je start true");
                if(operacija.equals("-")){
                    ekran.setText(operacija);
                    start=false;
                    screen="-";
                    if(zadnjaBinarnaOperacija.equals("=")){
                         ekran.setText(screen.substring(0,screen.length()-1));
                        start=true;
                        function=parser.parse(textBox);
                            if(function==null){
                                textBox="";
                            }
                        screen="";
                    }
                }else{
                    zadnjaBinarnaOperacija=operacija;
                    screen+=zadnjaBinarnaOperacija;
                    if(zadnjaBinarnaOperacija.equals("=")){
                        ekran.setText(screen.substring(0,screen.length()-1));
                        start=true;
                        function=parser.parse(textBox);
                            if(function==null){
                                textBox="";
                            }
                        screen="";
                    }else{
                        ekran.setText(screen);
                    }
                }
            }else{
                //System.out.println("Sad je start false i promijenit cu ga true");
 //               racunaj(Double.parseDouble(ekran.getText()));
                zadnjaBinarnaOperacija=operacija;
                screen+=zadnjaBinarnaOperacija;
                if(zadnjaBinarnaOperacija.equals("=")){
                   ekran.setText(screen.substring(0,screen.length()-1));
                   start=true;
                   function=parser.parse(textBox);
                        if(function==null){
                            textBox="";
                        }
                   screen="";
                }else{
                    ekran.setText(screen);
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
                case "=":
                    return "=";
                case "x^y":
                    return "^";
                case "x^(1/y)":
                    return "^(1/";
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
            if(start){
                //System.out.println("Sad je start true");
                if(operacija.equals("-")){
                    ekran.setText(operacija);
                    start=false;
                    screen="-";
                    if(zadnjaUnarnaOperacija.equals("=")){
                        ekran.setText(screen.substring(0,screen.length()-1));
                        function=parser.parse(textBox);
                            if(function==null){
                                textBox="";
                            }
                        screen="";
                    }
                }else{
                    zadnjaUnarnaOperacija=operacija;
                    screen+=zadnjaUnarnaOperacija;
                    if(zadnjaUnarnaOperacija.equals("=")){
                         ekran.setText(screen.substring(0,screen.length()-1));
                        start=true;
                        function=parser.parse(textBox);
                            if(function==null){
                                textBox="";
                            }
                        screen="";
                    }else{
                        ekran.setText(screen);
                    }
                }
            }else{
                //System.out.println("Sad je start false i promijenit cu ga true");
 //               racunaj(Double.parseDouble(ekran.getText()));
                zadnjaUnarnaOperacija=operacija;
                screen+=zadnjaUnarnaOperacija;
                if(zadnjaUnarnaOperacija.equals("=")){
                   ekran.setText(screen.substring(0,screen.length()-1));
                   start=true;
                   function=parser.parse(textBox);
                        if(function==null){
                            textBox="";
                        }
                   screen="";
                }else{
                   ekran.setText(screen);
                }
            }
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
            }
            
            switch(operacija) {
                case "+":
                    return "+";
                case "-":
                    return "-";
                case "*":
                    return "*";
                case "/":
                    return "/";
                case "=":
                    return "=";
            }
            return "";
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
				if (function != null) yy=function.evaluateAt(xx, yVar, zVar);
				
				double scaledX = x;
				double scaledY = toScreenY(yy);
				scaledY = Math.min(Math.max(scaledY, -5), HEIGHT + 5);
				
				xs.add(scaledX);
				ys.add(scaledY);
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
/*
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
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
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	*/
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