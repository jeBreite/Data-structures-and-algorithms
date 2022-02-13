
import fi.uef.cs.tra.*;

import java.util.ArrayList;

public class TRA_binaaripuu_esijarjestys {

    private ArrayList<BTreeNode> list = new ArrayList<>();

    //               
    /**
     *
     * Aikavaativuus O(n)
     * 
     * 
     * Onko binääripuu esijärjestyksessä vai ei.
     *
     * Jos puun solmujen alkiot puun esijärjestyksessä lueteltuna ovat
     * kasvavassa järjestyksessä, palautetaan true, muuten false.
     *
     * Puun solmut esijärjestyksessä ovat juurisolmu ja sen jälkeläiset
     * esijärjestyksessä. Solmun v ja sen jälkeläisten solmut esijärjestyksessä
     * ovat ensin solmu v, sitten solmun v mahdollisen vasemman lapsen ja sen
     * jälkeläisten solmut esijärjestyksessä ja lopuksi solmun v mahdollisen
     * oikean lapsen ja sen jälkeläisten solmut esijärjestyksessä.
     *
     * @param <E> puun alkioiden tyyppi
     * @param T syötepuu
     * @return true jos alkiot ovat esijärjestyksessä, muuten false
     */
    @Override
    public <E extends Comparable<? super E>> boolean onkoEsijarjestyksessa(BTree<E> T) {
        
        if (T.isEmpty()) {
            return true;
        }

        // alustetaan solmuksi puun juuri
        BTreeNode<E> node = T.getRoot();

        // kutsutaan metodia
        esijarjestys(node);

        /* käydään läpi listalla olevien solmujen data vertaamalla edellistä
        seuraavaan */
        for (int i = 0; i < list.size()-1; i++) {                               // O(n)
            int j = i + 1;
            BTreeNode<E> n1 = list.get(i);                                      // O(1)
            BTreeNode<E> n2 = list.get(j);                                      // O(1)

            // jos jokin alkioista on edeltäjäänsä suurempi palautetaan false
            if (n1.getElement().compareTo(n2.getElement()) > 0) {
                list.clear();                                                   // O(n)
                return false;
            }
        }

        /* lista käyty läpi ja mikään alkioista ei ole edeltäjäänsä suurempi 
        palautetaan true */
        list.clear();
        return true;
    }

    /**
     * BTreen rekurasiivinen läpikäynti esijärjestyksessä
     * O(n)
     * @param node 
     */
    private void esijarjestys(BTreeNode node) {
        // solmu on tyhjä palataan
        if (node == null) {
            return;
        }
        // lisätään solmu listalle
        list.add(node);                                                       

        // siirrytään vasempaan lapseen
        esijarjestys(node.getLeftChild());
        // siirrytään oikeaan lapseen
        esijarjestys(node.getRightChild());
    }
}
