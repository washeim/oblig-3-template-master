# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Adrian Asheim, S364585, s364585@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så brukte jeg programkoden fra boka, og endret p = new Node og la til q som forelder i den nye node.

I oppgave 2 så brukte jeg en while løkke og en int teller til å sammenligne verdien med verdien til noden. Hvis cmp blir mer enn null
går den til venstre node, hvis ikke så sjekker den om cmp er 0. Hvis cmp er 0 så legges en til på teller og flyttes til noden i høyre.

I oppgave 3 brukte jeg while for å finne den siste noden som enten har en venstre eller høyre verdi. P settes til den siste verdien her.
I neste postOrden sjekkes om det om p er høyreverdien til foreldre nodes sin, hvis der er det så returneres foreldrenoden Q.
Hvis det ikke er høyreverdien til Q så returneres høyreverdien til P.

I oppgave 4 lagde jeg en ny node av førstepostorden til roten og deretter kjører jeg en while løkke som gjør p om til neste i rekken.

I oppgave 5 lager jeg en ny arraylist og linked list som kø og legger roten inn i køen. Deretter mens køen ikke er tom så taes første elemt i køen ut og sjekkes om den er gyldig.
Hvis den er gyldig legges den inn i arrayet og neste elementene av q legges inn i køen. 

I oppgave 6 brukte jeg programkode fram pensum. fjernAlle brukte jeg denne koden fra fjern() til å kjøre den rekursivt mens den er true i en while løkke.
Oppdaterer også antall og returnerer hvor mange ganger while løkken kjører. For å få til nullstill(p) lagde jeg en lokal privat hjelpemetode nullstill() som kjøres med rot som p helt til treet er tomt.


