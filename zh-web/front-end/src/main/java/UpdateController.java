import dao.ChampionshipDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {
            int raceID = Integer.parseInt(id);
            req.setAttribute("race", ChampionshipDAO.getInstance().findById(raceID));
            req.setAttribute("title", "Update Race");
        }

        req.getRequestDispatcher("new.jsp").forward(req, resp);
    }
}
