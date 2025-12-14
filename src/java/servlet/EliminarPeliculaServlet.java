package servlet;

import dao.PeliculaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EliminarPeliculaServlet")
public class EliminarPeliculaServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {

            int id = Integer.parseInt(request.getParameter("id"));
            

            PeliculaDAO pDao = new PeliculaDAO();
            int res = pDao.eliminarPelicula(id);
            
            if (res > 0) {
                session.setAttribute("exito", "️ Película eliminada correctamente");
            } else {
                session.setAttribute("error", " Error al eliminar la película");
            }
            
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "ID de película inválido");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error al eliminar: " + e.getMessage());
        }
        

        response.sendRedirect("InicioServlet");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}