
package Nappulat;

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
     * Metodi kertoo onko nykyisen nappulan väri sama kuin parametrissä saadun nappulan väri
     * 
     * @param nappula Nappula jonka väriä halutaan verrata
     * @return totuusarvon joka kertoo ovatko nappulat samaa väriä
     */
    public boolean onkoSamaVari(Nappula nappula) {
        return vari.equals(nappula.getVari());
    }

    /**
     * Metodi palauttaa listan mahdollisia siirtoja nappulalle. Mahdolliset siirrot ovat String 
     * muodossa niin että ensimmäinen kirjain on siirron x arvo ja toinen on y arvo
     * 
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     * @return lista mahdollisista siirroista nappulalle
     */
    public ArrayList<String> mahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {
        
        mahdollisetSiirrot = new ArrayList<String>();

        if (this.tyyppi == Tyyppi.VKUNINGAS || tyyppi == Tyyppi.MKUNINGAS) {
            mahdollisetSiirrotKuningas(x, y, ruudukko);
        }
        if (this.tyyppi == Tyyppi.VSOTILAS) {
            mahdollisetSiirrotVSotilas(x, y, ruudukko);
        }
        if (this.tyyppi == Tyyppi.MSOTILAS) {
            mahdollisetSiirrotMSotilas(x, y, ruudukko);
        }

        if (this.tyyppi == Tyyppi.MTORNI || this.tyyppi == Tyyppi.VTORNI) {
            mahdollisetSiirrotTorni(x, y, ruudukko);
        }

        if (this.tyyppi == Tyyppi.MRATSU || this.tyyppi == Tyyppi.VRATSU) {
            mahdollisetSiirrotRatsu(x, y, ruudukko);
        }

        if (this.tyyppi == Tyyppi.MLAHETTI || this.tyyppi == Tyyppi.VLAHETTI) {
            mahdollisetSiirrotLahetti(x, y, ruudukko);
        }
        if (this.tyyppi == Tyyppi.MKUNINGATAR || this.tyyppi == Tyyppi.VKUNINGATAR) {
            mahdollisetSiirrotKuningatar(x, y, ruudukko);
        }

        return mahdollisetSiirrot;
    }

    /**
     * Metodia kutsutaan kun nappula on Kuningas
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     */
    private void mahdollisetSiirrotKuningas(int x, int y, Ruutu[][] ruudukko) {
        if (x + 1 <= 7 && (ruudukko[x + 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y].getNappula()))) {
            mahdollisetSiirrot.add("" + (x + 1) + (y));

        }
        if (y + 1 <= 7 && (ruudukko[x][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y + 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x) + (y + 1));
        }
        if (x + 1 <= 7 && y + 1 <= 7 && (ruudukko[x + 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y + 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x + 1) + (y + 1));
        }
        if (x - 1 >= 0 && (ruudukko[x - 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y].getNappula()))) {
            mahdollisetSiirrot.add("" + (x - 1) + (y));
        }
        if (y - 1 >= 0 && (ruudukko[x][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y - 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x) + (y - 1));
        }
        if (x - 1 >= 0 && y - 1 >= 0 && (ruudukko[x - 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y - 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x - 1) + (y - 1));
        }
        if (x + 1 <= 7 && y - 1 >= 0 && (ruudukko[x + 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y - 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x + 1) + (y - 1));
        }
        if (x - 1 >= 0 && y + 1 <= 7 && (ruudukko[x - 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y + 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x - 1) + (y + 1));
        }
    }
    
    /**
     * Metodia kutsutaan kun nappula on valkoinen Sotilas
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     */
    private void mahdollisetSiirrotVSotilas(int x, int y, Ruutu[][] ruudukko) {
        if (x == 6 && (ruudukko[x - 2][y].getNappula() == null && ruudukko[x - 1][y].getNappula() == null)) {
            mahdollisetSiirrot.add("" + (x - 2) + (y));
        }
        if (x - 1 >= 0 && ruudukko[x - 1][y].getNappula() == null) {
            mahdollisetSiirrot.add("" + (x - 1) + (y));
        }

        if (x - 1 >= 0 && y - 1 >= 0 && ruudukko[x - 1][y - 1].getNappula() != null && !onkoSamaVari(ruudukko[x - 1][y - 1].getNappula())) {
            mahdollisetSiirrot.add("" + (x - 1) + (y - 1));
        }

        if (x - 1 >= 0 && y + 1 <= 7 && ruudukko[x - 1][y + 1].getNappula() != null && !onkoSamaVari(ruudukko[x - 1][y + 1].getNappula())) {
            mahdollisetSiirrot.add("" + (x - 1) + (y + 1));
        }
    }
    
    /**
     * Metodia kutsutaan kun nappula on musta Sotilas
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     */
    private void mahdollisetSiirrotMSotilas(int x, int y, Ruutu[][] ruudukko) {
        if (x == 1 && (ruudukko[x + 2][y].getNappula() == null && ruudukko[x + 1][y].getNappula() == null)) {
            mahdollisetSiirrot.add("" + (x + 2) + (y));
        }
        if (x + 1 <= 7 && ruudukko[x + 1][y].getNappula() == null) {
            mahdollisetSiirrot.add("" + (x + 1) + (y));
        }
        if (x + 1 <= 7 && y + 1 <= 7 && ruudukko[x + 1][y + 1].getNappula() != null && !onkoSamaVari(ruudukko[x + 1][y + 1].getNappula())) {
            mahdollisetSiirrot.add("" + (x + 1) + (y + 1));
        }

        if (x + 1 <= 7 && y - 1 >= 0 && ruudukko[x + 1][y - 1].getNappula() != null && !onkoSamaVari(ruudukko[x + 1][y - 1].getNappula())) {
            mahdollisetSiirrot.add("" + (x + 1) + (y - 1));
        }
    }
    
    /**
     * Metodia kutsutaan kun nappula on Torni
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     */
    private void mahdollisetSiirrotTorni(int x, int y, Ruutu[][] ruudukko) {
        for (int i = x + 1; i <= 7; i++) {
            if (ruudukko[i][y].getNappula() == null) {
                mahdollisetSiirrot.add("" + (i) + (y));
            }
            if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                mahdollisetSiirrot.add("" + (i) + (y));
                break;
            }
            if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            if (ruudukko[i][y].getNappula() == null) {
                mahdollisetSiirrot.add("" + (i) + (y));
            }
            if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                mahdollisetSiirrot.add("" + (i) + (y));
                break;
            }
            if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                break;
            }
        }

        for (int i = y + 1; i <= 7; i++) {
            if (ruudukko[x][i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x) + (i));
            }
            if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                mahdollisetSiirrot.add("" + (x) + (i));
                break;
            }
            if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                break;
            }
        }

        for (int i = y - 1; i >= 0; i--) {
            if (ruudukko[x][i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x) + (i));
            }
            if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                mahdollisetSiirrot.add("" + (x) + (i));
                break;
            }
            if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                break;
            }
        }
    }

    /**
     * Metodia kutsutaan kun nappula on Ratsu
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     */
    private void mahdollisetSiirrotRatsu(int x, int y, Ruutu[][] ruudukko) {
        if (x - 1 >= 0 && y - 2 >= 0 && (ruudukko[x - 1][y - 2].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y - 2].getNappula()))) {
            mahdollisetSiirrot.add("" + (x - 1) + (y - 2));
        }
        if (x - 2 >= 0 && y - 1 >= 0 && (ruudukko[x - 2][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 2][y - 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x - 2) + (y - 1));
        }
        if (x - 1 >= 0 && y + 2 <= 7 && (ruudukko[x - 1][y + 2].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y + 2].getNappula()))) {
            mahdollisetSiirrot.add("" + (x - 1) + (y + 2));
        }
        if (x - 2 >= 0 && y + 1 <= 7 && (ruudukko[x - 2][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 2][y + 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x - 2) + (y + 1));
        }
        if (x + 1 <= 7 && y - 2 >= 0 && (ruudukko[x + 1][y - 2].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y - 2].getNappula()))) {
            mahdollisetSiirrot.add("" + (x + 1) + (y - 2));
        }
        if (x + 2 <= 7 && y - 1 >= 0 && (ruudukko[x + 2][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 2][y - 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x + 2) + (y - 1));
        }
        if (x + 1 <= 7 && y + 2 <= 7 && (ruudukko[x + 1][y + 2].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y + 2].getNappula()))) {
            mahdollisetSiirrot.add("" + (x + 1) + (y + 2));
        }
        if (x + 2 <= 7 && y + 1 <= 7 && (ruudukko[x + 2][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 2][y + 1].getNappula()))) {
            mahdollisetSiirrot.add("" + (x + 2) + (y + 1));
        }
    }

    /**
     * Metodia kutsutaan kun nappula on Lahetti
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     */
    private void mahdollisetSiirrotLahetti(int x, int y, Ruutu[][] ruudukko) {
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x + i) + (y + i));
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                mahdollisetSiirrot.add("" + (x + i) + (y + i));
                break;
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x - i) + (y - i));
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                mahdollisetSiirrot.add("" + (x - i) + (y - i));
                break;
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x - i) + (y + i));
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                mahdollisetSiirrot.add("" + (x - i) + (y + i));
                break;
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x + i) + (y - i));
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                mahdollisetSiirrot.add("" + (x + i) + (y - i));
                break;
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                break;
            }
        }
    }

    /**
     * Metodia kutsutaan kun nappula on Kuningatar
     * @param x Nappulan x sijainti ruudukossa
     * @param y Nappulan y sijainti ruudukossa
     * @param ruudukko Ruudukko ruutuja jossa nappula sijaitsee
     */
    private void mahdollisetSiirrotKuningatar(int x, int y, Ruutu[][] ruudukko) {
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x + i) + (y + i));
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                mahdollisetSiirrot.add("" + (x + i) + (y + i));
                break;
            }
            if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x - i) + (y - i));
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                mahdollisetSiirrot.add("" + (x - i) + (y - i));
                break;
            }
            if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x - i) + (y + i));
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                mahdollisetSiirrot.add("" + (x - i) + (y + i));
                break;
            }
            if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                break;
            }
        }
        for (int i = 1; i <= 7; i++) {
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x + i) + (y - i));
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                mahdollisetSiirrot.add("" + (x + i) + (y - i));
                break;
            }
            if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                break;
            }
        }
        for (int i = x + 1; i <= 7; i++) {
            if (ruudukko[i][y].getNappula() == null) {
                mahdollisetSiirrot.add("" + (i) + (y));
            }
            if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                mahdollisetSiirrot.add("" + (i) + (y));
                break;
            }
            if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            if (ruudukko[i][y].getNappula() == null) {
                mahdollisetSiirrot.add("" + (i) + (y));
            }
            if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                mahdollisetSiirrot.add("" + (i) + (y));
                break;
            }
            if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                break;
            }
        }

        for (int i = y + 1; i <= 7; i++) {
            if (ruudukko[x][i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x) + (i));
            }
            if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                mahdollisetSiirrot.add("" + (x) + (i));
                break;
            }
            if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                break;
            }
        }

        for (int i = y - 1; i >= 0; i--) {
            if (ruudukko[x][i].getNappula() == null) {
                mahdollisetSiirrot.add("" + (x) + (i));
            }
            if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                mahdollisetSiirrot.add("" + (x) + (i));
                break;
            }
            if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                break;
            }
        }
    }

}
