package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {

    private static final String URL = "jdbc:mysql://localhost:3306/BD_LOAYZA?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";      
    private static final String PASSWORD = "kali";     

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Driver JDBC
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver MySQL no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar a la base de datos.");
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConexion(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }
}
