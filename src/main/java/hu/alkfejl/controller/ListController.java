package hu.alkfejl.controller;

import hu.alkfejl.model.KisallatDAO;
import hu.alkfejl.model.KisallatDAOImpl;
import hu.alkfejl.model.bean.Kisallat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ListController")
public class ListController extends HttpServlet {

    KisallatDAO dao;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String driver= getServletContext().getInitParameter("driver");
        System.out.println(driver);
        dao = new KisallatDAOImpl(driver);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fajtaSzuro = req.getParameter("allatSzures");
        if (fajtaSzuro != null && !fajtaSzuro.isEmpty()) {
            System.out.println("Szures: " + fajtaSzuro);
            req.setAttribute("kisallatok", dao.kisallatListazas(fajtaSzuro));
        } else {
            req.setAttribute("kisallatok", dao.kisallatListazas());
        }
        for (Kisallat k : dao.kisallatListazas()) {
            System.out.println(k);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
