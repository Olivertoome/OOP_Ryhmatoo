/**
 * Jooksutab kogu protsessi.
 * Küsib kasutajalt jada.
 * Annab infot mängu sesu kohta.
 */
public class Mäng {

    private MänguSeis mänguSeis;
    private Jadagenereeria jadagenereeria;
    private PunktiHaldur punktiHaldur;

    public Mäng() {
        // Laeme parima tulemuse ja salvestame selle.
        punktiHaldur = new PunktiHaldur("parim_tulemus.txt");
        int parim = punktiHaldur.laeParimTulemus();

        mänguSeis = new MänguSeis(3, 0, parim);
        jadagenereeria = new Jadagenereeria();
    }

    public void alusta() {
        // Seletab mängu
        System.out.println("-----MÄLUMÄNG-----");
        System.out.println("Jäta numbrid meelde ja sisesta need õiges järjekorras.");
        System.out.println("Näen, et oled natuke eriline, nii et annan sulle 3 elu.");
        System.out.println("Parim tulemus on " + mänguSeis.getParimTulemus());
        System.out.println("Ära oma suguvõsa häbista, kivi kotti!");
        System.out.println();

        while (mänguSeis.getElud() != 0) {

        }

        System.out.println("Mäng läbi!");
        System.out.println("Sinu punktid: " + mänguSeis.getPunktid());

        int parim = mänguSeis.getParimTulemus();

        // Kontrollib kas on uus rekord või mitte ja tagastab selle.
        if (mänguSeis.getPunktid() > parim) {
            System.out.println("UUS REKORD!");
            punktiHaldur.salvestaParimTulemus(mänguSeis.getParimTulemus());
        } else if (mänguSeis.getPunktid() == parim) {
            System.out.println("Pole paha, jäid parima tulemusega viiki!");
        } else {
            System.out.println("Jäid rekordist " + (parim - mänguSeis.getParimTulemus()) + "maha.");
        }
    }
}
