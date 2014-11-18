/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

/**
 * Luokka perii Nappula luokan ja asettaa tyyppiksi joko VKUNINGATAR tai MKUNINGATAR
 * @author Sebbe
 */
public class Kuningatar extends Nappula {

    public Kuningatar(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VKUNINGATAR);
        } else {
            setTyyppi(Tyyppi.MKUNINGATAR);
        }
    }

}
