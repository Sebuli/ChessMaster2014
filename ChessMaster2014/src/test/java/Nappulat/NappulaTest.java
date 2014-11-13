package Nappulat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ChessMaster.Pelilauta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebbe
 */
public class NappulaTest {

    private Nappula mustaNappula;
    private Nappula valkoinenNappula;
    private Pelilauta pelilauta;

    @Before
    public void setUp() {
        mustaNappula = new Nappula("musta");
        valkoinenNappula = new Nappula("valkoinen");
        pelilauta = new Pelilauta();
        pelilauta.uusiPeli();
    }

    @Test
    public void nappulaSaaOikeanVarinKunSeLuodaan() {
        Nappula nappula = new Nappula("musta");
        assertEquals("musta", nappula.getVari());
    }

    @Test
    public void mustaTorniSaaOikeanTyypin() {
        Nappula nappula = new Torni("musta");
        assertEquals(Nappula.Tyyppi.MTORNI, nappula.getTyyppi());
    }

    @Test
    public void valkoinenTorniSaaOikeanTyypin() {
        Nappula nappula = new Torni("valkoinen");
        assertEquals(Nappula.Tyyppi.VTORNI, nappula.getTyyppi());
    }

    @Test
    public void mustakuningasSaaOikeanTyypin() {
        Nappula nappula = new Kuningas("musta");
        assertEquals(Nappula.Tyyppi.MKUNINGAS, nappula.getTyyppi());
    }

    @Test
    public void valkoinenkuningasSaaOikeanTyypin() {
        Nappula nappula = new Kuningas("valkoinen");
        assertEquals(Nappula.Tyyppi.VKUNINGAS, nappula.getTyyppi());
    }

    @Test
    public void mustakuningatarSaaOikeanTyypin() {
        Nappula nappula = new Kuningatar("musta");
        assertEquals(Nappula.Tyyppi.MKUNINGATAR, nappula.getTyyppi());
    }

    @Test
    public void valkoinenkuningatarSaaOikeanTyypin() {
        Nappula nappula = new Kuningatar("valkoinen");
        assertEquals(Nappula.Tyyppi.VKUNINGATAR, nappula.getTyyppi());
    }

    @Test
    public void mustalahettiSaaOikeanTyypin() {
        Nappula nappula = new Lahetti("musta");
        assertEquals(Nappula.Tyyppi.MLAHETTI, nappula.getTyyppi());
    }

    @Test
    public void valkoinenlahettiSaaOikeanTyypin() {
        Nappula nappula = new Lahetti("valkoinen");
        assertEquals(Nappula.Tyyppi.VLAHETTI, nappula.getTyyppi());
    }

    @Test
    public void mustaratsuSaaOikeanTyypin() {
        Nappula nappula = new Ratsu("musta");
        assertEquals(Nappula.Tyyppi.MRATSU, nappula.getTyyppi());
    }

    @Test
    public void valkoinenratsuSaaOikeanTyypin() {
        Nappula nappula = new Ratsu("valkoinen");
        assertEquals(Nappula.Tyyppi.VRATSU, nappula.getTyyppi());
    }

    @Test
    public void mustasotilasSaaOikeanTyypin() {
        Nappula nappula = new Sotilas("musta");
        assertEquals(Nappula.Tyyppi.MSOTILAS, nappula.getTyyppi());
    }

    @Test
    public void valkoinensotilasSaaOikeanTyypin() {
        Nappula nappula = new Sotilas("valkoinen");
        assertEquals(Nappula.Tyyppi.VSOTILAS, nappula.getTyyppi());
    }

    @Test
    public void palauttaaOikeanVarin() {
        Nappula nappula = new Nappula("musta");
        assertEquals("musta", nappula.getVari());
    }

    @Test
    public void palauttaaNullJosVariaEiOleMaaratty() {
        Nappula nappula = new Nappula(null);
        assertEquals(null, nappula.getVari());
    }

    @Test
    public void eiPalautaNullJosTyyppiOnMaaratty() {
        Nappula nappula = new Nappula(null);
        nappula.setTyyppi(Nappula.Tyyppi.MRATSU);
        assertNotNull(nappula.getTyyppi());
    }

    @Test
    public void eiPalautaNullTyyppia() {
        Nappula nappula = new Nappula("musta");
        assertNull(nappula.getTyyppi());
    }

    @Test
    public void kaksiSamanVaristaNappulaaOnSamanvarisia() {
        Nappula nappula = new Nappula("musta");
        Nappula toinen = new Nappula("musta");
        assertEquals(true, nappula.onkoSamaVari(toinen));
    }

    @Test
    public void mahdollisetSiirrotPalauttaaListan() {
        assertNotNull(mustaNappula.mahdollisetSiirrot(0, 0, pelilauta.getRuudukko()));
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinMKuningas() {
        Nappula kuningas = new Kuningas("musta");
        assertTrue(kuningas.mahdollisetSiirrot(3, 3, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinVKuningas() {
        Nappula kuningas = new Kuningas("valkoinen");
        assertTrue(kuningas.mahdollisetSiirrot(3, 3, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinVSotilas() {
        Nappula sotilas = new Sotilas("valkoinen");
        assertTrue(sotilas.mahdollisetSiirrot(6, 1, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinMSotilas() {
        Nappula sotilas = new Sotilas("musta");
        assertTrue(sotilas.mahdollisetSiirrot(1, 1, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinVKuningatar() {
        Nappula kuningatar = new Kuningatar("valkoinen");
        assertTrue(kuningatar.mahdollisetSiirrot(6, 1, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinMKuningatar() {
        Nappula kuningatar = new Kuningatar("musta");
        assertTrue(kuningatar.mahdollisetSiirrot(6, 1, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinMLahetti() {
        Nappula lahetti = new Lahetti("musta");
        assertTrue(lahetti.mahdollisetSiirrot(6, 1, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinVLahetti() {
        Nappula lahetti = new Lahetti("valkoinen");
        assertTrue(lahetti.mahdollisetSiirrot(6, 1, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinMRatsu() {
        Nappula ratsu = new Ratsu("musta");
        assertTrue(ratsu.mahdollisetSiirrot(6, 1, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinVRatsu() {
        Nappula ratsu = new Ratsu("valkoinen");
        assertTrue(ratsu.mahdollisetSiirrot(6, 1, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinMTorni() {
        Nappula torni = new Torni("musta");
        assertTrue(torni.mahdollisetSiirrot(6, 1, pelilauta.getRuudukko()).size() > 1);
    }

    @Test
    public void mahdollisetSiirrotPalauttaaOikeinVTorni() {
        Nappula torni = new Torni("valkoinen");
        assertTrue(torni.mahdollisetSiirrot(6, 1, pelilauta.getRuudukko()).size() > 1);
    }

}
