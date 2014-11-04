/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Nappulat.Kuningas;
import Nappulat.Kuningatar;
import Nappulat.Nappula;
import ChessMaster.Ruutu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebbe
 */
public class RuutuTest {
    
    private Ruutu ruutu;
    
    @Before
    public void setUp() {
        ruutu = new Ruutu();
    }

    @Test
    public void kunLuodaanRuutuSillaEiOleNappulaa(){
        assertEquals(null, ruutu.getNappula());
    }
    
    @Test
    public void pystyyLisataNappulanRuutuun(){
        Nappula nappula = new Kuningas("musta");
        ruutu.asetaNappula(nappula);
        assertEquals(nappula, ruutu.getNappula());
    }
    
    @Test
    public void pystyyPoistamaanNappulanRuudusta(){
        Nappula nappula = new Kuningas("musta");
        ruutu.asetaNappula(nappula);
        ruutu.poistaNappula();
        assertEquals(null, ruutu.getNappula());
    }
    
    @Test
    public void voiVaihtaaRuudunNappulanAsettamallaSilleUusiNappula(){
        Nappula nappula = new Kuningas("musta");
        Nappula uusiNappula = new Kuningatar("valkoinen");
        ruutu.asetaNappula(nappula);
        ruutu.asetaNappula(uusiNappula);
        assertEquals(Nappula.Tyyppi.KUNINGATAR, ruutu.getNappula().getTyyppi());
    }
    
    @Test
    public void palauttaaOikeanNappulan(){
        Nappula nappula = new Kuningas("musta");
        ruutu.asetaNappula(nappula);
        assertEquals(nappula, ruutu.getNappula());
    }
}
