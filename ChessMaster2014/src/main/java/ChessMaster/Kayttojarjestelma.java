package ChessMaster;

import Nappulat.Kuningatar;
import Nappulat.Nappula;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Sebbe
 */
public class Kayttojarjestelma {

    private JButton[][] ruudukko = new JButton[8][8];
    private Pelilauta pelilauta;
    private JPanel shakkiLauta;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private final JLabel kenenVuoro = new JLabel("");
    private final JLabel viesti = new JLabel("");
    private static final String COLS = "ABCDEFGH";
    public static final int KUNINGATAR = 0, KUNINGAS = 1,
            TORNI = 2, RATSU = 3, LAHETTI = 4, SOTILAS = 5;
    public static final int[] STARTING_ROW = {
        TORNI, RATSU, LAHETTI, KUNINGAS, KUNINGATAR, LAHETTI, RATSU, TORNI
    };
    public static final int MUSTA = 0, VALKOINEN = 1;
    private Image[][] chessPieceImages = new Image[2][6];

    private boolean onkoNaytaMahdollisetSiirrot;

    private boolean peliAlkanut;

    private int ekaX;
    private int ekaY;
    private int tokaX;
    private int tokaY;

    private boolean valkoisenVuoro;

    
    public Kayttojarjestelma() {
        initComponents();
    }

