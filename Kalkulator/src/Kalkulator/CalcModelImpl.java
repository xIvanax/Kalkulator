/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kalkulator;

/**
 *
 * @author ivana
 */
public class CalcModelImpl {
    /**
    *positive označava je li uneseni broj pozitivan ili ne
    */
    private boolean positive;
    /**
     * inputDigits je zapravo unos u kalkulator u obliku Stringa
     */
    private String inputDigits;
    /**
     * inputValue je brojčana vrijednost unosa inputDigits
     */
    private double inputValue;
    /**
     * activeOperand je operand s kojim trenutno računamo
     */
    private double activeOperand;
    /**
     * tipični konstruktor, sve inicijalizira na "prazno"
     */
    public CalcModelImpl(){
        inputDigits="";
        inputValue=0;
        activeOperand=Double.NaN;
        positive=true;
    }
    
    public double getValue(){
        return inputValue;
    }
    
    public void setValue(double value){
        if(value >= 0)
            positive = true;
        else 
            positive = false;
        inputValue = value;
        inputDigits = String.valueOf(value);
    }
    
    public void clear(){
        positive = true;
        inputDigits = "";
        inputValue = 0;
    }
    
    public void clearAll(){
        positive = true;
        inputDigits = "";
        inputValue = 0;
        activeOperand = Double.NaN;
    }
    
    public void swapSign(){
        if(positive){
            if(!inputDigits.equals(""))
                inputDigits = "-" + inputDigits;
            positive = false;
        }
        else {
            if(!inputDigits.equals(""))
                inputDigits = inputDigits.substring(1);
            positive = true;
        }
        
        inputValue = 0 - inputValue;
    }
    
    public void insertDecimalPoint() throws CalculatorInputException {
        if(inputDigits.contains(".") || inputDigits.equals(""))
            throw new CalculatorInputException();
        inputDigits += ".";
    }
    
    public void insertDigit(int digit) throws CalculatorInputException {
        String helper = inputDigits;
        /**
         * ako je prva znamenka 0 i unosimo novu znamenku, ne želimo uzimati tu nulu kao dio broja pri pretvorbi
         */
        if(helper.equals("0")){
            helper = String.valueOf(digit);
        }
        else {
            helper += digit;
        }
        
        inputValue = Double.parseDouble(helper);
        /**
         * kalkulator daje iznimku ako je inputValue beskonačna
         */
        if(Double.isFinite(inputValue)) {
            inputDigits = helper;
        }
        else 
            throw new CalculatorInputException();
    }
    
    public void setActiveOperand(double operand){
        activeOperand = operand;
    }
    
    public double getActiveOperand(){
        if(!Double.isNaN(activeOperand))
            return activeOperand;
        else 
            throw new IllegalStateException();
    }
    
    public void clearActiveOperand() {
        activeOperand = Double.NaN;
    }
    
    public String toString(){
        return inputDigits;
    }
}
