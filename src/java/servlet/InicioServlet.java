package servlet;

import dao.PeliculaDAO;
import model.Pelicula;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/InicioServlet")
public class InicioServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("usuario") == null) {

            response.sendRedirect("login.jsp");
            return;
        }
        
        String busqueda = request.getParameter("busqueda");
        

        PeliculaDAO peliculaDAO = new PeliculaDAO();
        List<Pelicula> peliculas;
        

        if (busqueda != null && !busqueda.trim().isEmpty()) {
            peliculas = peliculaDAO.buscarPeliculas(busqueda.trim());
            System.out.println(" Buscando películas con término: " + busqueda);
            System.out.println(" Resultados encontrados: " + peliculas.size());
        } else {

            peliculas = peliculaDAO.listarPeliculas();
            System.out.println(" Listando todas las películas: " + peliculas.size());
        }
        

        request.setAttribute("peliculas", peliculas);
        

        request.getRequestDispatcher("inicio.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}