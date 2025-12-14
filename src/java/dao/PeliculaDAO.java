package dao;

import model.Pelicula;
import util.MySQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {

    public int registrarPelicula(Pelicula p) {
        int res = 0;
        String sql = "INSERT INTO tb_pelicula(titulo, descripcion, duracion, url_portada, anio, precio, id_categoria) VALUES(?,?,?,?,?,?,?)";
        
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = MySQLConexion.getConexion();
            
            if (con == null) {
                System.err.println(" ERROR: Conexión nula");
                return 0;
            }
            
            System.out.println(" Conexión establecida");
            System.out.println(" Registrando película: " + p.getTitulo());
            
            pst = con.prepareStatement(sql);
            pst.setString(1, p.getTitulo());
            pst.setString(2, p.getDescripcion());
            pst.setInt(3, p.getDuracion());
            pst.setString(4, p.getUrlPortada());
            pst.setInt(5, p.getAnio());
            pst.setDouble(6, p.getPrecio());
            pst.setInt(7, p.getIdCategoria());
            
            res = pst.executeUpdate();
            
            if (res > 0) {
                System.out.println(" Película registrada exitosamente");
            }
            
        } catch (SQLException e) {
            System.err.println(" ERROR SQL al registrar película:");
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return res;
    }

    public List<Pelicula> listarPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT p.id_pelicula, p.titulo, p.descripcion, p.duracion, p.url_portada, p.anio, p.precio, " +
                     "c.id_categoria, c.nombre AS nombre_categoria " +
                     "FROM tb_pelicula p " +
                     "INNER JOIN tb_categoria c ON p.id_categoria = c.id_categoria " +
                     "ORDER BY p.id_pelicula DESC";

        try (Connection con = MySQLConexion.getConexion();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("id_pelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setDuracion(rs.getInt("duracion"));
                p.setUrlPortada(rs.getString("url_portada"));
                p.setAnio(rs.getInt("anio"));
                p.setPrecio(rs.getDouble("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNombreCategoria(rs.getString("nombre_categoria"));
                peliculas.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar películas: " + e.getMessage());
            e.printStackTrace();
        }

        return peliculas;
    }

    /**
     * 🔍 MÉTODO DE BÚSQUEDA - Busca películas por título o descripción
     * @param termino Término de búsqueda
     * @return Lista de películas que coinciden con el término
     */
    public List<Pelicula> buscarPeliculas(String termino) {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT p.id_pelicula, p.titulo, p.descripcion, p.duracion, p.url_portada, p.anio, p.precio, " +
                     "c.id_categoria, c.nombre AS nombre_categoria " +
                     "FROM tb_pelicula p " +
                     "INNER JOIN tb_categoria c ON p.id_categoria = c.id_categoria " +
                     "WHERE p.titulo LIKE ? OR p.descripcion LIKE ? OR c.nombre LIKE ? " +
                     "ORDER BY p.id_pelicula DESC";

        try (Connection con = MySQLConexion.getConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {

            String busqueda = "%" + termino + "%";
            pst.setString(1, busqueda);
            pst.setString(2, busqueda);
            pst.setString(3, busqueda);
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("id_pelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setDuracion(rs.getInt("duracion"));
                p.setUrlPortada(rs.getString("url_portada"));
                p.setAnio(rs.getInt("anio"));
                p.setPrecio(rs.getDouble("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNombreCategoria(rs.getString("nombre_categoria"));
                peliculas.add(p);
            }
            
            rs.close();

        } catch (SQLException e) {
            System.err.println("Error al buscar películas: " + e.getMessage());
            e.printStackTrace();
        }

        return peliculas;
    }

    public int eliminarPelicula(int id) {
        int res = 0;
        String sql = "DELETE FROM tb_pelicula WHERE id_pelicula=?";

        try (Connection con = MySQLConexion.getConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            res = pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar película: " + e.getMessage());
            e.printStackTrace();
        }

        return res;
    }

    public Pelicula obtenerPelicula(int id) {
        Pelicula p = null;
        String sql = "SELECT * FROM tb_pelicula WHERE id_pelicula=?";

        try (Connection con = MySQLConexion.getConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p = new Pelicula();
                p.setIdPelicula(rs.getInt("id_pelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setDuracion(rs.getInt("duracion"));
                p.setUrlPortada(rs.getString("url_portada"));
                p.setAnio(rs.getInt("anio"));
                p.setPrecio(rs.getDouble("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener película: " + e.getMessage());
            e.printStackTrace();
        }

        return p;
    }

    public int actualizarPelicula(Pelicula p) {
        int res = 0;
        String sql = "UPDATE tb_pelicula SET titulo=?, descripcion=?, duracion=?, url_portada=?, anio=?, precio=?, id_categoria=? WHERE id_pelicula=?";

        try (Connection con = MySQLConexion.getConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, p.getTitulo());
            pst.setString(2, p.getDescripcion());
            pst.setInt(3, p.getDuracion());
            pst.setString(4, p.getUrlPortada());
            pst.setInt(5, p.getAnio());
            pst.setDouble(6, p.getPrecio());
            pst.setInt(7, p.getIdCategoria());
            pst.setInt(8, p.getIdPelicula());

            res = pst.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar película: " + e.getMessage());
            e.printStackTrace();
        }

        return res;
    }
}