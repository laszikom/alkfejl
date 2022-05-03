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

@WebServlet("/SaveController")
public class SaveController extends HttpServlet {
    KisallatDAO dao;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String driver= getServletContext().getInitParameter("driver");
        System.out.println(driver);
        dao = new KisallatDAOImpl(driver);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Kisallat k = new Kisallat();
        try {
            k.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e) {
            // no id
        }

        k.setNev(req.getParameter("nev"));
        k.setFajta(req.getParameter("fajta"));
        k.setKor(Integer.parseInt(req.getParameter("kor")));
        System.out.println(k);
        dao.kisallatHozzaadas(k);
        resp.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
