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
import static org.junit.Assert.assertTrue;
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
    
    @Test
    public void josOnShakkiNiinPalauttaaTrueMusta(){
        lauta.siirra(6, 2, 1, 3);
        assertTrue(lauta.onkoMustaShakki());
    }
    
    @Test
    public void josOnShakkiNiinPalauttaaTrueValkoinen(){
        lauta.siirra(0, 3, 6, 3);
        assertTrue(lauta.onkoValkoinenShakki());
    }
    
    @Test
    public void josOnShakkMattiiNiinPalauttaaTrueMusta(){
        lauta.siirra(0, 4, 3, 3);
        lauta.siirra(7, 0, 5, 2);
        lauta.siirra(7, 7, 5, 3);
        lauta.siirra(7, 3, 5, 4);
        assertTrue(lauta.onkoMustaShakkiMatti());
    }
    
    @Test
    public void josOnShakkMattiiNiinPalauttaaTrueValkoinen(){
        lauta.siirra(7, 4, 4, 3);
        lauta.siirra(0, 0, 3, 2);
        lauta.siirra(0, 7, 3, 3);
        lauta.siirra(0, 3, 3, 4);
        assertTrue(lauta.onkoValkoinenShakkiMatti());
    }
    
    @Test
    public void ruudukossaOnValkoinenRatsu(){
        assertEquals(Nappula.Tyyppi.VRATSU, lauta.getRuudukko()[7][1].getNappula().getTyyppi());
    }
    
    @Test
    public void ruudukossaOnMustaRatsu(){
        assertEquals(Nappula.Tyyppi.MRATSU, lauta.getRuudukko()[0][1].getNappula().getTyyppi());
    }
    
    @Test
    public void ruudukossaOnValkoinenLahetti(){
        assertEquals(Nappula.Tyyppi.VLAHETTI, lauta.getRuudukko()[7][2].getNappula().getTyyppi());
    }
    
    @Test
    public void ruudukossaOnMustaLahetti(){
        assertEquals(Nappula.Tyyppi.MLAHETTI, lauta.getRuudukko()[0][2].getNappula().getTyyppi());
    }

}
