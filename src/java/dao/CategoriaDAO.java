package dao;

import model.Categoria;
import util.MySQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM tb_categoria ORDER BY nombre";

        try (Connection con = MySQLConexion.getConexion();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                categorias.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }
}
