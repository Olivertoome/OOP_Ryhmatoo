package com.example.oop_ryhmatoo;
import java.io.*;
import java.util.Scanner;
/**
 * Tegeleb faili parim_tulemus.txt lugemise ja kirjutamisega.
 * Loeb olemasoleva rekordi ning salvestab vajadusel uue.
 *
 * Olulisemad meetodid:
 * laeParimTulemus()          – loeb failist parima tulemuse (0 kui faili pole)
 * salvestaParimTulemus(int)  – kirjutab uue parima tulemuse faili
 */
public class PunktiHaldur {

    private String failinimi;

    public PunktiHaldur(String failinimi) {
        this.failinimi = failinimi;
    }

    public String getFailinimi() { return failinimi; }
    public void setFailinimi(String failinimi) { this.failinimi = failinimi; }

    public int laeParimTulemus() {
        try (Scanner scanner = new Scanner(new File(failinimi), "UTF-8")) {
            return scanner.nextInt();
        } catch (Exception e) {
            return 0;
        }
    }

    public void salvestaParimTulemus(int tulemus) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(failinimi))) {
            pw.print(tulemus);
        } catch (IOException e) {
            throw new RuntimeException("Rekordi salvestamine ebaõnnestus: " + e.getMessage());
        }
    }
}
