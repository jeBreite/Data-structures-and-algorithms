
import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

public class TRA_binaaripuun_lapikaynti {

    /**
     * 
     * Koko puun läpikäynnin aikavaativuus O(n log n)
     * (jokaisen solmun, joita on n kappaletta, edeltäjän hakeminen, joka on log n,
     * eli n kertaa log n)
     * 
     */
    
    /**
     * Palauttaa binääripuun sisäjärjestyksessä viimeisen solmun
     * 
     * Aikavaativuus O(log n)
     *
     * @param T Tarkasteltava puu.
     * @return Viimeinen solmu tai null jos puu T on tyhjä
     */
    @Override
    public BTreeNode sisaViimeinen(BTree T) {

        BTreeNode n = T.getRoot();

        // puu on tyhjä, palautetaan null
        if (T.isEmpty()) {          // O(1)
            return null;
        } else {
            // edetään juuresta oikeaan lapseen kunnes sitä ei ole ja palautetaan viimeisin
            while (n.getRightChild() != null) {         // ≈puun korkeus O(log n)
                n = n.getRightChild();
            }
            return n;

        }

    }

    /**
     * Palauttaa binääripuun solmun n edeltäjän sisäjärjestyksessä
     * 
     * Aikavaativuus O(log n)
     *
     * @param n Binääripuun solmu
     * @return edeltäjäsolmu tai null jollei edeltäjää ole
     */
    @Override
    public BTreeNode sisaEdellinen(BTreeNode n) {

        // solmulla ei ole vanhempaa eikä lapsia (puussa vain juuri) palautetaan null
        if (n.getParent() == null && n.getRightChild() == null && n.getLeftChild() == null) {
            return null;
        }

        // jos solmulla on vasen lapsi siirrytään siihen
        if (n.getLeftChild() != null) {
            n = n.getLeftChild();

            // siirrytään oikeaan lapseen niin kauan kuin sellainen on
            while (n.getRightChild() != null) {                             // pahimmillaan O(log n)
                n = n.getRightChild();
            }
            // ja palautetaan solmu kun sillä ei enää ole oikean puoleista lasta
            return n;
        } // muuten jos solmulla on vanhempi (ei ole vasenta lasta)
        else if (n.getParent() != null) {

            // jos solmu on vanhempansa oikea lapsi
            if (n == n.getParent().getRightChild()) {
                // palautetaan solmun vanhempi
                return n.getParent();

            } // jos solmu on vanhempansa vasen lapsi
            else if (n == n.getParent().getLeftChild()) {

                // siirrytään vanhempaan niin kauan kuin vanhempi ei ole null (tullaan juureen) 
                // ja solmu on vanhempansa vasen lapsi
                while (n.getParent() != null && n == n.getParent().getLeftChild()) {    // pahimmillaan O(log n)
                    n = n.getParent();
                }
                return n.getParent();
            }
        }

        // mikään ehdoista ei täyty, palautetaan null
        return null;
    }
}
