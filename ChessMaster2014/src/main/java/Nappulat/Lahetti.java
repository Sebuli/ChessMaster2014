/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

/**
 * Luokka perii Nappula luokan ja asettaa tyyppiksi joko VLAHETTI tai MLAHETTI
 * @author Sebbe
 */
public class Lahetti extends Nappula {

    public Lahetti(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VLAHETTI);
        } else {
            setTyyppi(Tyyppi.MLAHETTI);
        }
    }

}
