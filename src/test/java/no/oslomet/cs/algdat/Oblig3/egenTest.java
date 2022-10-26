package no.oslomet.cs.algdat.Oblig3;

import java.util.ArrayList;
import java.util.Comparator;

public class egenTest {
    // OPPGAVE 1 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        //Lag et nytt binærtre
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        int[] a = {10, 14, 6, 8, 1, 12, 7, 3, 11, 9, 13, 5, 2, 4};
        for (int verdi : a) { tre.leggInn(verdi); }
        //Gjør om treet til et array
        ArrayList<Integer> data = tre.serialize();
        System.out.println(data);
    }
}
