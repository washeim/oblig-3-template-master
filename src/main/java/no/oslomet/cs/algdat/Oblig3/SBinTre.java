package no.oslomet.cs.algdat.Oblig3;


import javax.swing.tree.TreeNode;
import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    //Oppgave 1
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");
        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel
        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }
        // p er nå null, dvs. ute av treet, q er den siste vi passerte
        p = new Node<>(verdi, q);                   // oppretter en ny node
        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q
        antall++;
        return true;                             // vellykket innlegging
    }

    //Oppgave 6
    public boolean fjern(T verdi) {
        if (verdi == null) return false;  // treet har ingen nullverdier
        Node<T> p = rot, q = null;   // q skal være forelder til p
        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) rot = b;
            else if (p == q.venstre) q.venstre = b;
            else q.høyre = b;
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
        }
        antall--;   // det er nå én node mindre i treet
        return true;
    }

    public int fjernAlle(T verdi) {
        int verdiAntall = 0;
        while (fjern(verdi)) verdiAntall++;
        antall = antall - verdiAntall;
        return verdiAntall;
    }
    private void nullstill(Node<T> p)
    {
        if (p.venstre != null)
        {
            nullstill(p.venstre);
            p.venstre = null;
        }
        if (p.høyre != null)
        {
            nullstill(p.høyre);
            p.høyre = null;
        }
        p.verdi = null;
    }
    public void nullstill() {
        if (!tom()) nullstill(rot);
        rot = null; antall = 0;
    }

    //Oppgave 2
    public int antall(T verdi) {
        Node<T> p = rot;
        int count = 0;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                p = p.venstre;
        } else {
                if (cmp == 0) count++;
                p = p.høyre;
            }
        }
        return count;
    }

    //Oppgave 3
    private static <T> Node<T> førstePostorden(Node<T> p) {
        while (true) {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        if (p.forelder == null) return null;
        Node<T> forelder = p.forelder;
        if (forelder.venstre==p && forelder.høyre!=null){
            p = forelder.høyre;
            if (p.venstre!=null) return p.venstre;
            else if (p.høyre!=null) return p.høyre;
        }
        else if (forelder.venstre==p && forelder.høyre==null){

        }
        return forelder;
    }

    public void postorden(Oppgave<? super T> oppgave) {
        Node<T> p = førstePostorden(rot);
        while (p.verdi!=null){
            p = nestePostorden(p);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {

    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //Oppgave 5
    public ArrayList<T> serialize() {
        ArrayList<T> list = new ArrayList<>();
        LinkedList<Node> q = new LinkedList<>();
        q.offer(rot);

        while (!q.isEmpty()) {
            Node<T> h = q.poll();
            if (h == null) {
                T finnesIkke = null;
                list.add(finnesIkke);
            } else {
                list.add(h.verdi);
                q.offer(h.venstre);
                q.offer(h.høyre);
            }
        }
        return list;
    }

    //Ikke fungerende, feil i tidligere oppgave gjør feil i videre kjøring av test.
    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {;
        throw new ConcurrentModificationException("");
    }


} // ObligSBinTre
