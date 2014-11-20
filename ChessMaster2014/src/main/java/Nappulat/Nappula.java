package Nappulat;

import ChessMaster.Pelilauta;
import ChessMaster.Ruutu;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Luokassa on metodeja joiden avulla saadaan tietoja nappulasta
 *
 * @author Sebbe
 */
public class Nappula {

    /**
     * Metodia kutsutaan kun nappula on Kuningas ja on Shakki tilanne
     * 
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    public ArrayList<String> mahdollisetSiirrotKuningasKunShakki(int x, int y, Ruutu[][] ruudukko) {
        
        ArrayList<String> siirrot = new ArrayList<>();
        
        int uhkaavaX = 0;
        int uhkaavaY = 0;

      
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && !ruudukko[i][t].getNappula().onkoSamaVari(ruudukko[x][y].getNappula()) && ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko).contains("" + x + y)) {
                    uhkaavaX = i;
                    uhkaavaY = t;
                    break;
                }
            }
        }
        Pelilauta kopioLauta = new Pelilauta();
        kopioLauta.setRuudukko(ruudukko);
        String vari = ruudukko[x][y].getNappula().getVari();

        if ((vari.equals("musta") && kopioLauta.onkoMustaShakki())
                || (vari.equals("valkoinen") && kopioLauta.onkoValkoinenShakki())) {

            if (x + 1 <= 7 && (ruudukko[x + 1][y].getNappula() == null)) {
                kopioLauta.siirra(x, y, x + 1, y);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x + 1) + (y));
                }
                kopioLauta.siirra(x + 1, y, x, y);
            }
            if (y + 1 <= 7 && (ruudukko[x][y + 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x, y + 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x) + (y + 1));
                }
                kopioLauta.siirra(x, y + 1, x, y);
            }
            if (x + 1 <= 7 && y + 1 <= 7 && (ruudukko[x + 1][y + 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x + 1, y + 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x + 1) + (y + 1));
                }
                kopioLauta.siirra(x + 1, y + 1, x, y);
            }
            if (x - 1 >= 0 && (ruudukko[x - 1][y].getNappula() == null)) {
                kopioLauta.siirra(x, y, x - 1, y);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x - 1) + (y));
                }
                kopioLauta.siirra(x - 1, y, x, y);
            }
            if (y - 1 >= 0 && (ruudukko[x][y - 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x, y - 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x) + (y - 1));
                }
                kopioLauta.siirra(x, y - 1, x, y);
            }
            if (x - 1 >= 0 && y - 1 >= 0 && (ruudukko[x - 1][y - 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x - 1, y - 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x - 1) + (y - 1));
                }
                kopioLauta.siirra(x - 1, y - 1, x, y);
            }
            if (x + 1 <= 7 && y - 1 >= 0 && (ruudukko[x + 1][y - 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x + 1, y - 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x + 1) + (y - 1));
                }
                kopioLauta.siirra(x + 1, y - 1, x, y);
            }
            if (x - 1 >= 0 && y + 1 <= 7 && (ruudukko[x - 1][y + 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x - 1, y + 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x - 1) + (y + 1));
                }
                kopioLauta.siirra(x - 1, y + 1, x, y);
            }
        }
        
        ArrayList<String> muutSiirrot = new ArrayList<>();
        
        if (x + 1 <= 7 && (ruudukko[x + 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y].getNappula()))) {
            muutSiirrot.add("" + (x + 1) + (y));
        }
        if (y + 1 <= 7 && (ruudukko[x][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y + 1].getNappula()))) {
            muutSiirrot.add("" + (x) + (y + 1));
        }
        if (x + 1 <= 7 && y + 1 <= 7 && (ruudukko[x + 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y + 1].getNappula()))) {
            muutSiirrot.add("" + (x + 1) + (y + 1));
        }
        if (x - 1 >= 0 && (ruudukko[x - 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y].getNappula()))) {
            muutSiirrot.add("" + (x - 1) + (y));
        }
        if (y - 1 >= 0 && (ruudukko[x][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y - 1].getNappula()))) {
            muutSiirrot.add("" + (x) + (y - 1));
        }
        if (x - 1 >= 0 && y - 1 >= 0 && (ruudukko[x - 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y - 1].getNappula()))) {
            muutSiirrot.add("" + (x - 1) + (y - 1));
        }
        if (x + 1 <= 7 && y - 1 >= 0 && (ruudukko[x + 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y - 1].getNappula()))) {
            muutSiirrot.add("" + (x + 1) + (y - 1));
        }
        if (x - 1 >= 0 && y + 1 <= 7 && (ruudukko[x - 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y + 1].getNappula()))) {
            muutSiirrot.add("" + (x - 1) + (y + 1));
        }
        
        if ( muutSiirrot.contains("" + uhkaavaX + uhkaavaY)){
            siirrot.add("" + uhkaavaX + uhkaavaY);
        }
        return siirrot;
       
    }
    
    /**
     * Metodia kutsutaan kun nappula on valkoinen Sotilas ja on Shakki tilanne
     * 
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    public ArrayList<String> mahdollisetSiirrotVSotilasKunShakki(int x, int y, Ruutu[][] ruudukko) {
        int uhkaavaX = 0;
        int uhkaavaY = 0;
        
        ArrayList<String> siirrot = new ArrayList<>();
        
        int a = 0;
        int b = 0;
        

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS) {
                    a = i;
                    b = t;
                    break;
                }
            }
        }
      
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && !ruudukko[i][t].getNappula().onkoSamaVari(ruudukko[a][b].getNappula()) && ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko).contains("" + a + b)) {
                    uhkaavaX = i;
                    uhkaavaY = t;
                    break;
                }
            }
        }
        if (mahdollisetSiirrotVSotilas(x, y, ruudukko).contains("" + uhkaavaX + uhkaavaY)){
            siirrot.add("" + uhkaavaX + uhkaavaY);
        }
        
        
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on musta Sotilas ja on Shakki tilanne
     * 
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    public ArrayList<String> mahdollisetSiirrotMSotilasKunShakki(int x, int y, Ruutu[][] ruudukko) {
        int uhkaavaX = 0;
        int uhkaavaY = 0;
        
        ArrayList<String> siirrot = new ArrayList<>();
        
        int a = 0;
        int b = 0;
        

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS) {
                    a = i;
                    b = t;
                    break;
                }
            }
        }
      
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && !ruudukko[i][t].getNappula().onkoSamaVari(ruudukko[x][y].getNappula()) && 
                        ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko).contains("" + a + b)) {
                    uhkaavaX = i;
                    uhkaavaY = t;
                    break;
                }
            }
        }
        if (mahdollisetSiirrotMSotilas(x, y, ruudukko).contains("" + uhkaavaX + uhkaavaY)){
            siirrot.add("" + uhkaavaX + uhkaavaY);
        }
        
        
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Torni ja on Shakki tilanne
     * 
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    public ArrayList<String> mahdollisetSiirrotTorniKunShakki(int x, int y, Ruutu[][] ruudukko) {
        int uhkaavaX = 0;
        int uhkaavaY = 0;
        ArrayList<String> siirrot = new ArrayList<>();
        int a = 0;
        int b = 0;
        

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ((ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS && ruudukko[x][y].getNappula().getVari().equals("musta"))||
                        (ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS && ruudukko[x][y].getNappula().getVari().equals("valkoinen")))) {
                    a = i;
                    b = t;
                    break;
                }
            }
        }

      
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && !ruudukko[i][t].getNappula().onkoSamaVari(ruudukko[a][b].getNappula()) && ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko).contains("" + a + b)) {
                    uhkaavaX = i;
                    uhkaavaY = t;
                    break;
                }
            }
        }
        
        if (mahdollisetSiirrotTorni(x, y, ruudukko).contains("" + uhkaavaX + uhkaavaY)){
            siirrot.add("" + uhkaavaX + uhkaavaY);
        }
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Ratsu ja on Shakki tilanne
     * 
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    public ArrayList<String> mahdollisetSiirrotRatsuKunShakki(int x, int y, Ruutu[][] ruudukko) {
        int uhkaavaX = 0;
        int uhkaavaY = 0;
        ArrayList<String> siirrot = new ArrayList<>();
        int a = 0;
        int b = 0;
        

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ((ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS && ruudukko[x][y].getNappula().getVari().equals("musta"))||
                        (ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS && ruudukko[x][y].getNappula().getVari().equals("valkoinen")))) {
                    a = i;
                    b = t;
                    break;
                }
            }
        }

      
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && !ruudukko[i][t].getNappula().onkoSamaVari(ruudukko[a][b].getNappula()) && ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko).contains("" + a + b)) {
                    uhkaavaX = i;
                    uhkaavaY = t;
                    break;
                }
            }
        }
        if (mahdollisetSiirrotRatsu(x, y, ruudukko).contains("" + uhkaavaX + uhkaavaY)){
            siirrot.add("" + uhkaavaX + uhkaavaY);
        }
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Lahetti ja on Shakki tilanne
     * 
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    public ArrayList<String> mahdollisetSiirrotLahettiKunShakki(int x, int y, Ruutu[][] ruudukko) {
        int uhkaavaX = 0;
        int uhkaavaY = 0;
        ArrayList<String> siirrot = new ArrayList<>();
        int a = 0;
        int b = 0;
        

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ((ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS && ruudukko[x][y].getNappula().getVari().equals("musta"))||
                        (ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS && ruudukko[x][y].getNappula().getVari().equals("valkoinen")))) {
                    a = i;
                    b = t;
                    break;
                }
            }
        }

      
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && !ruudukko[i][t].getNappula().onkoSamaVari(ruudukko[a][b].getNappula()) && ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko).contains("" + a + b)) {
                    uhkaavaX = i;
                    uhkaavaY = t;
                    break;
                }
            }
        }
        if (mahdollisetSiirrotLahetti(x, y, ruudukko).contains("" + uhkaavaX + uhkaavaY)){
            siirrot.add("" + uhkaavaX + uhkaavaY);
        }
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Kuningatar ja on Shakki tilanne
     * 
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    public ArrayList<String> mahdollisetSiirrotKuningatarKunShakki(int x, int y, Ruutu[][] ruudukko) {
        int uhkaavaX = 0;
        int uhkaavaY = 0;
        ArrayList<String> siirrot = new ArrayList<>();
        int a = 0;
        int b = 0;
        

        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && ((ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.MKUNINGAS && ruudukko[x][y].getNappula().getVari().equals("musta"))||
                        (ruudukko[i][t].getNappula().getTyyppi() == Nappula.Tyyppi.VKUNINGAS && ruudukko[x][y].getNappula().getVari().equals("valkoinen")))) {
                    a = i;
                    b = t;
                    break;
                }
            }
        }

      
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (ruudukko[i][t].getNappula() != null && !ruudukko[i][t].getNappula().onkoSamaVari(ruudukko[a][b].getNappula()) && ruudukko[i][t].getNappula().mahdollisetSiirrot(i, t, ruudukko).contains("" + a + b)) {
                    uhkaavaX = i;
                    uhkaavaY = t;
                    break;
                }
            }
        }
        if (mahdollisetSiirrotKuningatar(x, y, ruudukko).contains("" + uhkaavaX + uhkaavaY)){
            siirrot.add("" + uhkaavaX + uhkaavaY);
        }
        return siirrot;
        
    }

    public enum Tyyppi {

        VKUNINGAS, VKUNINGATAR, VLAHETTI, VRATSU, VSOTILAS, VTORNI,
        MKUNINGAS, MKUNINGATAR, MLAHETTI, MRATSU, MSOTILAS, MTORNI
    };

    /**
     * Nappulan väri, joko musta tai valkoinen
     */
    private String vari;
    /**
     * Lista nappulan mahdollisista siirroista
     */
    private ArrayList<String> mahdollisetSiirrot;

