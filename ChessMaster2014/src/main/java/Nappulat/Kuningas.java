/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

/**
 * Luokka perii Nappula luokan ja asettaa tyyppiksi joko VKUNINGAS tai MKUNINGAS
 * @author Sebbe
 */
public class Kuningas extends Nappula {

    public Kuningas(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VKUNINGAS);
        } else {
            setTyyppi(Tyyppi.MKUNINGAS);
        }
    }

}
