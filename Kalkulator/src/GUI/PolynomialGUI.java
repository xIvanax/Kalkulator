/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUIinterfaces.PolynomialInterface;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextArea;
import HelperClasses.PolyOps;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
/**
 * Polinomni kalkulator
 * @author Ivana
 */
public class PolynomialGUI extends JPanel implements PolynomialInterface{
    private final String url="jdbc:sqlite:baza.db";
    private ArrayList<String> iskoristenaImena = new ArrayList<>();
    
    private final JPanel spremnik = new JPanel();
    private final JPanel unos = new JPanel();
    private final JPanel prikaz = new JPanel();
    private String textBox;
    private final IntegratedDrawFunctionScreen nacrtaj = new IntegratedDrawFunctionScreen();;
    
    public JTextField ekran1 = new JTextField();
    public JTextField ekran2 = new JTextField();
    public JTextField display = new JTextField();
    
    public JTextField jTextField1 = new JTextField();
    
    private ArrayList<String> clanovi = new ArrayList<>();
    
    private Function function;
    private ExpressionParser parser;
    JTabbedPane tab;
    
    private boolean start = true;
    private String zadnjaBinarnaOperacija="=";
    private String screen="";
    
    private double evaluatedFunction;
    private String evaluateAt="";
    
    private int kojiEkran=1;
    
