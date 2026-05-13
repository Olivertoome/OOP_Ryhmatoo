package com.example.oop_ryhmatoo;
/**
 * Hoiab mängu hetkeseisu: elude arv, punktisumma, parim tulemus.
 *
 * Olulisemad meetodid:
 * Konstruktor – loob mänguseisu etteantud algväärtustega
 * get/set     – elude, punktide ja parima tulemuse lugemine/muutmine
 */
public class MänguSeis {

    private int elud;
    private int punktid;
    private int parimTulemus;

    public MänguSeis(int elud, int punktid, int parimTulemus) {
        this.elud = elud;
        this.punktid = punktid;
        this.parimTulemus = parimTulemus;
    }

    public int getElud() { return elud; }
    public void setElud(int elud) { this.elud = elud; }

    public int getPunktid() { return punktid; }
    public void setPunktid(int punktid) { this.punktid = punktid; }

    public int getParimTulemus() { return parimTulemus; }
    public void setParimTulemus(int parimTulemus) { this.parimTulemus = parimTulemus; }
}
