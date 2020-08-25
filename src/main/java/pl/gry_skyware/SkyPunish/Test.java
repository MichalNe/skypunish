package pl.gry_skyware.SkyPunish;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
       /* Scanner scanner = new Scanner(System.in);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minutes = Calendar.getInstance().get(Calendar.MINUTE);
        int secounds = Calendar.getInstance().get(Calendar.SECOND);

        System.out.println("dej czas: ");
        int userTime = scanner.nextInt();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateString = day + "-" + month + "-" + year + " " + hours + ":" + minutes + ":" + secounds;
        String timeTo = day+userTime + "-" + month + "-" + year + " " + hours+2 + ":" + minutes+30 + ":" + secounds;
        try {
            Date date = sdf.parse(dateString);
            System.out.println(date.getTime());


            Date dateTimeTo = sdf.parse(timeTo);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateTimeTo);

            System.out.println(calendar.getTimeInMillis());

            System.out.println(calendar.getTimeInMillis() - (calendar.getTimeInMillis() - date.getTime()));
        }catch (ParseException e){
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(1591097841000L);
        System.out.println(c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.HOUR_OF_DAY) + " " + c.get(Calendar.MINUTE));*/

        //tempban <nick> <czas> <powód>
        String[] command = {"tempban","Nerwo", "30d", "bo tak"};

        System.out.println("TIME: " + getTime(command));
        System.out.println("UNIT: " + getUnit(command));

        switch (getUnit(command)){
            case "d":
                System.out.println("DAY");
                break;
            case "h":
                System.out.println("HOURS");
                break;
            default:
                System.out.println("unhandled");
                break;
        }
    }


    static String getUnit(String[] arg){
        String unit = arg[2].substring(arg[2].length() - 1);
        return unit;
    }
    static int getTime(String[] arg){
        int time = Integer.parseInt(arg[2].substring(0,arg[2].length()-1));

        return time;
    }

}
