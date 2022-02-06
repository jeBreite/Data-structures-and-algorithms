
import java.util.NoSuchElementException;
import java.lang.StringBuilder;
import java.util.Iterator;

public class TRAI_21_X7_jebreite<E> implements TRAI_21_X7<E> {

    /**
     * ITSEARVIOINTI TÄHÄN: Tehtävä oli tällä kertaa haastava, erityisesti
     * renkaan toteuttaminen ja kasvattaminen (sekä tulostaminen) tuottivat
     * aluksi suuria hankaluuksia. Sain kuitenkin omasta mielestäni melko
     * suoraviivaisen ratkaisun toteutettua. Hieman jäin pohtimaan olisiko
     * ratkaisun voinut toteuttaa yksinkertaisemmin tai "kauniimmin".
     *
     * lisaa – O(1) tai jos taulukon kokoa kasvatetaan O(n) poista – O(1)
     * onkoTyhja – O(1)
     *
     */
    E[] jono;
    int eka;
    int vika;
    int alkioita;
    int koko;

    public TRAI_21_X7_jebreite() {
        koko = 20;
        jono = (E[]) (new Object[koko]);
        eka = -1;
        vika = -1;
        alkioita = 0;

    }

    /**
     * Lisää jonoon yhden alkion.
     *
     * Jos jonossa on tilaa – O(1) Jos taulukon koko tuplataan ja siirretään
     * alkiot uuteen taulukkoon – O(n)
     *
     * @param x lisättävä alkio.
     */
    @Override
    public void lisaa(E x) {

        //siirretään ensimmäisen indeksi nollaan kun lisätään ensimmäinen alkio
        if (eka == -1) {
            eka = 0;
        }

        // jos taulukko on täynnä tuplataan koko
        if (alkioita == jono.length) {
            E[] jono2 = (E[]) (new Object[jono.length * 2]);

            // siirretään alkiot uuteen taulukkoon
            for (int i = 0; (i + eka) < jono.length; i++) {
                jono2[i] = jono[eka + i];
                vika = i;
            }
            for (int i = 0; i < eka; i++) {
                vika++;
                jono2[vika] = jono[i];
            }
            jono = jono2;
            eka = 0;
            vika = alkioita - 1;
        }

        // kasvatetaan jonon viimeisen indeksiä ja sijoitetaan alkio taulukkoon
        vika = (vika + 1) % (jono.length);
        jono[vika] = x;
        alkioita++;
    }

    /**
     * Poistaa ja palauttaa jonosta siellÃ¤ pisimpÃ¤Ã¤n olleen alkion.
     *
     * O(1)
     *
     * @return poistettu jonossa pisimpÃ¤Ã¤n ollut alkio.
     * @throws NoSuchElementException jollei jonossa ole yhtÃ¤Ã¤n alkiota.
     */
    @Override
    public E poista() {
        // jos taulukko on tyhjä palautetaan poikkeus
        if (onkoTyhja() == true) {
            throw new NoSuchElementException();
        }
        //otetaan palautettava alkio talteen ja kasvatetaan ensimmäisen alkion indeksiä
        E e = jono[eka];
        eka = (eka + 1) % (jono.length);
        alkioita--;
        return e;

    }

    /**
     * Onko jono tyhjä vai ei?
     *
     * O(1)
     *
     * @return true jos jonossa ei ole yhtÃ¤Ã¤n alkiota, muuten false
     */
    @Override
    public boolean onkoTyhja() {
        //jos taulukko on tyhjä palautetaan true muuten false
        if (alkioita == 0) {
            return true;
        }
        return false;
    }

    /**
     * O(n) n=jonon alkiot
     *
     * @return jono tulostettuna
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("< ");

        /*jos viimeinen alkio on pienempi eli taulukko on mennyt "ympäri" 
        tulostetaan ensin jonon ensimmäisestä taulukon loppuun ja sitten 
        taulukon alusta viimeiseen*/
        if (vika < eka) {
            for (int i = eka; i < jono.length; i++) {
                sb.append(jono[i].toString());
                sb.append(" ");
            }
            for (int i = 0; i <= vika; i++) {
                sb.append(jono[i].toString());
                sb.append(" ");
            }
            // muuten ensimmäisestä viimeiseen
        } else {
            for (int i = eka; i <= vika; i++) {
                sb.append(jono[i].toString());
                sb.append(" ");
            }
        }
        sb.append("<");

        return sb.toString();
    }

    // jonon iteraattori
    @Override
    public Iterator<E> iterator() {
        return new JonoIter();
    }

    private class JonoIter implements Iterator<E> {

        int ekaInd; 
        int vikaInd;
        int laskuri;


        public JonoIter() {
            ekaInd = eka;
            vikaInd = vika;
            laskuri = 0;
        }

        // jonossa on seuraava mikäli laskuri on pienempi kuin alkioiden määrä
        // O(1)
        @Override
        public boolean hasNext() {
            return laskuri < alkioita; 
        }

        // jonon seuraava indeksi
        // O(1)
        @Override
        public E next() {
            // jos laskuri on suurempi tai yhtäsuuri kuin alkioiden määrä ei 
            // jonossa ole enää alkioita ja palautetaan poikkeus
            if (laskuri >= alkioita) {
                throw new NoSuchElementException("next() called without hasNext()");
            }

            // jos taulukko on tultu loppuun asetetaan ekaInd arvoksi 0 
            // rengasta aletaan lukea taulukon alusta)
            if (ekaInd == jono.length) {
                ekaInd = 0;
            }
            
            laskuri ++;

            return jono[ekaInd++];

        }

    }
}
