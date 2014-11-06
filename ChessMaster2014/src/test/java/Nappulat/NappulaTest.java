package Nappulat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Nappulat.Nappula;
import Nappulat.Torni;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebbe
 */
public class NappulaTest {
    
    
    

    @Before
    public void setUp() {
      
    }
    
    @Test
    public void nappulaSaaOikeanVarinKunSeLuodaan(){
        Nappula nappula = new Nappula("musta");
        assertEquals("musta", nappula.getVari());
    }
    
    @Test
    public void torniSaaOikeanTyypin(){
        Nappula nappula = new Torni("musta");
        assertEquals(Nappula.Tyyppi.TORNI, nappula.getTyyppi());
    }
    
    @Test
    public void kuningasSaaOikeanTyypin(){
        Nappula nappula = new Kuningas("musta");
        assertEquals(Nappula.Tyyppi.KUNINGAS, nappula.getTyyppi());
    }
    @Test
    public void kuningatarSaaOikeanTyypin(){
        Nappula nappula = new Kuningatar("musta");
        assertEquals(Nappula.Tyyppi.KUNINGATAR, nappula.getTyyppi());
    }
    @Test
    public void lahettiSaaOikeanTyypin(){
        Nappula nappula = new Lahetti("musta");
        assertEquals(Nappula.Tyyppi.LAHETTI, nappula.getTyyppi());
    }
    @Test
    public void ratsuSaaOikeanTyypin(){
        Nappula nappula = new Ratsu("musta");
        assertEquals(Nappula.Tyyppi.RATSU, nappula.getTyyppi());
    }
    @Test
    public void sotilasSaaOikeanTyypin(){
        Nappula nappula = new Sotilas("musta");
        assertEquals(Nappula.Tyyppi.SOTILAS, nappula.getTyyppi());
    }
    
    @Test
    public void palauttaaOikeanVarin(){
        Nappula nappula = new Nappula("musta");
        assertEquals("musta", nappula.getVari());
    }
    
    
    
    @Test
    public void palauttaaNullJosVariaEiOleMaaratty(){
        Nappula nappula = new Nappula(null);
        assertEquals(null, nappula.getVari());
    }
    
    @Test
    public void eiPalautaNullJosTyyppiOnMaaratty(){
        Nappula nappula = new Nappula(null);
        nappula.setTyyppi(Nappula.Tyyppi.RATSU);
        assertNotNull(nappula.getTyyppi());
    }
    
    @Test
    public void eiPalautaNullTyyppia(){
        Nappula nappula = new Nappula("musta");
        assertNull(nappula.getTyyppi());
    }
    
    @Test
    public void kaksiSamanVaristaNappulaaOnSamanvarisia(){
        Nappula nappula = new Nappula("musta");
        Nappula toinen = new Nappula("musta");
        assertEquals(true, nappula.onkoSamaVari(toinen));
    }
    
    
    

}
