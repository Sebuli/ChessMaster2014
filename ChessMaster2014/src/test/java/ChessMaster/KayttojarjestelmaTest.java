/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessMaster;

import javax.swing.JComponent;
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
public class KayttojarjestelmaTest {

    private Kayttojarjestelma kayttis;

    @Before
    public void setUp() {
        kayttis = new Kayttojarjestelma();

    }

    @Test
    public void luoKayttojarjestelmanOikein() {
        assertNotNull(kayttis.getGui());
    }

}
