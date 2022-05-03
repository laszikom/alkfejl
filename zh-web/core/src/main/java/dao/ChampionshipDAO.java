package dao;

import config.ConfigurationHelper;
import model.Race;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChampionshipDAO implements IChampionshipDAO {
    private static final ChampionshipDAO instance = new ChampionshipDAO();
    private final String databasePath;
    private final DateTimeFormatter dateTimeFormatter;

    private ChampionshipDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        databasePath = ConfigurationHelper.getValue("db.url");
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public static ChampionshipDAO getInstance() {
        return instance;
    }

    @Override
    public List<Race> findAll(boolean needPastRaces) {
        var races = new ArrayList<Race>();

        try (var connection = DriverManager.getConnection(databasePath);
             var statement = connection.createStatement()) {

            String query = "select * from Championships where Date > date('now')";
            if (needPastRaces)
                query = "select * from Championships";

            var result = statement.executeQuery(query);

            while (result.next())
                races.add(extractResult(result));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return races;
    }

    @Override
    public Race findById(int ID) {
        try (var connection = DriverManager.getConnection(databasePath);
             var statement = connection.prepareStatement("select * from Championships where ID=?")) {

            statement.setInt(1, ID);
            var result = statement.executeQuery();

            while (result.next())
                return extractResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Race save(Race race) {
        String insertCommand = "insert into Championships (Name, Country, Date, Guests) values (?, ?, ?, ?);";
        String updateCommand = "update Championships set Name=?, Country=?, Date=?, Guests=? where ID=?";

        try (var connection = DriverManager.getConnection(databasePath);
             var statement = race.getID() == 0 ?
                     connection.prepareStatement(insertCommand, Statement.RETURN_GENERATED_KEYS) :
                     connection.prepareStatement(updateCommand)) {

            // Update
            if (race.getID() != 0)
                statement.setInt(5, race.getID());

            statement.setString(1, race.getName());
            statement.setString(2, race.getCountry());
            statement.setString(3, race.getDate().format(dateTimeFormatter));
            statement.setInt(4, race.getGuests());

            if (statement.executeUpdate() == 0)
                return null;

            if (race.getID() == 0) {
                var generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next())
                    race.setID(generatedKeys.getInt(1));
            }

            return race;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Race race) {
        try (var connection = DriverManager.getConnection(databasePath);
             var statement = connection.prepareStatement("delete from Championships where ID=?")) {

            if (race.getID() == 0)
                throw new SQLException("The given race has not been saved yet!");

            statement.setInt(1, race.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Race extractResult(ResultSet result) throws SQLException {
        var race = new Race();
        race.setID(result.getInt("ID"));
        race.setName(result.getString("Name"));
        race.setCountry(result.getString("Country"));
        race.setDate(LocalDate.parse(result.getString("Date"), dateTimeFormatter));
        race.setGuests(result.getInt("Guests"));

        return race;
    }
}
