import java.io.*;
import java.util.Scanner;

/**
 * Loeb parim_tulemus.txt failist parema tulemuse.
 * Muudab vajadusel faili seisu.
 */

public class PunktiHaldur {

    private String failinimi;

    public PunktiHaldur(String failinimi) {
        this.failinimi = failinimi;
    }

    public String getFailinimi() {
        return failinimi;
    }

    public void setFailinimi(String failinimi) {
        this.failinimi = failinimi;
    }

    // Annab hetkeseisu parima tulemuse.
    public int laeParimTulemus() {
        try (Scanner scanner = new Scanner(new File(failinimi), "UTF-8")) {
            int tulemus = scanner.nextInt();
            return tulemus;

        } catch (Exception e) {
            return 0;
        }
    }

    // Salvestab uue parima tulemuse.
    public void salvestaParimTulemus(int tulemus) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(failinimi))) {
            printWriter.print(tulemus);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}