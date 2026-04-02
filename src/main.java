/**
 * Autorid:
 * Oliver Toome
 * Jan Tristan Moorlat
 * Karl Teetlaus
 *
 * --------------------------------------------------------------------------------------
 *
 * Projekti kirjeldus:
 * Projektiks on käsureal töötav mälumäng.
 * Mängu eesmärk on meelde jätta juhuslikult genereeritud arvujada ning
 * sisestada see õigesti samas järjekorras. Iga raundiga lisatakse jadasse
 * uus arv, mis muudab mängu järjest raskemaks.
 *
 * Mängijal on kolm elu. Vale vastuse korral kaotab mängija ühe elu ning
 * saab sama jada uuesti proovida. Mäng lõpeb siis, kui kõik elud on otsas.
 * Programmi alguses loetakse failist parim_tulemus.txt parim tulemus ning mängu lõpus
 * võrreldakse seda mängija saavutatud punktidega. Vajadusel salvestatakse uus rekord faili.
 *
 *--------------------------------------------------------------------------------------
 *
 * Klass Mäng:
 * Projekti peamine klass, mis juhib kogu mängu loogikat. Klass
 * korraldab mängu alustamise, raundide mängimise, kasutajalt vastuse küsimise, elu- ja
 * punktisüsteemi haldamise ning lõpptulemuse kuvamise.
 *
 * Olulisemad meetodid:
 * Mäng() – laeb failist parima tulemuse ja loob vajalikud objektid.
 * alusta() – näitab mängu algusteksti, käivitab raundid ja lõpetab mängu.
 * mängiRaund() – viib läbi ühe mänguraundi: lisab jadasse uue arvu, küsib kasutajalt vastust ja kontrollib selle õigsust.
 *
 *
 * Klass Jadagenereeria:
 * Klassi eesmärk on hallata mängus kasutatavat arvujada. Klass lisab jadasse
 * juhuslikke numbreid vahemikust 0 kuni 9.
 *
 * Olulisemad meetodid:
 * SuvalineArv() – lisab jadasse ühe uue juhusliku arvu vahemikust 0 kuni 9.
 * getJada() – tagastab hetkelise jada.
 *
 *
 * Klass MänguSeis:
 * Klassi eesmärk on hoida mängu hetkeseisu. Säilitatakse mängija elude arv, punktisumma ja parim tulemus.
 *
 * Olulisemad meetodid:
 * Konstruktor mänguseisu loomiseks.
 * get ja set elude, punktide ja parima tulemuse muutmiseks ja lugemine.
 *
 *
 * Klass PunktiHaldur:
 * Klassi eesmärk on tegeleda failiga parim_tulemus.txt. Klass loeb failist
 * olemasoleva rekordi ning salvestab sinna vajadusel uue parima tulemuse.
 *
 * Olulisemad meetodid:
 * Konstruktor, mis määrab faili nime.
 * laeParimTulemus() – loeb failist parima tulemuse.
 * salvestaParimTulemus(int tulemus) – kirjutab faili uue parima tulemuse.
 * get ja set faili nime muutmiseks ja lugemine.
 *
 *
 * Klass main:
 * Käivitab programmi.
 *
 * --------------------------------------------------------------------------------------
 *
 * Rühmaliikmete osalemine:
 *
 * Projekti käigus tegime enamus asju koos, aitasime üksteist,
 * parandasime vigu ja täiendasime koodi ühiselt. Lõpuks võib öelda, et kõik
 * panustasid projekti erinevatesse osadesse ja töö valmis koostööna.
 * Ajakulu kõikidel iseseisvalt oli umbes 2-3h ning tegime ka vahepeal kõik koos, nii et kolme peale kokku oli ajakulu 8-10h.
 *
 * Algne jaotus:
 *
 * Mõtlesime jaotada ära klassid: PunktiHaldur, MänguSeis ja Jadagenereeria,
 * seejärel tegeleda koos Mäng klassiga.
 *
 * Oliver Toome:
 * Klass PunktiHaldur
 * parim_tulemus.txt
 * Klass Mäng
 *
 * Jan Tristan Moorlat:
 * Klass MänguSeis
 * Klass main
 * Klass Mäng
 *
 * Karl Teetlaus:
 * Klass Jadagenereeria
 * Klass Mäng
 *
 * --------------------------------------------------------------------------------------
 *
 * Tegemise mured:
 *
 * Ainuke mure, mis vahepeal tekkis oli see, et kui juhtumisi 2 inimest samal ajal midagi muutsid või tegid Mäng klassis, tekkisid merge conflictid.
 *
 */

public class main {
    public static void main(String[] args) {
        Mäng mang = new Mäng();
        mang.alusta();
    }
}
