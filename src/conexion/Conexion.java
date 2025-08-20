
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conectar() {
        Connection cn = null;
        try {
            // URL de conexión descompuesta
            String url = "jdbc:mysql://bmi7gwcwh8fd0hbt85ac-mysql.services.clever-cloud.com:3306/bmi7gwcwh8fd0hbt85ac";
            String usuario = "uxsugxhwaic4basb";
            String contraseña = "CPBP0Dt6kxZV4A25sTia";
            
            cn = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return cn;
    }

}

