package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {
    // Intentar primero con MYSQL_URL (conexión completa de Railway)
    private static String getConnectionUrl() {
        String mysqlUrl = System.getenv("MYSQL_URL");
        
        if (mysqlUrl != null && !mysqlUrl.isEmpty()) {
            // Convertir mysql:// a jdbc:mysql://
            return mysqlUrl.replace("mysql://", "jdbc:mysql://") + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        }
        
        // Fallback a variables individuales para local
        String host = System.getenv("MYSQLHOST") != null ? System.getenv("MYSQLHOST") : "localhost";
        String port = System.getenv("MYSQLPORT") != null ? System.getenv("MYSQLPORT") : "3306";
        String database = System.getenv("MYSQLDATABASE") != null ? System.getenv("MYSQLDATABASE") : "BD_LOAYZA";
        
        return "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    }
    
    private static final String USER = System.getenv("MYSQLUSER") != null ? System.getenv("MYSQLUSER") : "root";
    private static final String PASSWORD = System.getenv("MYSQLPASSWORD") != null ? System.getenv("MYSQLPASSWORD") : "kali";
    
    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = getConnectionUrl();
            con = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println(" Conexión exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println(" Error: Driver MySQL no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(" Error: No se pudo conectar a la base de datos.");
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