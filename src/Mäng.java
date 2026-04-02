import java.util.List;
import java.util.Scanner;

/**
 * Jooksutab kogu protsessi.
 * Küsib kasutajalt jada.
 * Annab infot mängu sesu kohta.
 */
public class Mäng {

    private MänguSeis mänguSeis;
    private Jadagenereeria jadagenereeria;
    private PunktiHaldur punktiHaldur;
    private Scanner scanner = new Scanner(System.in);

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
            mängiRaund();
        }

        System.out.println("Mäng läbi!");
        System.out.println("Sinu punktid: " + mänguSeis.getPunktid());

        int parim = mänguSeis.getParimTulemus();

        // Kontrollib kas on uus rekord või mitte ja tagastab selle.
        if (mänguSeis.getPunktid() > parim) {
            System.out.println("UUS REKORD!");
            mänguSeis.setParimTulemus(mänguSeis.getPunktid());
            punktiHaldur.salvestaParimTulemus(mänguSeis.getPunktid());
        } else if (mänguSeis.getPunktid() == parim) {
            System.out.println("Pole paha, jäid parima tulemusega viiki!");
        } else {
            System.out.println("Jäid rekordist " + (parim - mänguSeis.getParimTulemus()) + " maha.");
        }
    }

    public void mängiRaund() {
        jadagenereeria.SuvalineArv();
        List<Integer> jada = jadagenereeria.getJada();

        System.out.println("Jada: " + jada);
        System.out.println("Vajuta Enter kui oled valmis...");
        scanner.nextLine();

        System.out.println("Sisesta " + jada.size() + " numbrit (tühikuga eraldatud):");
        boolean õige = true;
        for (int i = 0; i < jada.size(); i++) {
            int vastus = scanner.nextInt();
            if (vastus != jada.get(i)) {
                õige = false;
            }
        }
        scanner.nextLine();

        if (õige) {
            System.out.println("Õige! +1 punkt");
            mänguSeis.setPunktid(mänguSeis.getPunktid() + 1);
        } else {
            System.out.println("Vale! Kaotad elu. Elud: " + (mänguSeis.getElud() - 1));
            mänguSeis.setElud(mänguSeis.getElud() - 1);
            jadagenereeria.puhasta();
        }
    }
}
