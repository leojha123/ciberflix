package dao;

import model.Usuario;
import util.MySQLConexion;
import java.sql.*;

public class UsuarioDAO {

    public Usuario validarUsuario(String usuario, String password) {
        Usuario u = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = MySQLConexion.getConexion();
            String sql = "SELECT * FROM tb_usuario WHERE usuario = ? AND password = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, password); // ojo: en producción conviene encriptar la password
            rs = pst.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setUsuario(rs.getString("usuario"));
                u.setCorreo(rs.getString("correo"));
                u.setEstado(rs.getInt("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) MySQLConexion.closeConexion(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return u;
    }
}
