package no.oslomet.cs.algdat.Oblig3;

import java.util.Comparator;

public class egenTest {
    // OPPGAVE 1 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) {tre.leggInn(verdi); }
        System.out.println(tre.antall()); // Utskrift: 10
    }
}
