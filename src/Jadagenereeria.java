/**
 * Annab suvaliste arvudega jada
 * Liidab juurde numbrid 0 - 9.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jadagenereeria {

    private List<Integer> jada = new ArrayList<>();
    private Random random = new Random();

    // Annab suvalise arvu 0 - 9.
    public void SuvalineArv() {
        jada.add(random.nextInt(10));
    }

    // Tagastab jada.
    public List<Integer> getJada() {
        return jada;
    }
}
