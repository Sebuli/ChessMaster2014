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
public class Ratsu extends Nappula {

    public Ratsu(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VRATSU);
        } else {
            setTyyppi(Tyyppi.MRATSU);
        }
    }

}
