package pl.gry_skyware.SkyPunish;

public class BanRecord {

    private int id;
    private String reason;
    private long timeTo;
    private String banned;


    public BanRecord(int id, String reason, long timeTo, String banned){
        this.id = id;
        this.reason = reason;
        this.timeTo = timeTo;
        this.banned = banned;
    }

    public int getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public long getTimeTo() {
        return timeTo;
    }

    public String getBanned(){
        return banned;
    }

    public String toString(){
        return getId() + " " + getReason() + " " + getTimeTo() + " " + getBanned();
    }
}