    /**
     * Nappulan tyyppi
     */
    Tyyppi tyyppi;

    public Nappula(String vari) {
        this.vari = vari;

    }

    public String getVari() {
        return vari;
    }

    public void setTyyppi(Tyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Tyyppi getTyyppi() {
        return tyyppi;
    }

    /**
     * Metodi kertoo onko nykyisen nappulan väri sama kuin parametrissä saadun
     * nappulan väri
     *
     * @param nappula Nappula jonka väriä halutaan verrata
     * @return totuusarvon joka kertoo ovatko nappulat samaa väriä
     */
    public boolean onkoSamaVari(Nappula nappula) {
        return vari.equals(nappula.getVari());
    }

    /**
     * Metodi palauttaa listan mahdollisia siirtoja nappulalle. Mahdolliset
     * siirrot ovat String muodossa niin että ensimmäinen kirjain on siirron x
     * arvo ja toinen on y arvo
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * @return lista mahdollisista siirroista nappulalle
     */
    public ArrayList<String> mahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {

        mahdollisetSiirrot = new ArrayList<String>();
        Pelilauta lauta = new Pelilauta();
        lauta.setRuudukko(ruudukko);

        if ((ruudukko[x][y].getNappula().vari.equals("musta") && lauta.onkoMustaShakki())
                || (ruudukko[x][y].getNappula().vari.equals("valkoinen") && lauta.onkoValkoinenShakki())) {

            if (this.tyyppi == Tyyppi.VKUNINGAS || tyyppi == Tyyppi.MKUNINGAS) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotKuningasKunShakki(x, y, ruudukko));
            }
            if (this.tyyppi == Tyyppi.VSOTILAS) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotVSotilasKunShakki(x, y, ruudukko));
            }
            if (this.tyyppi == Tyyppi.MSOTILAS) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotMSotilasKunShakki(x, y, ruudukko));
            }

            if (this.tyyppi == Tyyppi.MTORNI || this.tyyppi == Tyyppi.VTORNI) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotTorniKunShakki(x, y, ruudukko));
            }

            if (this.tyyppi == Tyyppi.MRATSU || this.tyyppi == Tyyppi.VRATSU) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotRatsuKunShakki(x, y, ruudukko));
            }

            if (this.tyyppi == Tyyppi.MLAHETTI || this.tyyppi == Tyyppi.VLAHETTI) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotLahettiKunShakki(x, y, ruudukko));
            }
            if (this.tyyppi == Tyyppi.MKUNINGATAR || this.tyyppi == Tyyppi.VKUNINGATAR) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotKuningatarKunShakki(x, y, ruudukko));
            }
        } else {
            if (this.tyyppi == Tyyppi.VKUNINGAS || tyyppi == Tyyppi.MKUNINGAS) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotKuningas(x, y, ruudukko));
            }
            if (this.tyyppi == Tyyppi.VSOTILAS) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotVSotilas(x, y, ruudukko));
            }
            if (this.tyyppi == Tyyppi.MSOTILAS) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotMSotilas(x, y, ruudukko));
            }

            if (this.tyyppi == Tyyppi.MTORNI || this.tyyppi == Tyyppi.VTORNI) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotTorni(x, y, ruudukko));
            }

            if (this.tyyppi == Tyyppi.MRATSU || this.tyyppi == Tyyppi.VRATSU) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotRatsu(x, y, ruudukko));
            }

            if (this.tyyppi == Tyyppi.MLAHETTI || this.tyyppi == Tyyppi.VLAHETTI) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotLahetti(x, y, ruudukko));
            }
            if (this.tyyppi == Tyyppi.MKUNINGATAR || this.tyyppi == Tyyppi.VKUNINGATAR) {
                mahdollisetSiirrot.addAll(mahdollisetSiirrotKuningatar(x, y, ruudukko));
            }
        }
        return mahdollisetSiirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Kuningas
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    private ArrayList<String> mahdollisetSiirrotKuningas(int x, int y, Ruutu[][] ruudukko) {
        
        Pelilauta kopioLauta = new Pelilauta();
        kopioLauta.setRuudukko(ruudukko);
        String vari = ruudukko[x][y].getNappula().getVari();
        ArrayList<String> siirrot = new ArrayList<>();

        if (x + 1 <= 7 && (ruudukko[x + 1][y].getNappula() == null)) {
                kopioLauta.siirra(x, y, x + 1, y);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x + 1) + (y));
                }
                kopioLauta.siirra(x + 1, y, x, y);
            }
            if (y + 1 <= 7 && (ruudukko[x][y + 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x, y + 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x) + (y + 1));
                }
                kopioLauta.siirra(x, y + 1, x, y);
            }
            if (x + 1 <= 7 && y + 1 <= 7 && (ruudukko[x + 1][y + 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x + 1, y + 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x + 1) + (y + 1));
                }
                kopioLauta.siirra(x + 1, y + 1, x, y);
            }
            if (x - 1 >= 0 && (ruudukko[x - 1][y].getNappula() == null)) {
                kopioLauta.siirra(x, y, x - 1, y);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x - 1) + (y));
                }
                kopioLauta.siirra(x - 1, y, x, y);
            }
            if (y - 1 >= 0 && (ruudukko[x][y - 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x, y - 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x) + (y - 1));
                }
                kopioLauta.siirra(x, y - 1, x, y);
            }
            if (x - 1 >= 0 && y - 1 >= 0 && (ruudukko[x - 1][y - 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x - 1, y - 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x - 1) + (y - 1));
                }
                kopioLauta.siirra(x - 1, y - 1, x, y);
            }
            if (x + 1 <= 7 && y - 1 >= 0 && (ruudukko[x + 1][y - 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x + 1, y - 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x + 1) + (y - 1));
                }
                kopioLauta.siirra(x + 1, y - 1, x, y);
            }
            if (x - 1 >= 0 && y + 1 <= 7 && (ruudukko[x - 1][y + 1].getNappula() == null)) {
                kopioLauta.siirra(x, y, x - 1, y + 1);
                if ((vari.equals("musta") && !kopioLauta.onkoMustaShakki())
                        || (vari.equals("valkoinen") && !kopioLauta.onkoValkoinenShakki())) {
                    siirrot.add("" + (x - 1) + (y + 1));
                }
                kopioLauta.siirra(x - 1, y + 1, x, y);
            }
        
        return siirrot;

    }

    /**
     * Metodia kutsutaan kun nappula on valkoinen Sotilas
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    private ArrayList<String> mahdollisetSiirrotVSotilas(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();
        
        if (x == 6 && (ruudukko[x - 2][y].getNappula() == null && ruudukko[x - 1][y].getNappula() == null)) {
            siirrot.add("" + (x - 2) + (y));
        }
        if (x - 1 >= 0 && ruudukko[x - 1][y].getNappula() == null) {
            siirrot.add("" + (x - 1) + (y));
        }

        if (x - 1 >= 0 && y - 1 >= 0 && ruudukko[x - 1][y - 1].getNappula() != null && !onkoSamaVari(ruudukko[x - 1][y - 1].getNappula())) {
            siirrot.add("" + (x - 1) + (y - 1));
        }

        if (x - 1 >= 0 && y + 1 <= 7 && ruudukko[x - 1][y + 1].getNappula() != null && !onkoSamaVari(ruudukko[x - 1][y + 1].getNappula())) {
            siirrot.add("" + (x - 1) + (y + 1));
        }
        
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on musta Sotilas
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    private ArrayList<String> mahdollisetSiirrotMSotilas(int x, int y, Ruutu[][] ruudukko) {
        
        ArrayList<String> siirrot = new ArrayList<>();
        if (x == 1 && (ruudukko[x + 2][y].getNappula() == null && ruudukko[x + 1][y].getNappula() == null)) {
            siirrot.add("" + (x + 2) + (y));
        }
        if (x + 1 <= 7 && ruudukko[x + 1][y].getNappula() == null) {
            siirrot.add("" + (x + 1) + (y));
        }
        if (x + 1 <= 7 && y + 1 <= 7 && ruudukko[x + 1][y + 1].getNappula() != null && !onkoSamaVari(ruudukko[x + 1][y + 1].getNappula())) {
            siirrot.add("" + (x + 1) + (y + 1));
        }

        if (x + 1 <= 7 && y - 1 >= 0 && ruudukko[x + 1][y - 1].getNappula() != null && !onkoSamaVari(ruudukko[x + 1][y - 1].getNappula())) {
            siirrot.add("" + (x + 1) + (y - 1));
        }
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Torni
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    private ArrayList<String> mahdollisetSiirrotTorni(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();
        for (int i = x + 1; i <= 7; i++) {
            if (ruudukko[i][y].getNappula() == null) {
                siirrot.add("" + (i) + (y));
            }
            if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                siirrot.add("" + (i) + (y));
                break;
            }
            if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            if (ruudukko[i][y].getNappula() == null) {
                siirrot.add("" + (i) + (y));
            }
            if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                siirrot.add("" + (i) + (y));
                break;
            }
            if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                break;
            }
        }

        for (int i = y + 1; i <= 7; i++) {
            if (ruudukko[x][i].getNappula() == null) {
                siirrot.add("" + (x) + (i));
            }
            if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                siirrot.add("" + (x) + (i));
                break;
            }
            if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                break;
            }
        }

        for (int i = y - 1; i >= 0; i--) {
            if (ruudukko[x][i].getNappula() == null) {
                siirrot.add("" + (x) + (i));
            }
            if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                siirrot.add("" + (x) + (i));
                break;
            }
            if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                break;
            }
        }
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Ratsu
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    private ArrayList<String> mahdollisetSiirrotRatsu(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();
        if (x - 1 >= 0 && y - 2 >= 0 && (ruudukko[x - 1][y - 2].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y - 2].getNappula()))) {
            siirrot.add("" + (x - 1) + (y - 2));
        }
        if (x - 2 >= 0 && y - 1 >= 0 && (ruudukko[x - 2][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 2][y - 1].getNappula()))) {
            siirrot.add("" + (x - 2) + (y - 1));
        }
        if (x - 1 >= 0 && y + 2 <= 7 && (ruudukko[x - 1][y + 2].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y + 2].getNappula()))) {
            siirrot.add("" + (x - 1) + (y + 2));
        }
        if (x - 2 >= 0 && y + 1 <= 7 && (ruudukko[x - 2][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 2][y + 1].getNappula()))) {
            siirrot.add("" + (x - 2) + (y + 1));
        }
        if (x + 1 <= 7 && y - 2 >= 0 && (ruudukko[x + 1][y - 2].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y - 2].getNappula()))) {
            siirrot.add("" + (x + 1) + (y - 2));
        }
        if (x + 2 <= 7 && y - 1 >= 0 && (ruudukko[x + 2][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 2][y - 1].getNappula()))) {
            siirrot.add("" + (x + 2) + (y - 1));
        }
        if (x + 1 <= 7 && y + 2 <= 7 && (ruudukko[x + 1][y + 2].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y + 2].getNappula()))) {
            siirrot.add("" + (x + 1) + (y + 2));
        }
        if (x + 2 <= 7 && y + 1 <= 7 && (ruudukko[x + 2][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 2][y + 1].getNappula()))) {
            siirrot.add("" + (x + 2) + (y + 1));
        }
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Lahetti
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    private ArrayList<String> mahdollisetSiirrotLahetti(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() == null) {
                siirrot.add("" + (x + i) + (y + i));
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                siirrot.add("" + (x + i) + (y + i));
                break;
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() == null) {
                siirrot.add("" + (x - i) + (y - i));
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                siirrot.add("" + (x - i) + (y - i));
                break;
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() == null) {
                siirrot.add("" + (x - i) + (y + i));
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                siirrot.add("" + (x - i) + (y + i));
                break;
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() == null) {
                siirrot.add("" + (x + i) + (y - i));
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                siirrot.add("" + (x + i) + (y - i));
                break;
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                break;
            }
        }
        return siirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Kuningatar
     *
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * 
     * @return Lista mahdollisista siirroista
     */
    private ArrayList<String> mahdollisetSiirrotKuningatar(int x, int y, Ruutu[][] ruudukko) {
        ArrayList<String> siirrot = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() == null) {
                siirrot.add("" + (x + i) + (y + i));
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                siirrot.add("" + (x + i) + (y + i));
                break;
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() == null) {
                siirrot.add("" + (x - i) + (y - i));
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                siirrot.add("" + (x - i) + (y - i));
                break;
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() == null) {
                siirrot.add("" + (x - i) + (y + i));
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                siirrot.add("" + (x - i) + (y + i));
                break;
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() == null) {
                siirrot.add("" + (x + i) + (y - i));
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                siirrot.add("" + (x + i) + (y - i));
                break;
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                break;
            }
        }
        for (int i = x + 1; i <= 7; i++) {
            if (ruudukko[i][y].getNappula() == null) {
                siirrot.add("" + (i) + (y));
            }
            if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                siirrot.add("" + (i) + (y));
                break;
            }
            if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            if (ruudukko[i][y].getNappula() == null) {
                siirrot.add("" + (i) + (y));
            }
            if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                siirrot.add("" + (i) + (y));
                break;
            }
            if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                break;
            }
        }

        for (int i = y + 1; i <= 7; i++) {
            if (ruudukko[x][i].getNappula() == null) {
                siirrot.add("" + (x) + (i));
            }
            if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                siirrot.add("" + (x) + (i));
                break;
            }
            if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                break;
            }
        }

        for (int i = y - 1; i >= 0; i--) {
            if (ruudukko[x][i].getNappula() == null) {
                siirrot.add("" + (x) + (i));
            }
            if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                siirrot.add("" + (x) + (i));
                break;
            }
            if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                break;
            }
        }
        return siirrot;
    }

}
