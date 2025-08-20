
package controlador;
import java.sql.Statement;
import java.sql.Connection;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import modelo.Administrador;

public class Ctrl_Administrador {
    public boolean guardar(Administrador objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            System.out.println("Conectado a la base de datos.");
            // Especificar los nombres de las columnas en la instrucción INSERT INTO
            PreparedStatement consulta = cn.prepareStatement(
                    "INSERT INTO tb_administrador (nombre, apellido, usuario, password,telefono, estado) VALUES (?, ?, ?, ?, ?, ?)");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, objeto.getEstado());
            System.out.println("Ejecutando consulta de inserción...");
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Administrador guardado exitosamente.");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar administrador: " + e.getMessage());
        }
        return respuesta;
    }

    //metodo para consultar si el usuario ya existe
    public boolean existeAdministrador(String administrador) {
        boolean respuesta = false;
        String sql
                = "SELECT usuario FROM tb_administrador WHERE administrador = ?";
        try {
            Connection cn = Conexion.conectar();
            System.out.println("Conectado a la base de datos para consultar Administrador.");
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, administrador);
            System.out.println("Ejecutando consulta de existencia del Administrador...");
            ResultSet rs = consulta.executeQuery();
            if (rs.next()) {
                respuesta = true;
                System.out.println("Administrador encontrado: " + rs.getString("dni"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar Administrador: " + e.getMessage());
        }
        return respuesta;
    }

    public boolean loginUser(Administrador objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        String sql = "select usuario, password from tb_administrador where usuario = '" + objeto.getUsuario()+ "' and password = '" + objeto.getPassword() + "'";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion");
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
        }
        return respuesta;

    }
    
    //Metodo para actualizar un usuario

    public boolean actualizar(Administrador objeto, int idAdministrador) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "UPDATE tb_administrador SET nombre=?, apellido=?, usuario=?, password=?, telefono=?, estado=? WHERE idAdministrador=?";
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getPassword());
            consulta.setString(5, objeto.getTelefono());
            consulta.setInt(6, objeto.getEstado());
            consulta.setInt(7, idAdministrador);  // idCliente es el séptimo parámetro

            System.out.println("Ejecutando consulta de actualización...");
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Administrador actualizado exitosamente.");
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar Administrador: " + e);
        }

        return respuesta;
    }



    //Metodo para eliminar un usuario
    public boolean eliminar(int idAdministrador) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_administrador where idAdministrador= '" + idAdministrador + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar Administrador: " + e);
        }

        return respuesta;
    }
}
