package servlet;

import dao.CategoriaDAO;
import dao.PeliculaDAO;
import model.Pelicula;
import model.Categoria;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/RegistrarPeliculaServlet")
public class RegistrarPeliculaServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // Cargar categorías
        CategoriaDAO catDAO = new CategoriaDAO();
        List<Categoria> categorias = catDAO.listarCategorias();
        
        // Debug: verificar si hay categorías
        if (categorias == null || categorias.isEmpty()) {
            request.setAttribute("error", "No hay categorías disponibles. Por favor, registre categorías primero.");
        }
        
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("registrarPeliculas.jsp").forward(request, response);
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
        
        // Obtener parámetros
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String duracionStr = request.getParameter("duracion");
        String urlPortada = request.getParameter("urlPortada");
        String anioStr = request.getParameter("anio");
        String precioStr = request.getParameter("precio");
        String idCategoriaStr = request.getParameter("idCategoria");
        
        StringBuilder errores = new StringBuilder();
        int duracion = 0, anio = 0, idCategoria = 0;
        double precio = 0;
        
        // Validaciones
        if (titulo == null || titulo.trim().isEmpty()) {
            errores.append("El título es obligatorio. ");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            errores.append("La descripción es obligatoria. ");
        }
        if (urlPortada == null || urlPortada.trim().isEmpty()) {
            errores.append("La URL de portada es obligatoria. ");
        }
        
        try {
            duracion = Integer.parseInt(duracionStr);
            if (duracion <= 0) {
                errores.append("La duración debe ser mayor a 0. ");
            }
        } catch (Exception e) {
            errores.append("Duración inválida. ");
        }
        
        try {
            anio = Integer.parseInt(anioStr);
            if (anio < 1900 || anio > 2100) {
                errores.append("Año inválido. ");
            }
        } catch (Exception e) {
            errores.append("Año inválido. ");
        }
        
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                errores.append("El precio no puede ser negativo. ");
            }
        } catch (Exception e) {
            errores.append("Precio inválido. ");
        }
        
        try {
            idCategoria = Integer.parseInt(idCategoriaStr);
            if (idCategoria <= 0) {
                errores.append("Debe seleccionar una categoría válida. ");
            }
        } catch (Exception e) {
            errores.append("Debe seleccionar una categoría. ");
        }
        
        if (errores.length() == 0) {
            try {
                Pelicula p = new Pelicula(titulo, descripcion, duracion, urlPortada, anio, precio, idCategoria);
                PeliculaDAO dao = new PeliculaDAO();
                int res = dao.registrarPelicula(p);
                
                if (res > 0) {
                    request.setAttribute("exito", "Película registrada correctamente");
                    // Limpiar el formulario después del éxito
                } else {
                    request.setAttribute("error", "Error al registrar la película en la base de datos");
                }
            } catch (Exception e) {
                request.setAttribute("error", "Error al procesar la película: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            request.setAttribute("error", errores.toString());
            request.setAttribute("titulo", titulo);
            request.setAttribute("descripcion", descripcion);
            request.setAttribute("duracion", duracionStr);
            request.setAttribute("urlPortada", urlPortada);
            request.setAttribute("anio", anioStr);
            request.setAttribute("precio", precioStr);
            request.setAttribute("idCategoria", idCategoriaStr);
        }
        
        CategoriaDAO catDAO = new CategoriaDAO();
        List<Categoria> categorias = catDAO.listarCategorias();
        request.setAttribute("categorias", categorias);
        
        request.getRequestDispatcher("registrarPeliculas.jsp").forward(request, response);
    }
}