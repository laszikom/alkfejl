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

@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {

    KisallatDAO dao;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String driver= getServletContext().getInitParameter("driver");
        System.out.println(driver);
        dao = new KisallatDAOImpl(driver);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Kisallat k = dao.kisallatKereses(id);
            System.out.println("Update: " + k);
            req.setAttribute("kisallat", k);
        }

        req.getRequestDispatcher("uj_allat.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
