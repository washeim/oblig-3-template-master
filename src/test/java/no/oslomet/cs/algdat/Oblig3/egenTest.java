package no.oslomet.cs.algdat.Oblig3;

import java.util.ArrayList;
import java.util.Comparator;

public class egenTest {
    // OPPGAVE 1 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        //Lag et nytt binærtre
        int[] a = {6, 14, 1, 8, 12, 3, 7, 9, 11, 13, 2, 5, 4};
        SBinTre<Integer> tre =
                new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        String s = tre.toStringPostOrder();
        System.out.println(s);
    }
}
