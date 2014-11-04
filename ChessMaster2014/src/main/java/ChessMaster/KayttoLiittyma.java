/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessMaster;

/**
 *
 * @author Sebbe
 */
public class KayttoLiittyma {
    
    private Pelilauta pelilauta;

    public KayttoLiittyma(Pelilauta pelilauta) {
        this.pelilauta = pelilauta;
    }
    
    public void kaynnista() {
        pelilauta.uusiPeli();
    }

    
    
}
