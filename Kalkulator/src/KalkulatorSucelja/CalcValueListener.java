/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package KalkulatorSucelja;

/**
 * Služi za pamćenje vrijednosti unesenih u kalkulator nad kojima vršimo operacije
 * @author ivana
 */
public interface CalcValueListener {
    /**
     * Metoda koja se poziva kao rezultat promjene vrijednosti zapisane u kalkulator
     * @param model model kalkulatora u kojemu je došlo do promjene; ne može biti null
     */
    void valueChanged(CalcModel model);
}
