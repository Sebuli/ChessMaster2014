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
public class Torni extends Nappula{

    
    
    public Torni(String vari) {
        super(vari);
        if ( vari.equals("valkoinen")){
            setTyyppi(Tyyppi.VTORNI);
        }else{
            setTyyppi(Tyyppi.MTORNI);
        }
    }
    

   
    
}
