
import java.util.ArrayList;

public class TRA_listojenKasittely {

    /**
     *
     * Aikavaativuus T(O)=n
     */
    /**
     * Kasvavien listojen erotus. Palauttaa sellaiset alkiot jotka löytyvät
     * listasta A, mutta eivät listasta B. Jos alkio on listassa A useasti,
     * mutta ei lainkaan listassa B, alkio tulee tuloslistaan yhtä monta kertaa
     * kuin se listassa A on. Jos alkio on edes yhden kerran listassa B, se ei
     * tule tuloslistaan lainkaan. Tuloslista tulee myös kasvavaan
     * järjestykseen.
     *
     * @param A ensimmäinen syötelista, alkiot kasvavassa järjestyksessä
     * @param B toinen syötelista, alkiot kasvavassa järjestyksessä
     * @return erotuslista
     */
    @Override
    public ArrayList<Integer> kasvavienErotus(ArrayList<Integer> A, ArrayList<Integer> B) {

        ArrayList<Integer> tulos = new ArrayList<Integer>();
        
        // jos lista B on tyhjä palautetaan lista A
        if(B.isEmpty()) {       // T(O)=1
            return A;
        }

        // B:n indeksi
        int j = 0;

        //käydään lista A läpi
        for (int i = 0; i < A.size(); i++) {        // T(O)=n (suurin)

            // jos A(i) on suurempi kuin B(j)
            if (A.get(i) > B.get(j)) {

                //  jos B:n indeksi on sama kuin listan pituus -1 eli lista B on käyty loppuun lisätään suoraan A(i) tulokseen
                if (j == B.size()-1) {
                    tulos.add(A.get(i));        // T(O)=1

                  // muuten haetaan B listalta seuraava luku joka on suurempi kuin A(i)
                } else {
                    while (B.get(j) < A.get(i)) {
                        // jos tullaan listan loppuun lopetetaan
                        if(j == B.size()-1) {
                            break;
                        } else {
                            j++;
                        }                        
                    } // jos luku listalta B ei ole sama kuin listalta A lisätään se tulokseen
                    if (!B.get(j).equals(A.get(i))) {
                        tulos.add(A.get(i));        // T(O)=1
                        // "pakitetaan" B listalla yksi indeksi, koska while-loopissa on lisätty indeksin arvoa yhdellä "osuman" jälkeen
                        j--;
                    }
                }
            }
            // jos A(i) on pienempi kuin B(j) lisätään se suoraan listalle
            if (A.get(i) < B.get(j)) {
                tulos.add(A.get(i));        // T(O)=1
            }
        }

        return tulos;
    }

}
