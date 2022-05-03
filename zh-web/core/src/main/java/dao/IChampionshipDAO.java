package dao;

import model.Race;
import java.util.List;

public interface IChampionshipDAO {
    List<Race> findAll(boolean needPastRaces);
    Race findById(int ID);

    Race save(Race race);
    void delete(Race race);
}
