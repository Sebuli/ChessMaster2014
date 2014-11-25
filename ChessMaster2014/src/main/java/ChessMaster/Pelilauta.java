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
     * Metodi kutsuu kahta metodia ja ne tekevÃ¤t uuden pelilaudan jossa on 64
     * nappulaa.
     */
    public void uusiPeli() {
        luoRuudukko();
        lisaaNappulatLaudalle();

    }

    /**
     * Metodi alustaa ruudukon ja lisÃ¤Ã¤ jokaiseen paikkaan uuden Ruudun
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
            if (i == 4) {
                Kuningas mkuningas = new Kuningas("musta");
                Kuningas vkuningas = new Kuningas("valkoinen");
                ruudukko[0][i].asetaNappula(mkuningas);
                ruudukko[7][i].asetaNappula(vkuningas);
            }
            if (i == 3) {
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
     * Metodi siirtÃ¤Ã¤ nappulan joka sijaitsee vanhaX ja vanhaY koordinaattien
     * pÃ¤Ã¤ttÃ¤mÃ¤ssÃ¤ paikassa uusiX:n ja uusiY:n mÃ¤Ã¤rittÃ¤mÃ¤Ã¤n paikkaan.
     *
     * @param vanhaX Nappulan jota halutaan siirtÃ¤Ã¤ x sijainti ruudukossa
     * @param vanhaY Nappulan jota halutaan siirtÃ¤Ã¤ y sijainti ruudukossa
     * @param uusiX x sijainti minne halutaan siirtÃ¤Ã¤ nappula
     * @param uusiY y sijainti minne halutaan siirtÃ¤Ã¤ nappula
     */
    public void siirra(int vanhaX, int vanhaY, int uusiX, int uusiY) {

        Nappula nappula = ruudukko[vanhaX][vanhaY].getNappula();
        
        if (ruudukko[uusiX][uusiY].getNappula() == null) {
            if (vanhaX == 4 && nappula.getTyyppi() == Nappula.Tyyppi.MSOTILAS && uusiY != vanhaY
                    && ruudukko[uusiX - 1][uusiY].getNappula() != null && ruudukko[uusiX - 1][uusiY].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS) {
                ruudukko[uusiX - 1][uusiY].poistaNappula();
            } else if (vanhaX == 3 && nappula.getTyyppi() == Nappula.Tyyppi.VSOTILAS && uusiY != vanhaY
                    && ruudukko[uusiX + 1][uusiY].getNappula() != null && ruudukko[uusiX + 1][uusiY].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS) {
                ruudukko[uusiX + 1][uusiY].poistaNappula();
            }
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
     * Metodi tarkistaa uhkaako mikÃ¤Ã¤n valkoinen nappula mustaa kuningasta
     *
     * @return Palauttaa totta jos valkoinen nappula uhkaa mustaa kuningasta ei
     * totta muuten
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

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getVari().equals("valkoinen")) {
                    Pelilauta kopioLauta = new Pelilauta();
                    kopioLauta.setRuudukko(ruudukko);
                    Nappula.Tyyppi tyyppi = ruudukko[i][t].getNappula().getTyyppi();
                    Nappula napppula = ruudukko[i][t].getNappula();
                    ArrayList<String> kaikkiMahdollisetSiirrot = new ArrayList<>();

                    if (tyyppi == Nappula.Tyyppi.VSOTILAS) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotVSotilas(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.VRATSU) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotRatsu(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.VTORNI) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotTorni(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.VLAHETTI) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotLahetti(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.VKUNINGATAR) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotKuningatar(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.VKUNINGAS) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotKuningas(i, t, ruudukko));
                    }

                    if (kaikkiMahdollisetSiirrot.contains("" + x + y)) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    /**
     * Metodi tarkistaa uhkaako mikÃ¤Ã¤n musta nappula valkoista kuningasta
     *
     * @return Palauttaa totta jos musta nappula uhkaa valkoista kuningasta ei
     * totta muuten
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

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getVari().equals("musta")) {
                    Pelilauta kopioLauta = new Pelilauta();
                    kopioLauta.setRuudukko(ruudukko);
                    Nappula.Tyyppi tyyppi = ruudukko[i][t].getNappula().getTyyppi();
                    Nappula napppula = ruudukko[i][t].getNappula();
                    ArrayList<String> kaikkiMahdollisetSiirrot = new ArrayList<>();

                    if (tyyppi == Nappula.Tyyppi.MSOTILAS) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotVSotilas(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.MRATSU) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotRatsu(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.MTORNI) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotTorni(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.MLAHETTI) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotLahetti(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.MKUNINGATAR) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotKuningatar(i, t, ruudukko));
                    } else if (tyyppi == Nappula.Tyyppi.MKUNINGAS) {
                        kaikkiMahdollisetSiirrot.addAll(napppula.mahdollisetSiirrotKuningas(i, t, ruudukko));
                    }

                    if (kaikkiMahdollisetSiirrot.contains("" + x + y)) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    /**
     * Metodi tarkistaa onko mustalla shakkimatti tilanne
     *
     * @return Palauttaa totta jos valkoinen on voittanut
     */
    public boolean onkoMustaShakkiMatti() {

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getVari().equals("musta")
                        && !ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Metodi tarkistaa onko valkoisella shakkimatti tilanne
     *
     * @return Palauttaa totta jos musta on voittanut
     */
    public boolean onkoValkoinenShakkiMatti() {

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getVari().equals("valkoinen")
                        && !ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

}
