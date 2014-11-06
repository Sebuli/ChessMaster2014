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
public class Main {

    public static void main(String args[]){
        Pelilauta pelilauta = new Pelilauta();
        KayttoLiittyma kayttoliittyma = new KayttoLiittyma(pelilauta);
        kayttoliittyma.kaynnista();
    }
    
    
}
