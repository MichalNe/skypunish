package pl.gry_skyware.SkyPunish;

import java.util.ArrayList;
import java.util.List;

public enum BanUnit {
    DAY("d", 60 * 24),
    HOUR("h", 60);

    private String name;
    private int multi;

    BanUnit(String name, int multi){
        this.name = name;
        this.multi = multi;
    }

    public static long getTicks(String un, int time){
        long sec;

        try{
            sec = time * 60;
        }catch (NumberFormatException e){
            return 0;
        }
        for(BanUnit unit: BanUnit.values()){
            if(un.startsWith(unit.name)){
                return (sec *= unit.multi);
            }
        }
        return 0;
    }
}
