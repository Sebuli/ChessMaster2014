package ChessMaster;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Nappulat.Nappula;
import ChessMaster.Pelilauta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sebbe
 */
public class PelilautaTest {

    private Pelilauta lauta;

    @Before
    public void setUp() {
        lauta = new Pelilauta();
        lauta.uusiPeli();
    }

    @Test
    public void poistaaNappulanVanhastaRuudustaKunSiirretäänTyhjaan() {
        lauta.siirra(1, 0, 2, 0);
        assertEquals(null, lauta.getRuudukko()[1][0].getNappula());
    }

    @Test
    public void siirtaaMustanUuteenRuutuunKunOnTyhja() {
        lauta.siirra(1, 0, 2, 0);
        assertEquals(Nappula.Tyyppi.MSOTILAS, lauta.getRuudukko()[2][0].getNappula().getTyyppi());
    }

    @Test
    public void siirtaaValkoisenUuteenRuutuunKunOnTyhja() {
        lauta.siirra(6, 0, 5, 0);
        assertEquals(Nappula.Tyyppi.VSOTILAS, lauta.getRuudukko()[5][0].getNappula().getTyyppi());
    }

    @Test
    public void eiSiirraRuutuunKunSiinaOnSamanVarinenNappulaMusta() {
        lauta.siirra(1, 0, 1, 1);
        assertEquals(Nappula.Tyyppi.MSOTILAS, lauta.getRuudukko()[1][0].getNappula().getTyyppi());
    }

    @Test
    public void eiSiirraRuutuunKunSiinaOnSamanVarinenNappulaValkoinen() {
        lauta.siirra(6, 0, 6, 1);
        assertEquals(Nappula.Tyyppi.VSOTILAS, lauta.getRuudukko()[6][0].getNappula().getTyyppi());
    }

    @Test
    public void syoNappulanJosOnEriVarinenKunSiirretaan() {
        lauta.siirra(1, 0, 6, 0);
        assertEquals("musta", lauta.getRuudukko()[6][0].getNappula().getVari());
    }

    @Test
    public void poistaaNappulanAlkuperaisestaRuudustaKunSiirretaanRuutuunJossaEriVarinenNappula() {
        lauta.siirra(1, 0, 6, 0);
        assertEquals(null, lauta.getRuudukko()[1][0].getNappula());
    }

    @Test
    public void ruudukkoLuodaanOikein() {
        Pelilauta pelilauta = new Pelilauta();
        pelilauta.luoRuudukko();
        assertNotNull(pelilauta.getRuudukko());
    }

    @Test
    public void onkoMustaShakkiMattiPalauttaaFalse() {
        assertEquals(false, lauta.onkoMustaShakkiMatti());
    }
    @Test
    public void onkoValkoinenShakkiMattiPalauttaaFalse() {
        assertEquals(false, lauta.onkoValkoinenShakkiMatti());
    }
    
    @Test
    public void onkoValkoinenShakkiPalauttaaFalse(){
        assertEquals(false, lauta.onkoValkoinenShakki());
    }
    
    @Test
    public void onkoMustaShakkiPalauttaaFalse(){
        assertEquals(false, lauta.onkoMustaShakki());
    }

}
