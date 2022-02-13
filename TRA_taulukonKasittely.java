
public class TRA_taulukonKasittely {

    /**
     * T(O) = n
     */
    /**
     * Suurimman ja pienimmän summa. Palauttaa taulukon suurimman ja pienimmän
     * luvun summan tai null jos taulukko on tyhjä.
     *
     * @param A Syötetaulukko.
     * @return suurimman ja pienimmän summan tai null jos taulukko on tyhjä.
     */
    @Override
    public Integer suurinJaPieninSumma(Integer[] A) {

	// alustetaan summa
        Integer summa = 0;

	// jos taulukko on tyhjä palautetaan null
        if (A.length < 1) {
            return null;
        }
	// jos taulukossa vain yksi alkio summataan se itsensä kanssa
        if (A.length == 1) {
            summa = A[0] * 2;

	// muuten haetaan pienin ja suurin alkio
        } else {
            Integer pienin = A[0];
            Integer suurin = A[0];

	    // käydään taulukko läpi 
            for (int i = 0; i < A.length; i++) {        // n askelta
                 // jos pienin on suurempi kuin taulukon käsittelyssä oleva alkio pienin on tämä alkio
	        if (pienin > A[i]) {
                    pienin = A[i];
                } 
	        // jos suurin on on pienempi kuin käsittelyssä oleva alkio suurin on tämä
                if (suurin < A[i]) {
                    suurin = A[i];
                }
            }
            // summataan pienin ja suurin
            summa = pienin + suurin;
        }

        return summa;
    }
}
