/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessMaster;

import Nappulat.Nappula;

/**
 *
 * @author Sebbe
 */
public class Ruutu {
    
    private Nappula nappula;

    public void asetaNappula(Nappula nappula) {
        this.nappula = nappula;
    }
    
    public void poistaNappula(){
        this.nappula = null;
    }

    public Nappula getNappula() {
        return nappula;
    }
    
    
    
}
