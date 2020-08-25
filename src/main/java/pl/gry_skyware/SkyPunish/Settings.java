package pl.gry_skyware.SkyPunish;

public class Settings {
    public static String mysqlHostname = "";
    public static String mysqlUsername = "";
    public static String mysqlPassword = "";
    public static String mysqlDatabase = "";
    public static String mysqlPrefix = "";

    public static void Load() {
        Main.getInstance().reloadConfig();

        mysqlHostname = Main.getInstance().getConfig().getString("DataBase.hostname");
        mysqlUsername = Main.getInstance().getConfig().getString("DataBase.username");
        mysqlPassword = Main.getInstance().getConfig().getString("DataBase.password");
        mysqlDatabase = Main.getInstance().getConfig().getString("DataBase.database");
        mysqlPrefix = Main.getInstance().getConfig().getString("DataBase.prefix");
    }

}
