
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TRA_joukkojenJoukko_ja_HashMap {

    /**
     *
     * Aikavaativuus O(n) 
     * (jossa n = joukkojen alkioiden määrä)
     */
    /**
     * Alkioiden hakemisto. Palauttaa kuvauksen jossa kutakin syötejoukoissa
     * olevaa alkiota kohti on joukko niitä joukkoja jossa ko. syötealkio
     * esiintyi.
     *
     * @param SS syötejoukkojen joukko
     * @param <E> alkioiden tyyppi
     * @return kuvaus alkioilta syötejoukoille
     */
    @Override
    public <E> Map<E, Set< Set<E>>> hakemisto(Set<Set<E>> SS) {

        Map<E, Set< Set<E>>> tulosHakemisto = new HashMap<>();

        // käydään läpi joukon joukot
        for (Set<E> set : SS) {

            // käydään läpi joukon alkiot
            for (E e : set) {

                /* jos alkio ei ole kuvauksen avaimena lisätään se ja luodaan 
                uusi joukko jonne sijoitetaan joukko jossa alkio oli */
                if (!tulosHakemisto.containsKey(e)) {                           // kaikki operaatiot O(1 *n) 
                    Set<Set<E>> newSet = new HashSet<>();
                    newSet.add(set);
                    tulosHakemisto.put(e, newSet);

                    /* muuten, eli jos avain on jo kuvauksessa haetaan avaimen 
                    arvona oleva joukko ja lisätään joukko joukkoon */
                } else {
                    Set<Set<E>> getSet = new HashSet<>(tulosHakemisto.get(e));  // kaikki operaatiot O(1 *n) 
                    getSet.add(set);
                    tulosHakemisto.put(e, getSet);
                }
            }
        }

        return tulosHakemisto;
    }
}
