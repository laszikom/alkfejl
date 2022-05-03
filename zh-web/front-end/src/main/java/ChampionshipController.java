import dao.ChampionshipDAO;
import model.Race;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/ChampionshipController")
public class ChampionshipController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        var dao = ChampionshipDAO.getInstance();

        int ID = 0, guests = 0;
        try {
            ID = Integer.parseInt(req.getParameter("id"));
            guests = Integer.parseInt(req.getParameter("guests"));
        } catch (Exception e) {
            // Ok.
        }

        var race = dao.findById(ID);

        if (race == null) {
            race = new Race();
            race.setName(req.getParameter("name"));
            race.setCountry(req.getParameter("country"));
        }

        race.setDate(LocalDate.parse(req.getParameter("date")));
        race.setGuests(guests);
        dao.save(race);

        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var dao = ChampionshipDAO.getInstance();

        boolean needPastRaces = Boolean.parseBoolean(req.getParameter("needPastRaces"));
        req.setAttribute("races", dao.findAll(needPastRaces));

        req.setAttribute("title", needPastRaces ? "All Races" : "Upcoming Races");
    }
}
