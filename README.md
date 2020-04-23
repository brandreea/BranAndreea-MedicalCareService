# Aplicatie destinata unei clinici medicale
Aceasta aplicatie este destinata facilitarii bunei functionari a unei clinici medicale.
Aceasta este destinata in principiu personalului medical si al angajatilor din call-center-ul asociat, oferind informatii utile atat pacientilor, cat si medicilor, avand posibilitatea de a le pastra intr-o baza de date.

###Utilizare

Inainte de a o rula, se aplica un maven clean+install modulului.

## Pachete
In cadrul acestui proiect exista urmatoarele pachete de clase de baza:
* workers: pachet destinat managerierii angajatorilor (medici (**Medic**) si asistente (**Nurse**)); 
* consults: pachet destinat managerierii serviciilor(consulturi (**Consult**), proceduri (**Procedure**), retete (**Prescription**));
* patients: pachet destinat managerierii pacientilor(pacienti abonati (**SubscribedPatient**) si neabonati (**UnsubscribedPatient**));
* medication: pachet destinat managerierii resurselor de medicamente(**Medication**).
* filemanagement: pachet destinat citirii si scrierii din fisierele csv.

## Servicii
In cadrul aplicatiei exista mai multe servicii:
* adaugarea unui medic;
* adaugarea unei asistente;
* adaugarea unui pacient;
* adaugarea unui consult;
* actualizarea informatiilor pentru un consult;
* adaugarea medicilor care aplica o procedura;
* vizualizarea medicilor dintr-un departament;
* vizualizarea pacientilor;
* adaugarea unor medicamente;
* actualizarea cantitatii unui medicament
* adaugarea unei proceduri
* vizualizarea consulturilo in functie de medic

## Despre citirea din fisiere
Aceasta se aplica fisierelor din root si nu celor din resources. 

Citirea si scrierea folosesc clase din libraria Jackson, exceptie facand clasa ce scrie ce metoda a fost apelata.