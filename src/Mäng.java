import java.util.List;

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
            mängiRaund();
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

    public void mängiRaund() {
        jadagenereeria.SuvalineArv();
        List<Integer> jada = jadagenereeria.getJada();

        int uusArv = jada.get(jada.size() - 1);

        System.out.println("Jäta see number meelde: " + uusArv);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Sisesta kogu jada:");

        boolean õige = true;
        String vastus = scanner.nextLine();

        if (vastus.length() != jada.size()) {
            õige = false;
        } else {
            for (int i = 0; i < jada.size(); i++) {
                int number = vastus.charAt(i) - '0';

                if (number != jada.get(i)) {
                    õige = false;
                }
            }
        }

        // "peidab" vanad read
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }

        if (õige) {
            System.out.println("Õige!");
            mänguSeis.setPunktid(mänguSeis.getPunktid() + 1);
        } else {
            System.out.println("Vale!");
            mänguSeis.setElud(mänguSeis.getElud() - 1);
        }

        System.out.println("Elusid alles: " + mänguSeis.getElud());
    }
}
