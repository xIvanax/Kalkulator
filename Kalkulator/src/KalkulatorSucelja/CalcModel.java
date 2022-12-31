/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package KalkulatorSucelja;

import Kalkulator.CalculatorInputException;
import java.util.function.DoubleBinaryOperator;

/**
 *
 * @author ivana
 */
public interface CalcModel {
    /**
     * Prijava Listenera koje treba obavijestiti o promjeni vrijednosti pohranjene u kalkulatoru
     * @param l 
     */
    void addCalcValueListener(CalcValueListener l);
    /**
     * Odjava listenera s popisa listenera koje treba obavijestiti o promjeni vrijednosti pohranjene u kalkulatoru
     * @param l 
     */
    void removeCalcValueListener(CalcValueListener l);
    /**
     * 
     * @return vrijednost pohranjena u kalkulatoru
     */
    public double getValue();
    /**
     * vrijednost broja koji treba upisati u kalkulator
     * @param value broj koji treba upisati
     */
    public void setValue(double value);
    /**
     * Resetira trenutno vrijednost (započinje unos nove operacije) i vraća kalkulator na editable stanje
     * @return 
     */
    boolean isEditable();
    /**
     * soft delete, tj. čistimo samo unos
     */
    public void clear();
    /**
     * hard delete, tj. čistimo sve varijable i uklanjamo aktivni operand
     */
    public void clearAll();
    /**
     * negativan broj postaje pozitivan i obrnuto
     */
    public void swapSign();
    /**
     * umetanje decimalne točke i provjera ima li broj već decimalnu točku
     * @throws CalculatorInputException ako broj već ima decimalnu točku
     */
    public void insertDecimalPoint();
    /**
     * upisivanje nove znamenke i prilagodba formata s obzirom na to je li prvi uneseni broj 0 ili ne
     * @param digit znamenka koju treba dodati
     * @throws CalculatorInputException ako bi novonastali broj postao prevelik
     */
    public void insertDigit(int digit);
    /**
     * postavi activeOperand na operand
     * @param operand novi aktivni operand
     */
    public void setActiveOperand(double operand);
    /**
     * Provjera je li postavljen aktivni operand
     * @return true ako je aktivni operand postavljen, false inače
     */
    boolean isActiveOperandSet();
    /**
     * dohvaćanje aktivnog operanda (ukoliko je validan)
     * @return aktivni operand
     */
    public double getActiveOperand();
    /**
     * uklanjanje aktivnog operanda
     */
    public void clearActiveOperand();
    /**
     * dohvat zakazane binarne operacije
     * @return zakazana operacija (null ako nema zakazane operacije)
     */
    DoubleBinaryOperator getPendingBinaryOperation();
    /**
     * postavljanje zakazane operacije (ako zakazana operacija već postoji, ovaj je poziv nadjačava predanom vrijednošću
     */
    void setPendingBinaryOperation(DoubleBinaryOperator op);
    /**
     * Vraca tekst koji treba pokazati na zaslonu
     * @return tekst za prikaz na zaslonu kalkulatora
     */
    String toString();
}
