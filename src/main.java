/**
 * Autorid:
 * Oliver Toome
 * Jan Tristan Moorlat
 * Karl Teetlaus
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
 */

public class main {
    public static void main(String[] args) {
        Mäng mang = new Mäng();
        mang.alusta();
    }
}
