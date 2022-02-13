
import java.util.HashSet;
import java.util.Set;

public class TRA_HashSet_kasittely {

    /**
     *
     * Aikavaativuus O(n)
     */
    /**
     * Joukkojen kaksi kolmesta -leikkaus. Luo uuden joukon johon algoritmi
     * laittaa ne syötejoukkojen alkiot jotka kuuluvat tasan kahteen kolmesta
     * syötejoukosta. Jos jokin alkio kuuluu vain yhteen tai kaikkiin kolmeen
     * syötejoukkoon, alkiota ei laiteta tulosjoukkoon. Ei muuta
     * syötejoukkojensa sisältöä (vaan luo uuden tulosjoukon) Jos mikään
     * alkio ei täytä ehtoa, palautetaan tyhjä joukko.
     *
     * @param S1 syötejoukko
     * @param S2 syötejoukko
     * @param S3 syötejoukko
     * @return tulosjoukko
     */
    @Override
    public <E> Set<E> kaksiKolmesta(Set<E> S1, Set<E> S2, Set<E> S3) {
        Set<E> tulos = new HashSet<>();

        HashSet<E> S1_S2 = new HashSet<E>(S1);
        HashSet<E> S2_S3 = new HashSet<E>(S2);
        HashSet<E> S1_S3 = new HashSet<E>(S3);

        // 1 ja 2 leikkaus
        S1_S2.retainAll(S2);                        // kaikki operaatiot O(1*n) = O(n)
        // lisätään jääneet alkiot tulokseen
        tulos.addAll(S1_S2);

        // 2 ja 3 leikkaus
        S2_S3.retainAll(S3);
        // lisätään jääneet alkiot tulokseen
        tulos.addAll(S2_S3);

        // 1 ja 3 leikkaus
        S1_S3.retainAll(S1);
        // lisätään jääneet alkiot tulokseen
        tulos.addAll(S1_S3);

        // 1 ja 2 leikkauksen leikkaus 2 ja 3 kolmen leikkauksesta
        // (jäljelle jää alkio(t) jo(t)ka kaikissa joukoissa
        S1_S2.retainAll(S2_S3);
        
        // poistetaan tuloksessa olevista leikkauksista kaikissa oleva(t) alkio(t)
        tulos.removeAll(S1_S2);


        return tulos;
    }
}
