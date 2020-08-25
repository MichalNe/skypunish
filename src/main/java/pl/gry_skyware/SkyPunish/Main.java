package pl.gry_skyware.SkyPunish;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import pl.gry_skyware.SkyPunish.Commands.*;

import java.util.ArrayList;

public class Main extends JavaPlugin {


    private static Main instance;
    public ArrayList<String> mutedPlayers = new ArrayList<String>();

    @Override
    public void onEnable() {
        instance = this;
        setupConfig();
        setupDatabase();

        Bukkit.getPluginManager().registerEvents(new Listeners(), this);

        this.getCommand("kick").setExecutor(new Kick());
        this.getCommand("ban").setExecutor(new Ban());
        this.getCommand("tempban").setExecutor(new TempBan());
        this.getCommand("mute").setExecutor(new Mute());
        this.getCommand("tempmute").setExecutor(new TempMute());

    }

    @Override
    public void onDisable() {
        database.disconnect();
    }
    private void setupConfig() {
        saveDefaultConfig();
        Settings.Load();
    }
    public static DataBase database;
    private void setupDatabase() {
        database = new DataBase(Settings.mysqlHostname,Settings.mysqlUsername,Settings.mysqlPassword,Settings.mysqlDatabase);
    }
    public static Main getInstance() {return instance;}

}
