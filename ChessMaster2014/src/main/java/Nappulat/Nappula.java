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

    private int siirtojenMaara;

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
        siirtojenMaara = 0;
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
        ArrayList<String> siirrot = new ArrayList<>();
        Pelilauta kopioLauta = new Pelilauta();
        kopioLauta.setRuudukko(ruudukko);
        String vari = ruudukko[x][y].getNappula().getVari();
        Nappula.Tyyppi tyyppi = ruudukko[x][y].getNappula().getTyyppi();

        ArrayList<String> kaikkiMahdollisetSiirrot = new ArrayList<>();

        if (tyyppi == Tyyppi.VSOTILAS) {
            kaikkiMahdollisetSiirrot.addAll(mahdollisetSiirrotVSotilas(x, y, ruudukko));
        } else if (tyyppi == Tyyppi.MSOTILAS) {
            kaikkiMahdollisetSiirrot.addAll(mahdollisetSiirrotMSotilas(x, y, ruudukko));
        } else if (tyyppi == Tyyppi.VRATSU || tyyppi == Tyyppi.MRATSU) {
            kaikkiMahdollisetSiirrot.addAll(mahdollisetSiirrotRatsu(x, y, ruudukko));
        } else if (tyyppi == Tyyppi.VTORNI || tyyppi == Tyyppi.MTORNI) {
            kaikkiMahdollisetSiirrot.addAll(mahdollisetSiirrotTorni(x, y, ruudukko));
        } else if (tyyppi == Tyyppi.VLAHETTI || tyyppi == Tyyppi.MLAHETTI) {
            kaikkiMahdollisetSiirrot.addAll(mahdollisetSiirrotLahetti(x, y, ruudukko));
        } else if (tyyppi == Tyyppi.VKUNINGATAR || tyyppi == Tyyppi.MKUNINGATAR) {
            kaikkiMahdollisetSiirrot.addAll(mahdollisetSiirrotKuningatar(x, y, ruudukko));
        } else if (tyyppi == Tyyppi.VKUNINGAS || tyyppi == Tyyppi.MKUNINGAS) {
            kaikkiMahdollisetSiirrot.addAll(mahdollisetSiirrotKuningas(x, y, ruudukko));
        }

        for (String siirto : kaikkiMahdollisetSiirrot) {
            int uusix = Integer.parseInt("" + siirto.charAt(0));
            int uusiy = Integer.parseInt("" + siirto.charAt(1));
            if (ruudukko[uusix][uusiy].getNappula() == null && (ruudukko[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS
                    || ruudukko[x][y].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS) && uusiy != y) {

                Nappula nappula = ruudukko[x][uusiy].getNappula();
                kopioLauta.siirra(x, y, uusix, uusiy);
                if ((!kopioLauta.onkoMustaShakki() && vari.equals("musta")) || (!kopioLauta.onkoValkoinenShakki() && vari.equals("valkoinen"))) {
                    siirrot.add(siirto);
                }
                kopioLauta.siirra(uusix, uusiy, x, y);
                ruudukko[x][uusiy].asetaNappula(nappula);

            } else if (ruudukko[uusix][uusiy].getNappula() == null) {
                kopioLauta.siirra(x, y, uusix, uusiy);
                if ((!kopioLauta.onkoMustaShakki() && vari.equals("musta")) || (!kopioLauta.onkoValkoinenShakki() && vari.equals("valkoinen"))) {
                    siirrot.add(siirto);
                }
                kopioLauta.siirra(uusix, uusiy, x, y);

            } else if (!ruudukko[uusix][uusiy].getNappula().onkoSamaVari(ruudukko[x][y].getNappula())) {
                Nappula nappula = ruudukko[uusix][uusiy].getNappula();
                kopioLauta.siirra(x, y, uusix, uusiy);
                if ((!kopioLauta.onkoMustaShakki() && vari.equals("musta")) || (!kopioLauta.onkoValkoinenShakki() && vari.equals("valkoinen"))) {
                    siirrot.add(siirto);
                }
                kopioLauta.siirra(uusix, uusiy, x, y);
                ruudukko[uusix][uusiy].asetaNappula(nappula);
            }
        }

        return siirrot;
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
    public ArrayList<String> mahdollisetSiirrotKuningas(int x, int y, Ruutu[][] ruudukko) {

        ArrayList<String> siirrot = new ArrayList<>();

        if (x + 1 <= 7 && (ruudukko[x + 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y].getNappula()))) {
            siirrot.add("" + (x + 1) + (y));
        }
        if (y + 1 <= 7 && (ruudukko[x][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y + 1].getNappula()))) {
            siirrot.add("" + (x) + (y + 1));
        }
        if (x + 1 <= 7 && y + 1 <= 7 && (ruudukko[x + 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y + 1].getNappula()))) {
            siirrot.add("" + (x + 1) + (y + 1));
        }
        if (x - 1 >= 0 && (ruudukko[x - 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y].getNappula()))) {
            siirrot.add("" + (x - 1) + (y));
        }
        if (y - 1 >= 0 && (ruudukko[x][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y - 1].getNappula()))) {
            siirrot.add("" + (x) + (y - 1));
        }
        if (x - 1 >= 0 && y - 1 >= 0 && (ruudukko[x - 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y - 1].getNappula()))) {
            siirrot.add("" + (x - 1) + (y - 1));
        }
        if (x + 1 <= 7 && y - 1 >= 0 && (ruudukko[x + 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y - 1].getNappula()))) {
            siirrot.add("" + (x + 1) + (y - 1));
        }
        if (x - 1 >= 0 && y + 1 <= 7 && (ruudukko[x - 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y + 1].getNappula()))) {
            siirrot.add("" + (x - 1) + (y + 1));
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
    public ArrayList<String> mahdollisetSiirrotVSotilas(int x, int y, Ruutu[][] ruudukko) {
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
        if (x == 3 && y + 1 <= 7 && ruudukko[x - 1][y + 1].getNappula() == null && ruudukko[x][y + 1].getNappula() != null
                && ruudukko[x][y + 1].getNappula().getTyyppi() == Tyyppi.MSOTILAS && ruudukko[x][y + 1].getNappula().getSiirtojenMaara() == 1) {
            siirrot.add("" + (x - 1) + (y + 1));
        }
        if (x == 3 && y - 1 >= 0 && ruudukko[x - 1][y - 1].getNappula() == null && ruudukko[x][y - 1].getNappula() != null
                && ruudukko[x][y - 1].getNappula().getTyyppi() == Tyyppi.MSOTILAS && ruudukko[x][y - 1].getNappula().getSiirtojenMaara() == 1) {
            siirrot.add("" + (x - 1) + (y - 1));
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
    public ArrayList<String> mahdollisetSiirrotMSotilas(int x, int y, Ruutu[][] ruudukko) {

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
        if (x == 4 && y + 1 <= 7 && ruudukko[x - 1][y + 1].getNappula() == null && ruudukko[x][y + 1].getNappula() != null
                && ruudukko[x][y + 1].getNappula().getTyyppi() == Tyyppi.VSOTILAS && ruudukko[x][y + 1].getNappula().getSiirtojenMaara() == 1) {
            siirrot.add("" + (x + 1) + (y + 1));
        }
        if (x == 4 && y - 1 >= 0 && ruudukko[x - 1][y - 1].getNappula() == null && ruudukko[x][y - 1].getNappula() != null
                && ruudukko[x][y - 1].getNappula().getTyyppi() == Tyyppi.VSOTILAS && ruudukko[x][y - 1].getNappula().getSiirtojenMaara() == 1) {
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
    public ArrayList<String> mahdollisetSiirrotTorni(int x, int y, Ruutu[][] ruudukko) {
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
    public ArrayList<String> mahdollisetSiirrotRatsu(int x, int y, Ruutu[][] ruudukko) {
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
    public ArrayList<String> mahdollisetSiirrotLahetti(int x, int y, Ruutu[][] ruudukko) {
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
    public ArrayList<String> mahdollisetSiirrotKuningatar(int x, int y, Ruutu[][] ruudukko) {
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

    public int getSiirtojenMaara() {
        return siirtojenMaara;
    }

    public void kasvataSiirtojenMaaraa() {
        siirtojenMaara += 1;
    }
}
