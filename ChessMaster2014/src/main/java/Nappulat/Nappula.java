/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nappulat;

import ChessMaster.Ruutu;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sebbe
 */
public class Nappula {

    public enum Tyyppi {

        VKUNINGAS, VKUNINGATAR, VLAHETTI, VRATSU, VSOTILAS, VTORNI,
        MKUNINGAS, MKUNINGATAR, MLAHETTI, MRATSU, MSOTILAS, MTORNI
    };

    private String vari;

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

    public boolean onkoSamaVari(Nappula nappula) {
        return vari.equals(nappula.getVari());
    }

    public HashMap<Integer, Integer> mahdollisetSiirrot(int x, int y, Ruutu[][] ruudukko) {
        HashMap<Integer, Integer> siirrot = new HashMap();
        
        ArrayList<String> mahdollisetSiirrot = new ArrayList<>();

        if (this.tyyppi == Tyyppi.VKUNINGAS || tyyppi == Tyyppi.MKUNINGAS) {
            if (x + 1 <= 7 && (ruudukko[x + 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y].getNappula()))) {
                siirrot.put(x + 1, y);
            }
            if (y + 1 <= 7 && (ruudukko[x][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y + 1].getNappula()))) {
                siirrot.put(x, y + 1);
            }
            if (x + 1 <= 7 && y + 1 <= 7 && (ruudukko[x + 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y + 1].getNappula()))) {
                siirrot.put(x + 1, y + 1);
            }
            if (x - 1 >= 0 && (ruudukko[x - 1][y].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y].getNappula()))) {
                siirrot.put(x - 1, y);
            }
            if (y - 1 >= 0 && (ruudukko[x][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x][y - 1].getNappula()))) {
                siirrot.put(x, y - 1);
            }
            if (x - 1 >= 0 && y - 1 >= 0 && (ruudukko[x - 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y - 1].getNappula()))) {
                siirrot.put(x - 1, y - 1);
            }
            if (x + 1 <= 7 && y - 1 >= 0 && (ruudukko[x + 1][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y - 1].getNappula()))) {
                siirrot.put(x + 1, y - 1);
            }
            if (x - 1 >= 0 && y + 1 <= 7 && (ruudukko[x - 1][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y + 1].getNappula()))) {
                siirrot.put(x - 1, y + 1);
            }
        }
        if (this.tyyppi == Tyyppi.VSOTILAS) {
            if (x == 6 && (ruudukko[x - 2][y].getNappula() == null)) {
                siirrot.put(x - 2, y);
            }
            if (x - 1 >= 0 && ruudukko[x - 1][y].getNappula() == null) {
                siirrot.put(x - 1, y);
            }

            if (x - 1 >= 0 && y - 1 >= 0 && ruudukko[x - 1][y - 1].getNappula() != null && !onkoSamaVari(ruudukko[x - 1][y - 1].getNappula())) {
                siirrot.put(x - 1, y - 1);
            }

            if (x - 1 >= 0 && y + 1 <= 7 && ruudukko[x - 1][y + 1].getNappula() != null && !onkoSamaVari(ruudukko[x - 1][y + 1].getNappula())) {
                siirrot.put(x - 1, y + 1);
            }
        }
        if (this.tyyppi == Tyyppi.MSOTILAS) {
            if (x == 1 && (ruudukko[x + 2][y].getNappula() == null)) {
                siirrot.put(x + 2, y);
            }
            if (x + 1 <= 7 && ruudukko[x + 1][y].getNappula() == null) {
                siirrot.put(x + 1, y);
            }
            if (x + 1 <= 7 && y + 1 <= 7 && ruudukko[x + 1][y + 1].getNappula() != null && !onkoSamaVari(ruudukko[x + 1][y + 1].getNappula())) {
                siirrot.put(x - 1, y - 1);
            }

            if (x + 1 <= 7 && y - 1 >= 0 && ruudukko[x + 1][y - 1].getNappula() != null && !onkoSamaVari(ruudukko[x + 1][y - 1].getNappula())) {
                siirrot.put(x + 1, y - 1);
            }
        }

        if (this.tyyppi == Tyyppi.MTORNI || this.tyyppi == Tyyppi.VTORNI) {
            for (int i = x + 1; i <= 7; i++) {
                if (ruudukko[i][y].getNappula() == null) {
                    siirrot.put(i, y);
                }
                if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                    siirrot.put(i, y);
                    break;
                }
                if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                    break;
                }
            }

            for (int i = x - 1; i >= 0; i--) {
                if (ruudukko[i][y].getNappula() == null) {
                    siirrot.put(i, y);
                }
                if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                    siirrot.put(i, y);
                    break;
                }
                if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                    break;
                }
            }

            for (int i = y + 1; i <= 7; i++) {
                if (ruudukko[x][i].getNappula() == null) {
                    siirrot.put(x, i);
                }
                if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                    siirrot.put(x, i);
                    break;
                }
                if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                    break;
                }
            }

            for (int i = y - 1; i >= 0; i--) {
                if (ruudukko[x][i].getNappula() == null) {
                    siirrot.put(x, i);
                }
                if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                    siirrot.put(x, i);
                    break;
                }
                if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                    break;
                }
            }
        }

        if (this.tyyppi == Tyyppi.MRATSU || this.tyyppi == Tyyppi.VRATSU) {
            if (x - 1 >= 0 && y - 2 >= 0 && (ruudukko[x - 1][y - 2].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y - 2].getNappula()))) {
                siirrot.put(x - 1, y - 2);
            }
            if (x - 2 >= 0 && y - 1 >= 0 && (ruudukko[x - 2][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 2][y - 1].getNappula()))) {
                siirrot.put(x - 2, y - 1);
            }
            if (x - 1 >= 0 && y + 2 <= 7 && (ruudukko[x - 1][y + 2].getNappula() == null || !onkoSamaVari(ruudukko[x - 1][y + 2].getNappula()))) {
                siirrot.put(x - 1, y + 2);
            }
            if (x - 2 >= 0 && y + 1 <= 7 && (ruudukko[x - 2][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x - 2][y + 1].getNappula()))) {
                siirrot.put(x - 2, y + 1);
            }
            if (x + 1 <= 7 && y - 2 >= 0 && (ruudukko[x + 1][y - 2].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y - 2].getNappula()))) {
                siirrot.put(x + 1, y - 2);
            }
            if (x + 2 <= 7 && y - 1 >= 0 && (ruudukko[x + 2][y - 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 2][y - 1].getNappula()))) {
                siirrot.put(x + 2, y - 1);
            }
            if (x + 1 <= 7 && y + 2 <= 7 && (ruudukko[x + 1][y + 2].getNappula() == null || !onkoSamaVari(ruudukko[x + 1][y + 2].getNappula()))) {
                siirrot.put(x + 1, y + 2);
            }
            if (x + 2 <= 7 && y + 1 <= 7 && (ruudukko[x + 2][y + 1].getNappula() == null || !onkoSamaVari(ruudukko[x + 2][y + 1].getNappula()))) {
                siirrot.put(x + 2, y + 1);
            }
        }

        if (this.tyyppi == Tyyppi.MLAHETTI || this.tyyppi == Tyyppi.VLAHETTI) {

            for (int i = 1; i <= 7; i++) {
                if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() == null) {
                    siirrot.put(x + i, y + i);
                }
                if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                    siirrot.put(x + i, y + i);
                    break;
                }
                if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                    break;
                }
            }
            for (int i = 1; i <= 7; i++) {
                if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() == null) {
                    siirrot.put(x - i, y - i);
                }
                if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                    siirrot.put(x - i, y - i);
                    break;
                }
                if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                    break;
                }
            }
            for (int i = 1; i <= 7; i++) {
                if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() == null) {
                    siirrot.put(x - i, y + i);
                }
                if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                    siirrot.put(x - i, y + i);
                    break;
                }
                if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                    break;
                }
            }
            for (int i = 1; i <= 7; i++) {
                if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() == null) {
                    siirrot.put(x + i, y - i);
                }
                if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                    siirrot.put(x + i, y - i);
                    break;
                }
                if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                    break;
                }
            }

            if (this.tyyppi == Tyyppi.MKUNINGATAR || this.tyyppi == Tyyppi.VKUNINGATAR) {
                for (int i = 1; i <= 7; i++) {
                    if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() == null) {
                        siirrot.put(x + i, y + i);
                    }
                    if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                        siirrot.put(x + i, y + i);
                        break;
                    }
                    if (x + i <= 7 && y + i <= 7 && ruudukko[x + i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y + i].getNappula())) {
                        break;
                    }
                }
                for (int i = 1; i <= 7; i++) {
                    if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() == null) {
                        siirrot.put(x - i, y - i);
                    }
                    if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                        siirrot.put(x - i, y - i);
                        break;
                    }
                    if (x - i >= 0 && y - i >= 0 && ruudukko[x - i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y - i].getNappula())) {
                        break;
                    }
                }
                for (int i = 1; i <= 7; i++) {
                    if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() == null) {
                        siirrot.put(x - i, y + i);
                    }
                    if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && !onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                        siirrot.put(x - i, y + i);
                        break;
                    }
                    if (x - i >= 0 && y + i <= 7 && ruudukko[x - i][y + i].getNappula() != null && onkoSamaVari(ruudukko[x - i][y + i].getNappula())) {
                        break;
                    }
                }
                for (int i = 1; i <= 7; i++) {
                    if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() == null) {
                        siirrot.put(x + i, y - i);
                    }
                    if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && !onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                        siirrot.put(x + i, y - i);
                        break;
                    }
                    if (x + i <= 7 && y - i >= 0 && ruudukko[x + i][y - i].getNappula() != null && onkoSamaVari(ruudukko[x + i][y - i].getNappula())) {
                        break;
                    }
                }
                for (int i = x + 1; i <= 7; i++) {
                    if (ruudukko[i][y].getNappula() == null) {
                        siirrot.put(i, y);
                    }
                    if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                        siirrot.put(i, y);
                        break;
                    }
                    if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                        break;
                    }
                }

                for (int i = x - 1; i >= 0; i--) {
                    if (ruudukko[i][y].getNappula() == null) {
                        siirrot.put(i, y);
                    }
                    if (ruudukko[i][y].getNappula() != null && !onkoSamaVari(ruudukko[i][y].getNappula())) {
                        siirrot.put(i, y);
                        break;
                    }
                    if (ruudukko[i][y].getNappula() != null && onkoSamaVari(ruudukko[i][y].getNappula())) {
                        break;
                    }
                }

                for (int i = y + 1; i <= 7; i++) {
                    if (ruudukko[x][i].getNappula() == null) {
                        siirrot.put(x, i);
                    }
                    if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                        siirrot.put(x, i);
                        break;
                    }
                    if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                        break;
                    }
                }

                for (int i = y - 1; i >= 0; i--) {
                    if (ruudukko[x][i].getNappula() == null) {
                        siirrot.put(x, i);
                    }
                    if (ruudukko[x][i].getNappula() != null && !onkoSamaVari(ruudukko[x][i].getNappula())) {
                        siirrot.put(x, i);
                        break;
                    }
                    if (ruudukko[x][i].getNappula() != null && onkoSamaVari(ruudukko[x][i].getNappula())) {
                        break;
                    }
                }
            }
        }
        return siirrot;
    }

}
