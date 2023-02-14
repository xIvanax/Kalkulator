/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HelperClasses;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Polinomne operacije na jednom mjestu
 * @author Ivana
 */
public class PolyOps {
        ArrayList<String> clanovi;
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
         * ovo bi trebalo mozda negdje drugdje prebaciti, bas i ne spada tu
         * @param ulaz
         * @param token
         * @return 
         */
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
                if(info==null){
                    return null;
                }
                coef=info[0];
                exp=info[1];
                ArrayList<String> help = new ArrayList<>();
                for(String expr2:poly2){
                    double info2[] = new double[3];
                    info2 = coefAndExp(expr2);
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
                resClanovi=polyAdd(i,resClanovi);
            }
            return resClanovi;
        }
        /**
         * fja kao argumente uzima clanove dva polinoma i zbraja te polinome
         * @param poly1 clanovi prvog polinoma
         * @param poly2 clanovi drugog polinoma
         * @return rezultat zbrajanja poly1 i poly2 u obliku liste clanova
         * @author Ivana
         */
        public ArrayList<String> polyAdd(ArrayList<String> poly1, ArrayList<String> poly2){
            String res="";
            ArrayList<String> clanoviRes = new ArrayList<String>(); 
            double coef=0, exp=0, konstanta=0;
            double coef2=0, exp2=0, konstanta2=0;
            int flag=0; 
            int eksponentJeRazlomakIliNegativan=0;
            
            for(String expr1:poly1){
                double info[] = new double[3];
                info = coefAndExp(expr1);
                if(info==null){
                    return null;
                }
                coef=info[0];
                exp=info[1];
                for(String expr2:poly2){
                    double info2[] = new double[3];
                    info2 = coefAndExp(expr2);
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
                info = coefAndExp(expr1);
                if(info==null){
                    return null;
                }
                coef=info[0];
                exp=info[1];
                for(String expr2:clanoviRes){
                    double info2[] = new double[3];
                    info2 = coefAndExp(expr2);
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
            return output;
        }
        /**
         * funkcija vraca informacije o koeficijentu, eksponentu i je li eksponent pozitivan (1) ili je negativan/razlomak (0)
         * @param expr1 
         * @return polje double: na indeksu 0 je koeficijent, 1 je eksponent, 2 je li eksponent pozitivan
         * @author Ivana
         */    
        public double[] coefAndExp(String expr1){
            double[] ret = new double[3];
            double coef=0, exp=0, konstanta=0;
            
            if(expr1.contains("*")){//dakle sadrzi i *x
                ret[0]=Double.parseDouble(expr1.split("\\*")[0]);
            }else if(expr1.contains("x") && !(expr1.contains("*"))){//ako je koeficijent jednak 1
                if(expr1.contains("-"))
                    ret[0]=-1;
                else
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
                    return null;
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
         * 
         * @param expr
         * @return
         * @author Dorotea
         */
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
                /**
                 * mislim da bi se ovo trebalo nekak handleat jer ovak i ak ne valja broj zagrada se nis ne dogodi neg kod sam nastavi ko da sve valja
                 * @Ivana
                 */
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
         * @param ulaz
         * @return String koji sadrži derivaciju unesenog polinoma
         * @author Dorotea
         */
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
            
            if(rezultat.charAt(rezultat.length()-1)=='+' || rezultat.charAt(rezultat.length()-1)=='-'){
                rezultat=rezultat.substring(0,rezultat.length()-1);
            }
            return rezultat;
        }        
}
