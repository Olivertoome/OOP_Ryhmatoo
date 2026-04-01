/**
 * Annab elud, punktide seisu ja parima tulemuse
 */

public class MänguSeis {

    private int elud = 3;
    private int punktid;
    private int parimTulemus;

    public MänguSeis(int elud, int punktid, int parimTulemus) {
        this.elud = elud;
        this.punktid = punktid;
        this.parimTulemus = parimTulemus;
    }

    public int getElud() {
        return elud;
    }

    public void setElud(int elud) {
        this.elud = elud;
    }

    public int getPunktid() {
        return punktid;
    }

    public void setPunktid(int punktid) {
        this.punktid = punktid;
    }

    public int getParimTulemus() {
        return parimTulemus;
    }

    public void setParimTulemus(int parimTulemus) {
        this.parimTulemus = parimTulemus;
    }
}
