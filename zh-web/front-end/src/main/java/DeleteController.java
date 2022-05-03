import dao.ChampionshipDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {
            var dao = ChampionshipDAO.getInstance();
            var race = dao.findById(Integer.parseInt(id));
            dao.delete(race);
        }
        resp.sendRedirect("index.jsp");
    }
}
