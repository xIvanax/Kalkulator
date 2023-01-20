/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

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

import Expressions.Function;
import Parser.ExpressionParser;

/**
 *
 * @author Dorotea
 */
public class GraphingGUI extends JPanel{ 
    private JPanel spremnik;
    private JPanel unos;
    //private JPanel nacrtaj;
    private DrawFunctionScreen nacrtaj;
    /**
     * @Ivana
     */
    public JTextField ekran;
    
    JTabbedPane tab;
    JButton jednako = new JButton();
    
    //obrisi
    private boolean start = true;
    private ArrayList<String> funkcija;
    private String zadnjaBinarnaOperacija="=";
    private String zadnjaUnarnaOperacija="";
    
    public GraphingGUI(){
        /**
         * @Ivana
         */
        //nacrtaj=new JPanel();
        nacrtaj = new DrawFunctionScreen();
        
        /**
         * njegov Window je nas DrawFunctionString i on ovaj dio ima u main-u, ali nisam sigurna kak da to sve povežem
         * Window window = new Window();
		
		JFrame frame = new JFrame("Function Grapher");
		frame.add(window);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(window).start();
         */
        unos=new JPanel();
        
        unos.setLayout(new BorderLayout());
        
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
        //koristim gumbe kao u CalculatorGUI, uz neke promijene i dodatke
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
        
        dodajGumb("ln",unar_naredba); dodajGumb("0",pisanje);
        dodajGumb(".",pisanje); dodajGumb("=",bin_naredba);
        dodajGumb("+",bin_naredba); dodajGumb("ctg",unar_naredba); 
        dodajGumb("arcctg",unar_naredba);
        
        /**
         * @Ivana
         * ? znaci da je taj gumb prazan
         */
        dodajGumb("x",pisanje); dodajGumb("y",pisanje);
        dodajGumb("?",pisanje); dodajGumb("?",pisanje);
        dodajGumb("?",pisanje); dodajGumb("?",pisanje);
        dodajGumb("?",pisanje);
        
        /**
         * @Dorotea
         */
        unos.add(spremnik, BorderLayout.CENTER);
        tab=new JTabbedPane();
        tab.add("Unos",unos);
        tab.add("Graf",nacrtaj);
        /**
         * @Ivana
         */
        new Thread(nacrtaj).start();
        
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
                    //brišem cijeli zadnji entry
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
/**
 *
 * @author ivana
 */
public class DrawFunctionScreen extends JPanel implements MouseWheelListener, KeyListener, Runnable{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

    private BufferedImage buff;
    private Graphics2D g2d;
	
    private ExpressionParser parser;
    private Function function;
	
    private double windowX, windowY, windowWidth, windowHeight;
    private Point mousePt;
	
    private String textBox;
	
    public DrawFunctionScreen() {
        //textBox = readScreen();
	addMouseWheelListener(this);
	addKeyListener(this);
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
	textBox = "0";
	function = parser.parse(textBox);
	windowX = 0.0;
	windowY = 0.0;
	windowHeight = 2.0;
	windowWidth = windowHeight * WIDTH / HEIGHT;
    }
	
    // Time variables
    private double yVar = 0.0;	// Constantly increasing
    private double zVar = 0.0;	// Cycles smoothly from -1 to 1
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
            //****
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

    @Override
    public void keyTyped(KeyEvent e) {		
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String str = readScreen();
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