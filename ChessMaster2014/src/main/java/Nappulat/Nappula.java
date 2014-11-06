/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

/**
 *
 * @author Sebbe
 */
public class Nappula {
    
    public enum Tyyppi { KUNINGAS, KUNINGATAR, LAHETTI, RATSU, SOTILAS, TORNI };
    
    private String vari;

    Tyyppi tyyppi;
    
    public Nappula(String vari){
        this.vari = vari;
       ;
    }

    public String getVari() {
        return vari;
    }

    public void setTyyppi(Tyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }
    
    public Tyyppi getTyyppi() {
        return tyyppi;
    }
    
    public boolean onkoSamaVari(Nappula nappula){
        return vari.equals(nappula.getVari());
    }
    
    
    
}
