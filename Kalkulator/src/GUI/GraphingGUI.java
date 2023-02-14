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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Grapher.Expressions.Function;
import Grapher.Parser.ExpressionParser;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import HelperClasses.popUp;

/**
 *
 * @author Dorotea
 */
public class GraphingGUI extends JPanel implements GraphingInterface{ 
    private final String url="jdbc:sqlite:baza.db";
    private ArrayList<String> iskoristenaImena = new ArrayList<>();
    
    private JPanel spremnik = new JPanel();
    private JPanel unos = new JPanel();
    private JPanel prikaz = new JPanel();
    private String textBox;
    private IntegratedDrawFunctionScreen nacrtaj = new IntegratedDrawFunctionScreen();
    public JTextField ekran = new JTextField();
    private Function function;
    private ExpressionParser parser;
    private JTabbedPane tab = new JTabbedPane();
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
        
        this.setLayout(new BorderLayout());
        unos.setLayout(new BorderLayout());
        prikaz.setLayout(new BorderLayout());
        spremnik.setLayout(new GridLayout(6,7));
        
        ActionListener pisanje = new GraphingGUI.AkcijaPisanja();
        ActionListener brisanje = new GraphingGUI.AkcijaBrisanja();
        ActionListener bin_naredba = new GraphingGUI.AkcijaBinarneOperacije();
        ActionListener unar_naredba = new GraphingGUI.AkcijaUnarneOperacije();
        ActionListener mem = new GraphingGUI.AkcijaMemorije();
        
        setUpButtons(ekran, unos, spremnik, tab, pisanje, brisanje, bin_naredba, unar_naredba, mem);
        
        unos.add(spremnik, BorderLayout.CENTER);
        prikaz.add(nacrtaj, BorderLayout.CENTER);
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
    
    /**
     * ActionListener odgovoran za poziv skočnog prozora.
     * @author Ivana
     */
    private class AkcijaMemorije implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            popUp p = new popUp(unos, url, iskoristenaImena, ekran, evaluateAt, evaluatedFunction);
        }
    }
    
    private class AkcijaPisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String unos=event.getActionCommand();
            if(start){
                textBox=ekran.getText();
                start=false;
            }
            ekran.setText(ekran.getText()+unos);
            
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
        }
    }
    
    private class AkcijaBrisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String unos = event.getActionCommand();
            switch (unos) {
                case "D":
                    String str = ekran.getText();
                    if("".equals(str))
                        break;
                    if(str.length()>1){
                        ekran.setText(str.substring(0, str.length()-1));
                        screen=ekran.getText();
                    }else{
                        ekran.setText("");
                        screen="";
                    }
                    break;
                case "C":
                    start = true;
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
                        screen=ekran.getText();
                    }
                }
            }else{
                start=true;
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
                    evaluatedFunction=f.evaluateAt(Double.parseDouble(evaluateAt));
                    String fja = "f(x) = " + ekran.getText();
                    String output = fja + "\n" + "f("+evaluateAt+")="+evaluatedFunction;
                    System.out.println("f("+evaluateAt+")="+evaluatedFunction);
                    JOptionPane.showMessageDialog(spremnik, output, "Rezultat evaluacije", JOptionPane.INFORMATION_MESSAGE);
            }
            return "";
        }
    }
    
    private class AkcijaUnarneOperacije implements ActionListener{
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
    
    /**
     * pokušaj integracije grafa i unosa
     * @Ivana
     */
    public class IntegratedDrawFunctionScreen extends JPanel implements MouseWheelListener, /*KeyListener,*/ Runnable{
        public static final int WIDTH = 800;
	public static final int HEIGHT = 400;

	private BufferedImage buff;
	private Graphics2D g2d;
        
	private double windowX, windowY, windowWidth, windowHeight;
	private Point mousePt;
	
	public IntegratedDrawFunctionScreen() {
            addMouseWheelListener(this);
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
	
	private double yVar=0.0;	
	private double zVar=0.0;	
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
				if (function != null) yy = function.evaluateAt(xx);
                                String check = Double.toString(yy);
				double scaledX = x;
				double scaledY = toScreenY(yy);
				scaledY = Math.min(Math.max(scaledY, -5), HEIGHT + 5);
                                
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