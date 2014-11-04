/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.chessmaster.chessmaster2014.Nappulat.Nappula;
import com.chessmaster.chessmaster2014.Pelilauta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
    public void poistaaNappulanVanhastaRuudustaKunSiirretäänTyhjaan(){
        lauta.siirra(1, 0, 2, 0);
        Assert.assertEquals(null, lauta.getRuudukko()[1][0].getNappula());
    }
    
    @Test
    public void siirtaaUuteenRuutuunKunOnTyhja(){
        lauta.siirra(1, 0, 2, 0);
        Assert.assertEquals(Nappula.Tyyppi.SOTILAS, lauta.getRuudukko()[2][0].getNappula().getTyyppi());
    }
    
    @Test
    public void eiSiirraRuutuunKunSiinaOnSamanVarinenNappula(){
        lauta.siirra(1, 0, 1, 1);
        Assert.assertEquals(Nappula.Tyyppi.SOTILAS, lauta.getRuudukko()[1][0].getNappula().getTyyppi());
    }
    
    @Test
    public void syoNappulanJosOnEriVarinenKunSiirretaan(){
        lauta.siirra(1, 0, 6, 0);
        Assert.assertEquals("musta", lauta.getRuudukko()[6][0].getNappula().getVari());
    }
    
    @Test
    public void poistaaNappulanAlkuperaisestaRuudustaKunSiirretaanRuutuunJossaEriVarinenNappula(){
        lauta.siirra(1, 0, 6, 0);
        Assert.assertEquals(null, lauta.getRuudukko()[1][0].getNappula());
    }

   
}
