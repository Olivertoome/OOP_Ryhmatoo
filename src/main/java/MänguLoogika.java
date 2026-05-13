import java.util.List;

/**
 * Mängu põhiloogika ilma konsoolist sisend/väljundita.
 * Haldab mängu seisu, numbrijada ja vastuste kontrolli.
 *
 * Olulisemad meetodid:
 * uusMäng()          – lähtestab mängu algseisu
 * alustaSekventsi()  – lisab jadasse uue numbri ja tagastab terve jada
 * kontrolliVastus()  – kontrollib kasutaja sisestust, uuendab elu/punktid
 * salvestaRekord()   – kirjutab uue rekordi faili (kui ületati)
 */
public class MänguLoogika {

    public enum Tulemus { ÕIGE, VALE, MÄNG_LÄBI }

    private MänguSeis mänguSeis;
    private Jadagenereeria jadagenereeria;
    private final PunktiHaldur punktiHaldur;

    public MänguLoogika() {
        punktiHaldur = new PunktiHaldur("parim_tulemus.txt");
        int parim = punktiHaldur.laeParimTulemus();
        mänguSeis = new MänguSeis(3, 0, parim);
        jadagenereeria = new Jadagenereeria();
    }

    public void uusMäng() {
        int parim = punktiHaldur.laeParimTulemus();
        mänguSeis = new MänguSeis(3, 0, parim);
        jadagenereeria = new Jadagenereeria();
    }

    /** Lisab jadasse ühe uue juhusliku numbri ja tagastab terve jada. */
    public List<Integer> alustaSekventsi() {
        jadagenereeria.SuvalineArv();
        return jadagenereeria.getJada();
    }

    /** Tagastab praeguse jada (kasutuse korral, kui mängija peab uuesti proovima). */
    public List<Integer> getJada() {
        return jadagenereeria.getJada();
    }

    /**
     * Kontrollib mängija sisestust praeguse jada vastu.
     * @param vastus mängija sisestatud numbrijada (ainult digidad, tühikuteta)
     * @return ÕIGE / VALE / MÄNG_LÄBI
     */
    public Tulemus kontrolliVastus(String vastus) {
        List<Integer> jada = jadagenereeria.getJada();

        boolean õige = vastus.length() == jada.size();
        if (õige) {
            for (int i = 0; i < jada.size(); i++) {
                if ((vastus.charAt(i) - '0') != jada.get(i)) {
                    õige = false;
                    break;
                }
            }
        }

        if (õige) {
            mänguSeis.setPunktid(mänguSeis.getPunktid() + 1);
            return Tulemus.ÕIGE;
        }

        mänguSeis.setElud(mänguSeis.getElud() - 1);
        return mänguSeis.getElud() <= 0 ? Tulemus.MÄNG_LÄBI : Tulemus.VALE;
    }

    /**
     * Salvestab rekordi faili, kui mängija tulemus ületas senise rekordi.
     * @return true kui uus rekord saavutati
     */
    public boolean salvestaRekord() {
        if (mänguSeis.getPunktid() > mänguSeis.getParimTulemus()) {
            mänguSeis.setParimTulemus(mänguSeis.getPunktid());
            punktiHaldur.salvestaParimTulemus(mänguSeis.getParimTulemus());
            return true;
        }
        return false;
    }

    public int getElud() { return mänguSeis.getElud(); }
    public int getPunktid() { return mänguSeis.getPunktid(); }
    public int getParimTulemus() { return mänguSeis.getParimTulemus(); }
}
