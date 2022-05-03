import dao.ChampionshipDAO;
import model.Race;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDAO {
    @Test
    public void test_1_ListingUpcomingRaces() {
        var dao = ChampionshipDAO.getInstance();

        var result = dao.findAll(false);
        assertEquals(0, result.size());
    }

    @Test
    public void test_2_ListingAllRaces() {
        var dao = ChampionshipDAO.getInstance();

        var result = dao.findAll(true);
        assertEquals(1, result.get(0).getID());
        assertEquals("Bahrain", result.get(0).getCountry());
    }

    @Test
    public void test_3_Insert() {
        var dao = ChampionshipDAO.getInstance();

        var race = new Race();
        race.setName("Monaco Grand Prix");
        race.setCountry("Italy");
        race.setDate(LocalDate.parse("2022-04-24"));

        dao.save(race);
        assertNotEquals(0, race.getID());
    }

    @Test
    public void test_4_Update() {
        var dao = ChampionshipDAO.getInstance();

        var filtered = dao.findAll(false).stream().
                filter(item -> item.getCountry().equals("Italy")).toList();

        assertEquals(1, filtered.size());

        var race = filtered.get(0);
        race.setGuests(7500); // 2021

        try {
            dao.save(race);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void test_5_Remove() {
        var dao = ChampionshipDAO.getInstance();

        var filtered = dao.findAll(false).stream().
                filter(item -> item.getCountry().equals("Italy")).toList();

        dao.delete(filtered.get(0));
        assertEquals(3, dao.findAll(true).size());
    }
}
