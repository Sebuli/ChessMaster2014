/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import ChessMaster.Pelilauta;
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
public class RandomAITest {

    private Pelilauta pelilauta;
    private RandomAI random;

    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
        pelilauta.uusiPeli();
        random = new RandomAI();
    }

    @Test
    public void testHaeSiirtoMusta() {
        String siirto = random.haeSiirto(pelilauta, "musta");
        assertNotNull(siirto);
    }

    @Test
    public void testHaeSiirtoValkoinen() {
        String siirto = random.haeSiirto(pelilauta, "valkoinen");
        assertNotNull(siirto);
    }

}
