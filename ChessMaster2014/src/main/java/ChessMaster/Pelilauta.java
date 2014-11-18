/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessMaster;

import Nappulat.Torni;
import Nappulat.Lahetti;
import Nappulat.Kuningatar;
import Nappulat.Sotilas;
import Nappulat.Nappula;
import Nappulat.Ratsu;
import Nappulat.Kuningas;
import java.util.ArrayList;

/**
 * Luokalla on metodeja pelilaudan muunteluun.  
 *
 * @author Sebbe
 */
public class Pelilauta {

    /**
     * Kaksiuloitteinen array ruutuja
     */
    private Ruutu[][] ruudukko;

    /**
     * Metodi kutsuu kahta metodia ja ne tekevät uuden pelilaudan jossa on 64
     * nappulaa.
     */
    public void uusiPeli() {
        luoRuudukko();
        lisaaNappulatLaudalle();

    }

    /**
     * Metodi alustaa ruudukon ja lisää jokaiseen paikkaan uuden Ruudun
     */
    public void luoRuudukko() {
        ruudukko = new Ruutu[8][8];
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                ruudukko[i][t] = new Ruutu();
            }
        }
    }

    /**
     * Metodi luo jokaisen tarvittavan Nappulan shakkipelin alkuun ja asettaa ne
     * oikeisiin ruutuihin.
     */
    public void lisaaNappulatLaudalle() {
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

    /**
     * Metodi siirtää nappulan joka sijaitsee vanhaX ja vanhaY koordinaattien
     * päättämässä paikassa uusiX:n ja uusiY:n määrittämään paikkaan.
     *
     * @param vanhaX Nappulan jota halutaan siirtää x sijainti ruudukossa
     * @param vanhaY Nappulan jota halutaan siirtää y sijainti ruudukossa
     * @param uusiX x sijainti minne halutaan siirtää nappula
     * @param uusiY y sijainti minne halutaan siirtää nappula
     */
    public void siirra(int vanhaX, int vanhaY, int uusiX, int uusiY) {

        Nappula nappula = ruudukko[vanhaX][vanhaY].getNappula();

        if (ruudukko[uusiX][uusiY].getNappula() == null) {
            ruudukko[vanhaX][vanhaY].poistaNappula();
            ruudukko[uusiX][uusiY].asetaNappula(nappula);

        } else if (!ruudukko[vanhaX][vanhaY].getNappula().onkoSamaVari(ruudukko[uusiX][uusiY].getNappula())) {
            ruudukko[vanhaX][vanhaY].poistaNappula();
            ruudukko[uusiX][uusiY].poistaNappula();
            ruudukko[uusiX][uusiY].asetaNappula(nappula);
        }
    }

    public Ruutu[][] getRuudukko() {
        return ruudukko;
    }

    public void setRuudukko(Ruutu[][] ruudukko) {
        this.ruudukko = ruudukko;
    }

    /**
     * Metodi tarkistaa uhkaako mikään valkoinen nappula mustaa kuningasta
     *
     * @return Palauttaa totta jos valkoinen nappula uhkaa mustaa kuningasta ei totta muuten
     */
    public boolean onkoMustaShakki() {
        int x = 0;
        int y = 0;
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS) {
                    x = i;
                    y = t;
                    break;
                }
            }
        }

        if ((x + 1 <= 7 && y - 1 >= 0 && ruudukko[x + 1][y - 1].getNappula() != null && ruudukko[x + 1][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS)
                || (x + 1 <= 7 && y + 1 <= 7 && ruudukko[x + 1][y + 1].getNappula() != null && ruudukko[x + 1][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS)) {
            return true;
        }
        if ((x - 2 >= 0 && y - 1 >= 0 && ruudukko[x - 2][y - 1].getNappula() != null && ruudukko[x - 2][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU)
                || (x - 1 >= 0 && y - 2 >= 0 && ruudukko[x - 1][y - 2].getNappula() != null && ruudukko[x - 1][y - 2].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU)
                || (x - 2 >= 0 && y + 1 <= 7 && ruudukko[x - 2][y + 1].getNappula() != null && ruudukko[x - 2][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU)
                || (x - 1 >= 0 && y + 2 <= 7 && ruudukko[x - 1][y + 2].getNappula() != null && ruudukko[x - 1][y + 2].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU)
                || (x + 2 <= 7 && y - 1 >= 0 && ruudukko[x + 2][y - 1].getNappula() != null && ruudukko[x + 2][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU)
                || (x + 1 <= 7 && y - 2 >= 0 && ruudukko[x + 1][y - 2].getNappula() != null && ruudukko[x + 1][y - 2].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU)
                || (x + 2 <= 7 && y + 1 <= 7 && ruudukko[x + 2][y + 1].getNappula() != null && ruudukko[x + 2][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU)
                || (x + 1 <= 7 && y + 2 <= 7 && ruudukko[x + 1][y + 2].getNappula() != null && ruudukko[x + 1][y + 2].getNappula().getTyyppi() == Nappula.Tyyppi.VRATSU)) {
            return true;
        }

        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && ruudukko[x + i][y].getNappula() != null
                    && (ruudukko[x + i][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR || ruudukko[x + i][y].getNappula().getTyyppi() == Nappula.Tyyppi.VTORNI)) {
                return true;
            }
            if (x + i <= 7 && ruudukko[x + i][y].getNappula() != null && ruudukko[x + i][y].getNappula().getVari().equals("musta")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (y + i <= 7 && ruudukko[x][y + i].getNappula() != null
                    && (ruudukko[x][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR || ruudukko[x][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.VTORNI)) {
                return true;
            }
            if (y + i <= 7 && ruudukko[x][y + i].getNappula() != null && ruudukko[x][y + i].getNappula().getVari().equals("musta")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && ruudukko[x - i][y].getNappula() != null
                    && (ruudukko[x - i][y].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR || ruudukko[x - i][y].getNappula().getTyyppi() == Nappula.Tyyppi.VTORNI)) {
                return true;
            }
            if (x - i >= 0 && ruudukko[x - i][y].getNappula() != null && ruudukko[x - i][y].getNappula().getVari().equals("musta")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (y - i >= 0 && ruudukko[x][y - i].getNappula() != null
                    && (ruudukko[x][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR || ruudukko[x][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.VTORNI)) {
                return true;
            }
            if (y - i >= 0 && ruudukko[x][y - i].getNappula() != null && ruudukko[x][y - i].getNappula().getVari().equals("musta")) {
                break;
            }
        }

        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null
                    && (ruudukko[x + i][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR || ruudukko[x + i][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI)) {
                return true;
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && ruudukko[x + i][y + i].getNappula().getVari().equals("musta")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null
                    && (ruudukko[x + i][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR || ruudukko[x + i][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI)) {
                return true;
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && ruudukko[x + i][y - i].getNappula().getVari().equals("musta")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null
                    && (ruudukko[x - i][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR || ruudukko[x - i][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI)) {
                return true;
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && ruudukko[x - i][y + i].getNappula().getVari().equals("musta")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null
                    && (ruudukko[x - i][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGATAR || ruudukko[x - i][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.VLAHETTI)) {
                return true;
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && ruudukko[x - i][y - i].getNappula().getVari().equals("musta")) {
                break;
            }
        }

        return false;
    }

    /**
     * Metodi tarkistaa uhkaako mikään musta nappula valkoista kuningasta
     *
     * @return Palauttaa totta jos musta nappula uhkaa valkoista kuningasta ei totta muuten
     */
    public boolean onkoValkoinenShakki() {
        int x = 0;
        int y = 0;
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS) {
                    x = i;
                    y = t;
                    break;
                }
            }
        }

        if ((x - 1 >= 0 && y - 1 >= 0 && ruudukko[x - 1][y - 1].getNappula() != null && ruudukko[x - 1][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS)
                || (x - 1 >= 0 && y + 1 <= 7 && ruudukko[x - 1][y + 1].getNappula() != null && ruudukko[x - 1][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS)) {
            return true;
        }
        if ((x - 2 >= 0 && y - 1 >= 0 && ruudukko[x - 2][y - 1].getNappula() != null && ruudukko[x - 2][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU)
                || (x - 1 >= 0 && y - 2 >= 0 && ruudukko[x - 1][y - 2].getNappula() != null && ruudukko[x - 1][y - 2].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU)
                || (x - 2 >= 0 && y + 1 <= 7 && ruudukko[x - 2][y + 1].getNappula() != null && ruudukko[x - 2][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU)
                || (x - 1 >= 0 && y + 2 <= 7 && ruudukko[x - 1][y + 2].getNappula() != null && ruudukko[x - 1][y + 2].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU)
                || (x + 2 <= 7 && y - 1 >= 0 && ruudukko[x + 2][y - 1].getNappula() != null && ruudukko[x + 2][y - 1].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU)
                || (x + 1 <= 7 && y - 2 >= 0 && ruudukko[x + 1][y - 2].getNappula() != null && ruudukko[x + 1][y - 2].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU)
                || (x + 2 <= 7 && y + 1 <= 7 && ruudukko[x + 2][y + 1].getNappula() != null && ruudukko[x + 2][y + 1].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU)
                || (x + 1 <= 7 && y + 2 <= 7 && ruudukko[x + 1][y + 2].getNappula() != null && ruudukko[x + 1][y + 2].getNappula().getTyyppi() == Nappula.Tyyppi.MRATSU)) {
            return true;
        }

        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && ruudukko[x + i][y].getNappula() != null
                    && (ruudukko[x + i][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR || ruudukko[x + i][y].getNappula().getTyyppi() == Nappula.Tyyppi.MTORNI)) {
                return true;
            }
            if (x + i <= 7 && ruudukko[x + i][y].getNappula() != null && ruudukko[x + i][y].getNappula().getVari().equals("valkoinen")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (y + i <= 7 && ruudukko[x][y + i].getNappula() != null
                    && (ruudukko[x][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR || ruudukko[x][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.MTORNI)) {
                return true;
            }
            if (y + i <= 7 && ruudukko[x][y + i].getNappula() != null && ruudukko[x][y + i].getNappula().getVari().equals("valkoinen")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && ruudukko[x - i][y].getNappula() != null
                    && (ruudukko[x - i][y].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR || ruudukko[x - i][y].getNappula().getTyyppi() == Nappula.Tyyppi.MTORNI)) {
                return true;
            }
            if (x - i >= 0 && ruudukko[x - i][y].getNappula() != null && ruudukko[x - i][y].getNappula().getVari().equals("valkoinen")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (y - i >= 0 && ruudukko[x][y - i].getNappula() != null
                    && (ruudukko[x][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR || ruudukko[x][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.MTORNI)) {
                return true;
            }
            if (y - i >= 0 && ruudukko[x][y - i].getNappula() != null && ruudukko[x][y - i].getNappula().getVari().equals("valkoinen")) {
                break;
            }
        }

        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null
                    && (ruudukko[x + i][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR || ruudukko[x + i][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI)) {
                return true;
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && ruudukko[x + i][y + i].getNappula().getVari().equals("valkoinen")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null
                    && (ruudukko[x + i][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR || ruudukko[x + i][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI)) {
                return true;
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && ruudukko[x + i][y - i].getNappula().getVari().equals("valkoinen")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null
                    && (ruudukko[x - i][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR || ruudukko[x - i][y + i].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI)) {
                return true;
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && ruudukko[x - i][y + i].getNappula().getVari().equals("valkoinen")) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null
                    && (ruudukko[x - i][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGATAR || ruudukko[x - i][y - i].getNappula().getTyyppi() == Nappula.Tyyppi.MLAHETTI)) {
                return true;
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && ruudukko[x - i][y - i].getNappula().getVari().equals("valkoinen")) {
                break;
            }
        }

        return false;
    }

    /**
     * Metodi tarkistaa onko mustalla shakkimatti tilanne
     * @return Palauttaa totta jos valkoinen on voittanut
     */
    public boolean onkoMustaShakkiMatti() {
        int x = 0;
        int y = 0;

        Pelilauta kopio = new Pelilauta();
        kopio.setRuudukko(ruudukko);

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS) {
                    x = i;
                    y = t;
                    break;
                }
            }
        }

        if (!kopio.onkoMustaShakki()) {
            return false;
        }

        ArrayList<String> mahdollisetSiirrot = ruudukko[x][y].getNappula().mahdollisetSiirrot(x, y, ruudukko);

        for (String siirto : mahdollisetSiirrot) {
            int uusiX = Integer.parseInt("" + siirto.charAt(0));
            int uusiY = Integer.parseInt("" + siirto.charAt(1));

            if (!kopio.onkoMustaShakki()) {
                setRuudukko(kopio.getRuudukko());
                return false;
            }
            setRuudukko(kopio.getRuudukko());
        }

        return true;
    }

    /**
     * Metodi tarkistaa onko valkoisella shakkimatti tilanne
     * @return Palauttaa totta jos musta on voittanut
     */
    public boolean onkoValkoinenShakkiMatti() {
        return false;
    }

}