    /**
     * Metodi luo käyttöjärjestelmälle kaikki tarvittavas komponentit ja alustaa muuttujia.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        this.pelilauta = new Pelilauta();
        peliAlkanut = false;
        luoNappulaKuvat();
        onkoNaytaMahdollisetSiirrot = false;

        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("New Game") {

            @Override
            public void actionPerformed(ActionEvent e) {
                aloitaPeli();
            }

        };

        tools.add(newGameAction);
        tools.addSeparator();

        Action Giveup = new AbstractAction("Give Up") {

            @Override
            public void actionPerformed(ActionEvent e) {
                annaPeriksi();

            }

        };
        tools.add(Giveup);
        tools.addSeparator();

        JCheckBox naytaMahdollisetSiirrot = new JCheckBox(new Action() {

            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {
            }

            @Override
            public void setEnabled(boolean b) {
                onkoNaytaMahdollisetSiirrot = b;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (onkoNaytaMahdollisetSiirrot) {
                    onkoNaytaMahdollisetSiirrot = false;
                    varitaPelilauta();
                    if ( ekaX != -1){
                        ruudukko[ekaX][ekaY].setBackground(Color.orange);
                    }
                } else {
                    onkoNaytaMahdollisetSiirrot = true;
                    if (ekaX != -1) {
                        varitaMahdollisetSiirrot();
                    }
                }
            }
        });

        naytaMahdollisetSiirrot.setText("Enable help");

        tools.add(naytaMahdollisetSiirrot);
        tools.addSeparator();
        tools.add(kenenVuoro);
        tools.addSeparator();
        tools.add(viesti);

        shakkiLauta = new JPanel(new GridLayout(0, 9)) {

            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
                } else if (c != null && c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                int s = (w > h ? h : w);
                return new Dimension(s, s);
            }
        };

        Color vari = new Color(204, 204, 255);
        shakkiLauta.setBackground(vari);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(vari);
        boardConstrain.add(shakkiLauta);
        gui.add(boardConstrain);

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                b.addMouseListener(new Mouse());
                ruudukko[j][i] = b;
            }

        }
        shakkiLauta.add(new JLabel(""));
        for (int i = 0; i < 8; i++) {
            shakkiLauta.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (j) {
                    case 0:
                        shakkiLauta.add(new JLabel("" + (9 - (i + 1)), SwingConstants.CENTER));
                    default:
                        shakkiLauta.add(ruudukko[i][j]);

                }
            }
        }
    }

    /**
     * Tyhjentää ruudukon nappuloista
     */
    private final void annaPeriksi() {
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                ruudukko[i][t].setIcon(null);
            }
        }
        peliAlkanut = false;
        varitaPelilauta();
        pelilauta.uusiPeli();
        viesti.setText("Coward...");
        kenenVuoro.setText("");
    }

    /**
     * Värittää pelilaudan ja laittaa nappulat oikeisiin paikkoihin. Ruutuja voi metodin suorituksen jälkeen painaa.
     */
    private final void aloitaPeli() {
        viesti.setText("");
        peliAlkanut = true;
        pelilauta.uusiPeli();
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                ruudukko[i][t].setEnabled(true);
            }
        }
        kenenVuoro.setText("White to move");
        ekaX = -1;
        ekaY = -1;
        tokaX = -1;
        tokaY = -1;

        valkoisenVuoro = true;

        varitaPelilauta();

        piirraNappulat();
    }

    /**
     * Lopettaa pelin ja nollaa pelilaudan.
     */
    private void lopetaPeli() {
        if (!valkoisenVuoro) {
            viesti.setText("Checkmate, Black wins!");
        } else {
            viesti.setText("Checkmate, White wins!");
        }
        peliAlkanut = false;
        pelilauta.uusiPeli();

    }
    
    /**
     * Piirtää pelinappulat niihin sijanteihin missä ne ovat pelilaudassa.
     */
    private final void piirraNappulat() {
        viesti.setText("");
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                ruudukko[i][t].setIcon(null);
            }
        }
        for (int i = 0; i <= 7; i++) {
            for (int t = 0; t <= 7; t++) {
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.VKUNINGAS)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][KUNINGAS]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.MKUNINGAS)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][KUNINGAS]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.VKUNINGATAR)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][KUNINGATAR]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.MKUNINGATAR)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][KUNINGATAR]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.VLAHETTI)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][LAHETTI]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.MLAHETTI)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][LAHETTI]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.VRATSU)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][RATSU]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.MRATSU)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][RATSU]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.VSOTILAS)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][SOTILAS]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.MSOTILAS)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][SOTILAS]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.VTORNI)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[VALKOINEN][TORNI]));
                }
                if (pelilauta.getRuudukko()[i][t].getNappula() != null && pelilauta.getRuudukko()[i][t].getNappula().getTyyppi().equals(Nappula.Tyyppi.MTORNI)) {
                    ruudukko[i][t].setIcon(new ImageIcon(chessPieceImages[MUSTA][TORNI]));
                }
            }

        }
    }

    /**
     * Värittää pelilaudan ruudut mustavalkoisiksi
     */
    private void varitaPelilauta() {
        for (int k = 0; k <= 7; k++) {
            for (int j = 0; j <= 7; j++) {
                if ((j % 2 == 1 && k % 2 == 1) || (j % 2 == 0 && k % 2 == 0)) {
                    ruudukko[k][j].setBackground(Color.WHITE);
                } else {
                    ruudukko[k][j].setBackground(Color.BLACK);
                }

            }
        }
    }

    /**
     * Siirtää nappulan ekaX,ekaY sijainnista tokaX,tokaY sijaintiin.
     */
    private void siirra() {

        pelilauta.siirra(ekaX, ekaY, tokaX, tokaY);

        if (tokaX == 0 && pelilauta.getRuudukko()[tokaX][tokaY].getNappula().getTyyppi() == Nappula.Tyyppi.VSOTILAS) {
            pelilauta.getRuudukko()[tokaX][tokaY].asetaNappula(new Kuningatar("valkoinen"));
        }

        if (tokaX == 7 && pelilauta.getRuudukko()[tokaX][tokaY].getNappula().getTyyppi() == Nappula.Tyyppi.MSOTILAS) {
            pelilauta.getRuudukko()[tokaX][tokaY].asetaNappula(new Kuningatar("musta"));
        }
        varitaPelilauta();
        ekaX = -1;
        ekaY = -1;
        tokaX = -1;
        tokaY = -1;

        piirraNappulat();
        if (valkoisenVuoro && pelilauta.onkoMustaShakkiMatti()) {
            lopetaPeli();
        } else if (!valkoisenVuoro && pelilauta.onkoValkoinenShakkiMatti()) {
            lopetaPeli();
        }
        
        if (valkoisenVuoro && pelilauta.onkoMustaShakki()) {
            viesti.setText("Chess against Black!");
        } else if (!valkoisenVuoro && pelilauta.onkoValkoinenShakki()) {
            viesti.setText("Chess against White!");
        }

        if (valkoisenVuoro) {
            valkoisenVuoro = false;
            kenenVuoro.setText("Black to move");
        } else {
            valkoisenVuoro = true;
            kenenVuoro.setText("White to move");
        }
    }

  
    private final void luoNappulaKuvat() {
        try {

            BufferedImage bi = ImageIO.read(new File("C:/Users/Sebbe/Documents/GitHub/ChessMaster2014/Assets/shakkikuva.png"));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    chessPieceImages[i][j] = bi.getSubimage(
                            j * 64, i * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                Kayttojarjestelma lauta = new Kayttojarjestelma();

                JFrame f = new JFrame("Chess Master 2014");
                f.add(lauta.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

    public class Mouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (peliAlkanut) {

                

                for (int i = 0; i <= 7; i++) {
                    for (int t = 0; t <= 7; t++) {
                        if (e.getComponent() == ruudukko[i][t]) {
                            if (pelilauta.getRuudukko()[i][t].getNappula() != null
                                    && ((pelilauta.getRuudukko()[i][t].getNappula().getVari().equals("musta") && !valkoisenVuoro)
                                    || (pelilauta.getRuudukko()[i][t].getNappula().getVari().equals("valkoinen") && valkoisenVuoro))
                                    && ekaX == -1) {

                                ekaX = i;
                                ekaY = t;

                                e.getComponent().setBackground(Color.ORANGE);

                                if (onkoNaytaMahdollisetSiirrot) {
                                    varitaMahdollisetSiirrot();
                                }
                                break;
                            } else if (ekaX != -1) {

                                tokaX = i;
                                tokaY = t;

                                ArrayList<String> mahdollisetSiirrot = pelilauta.getRuudukko()[ekaX][ekaY].getNappula().mahdollisetSiirrot(ekaX, ekaY, pelilauta.getRuudukko());
                                if (mahdollisetSiirrot.contains("" + tokaX + tokaY)) {
                                    siirra();
                                    break;
                                }
                                varitaPelilauta();
                                if (ekaX != tokaX || ekaY != tokaY) {
                                    viesti.setText("Illeagal move");
                                }
                                ekaX = -1;
                                ekaY = -1;
                                tokaX = -1;
                                tokaY = -1;

                                break;

                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    /**
     * Kun ruutu on valitti niin metodi piirtää sen nappulan joka on ruudusa mahdolliset siirtoruudut keltaisiksi.
     */
    private void varitaMahdollisetSiirrot() {
        ArrayList<String> mahdolliset = pelilauta.getRuudukko()[ekaX][ekaY].getNappula().mahdollisetSiirrot(ekaX, ekaY, pelilauta.getRuudukko());

        for (int a = 0; a <= 7; a++) {
            for (int b = 0; b <= 7; b++) {

                if (mahdolliset.contains("" + a + b)) {
                    ruudukko[a][b].setBackground(Color.yellow);
                }
            }
        }
    }
}
