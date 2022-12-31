/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kalkulator;

import KalkulatorSucelja.CalcModel;
import KalkulatorSucelja.CalcValueListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

/**
 *
 * @author ivana
 */
public class CalcModelImpl implements CalcModel{
    private boolean positive; //positive označava je li uneseni broj pozitivan ili ne
    private String inputDigits; //inputDigits je zapravo unos u kalkulator u obliku Stringa
    private double inputValue; //inputValue je brojčana vrijednost unosa inputDigits
    private double activeOperand; //activeOperand je operand s kojim trenutno računamo
    private DoubleBinaryOperator pendingOperation; //operacija koju treba izvršiti
    private List<CalcValueListener> listeners; //pamti aktivne vrijednosti
    private boolean isEditable; //možemo li trenutno unositi vrijednosti ili ne
    /**
     * tipični konstruktor, sve inicijalizira na "prazno"
     */
    public CalcModelImpl(){
        inputDigits = "";
        inputValue = 0;
        activeOperand = Double.NaN;
        positive = true;
        isEditable = true;
        listeners = new ArrayList<>();
    }
    
    @Override
    public void addCalcValueListener(CalcValueListener l){
        listeners.add(l);
    }
    
    @Override
    public void removeCalcValueListener(CalcValueListener l){
        listeners.remove(l);
    }
    
    @Override
    public double getValue(){
        return inputValue;
    }
    
    @Override
    public void setValue(double value){
        positive = value >= 0;
        inputValue = value;
        inputDigits = String.valueOf(value);
        isEditable = false;
        for(CalcValueListener l : listeners)
            l.valueChanged(this);
    }
    
    @Override
    public void clear(){
        positive = true;
        inputDigits = "";
        inputValue = 0;
        this.isEditable = true;
        for(CalcValueListener l : listeners)
            l.valueChanged(this);
    }
    
    @Override
    public void clearAll(){
        positive = true;
        inputDigits = "";
        inputValue = 0;
        activeOperand = Double.NaN;
        pendingOperation = null;
        isEditable = true;
        for(CalcValueListener l : listeners)
            l.valueChanged(this);
    }
    
    @Override
    public void swapSign(){
        if(!isEditable)
            throw new CalculatorInputException();
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
        for(CalcValueListener l : listeners)
            l.valueChanged(this);
    }
    
    @Override
    public void insertDecimalPoint() throws CalculatorInputException {
        if(!isEditable)
            throw new CalculatorInputException();
        if(inputDigits.contains(".") || inputDigits.equals(""))
            throw new CalculatorInputException();
        inputDigits += ".";
    }
    
    @Override
    public void insertDigit(int digit) throws CalculatorInputException {
        if(!isEditable)
            throw new CalculatorInputException();
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
        for(CalcValueListener l : listeners)
            l.valueChanged(this);
    }
    
    @Override
    public void setActiveOperand(double operand){
        activeOperand = operand;
    }
    
    @Override
    public double getActiveOperand()throws IllegalStateException{
        if(isActiveOperandSet())
            return activeOperand;
        else 
            throw new IllegalStateException();
    }
    
    @Override
    public void clearActiveOperand() {
        activeOperand = Double.NaN;
    }
    
    @Override
    public String toString(){
        return inputDigits;
    }
    
    @Override
    public boolean isEditable() {
        return isEditable;
    }

    @Override
    public boolean isActiveOperandSet() {
        return !Double.isNaN(activeOperand);
    }

    @Override
    public DoubleBinaryOperator getPendingBinaryOperation() {
        return pendingOperation;
    }
    
    @Override
    public void setPendingBinaryOperation(DoubleBinaryOperator op) {
        pendingOperation = op;
    }
}