    JButton jButton5;
    JButton jButton1;
    /**
     * Postavljanje dizajna polinomnog kalkulatora.
     * @author Ivana
     */
    public PolynomialGUI(){
        setUpDatabase(url, unos);
        
        this.setLayout(new BorderLayout());
        unos.setLayout(new FlowLayout(FlowLayout.LEFT));
        prikaz.setLayout(new BorderLayout());
        
        setUpDisplaysDesign(ekran1, ekran2, display, unos, spremnik);
        
        ActionListener pisanje = new PolynomialGUI.AkcijaPisanja();
        ActionListener der_naredba = new PolynomialGUI.AkcijaDerivacije();
        ActionListener poli_naredba = new PolynomialGUI.AkcijaPolinomneOperacije();
        ActionListener eval_naredba = new PolynomialGUI.AkcijaEvaluacije();
        ActionListener graf_naredba = new PolynomialGUI.AkcijaCrtanja();
        ActionListener spremi_naredba = new PolynomialGUI.AkcijaSpremanja();
        ActionListener doh_naredba = new PolynomialGUI.AkcijaUzimanja();
        ActionListener mem = new PolynomialGUI.AkcijaPregledaMemorije();
        
        JRadioButton jRadioButton1 = new javax.swing.JRadioButton();
        JRadioButton jRadioButton2 = new javax.swing.JRadioButton();
        JRadioButton jRadioButton3 = new javax.swing.JRadioButton();
        JRadioButton jRadioButton4 = new javax.swing.JRadioButton();
        JRadioButton jRadioButton5 = new javax.swing.JRadioButton();
        JButton jButton3 = new javax.swing.JButton();
        JButton jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        JButton jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        JButton jButton6 = new javax.swing.JButton();
        
        setUpVisuals(spremnik, jTextField1, jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jRadioButton1, jRadioButton2, jRadioButton3, jRadioButton4, jRadioButton5);
        
        jRadioButton1.addActionListener(pisanje);
        jRadioButton2.addActionListener(pisanje);

        jRadioButton3.addActionListener(poli_naredba);
        jRadioButton4.addActionListener(poli_naredba);
        jRadioButton5.addActionListener(poli_naredba);
        
        jButton1.addActionListener(spremi_naredba);
        jButton2.addActionListener(eval_naredba);
        jButton3.addActionListener(graf_naredba);
        jButton4.addActionListener(der_naredba);
        jButton5.addActionListener(doh_naredba);
        jButton6.addActionListener(mem);
        
        unos.add(spremnik);
        prikaz.add(nacrtaj, BorderLayout.CENTER);
        
        tab=new JTabbedPane();
        tab.add("Unos",unos);
        tab.add("Graf",prikaz);
        
        new Thread(nacrtaj).start();
        
        this.add(tab);
        
        ekran1.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                kojiEkran=1;
            }
        });
        
        ekran2.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                kojiEkran=2;
            }
        });
    }
    /**
     * ActionListener odgovoran za dodavanje posebnih vrijednosti na ekran.
     * @author Ivana
     */
    private class AkcijaPisanja implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String unos=event.getActionCommand();
            if(start){
                textBox=ekran1.getText();
                start=false;
            }
            
            switch (unos) {
                case "π":
                    String pi=Double.toString(Math.PI);
                    screen+=pi;
                    if(kojiEkran==1)
                        ekran1.setText(ekran1.getText()+pi);
                    else if(kojiEkran==2)
                        ekran2.setText(ekran2.getText()+pi);
                    else{
                        JOptionPane.showMessageDialog(spremnik, "Kliknite na ekran na koji želite unesti odabranu vrijednost.", "Uputa", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    textBox=screen;
                    break;
                case "e":
                    String const_e=Double.toString(Math.E);
                    screen+=const_e;
                    if(kojiEkran==1)
                        ekran1.setText(ekran1.getText()+const_e);
                    else if(kojiEkran==2)
                        ekran2.setText(ekran2.getText()+const_e);
                    else{
                        JOptionPane.showMessageDialog(spremnik, "Kliknite na ekran na koji želite unesti odabranu vrijednost.", "Uputa", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    textBox=screen;
                    break;
                default:
                    screen+=unos;
                    ekran1.setText(screen);
                    textBox=screen;
                    break;
            }
        }
    }
    /**
     * ActionListener odgovaran za obradu odabrane polinomne operacije.
     * @author Ivana
     */
    private class AkcijaPolinomneOperacije implements ActionListener{  
        @Override
        public void actionPerformed(ActionEvent event) {
            String op=event.getActionCommand();
            int polyOp=operation(op);
            
            ArrayList<String> clanovi1 = new ArrayList<>();
            ArrayList<String> clanovi2 = new ArrayList<>();
                
            PolyOps p = new PolyOps();
            clanovi1 = p.starFormat(p.dohvati(ekran1.getText()));
            ArrayList<String> clanoviCopy = new ArrayList<>();
            for(String i:clanovi1)
                clanoviCopy.add(i);
                    
            p = new PolyOps();
            clanovi2 = p.starFormat(p.dohvati(ekran2.getText()));
            if(polyOp==0){
                ArrayList<String> clanoviRes = p.polyAdd(clanoviCopy, clanovi2, spremnik);
                if(clanoviRes==null){
                    return;
                }
                String res="";
                if(clanoviRes.isEmpty()==false){
                    res+=clanoviRes.get(0);
                clanoviRes.remove(0);
                for(String clan:clanoviRes)
                    if(clan.charAt(0)!='-')
                        res+="+"+clan;
                    else
                        res+=clan;
                display.setText(res);
                }else display.setText("0.0");
            }else if(polyOp==1){
                ArrayList<String> reverse = new ArrayList<>();
                for(String i:clanovi2){
                    if(i.length()>0)
                        if(i.charAt(0)=='-')
                            reverse.add(i.substring(1));
                        else
                            reverse.add("-"+i);
                }
                ArrayList<String> clanoviRes = p.polyAdd(clanoviCopy, reverse, spremnik);
                if(clanoviRes==null){
                    return;
                }
                String res="";
                if(clanoviRes.isEmpty()==false){
                    res+=clanoviRes.get(0);
                    clanoviRes.remove(0);
                    for(String clan:clanoviRes)
                    if(clan.charAt(0)!='-'){
                        res+="+"+clan;
                    }
                    else{
                        res+=clan;
                    }
                    display.setText(res);
                }
                else{
                    display.setText("0.0");
                }
            }else{
                ArrayList<String> clanoviRes = p.polyMulti(clanoviCopy, clanovi2, spremnik);
                if(clanoviRes==null){
                    return;
                }
                String res="";
                if(clanoviRes.isEmpty()==false){
                    res+=clanoviRes.get(0);
                clanoviRes.remove(0);
                for(String clan:clanoviRes)
                    if(clan.charAt(0)!='-')
                        res+="+"+clan;
                    else
                        res+=clan;
                display.setText(res);
                }else display.setText("0.0");
            }
        }
        
        public int operation(String operacija){
            return switch (operacija) {
                case "zbrajanje" -> 0;
                case "oduzimanje" -> 1;
                case "množenje" -> 2;
                default -> -1;
            };
        }
    }
    /**
     * ActionListener odgovaran za obradu evaluacije polinoma u odabranoj točki.
     * @author Ivana
     */
    private class AkcijaEvaluacije implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            evaluateAt = jTextField1.getText();
            PolyOps p = new PolyOps();
            ArrayList<String> clanovi1 = p.starFormat(p.dohvati(ekran1.getText()));
            String res="";
                if(clanovi1.isEmpty()==false){
                    res+=clanovi1.get(0);
                    clanovi1.remove(0);
                    for(String clan:clanovi1)
                    if(clan.charAt(0)!='-'){
                        res+="+"+clan;
                    }
                    else{
                        res+=clan;
                    }
                }
            Function f;
            if((!"".equals(evaluateAt)) && (evaluateAt!=null)){
                double value;
                try{
                    value = Double.parseDouble(evaluateAt);
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(spremnik, "Unos mora biti broj!", "Pogrešan unos", JOptionPane.OK_CANCEL_OPTION);
                    return;
                }
                f=parser.parse(res);
                evaluatedFunction=f.evaluateAt(value);
                display.setText(Double.toString(evaluatedFunction));
            }
        }
    }
    
    private class AkcijaDerivacije implements ActionListener{  
        @Override
        public void actionPerformed(ActionEvent event) {
            PolyOps p1 = new PolyOps();
            String t=p1.pozivUredi(ekran1.getText(), spremnik);
            ArrayList<String> clanovi1 = p1.starFormat(p1.dohvati(t));
            String ulaz="";
                if(clanovi1.isEmpty()==false){
                    ulaz+=clanovi1.get(0);
                    clanovi1.remove(0);
                    for(String clan:clanovi1)
                    if(clan.charAt(0)!='-'){
                        ulaz+="+"+clan;
                    }
                    else{
                        ulaz+=clan;
                    }
                }
            PolyOps p = new PolyOps();
            ArrayList<String> als = p.dohvati(t);
            ArrayList<String> pom = p.combineLikeTerms(als, spremnik);
            String res="";
                if(pom.isEmpty()==false){
                    res+=pom.get(0);
                pom.remove(0);
                for(String clan:pom)
                    if(clan.charAt(0)!='-')
                        res+="+"+clan;
                    else
                        res+=clan;
                }
            ulaz=res;
            String izlaz=p.deriviraj(ulaz, prikaz);
            if(izlaz.length()>1)
                if(izlaz.charAt(izlaz.length()-1)=='+' || izlaz.charAt(izlaz.length()-1)=='-'){
                    display.setText(izlaz.substring(0,izlaz.length()-1));
                }else if(izlaz.charAt(0)=='+'){
                    display.setText(izlaz.substring(1,izlaz.length()));
                }else{
                    display.setText(izlaz);
                }
            String operacija=ulaz;
        }
    }
    /**
     * ActionListener odgovaran za održavanje grafa prvog polinoma.
     * @author Ivana
     */
    private class AkcijaCrtanja implements ActionListener{
    @Override
        public void actionPerformed(ActionEvent event) {
            String op=event.getActionCommand();
            String operacija=operation(op);
            
            if(start){
                if(operacija.equals("-") && "".equals(ekran1.getText())){
                    ekran1.setText(operacija);
                    start=false;
                    screen="-";
                }else{
                    zadnjaBinarnaOperacija=operacija;
                    
                    if(zadnjaBinarnaOperacija.equals("=")){
                        textBox=ekran1.getText();
                        function=parser.parse(textBox);
                            if(function==null){
                                textBox="";
                            }
                        screen="";
                    }else{
                        ekran1.setText(ekran1.getText()+zadnjaBinarnaOperacija);
                        screen=ekran1.getText();
                    }
                }
            }else{
                start=true;
                zadnjaBinarnaOperacija=operacija;
                
                if(zadnjaBinarnaOperacija.equals("=")){
                   textBox=ekran1.getText();
                   function=parser.parse(textBox);
                        if(function==null){
                            textBox="";
                        }
                   screen="";
                }else{
                    ekran1.setText(ekran1.getText()+zadnjaBinarnaOperacija);
                    screen=ekran1.getText();
                }
            }
        }
        
        public String operation(String operacija){
            return switch (operacija) {
                case "+" -> "+";
                case "-" -> "-";
                case "*" -> "*";
                case "/" -> "/";
                case "graf" -> "=";
                case "x^y" -> "^";
                case "x^(1/y)" -> "^(1/";
                default -> "";
            };
        }
    }
    /**
     * ActionListener odgovoran za dohvaćanje odabrane spremljene vrijednosti iz memorije kalkulatora
     * @author Ivana
     */
    private class AkcijaUzimanja implements ActionListener{
            @Override
        public void actionPerformed(ActionEvent event) {
            String trazeni = JOptionPane.showInputDialog(spremnik, "Koji polinom želite dohvatiti?", "Dohvaćanje polinoma", JOptionPane.QUESTION_MESSAGE);
            try{
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex){
            }
            
            jButton5.setEnabled(false);
            
            SwingWorker<ResultSet, Void> worker = new SwingWorker<ResultSet, Void>(){
                @Override
                protected ResultSet doInBackground() throws Exception{
                    String sql ="SELECT Ime, Polinom FROM Polinomi";
                    Connection conn=null;
                    ResultSet result = null; 
                    try{
                       conn = DriverManager.getConnection(url);
                       Statement stmt = conn.createStatement();
                       result = stmt.executeQuery(sql);
                    }catch(SQLException e){
                       JOptionPane.showMessageDialog(spremnik, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                    return result;
                }
                @Override 
                protected void done(){
                    ResultSet result;
                    try{
                        result=get();
                        while(result.next()){
                            String ime=result.getString("Ime");
                            if(ime.equals(trazeni)){
                                String polinom=result.getString("Polinom");
                                if(kojiEkran==1){
                                    ekran1.setText(polinom);
                                }else if(kojiEkran==2){
                                    ekran2.setText(polinom);
                                }
                                jButton5.setEnabled(true);
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(spremnik, "Ne postoji spremljeni polinom s imenom "+trazeni+".", "Greška", JOptionPane.ERROR_MESSAGE);
                    }catch (SQLException | InterruptedException | ExecutionException ex){
                        JOptionPane.showMessageDialog(spremnik, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                    jButton5.setEnabled(true);
                }
        
            };
            worker.execute();
        }
    }
    /**
     * ActionListener odgovoran za obradu zahtjeva za spremanjem polinoma u memoriju kalkulatora.
     * @author Ivana
     */
    private class AkcijaSpremanja implements ActionListener{
            @Override
        public void actionPerformed(ActionEvent event) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {}
            String name = "";
            while(true){
                name = JOptionPane.showInputDialog(spremnik, "Pod kojim imenom želite spremiti polinom?", "Spremanje polinoma", JOptionPane.QUESTION_MESSAGE);
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
            String sql ="INSERT INTO Polinomi (Ime, Polinom) VALUES ('"+name+"','"+ekran1.getText()+"');";
            Connection conn=null;
            ResultSet result = null; 
            try{
                conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }catch(SQLException e){
               JOptionPane.showMessageDialog(spremnik, e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
               return;
            }
        }
    }
    /**
     * ActionListener odgovoran za prikaz memorije kalkulatora.
     * @author Ivana
     */
    class AkcijaPregledaMemorije implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String output ="Ime"+"\t"+"Polinom"+"\n";
            try{
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex){
            }
            String sql ="SELECT Ime, Polinom FROM Polinomi";
            Connection conn=null;
            ResultSet result = null; 
            try{
               conn = DriverManager.getConnection(url);
               Statement stmt = conn.createStatement();
               result = stmt.executeQuery(sql);
            }catch(SQLException ev){
               JOptionPane.showMessageDialog(spremnik, ev.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
               return;
            }
            try {
                while(result.next()){
                    String ime=result.getString("Ime");
                    String polinom=result.getString("Polinom");
                    if(ime!=null)
                        output+=ime+"\t"+polinom+"\n";
                }
            }    
            catch (SQLException ex){
                JOptionPane.showMessageDialog(spremnik, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(spremnik, new JTextArea(output));
    }
    }
    /**
     * Prozor za crtanje grafa funkcije unesene na ekran.
     * @author Ivana
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

