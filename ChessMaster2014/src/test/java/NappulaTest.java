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
    public void nappulaSaaOikeanTyypin(){
        Nappula nappula = new Torni("musta");
        assertEquals(Nappula.Tyyppi.TORNI, nappula.getTyyppi());
    }
    
    @Test
    public void palauttaaOikeanVarin(){
        Nappula nappula = new Nappula("musta");
        assertEquals("musta", nappula.getVari());
    }
    
    @Test
    public void palauttaaOikeanTyypin(){
        Nappula nappula = new Nappula("musta");
        nappula.setTyyppi(Nappula.Tyyppi.RATSU);
        assertEquals(Nappula.Tyyppi.RATSU, nappula.getTyyppi());
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
    

}
