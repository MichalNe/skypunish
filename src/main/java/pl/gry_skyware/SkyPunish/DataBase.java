package pl.gry_skyware.SkyPunish;

import pl.gry_skyware.SkyPunish.Commands.Ban;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DataBase {

    public void updateRecord(String nick, String reason, String uuid_player, int banned, long timeFrom, long timeTo){
        PreparedStatement preparedStatement = null;
        Date time = new Date();
        long now = time.getTime();
        if(banned == 0){
            try{

                String query = "UPDATE skypunish_bans set reason = ?, timeFrom = ?, timeTo = ?, banned = ? WHERE uuid_player = ? AND timeTo > ?";
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, reason);
                preparedStatement.setLong(2, timeFrom);
                preparedStatement.setLong(3, timeTo);
                preparedStatement.setInt(4, banned);
                preparedStatement.setString(5, uuid_player);
                preparedStatement.setLong(6, timeFrom);


                preparedStatement.execute();
                preparedStatement.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    public void addRecord(String name, String reason, String uuid_player, int banned, long timeFrom, long timeTo){
        PreparedStatement statement;

        try{

            String query = "INSERT INTO skypunish_bans (nick, reason,uuid_player, banned, timeFrom, timeTo) VALUES (?,?,?,?,?,?)";

            statement = connection.prepareStatement(query);

            statement.setString(1, name);
            statement.setString(2, reason);
            statement.setString(3, uuid_player);
            statement.setInt(4, banned);
            statement.setLong(5, timeFrom);
            statement.setLong(6, timeTo);

            statement.execute();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public boolean isInDb(String nick){

        try{
            String query = "SELECT `id` FROM skypunish_bans WHERE `nick` = ? AND `timeTo` > `timeFrom`;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nick);

            ResultSet results = stmt.executeQuery();

            return results.next();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<BanRecord> getBanRecords(String nick){
        List<BanRecord> ban = new LinkedList<BanRecord>();
        try{
            String query = "SELECT `id`,`reason`,`timeTo`, `banned` FROM `skypunish_bans` WHERE `nick` = ? AND (`timeTo` > `timeFrom` OR `timeTo` = 0);";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nick);

            ResultSet results = stmt.executeQuery();

            while(results.next()){
                int id = results.getInt("id");
                String reason = results.getString("reason");
                long timeTo = results.getLong("timeTo");
                String banned = results.getString("banned");

                BanRecord banRecord = new BanRecord(id, reason, timeTo, banned);
                ban.add(banRecord);
            }

            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ban;
    }

    static Connection connection;

    public DataBase (String hostname, String username, String password, String database) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("jdbc driver unavailable!");
            return;
        }
        String url = "jdbc:mysql://"+hostname+"/"+database+"?autoReconnect=true&useUnicode=yes";
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        /*String sql = "CREATE TABLE IF NOT EXISTS `rankup` (\n" +
                "  `uuid` varchar(128) NOT NULL,\n" +
                "  `name` varchar(33) NOT NULL,\n" +
                "  `time` int(10) unsigned NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";


        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void disconnect() {
        try {
            if (connection!=null && !connection.isClosed()){
                connection.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
