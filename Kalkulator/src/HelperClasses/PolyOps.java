/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HelperClasses;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Polinomne operacije na jednom mjestu
 * @author Ivana
 */
public class PolyOps {
        ArrayList<String> clanovi;
        ArrayList<String> list=new ArrayList<>();
        /**
         * prazan konstruktor
         */
        public PolyOps(){
            clanovi = new ArrayList<>();
        }
        /**
         * konstruktor koji kao parametar prima listu clanova polinoma koja će biti potrebna u metodi "dohvati"
         * @param clanovi clanovi polinoma koji promatramo
         */
        public PolyOps(ArrayList<String> clanovi){
            this.clanovi=clanovi;
        }
        /**
         * Izbacuje ponavljanja članova s istim eksponentom te ih spaja u jedan.
         * @param poly polinom reprezentiran preko liste svojih clanova
         * @param parent  prozor na koji će se "zakačiti" poruka o grešci
         * @return polinom bez ponavljanja koeficijenata
         * @author Ivana
         */
        public ArrayList<String> combineLikeTerms(ArrayList<String> poly, JPanel parent){
            double deg = maxDegree(poly, parent);
            ArrayList<String> resPoly = new ArrayList<>();
            ArrayList<Double> stupnjevi = listOfDegrees(poly, parent);
            for(double i:stupnjevi){
                double sum=0.0;
                for(String clan:poly){
                    if(i==coefAndExp(clan,parent)[1]){
                        sum+=coefAndExp(clan,parent)[0];
                    }
                }
                String noviClan;
                if(i>=0)
                    if(i==0.0)
                        noviClan = Double.toString(sum);
                    else
                        noviClan = Double.toString(sum) + "*x^" + Double.toString(i);
                else
                    noviClan = Double.toString(sum) + "*x^(" + Double.toString(i) + ")";
                if(sum!=0.0)
                    resPoly.add(noviClan);
                sum=0.0;
            }
            return resPoly;
        }
        /**
         * Vraća stupanj polinoma.
         * @param poly polinom reprezentiran preko liste svojih clanova
         * @param parent prozor na koji će se "zakačiti" poruka o grešci.
         * @return stupanj polinoma
         * @author Ivana
         */
        public double maxDegree(ArrayList<String> poly, JPanel parent){
            PolySorter ps = new PolySorter(parent);
            ps.sortPolynomial(poly);
            return coefAndExp(poly.get(poly.size()-1), parent)[1];
        }
        /**
         * Vraća listu svih različitih stupnjeva u polinomu.
         * @param poly polinom reprezentiran preko liste svojih clanova
         * @param parent prozor na koji će se "zakačiti" poruka o grešci. 
         * @return lista različitih stupnjeva u polinomu
         * @author Ivana
         */
        public ArrayList<Double> listOfDegrees(ArrayList<String> poly, JPanel parent){
            ArrayList<Double> stupnjevi = new ArrayList<>();
            for(String i:poly){
                if(!stupnjevi.contains(coefAndExp(i, parent)[1]))
                    stupnjevi.add(coefAndExp(i,parent)[1]);
            }
            return stupnjevi;
        }
        /**
         * Provjerava jesu li sve zagrade zatvorene i vraća lokaciju char-a token.
         * @param ulaz String u kojem provjeravamo broj zagrada
         * @param token char za koji provjeravamo nalazi li se u Stringu ulaz
         * @return lokacija char-a token
         * @author Ivana
         */
        public int scanFromRight(String ulaz, char token){
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

        /**
        * funkcija za dohvacanje clanova u polinomu
        * @param ulaz polinom
        * @return lista clanova polinoma
        * @author Ivana
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
         * funkcija uzima dva polinoma reprezentirana preko svoje liste clanova i vraca njihov umnozak u obliku liste clanova
         * @param poly1 clanovi prvog polinoma
         * @param poly2 clanovi drugog polinoma
         * @param parent JPanel na koji će se "zakačiti" poruka upozorenja ako korisnik nešto pogrešno unese         * @return rezultat mnozenja u obliku liste clanova
         * @author Ivana
         */
        public ArrayList<String> polyMulti(ArrayList<String> poly1, ArrayList<String> poly2, JPanel parent){
            ArrayList<String> resClanovi = new ArrayList<>();
            ArrayList<ArrayList<String>> svi = new ArrayList<>();
            double coef, exp, coef2, exp2;
            
            poly1=combineLikeTerms(poly1, parent);
            poly2=combineLikeTerms(poly2, parent);
            
            for(String expr1:poly1){
                double info[] = new double[3];
                info = coefAndExp(expr1, parent);
                if(info==null){
                    return null;
                }
                coef=info[0];
                exp=info[1];
                ArrayList<String> help = new ArrayList<>();
                for(String expr2:poly2){
                    double info2[] = new double[3];
                    info2 = coefAndExp(expr2, parent);
                    if(info2==null){
                        return null;
                    }
                    coef2=info2[0];
                    exp2=info2[1];
                    String str;

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
                    help.add(str);
                }
                svi.add(help);
            }
            for(String i:svi.get(0))
                resClanovi.add(i);

            svi.remove(0);
            for(ArrayList<String> i:svi){
                resClanovi=polyAdd(i,resClanovi, parent);
            }
            
            resClanovi=combineLikeTerms(resClanovi,parent);
            PolySorter ps = new PolySorter(parent);
            ps.sortPolynomial(resClanovi);
            
            return resClanovi;
        }
        /**
         * fja kao argumente uzima clanove dva polinoma i zbraja te polinome
         * @param poly1 clanovi prvog polinoma
         * @param poly2 clanovi drugog polinoma
         * @param parent JPanel na koji će se "zakačiti" poruka upozorenja ako korisnik nešto pogrešno unese
         * @return rezultat zbrajanja poly1 i poly2 u obliku liste clanova
         * @author Ivana
         */
        public ArrayList<String> polyAdd(ArrayList<String> poly1, ArrayList<String> poly2, JPanel parent){
            String res="";
            ArrayList<String> clanoviRes = new ArrayList<String>(); 
            double coef=0, exp=0, konstanta=0;
            double coef2=0, exp2=0, konstanta2=0;
            int flag=0; 
            int eksponentJeRazlomakIliNegativan=0;
            poly1 = combineLikeTerms(poly1,parent);
            poly2 = combineLikeTerms(poly2, parent);
            for(String expr1:poly1){
                double info[] = new double[3];
                info = coefAndExp(expr1, parent);
                if(info==null){
                    return null;
                }
                coef=info[0];
                exp=info[1];
                for(String expr2:poly2){
                    double info2[] = new double[3];
                    info2 = coefAndExp(expr2, parent);
                    if(info2==null){
                        return null;
                    }
                    coef2=info2[0];
                    exp2=info2[1];
                    
                   if(exp==exp2){
                        flag=1; 
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
            flag=0; 
            konstanta=0; konstanta2=0;
            for(String expr1:poly2){
                double info[] = new double[3];
                info = coefAndExp(expr1, parent);
                if(info==null){
                    return null;
                }
                coef=info[0];
                exp=info[1];
                for(String expr2:clanoviRes){
                    double info2[] = new double[3];
                    info2 = coefAndExp(expr2, parent);
                    if(info2==null){
                        return null;
                    }
                    
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
            ArrayList<String> output = new ArrayList<String>();
            for(String i: clanoviRes)
                if(!i.contains("0.0*x"))
                    if(!i.contains("^0.0"))
                        output.add(i);
                    else
                        output.add(i.split("\\*")[0]);
            
            output=combineLikeTerms(output, parent);
            PolySorter ps = new PolySorter(parent);
            ps.sortPolynomial(output);
            
            return output;
        }
        /**
         * funkcija vraca informacije o koeficijentu, eksponentu i je li eksponent pozitivan (1) ili je negativan/razlomak (0)
         * @param expr1 
         * @return polje double: na indeksu 0 je koeficijent, 1 je eksponent, 2 je li eksponent pozitivan
         * @author Ivana
         */    
        public static double[] coefAndExp(String expr1, JPanel parent){
            double[] ret = new double[3];
            double coef=0, exp=0, konstanta=0;
            
            if(expr1.contains("*")){
                ret[0]=Double.parseDouble(expr1.split("\\*")[0]);
            }else if(expr1.contains("x") && !(expr1.contains("*"))){
                if(expr1.contains("-"))
                    ret[0]=-1;
                else
                    ret[0]=1;
            }else if(!(expr1.contains("x") && !(expr1.contains("*")))){
                ret[0]=Double.parseDouble(expr1);
                konstanta=1;
            }
            
            if(konstanta==0){
                int jednako=-1;
                if(expr1.contains("(") && expr1.contains(")"))
                    jednako=1;
                else if(!expr1.contains("(") && !expr1.contains(")"))
                    jednako = 0;
                if(jednako==-1){
                    JOptionPane.showMessageDialog(parent, "Niste zatvorili sve zagrade!", "Greška", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                if(jednako==1){
                    ret[2]=0;
                    int start=expr1.indexOf("(");
                    int finish=expr1.indexOf(")");

                    String izmeduZagrada = expr1.substring(start+1, finish);
                    
                    int q=-1;
                    if(expr1.substring(start+1, finish).contains(("/"))){
                        q=expr1.substring(start+1, finish).indexOf("/");
                    }

                    int negative=0;
                    if(expr1.substring(start+1, finish).contains(("-")))
                        negative=1;

                    if(q!=-1){
                        double brojnik=Double.parseDouble(izmeduZagrada.split("/")[0]);
                        double nazivnik=Double.parseDouble(izmeduZagrada.split("/")[1]);

                        if(negative==1){
                            exp=-(brojnik/nazivnik);
                        }else if(negative==0){
                            exp=brojnik/nazivnik;
                        }
                    }else{
                        if(negative==1){
                            exp=-(Double.parseDouble(expr1.substring(start+1, finish)));
                        }else{
                            exp=Double.parseDouble(expr1.substring(start+1, finish));
                        }
                    }
                }else{
                    ret[2]=1;
                    if(expr1.contains("^")){
                        exp=Double.parseDouble(expr1.split("\\^")[1]);
                    }else{
                        exp=1;
                    }
                }
            }
            ret[1]=exp;
            return ret;
        }
        /**
         * 
         * @param expr koji je zapravo jedan clan u polinomu
         * @return vraca derivaciju unesenog clana
         * @param parent  prozor na koji će se "zakačiti" poruka o grešci
         * @author Dorotea
         */
        public String Der(String expr,JPanel parent){
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
                if(expr.charAt(i)=='('){
                    jednako--;
                }else if(expr.charAt(i)=='('){
                    jednako++;

                }     
            }

            if(jednako!=0){
                JOptionPane.showMessageDialog(parent, "Niste zatvorili sve zagrade!", "Greška", JOptionPane.ERROR_MESSAGE);
                return null;

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
                
                String izmeduZagrada=expr.substring(start+1, finish);

                int q=-1;
                if(expr.substring(start+1, finish).contains(("/"))){
                    q=expr.substring(start+1, finish).indexOf("/");
                }
                
                int negative=0;//provjera je li eksponent negativan
                if(expr.substring(start+1, finish).contains(("-")))
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
                        exp=-(Double.parseDouble(expr.substring(start+1, finish)));
                    }else{
                        exp=Double.parseDouble(expr.substring(start+1, finish));
                    }
                }
            }else{//slucaj kad nemamo zagrade u eskponentu
                if(expr.contains("^")){//x^nesto
                    int pr=expr.indexOf("^");
                    exp=Double.parseDouble(expr.substring(pr+1,expr.length()));
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
        
        /**
         * Sljedeća funkcija je po konstrukciji slična funkciji doOrderOfOperations iz EXPressionParser-a
         * @param ulaz je polinom koji zelimo derivirati
         * @param parent  prozor na koji će se "zakačiti" poruka o grešci
         * @return String koji sadrži derivaciju unesenog polinoma
         * @author Dorotea
         */
        public String deriviraj(String ulaz,JPanel parent){
            String rezultat="";
            int location=scanFromRight(ulaz,'+');
            if(location!=-1){
                String left=ulaz.substring(0,location);
                String right=ulaz.substring(location+1,ulaz.length());
                rezultat+=deriviraj(left,parent)+"+"+Der(right,parent);

            }else{
                location=scanFromRight(ulaz,'-');
                if(location!=-1){
                    if(location==0){
                        String right=ulaz.substring(location+1,ulaz.length());
                        rezultat+="-"+Der(right,parent);
                    }else{
                        String left=ulaz.substring(0,location);
                        String right=ulaz.substring(location+1,ulaz.length());
                        if(left.isEmpty()){
                            rezultat+=Der(right,parent);
                        }
                        else{
                            rezultat+=deriviraj(left,parent)+"-"+Der(right,parent);
                        }
                    } 
                }else{
                    rezultat+=Der(ulaz,parent);
                }
            }
            
          return rezultat;  
        }   
    
    
       /**
         * 
         * @param expr koji je polinom
         * @return vraca koliko parova zagrada se nalazi u stringu
         * vraca -1 ako nije jednak broj otvorenih i zatvorenih zagrada
         * inace vraca broj parova otvorenih i zatvorenih zagrada
         * @author Dorotea
         */
        
        public int zagrade(String expr){
            int brojac=0,z=0;
            for(int i=0; i<expr.length(); i++){
                if(expr.charAt(i)=='('){
                    brojac++;
                    z++;
                }
                else if(expr.charAt(i)==')'){
                    brojac--;
                }
            }
            if(brojac!=0){
                return -1;
            }
            
            return z;
        }
        
        /**
         * 
         * @param expr koji je polinom
         * @return vraca indeks otvorene zagrade i pripadne zatvorene
         * @author Dorotea
         */
        public int[] par(String expr){
            int[] rez=new int[2];
            int z=zagrade(expr);
            int brojac=0, provjera=0;
            for(int i=0; i<expr.length(); i++){
                if(expr.charAt(i)=='(' && provjera==0){
                    rez[0]=i;
                    provjera=1;
                }else if(expr.charAt(i)=='(' && provjera==1){
                    brojac++;
                }else if(expr.charAt(i)==')' && brojac!=0){
                    brojac--;
                }else if(expr.charAt(i)==')' && brojac==0){
                    rez[1]=i;
                    break;
                }
            }
            return rez;
        }
        
        /**
         * Sljedeća funkcija je po konstrukciji slična funkciji doOrderOfOperations iz EXPressionParser-a
         * @param ulaz je polinom koji zelimo "urediti" tj zapisati bez nepotrebnih zagrada npr. (x+x) je jednostavno x+x
         * @param parent  prozor na koji će se "zakačiti" poruka o grešci
         * @param pozitivan je varijabl au kojoj provjeravamo je li koeficijent negativan, 1 ako je pozitivan, 0 ako je negativan
         * @return String koji sadrži derivaciju unesenog polinoma
         * @author Dorotea
         */
        public String uredi(String ulaz,int pozitivan, JPanel parent){
            //ArrayList<String> list=new ArrayList<>();
            int location;
            int z=zagrade(ulaz);
        
            location=scanFromRight(ulaz,'+');
                    if(location!=-1){
                        String left=ulaz.substring(0,location);
                        String right=ulaz.substring(location+1,ulaz.length());
                        if(pozitivan==1)
                            uredi(left,1,parent);
                        else if(pozitivan==0)
                            uredi(left,0,parent);
                        if(pozitivan==1)
                            uredi(right,1,parent);
                        else if(pozitivan==0)
                            uredi(right,0,parent);
                    }else{
                        if(z>1){
                            int[] zag=par(ulaz);
                            if(zag[1]==ulaz.length()-1){
                                String left=ulaz.substring(zag[0]+1,zag[1]);
                                if(pozitivan==1)
                                    uredi(left,1,parent);
                                else if(pozitivan==0)
                                    uredi(left,0,parent);
                            }else{
                                String left=ulaz.substring(zag[0]+1,zag[1]);
                                String right=ulaz.substring(zag[1]+1,ulaz.length());
                                if(pozitivan==1)
                                    uredi(left,1,parent);
                                else if(pozitivan==0)
                                    uredi(left,0,parent);
                                if(pozitivan==1)
                                    uredi(right,1,parent);
                                else if(pozitivan==0)
                                    uredi(right,0,parent);
                            }
                        }else{
                            if(ulaz.length()>=2 && ulaz.charAt(ulaz.length()-1)==')' && ulaz.charAt(0)=='('){
                                String str=ulaz.substring(1,ulaz.length()-1);
                                if(pozitivan==1)
                                    uredi(str,1,parent);
                                else if(pozitivan==0)
                                    uredi(str,0,parent); 
                            }else{
                                location=scanFromRight(ulaz,'-');
                                if(location!=-1){
                                    String left=ulaz.substring(0,location);
                                    String right=ulaz.substring(location+1,ulaz.length());
                                    if(pozitivan==1)
                                        uredi(left,1,parent);
                                    else if(pozitivan==0)
                                        uredi(left,0,parent);
                                    if(pozitivan==1)
                                        uredi(right,0,parent);
                                    else if(pozitivan==0)
                                        uredi(right,1,parent);
                                }else if(location==0){
                                    String right=ulaz.substring(location+1,ulaz.length());
                                    if(pozitivan==1)
                                        uredi(right,0,parent);
                                    else if(pozitivan==0)
                                        uredi(right,1,parent);
                                }else{
                                    if(pozitivan==1)
                                        list.add(ulaz);
                                    else if(pozitivan==0)
                                        list.add("-"+ulaz);
                                }
                            }
                        }
                    }
            //System.out.println("list= "+list);
            String rezultat="";
            StringBuilder sb = new StringBuilder();
            for(String str:list){
                //System.out.println("prije sb="+sb);
                sb.append(str).append(" ");
                //System.out.println("str="+str);
                //System.out.println("kasnije sb="+sb);
            }
            rezultat=sb.substring(0, sb.length() - 1);
            //System.out.println("rezultat=" +rezultat);
            return rezultat;
        }
        
        public String pozivUredi(String ulaz, JPanel parent){
            list.clear();
            uredi(ulaz,1, parent);
            System.out.println("list= "+list);
            String prettyPrint="";
            int prvi=1;
            for(String i:list){
                if(i.equals(""))
                    continue;
                if(i.charAt(0)!='-'){
                    if(prvi!=1)
                        prettyPrint+="+"+i;
                    else{
                        prettyPrint+=i;
                        prvi=0;
                    }
                }else{
                    prettyPrint+=i;
                    if(prvi==1)
                        prvi=0;
                }
            }
            System.out.println("pretty= "+prettyPrint);
            return prettyPrint;
        }
        
        /**
         * Pretvara zapis oblika npr 2x+3x^2 u 2*x+3*x^2
         * @param poly polinom reprezentiran kao lista njegovih članova
         * @return polinom u formatu sa *
         * @author Ivana
         */
        public ArrayList<String> starFormat(ArrayList<String> poly){
            ArrayList<String> pomocni = new ArrayList<>();
            for(String i: poly){
                if(!i.contains("x")){
                    pomocni.add(i);
                }else if(i.contains("x")){
                    if(i.contains("*"))
                        pomocni.add(i);
                    else if(i.charAt(0)!='x'){
                        String razdvoji[] = i.split("x");
                        String pretvoren ="";
                        if(razdvoji.length==2)
                            pretvoren+=razdvoji[0] + "*x" + razdvoji[1];
                        else
                            pretvoren+=razdvoji[0] + "*x";
                        
                        pomocni.add(pretvoren);
                    }
                    else
                        pomocni.add(i);
                }
            }
            return pomocni;
        }
}
