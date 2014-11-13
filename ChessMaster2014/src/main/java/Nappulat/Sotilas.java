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
public class Sotilas extends Nappula {

    public Sotilas(String vari) {
        super(vari);
        if (vari.equals("valkoinen")) {
            setTyyppi(Tyyppi.VSOTILAS);
        } else {
            setTyyppi(Tyyppi.MSOTILAS);
        }

    }

}
