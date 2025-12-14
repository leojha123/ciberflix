package servlet;
import model.Usuario;
import dao.UsuarioDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        UsuarioDAO dao = new UsuarioDAO();
        Usuario user = dao.validarUsuario(usuario, password);
        
        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);
            

            response.sendRedirect("InicioServlet");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}