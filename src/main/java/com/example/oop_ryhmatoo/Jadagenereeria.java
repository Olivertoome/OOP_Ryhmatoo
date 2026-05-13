package com.example.oop_ryhmatoo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Haldab mängus kasutatavat numbrijada.
 * Lisab juhuslikke numbreid vahemikust 0–9.
 *
 * Olulisemad meetodid:
 * SuvalineArv() – lisab jadasse ühe juhusliku arvu (0–9)
 * getJada()     – tagastab praeguse jada
 */
public class Jadagenereeria {

    private final List<Integer> jada = new ArrayList<>();
    private final Random random = new Random();

    public void SuvalineArv() {
        jada.add(random.nextInt(10));
    }

    public List<Integer> getJada() {
        return jada;
    }
}
