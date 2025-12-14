package servlet;

import dao.CategoriaDAO;
import dao.PeliculaDAO;
import model.Categoria;
import model.Pelicula;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/EditarPeliculaServlet")
public class EditarPeliculaServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            // Obtener el ID de la película
            int id = Integer.parseInt(request.getParameter("id"));
            

            PeliculaDAO pDao = new PeliculaDAO();
            Pelicula p = pDao.obtenerPelicula(id);
            
            if (p == null) {
                request.setAttribute("error", "Película no encontrada");
                response.sendRedirect("InicioServlet");
                return;
            }
            
            // Cargar categorías
            CategoriaDAO cDao = new CategoriaDAO();
            List<Categoria> categorias = cDao.listarCategorias();
            
            // Enviar datos al JSP
            request.setAttribute("pelicula", p);
            request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("editarPelicula.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            response.sendRedirect("InicioServlet");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            // Obtener parámetros
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            int duracion = Integer.parseInt(request.getParameter("duracion"));
            String urlPortada = request.getParameter("urlPortada");
            int anio = Integer.parseInt(request.getParameter("anio"));
            double precio = Double.parseDouble(request.getParameter("precio"));
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            
            // Crear objeto Pelicula
            Pelicula p = new Pelicula(titulo, descripcion, duracion, urlPortada, anio, precio, idCategoria);
            p.setIdPelicula(id);
            
            // Actualizar en la base de datos
            PeliculaDAO pDao = new PeliculaDAO();
            int res = pDao.actualizarPelicula(p);
            
            if (res > 0) {
                session.setAttribute("exito", " Película actualizada correctamente");
            } else {
                session.setAttribute("error", " Error al actualizar la película");
            }
            
            // Redirigir al inicio
            response.sendRedirect("InicioServlet");
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Datos inválidos. Por favor verifica los campos numéricos.");
            doGet(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al procesar la actualización: " + e.getMessage());
            doGet(request, response);
        }
    }
}