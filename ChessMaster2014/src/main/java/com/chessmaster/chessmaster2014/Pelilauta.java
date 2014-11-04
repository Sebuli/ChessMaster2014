/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chessmaster.chessmaster2014;

import com.chessmaster.chessmaster2014.Nappulat.Torni;
import com.chessmaster.chessmaster2014.Nappulat.Lahetti;
import com.chessmaster.chessmaster2014.Nappulat.Kuningatar;
import com.chessmaster.chessmaster2014.Nappulat.Sotilas;
import com.chessmaster.chessmaster2014.Nappulat.Nappula;
import com.chessmaster.chessmaster2014.Nappulat.Ratsu;
import com.chessmaster.chessmaster2014.Nappulat.Kuningas;
import java.util.Arrays;

/**
 *
 * @author Sebbe
 */
public class Pelilauta {

    private Ruutu[][] ruudukko;

    public void uusiPeli() {
        ruudukko = new Ruutu[8][8];

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                ruudukko[i][t] = new Ruutu();
            }
        }

        for (int i = 0; i <= 7; i++) {
            if (i == 0 || i == 7) {
                Torni mtorni = new Torni("musta");
                Torni vtorni = new Torni("valkoinen");
                ruudukko[0][i].asetaNappula(mtorni);
                ruudukko[7][i].asetaNappula(vtorni);
            }
            if (i == 1 || i == 6) {
                Ratsu mratsu = new Ratsu("musta");
                Ratsu vratsu = new Ratsu("valkoinen");
                ruudukko[0][i].asetaNappula(mratsu);
                ruudukko[7][i].asetaNappula(vratsu);
            }
            if (i == 2 || i == 5) {
                Lahetti mlahetti = new Lahetti("musta");
                Lahetti vlahetti = new Lahetti("valkoinen");
                ruudukko[0][i].asetaNappula(mlahetti);
                ruudukko[7][i].asetaNappula(vlahetti);
            }
            if (i == 3) {
                Kuningas mkuningas = new Kuningas("musta");
                Kuningas vkuningas = new Kuningas("valkoinen");
                ruudukko[0][i].asetaNappula(mkuningas);
                ruudukko[7][i].asetaNappula(vkuningas);
            }
            if (i == 4) {
                Kuningatar mkuningatar = new Kuningatar("musta");
                Kuningatar vkuningatar = new Kuningatar("valkoinen");
                ruudukko[0][i].asetaNappula(mkuningatar);
                ruudukko[7][i].asetaNappula(vkuningatar);
            }

            Sotilas msotilas = new Sotilas("musta");
            Sotilas vsotilas = new Sotilas("valkoinen");
            ruudukko[1][i].asetaNappula(msotilas);
            ruudukko[6][i].asetaNappula(vsotilas);

        }

    }
    
    public void siirra(int vanhaX, int vanhaY, int uusiX, int uusiY){
        
        Nappula nappula = ruudukko[vanhaX][vanhaY].getNappula();
        
        
        if (ruudukko[uusiX][uusiY].getNappula() == null){
            ruudukko[vanhaX][vanhaY].poistaNappula();
            ruudukko[uusiX][uusiY].asetaNappula(nappula);
                    
        }else if (!onkoSamaVari(nappula, ruudukko[uusiX][uusiY].getNappula())){
            ruudukko[vanhaX][vanhaY].poistaNappula();
            ruudukko[uusiX][uusiY].asetaNappula(nappula);
            
        }else{
            System.out.println("Ei sallittu siirto!");
        }
    }
    
    public boolean onkoSamaVari(Nappula ensimmainen, Nappula toinen){
        if (ensimmainen.getVari().equals("valkoinen") && toinen.getVari().equals("valkoinen")){
            return true;
            
        } return ensimmainen.getVari().equals("musta") && toinen.getVari().equals("musta");
    }

    public Ruutu[][] getRuudukko() {
        return ruudukko;
    }
    
    


}
