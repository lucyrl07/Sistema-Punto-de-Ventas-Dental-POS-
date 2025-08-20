
package controlador;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Tratamiento;

public class Ctrl_Tratamiento {
     // Método para guardar nuevo tratamiento

    public boolean guardar(Tratamiento objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_tratamiento values(?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0); // id
            consulta.setString(2, objeto.getNombre());
            consulta.setInt(3, objeto.getCantidad());
            consulta.setDouble(4, objeto.getPrecio());
            consulta.setString(5, objeto.getDescripcion());
            consulta.setInt(6, objeto.getPorcentajeIgv());
            consulta.setInt(7, objeto.getIdCategoria());
            consulta.setInt(8, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar tratamiento: " + e);
        }

        return respuesta;
    }

    // Método para consultar si ya existe tratamiento en la base de datos
    public boolean existeTratamiento(String tratamiento) {
        boolean respuesta = false;
        String sql = "select nombre from tb_tratamiento where nombre = '" + tratamiento + "'";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar tratamiento: " + e);
        }

        return respuesta;
    }

    // Método para actualizar un tratamiento
    public boolean actualizar(Tratamiento objeto, int idTratamiento) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "UPDATE tb_tratamiento SET nombre=?, cantidad=?, precio=?, descripcion=?, porcentajeIgv=?, idCategoria=?, estado=? WHERE idTratamiento=?";
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, objeto.getNombre());
            consulta.setInt(2, objeto.getCantidad());
            consulta.setDouble(3, objeto.getPrecio());
            consulta.setString(4, objeto.getDescripcion());
            consulta.setInt(5, objeto.getPorcentajeIgv());
            consulta.setInt(6, objeto.getIdCategoria());
            consulta.setInt(7, objeto.getEstado());
            consulta.setInt(8, idTratamiento);  // Agregar el parámetro idTratamiento

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tratamiento: " + e);
        }

        return respuesta;
    }

    // Método para eliminar un tratamiento
    public boolean eliminar(int idTratamiento) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_tratamiento where idTratamiento= '" + idTratamiento + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar tratamiento: " + e);
        }

        return respuesta;
    }

    // Método para actualizar stock de un tratamiento
    public boolean actualizarStock(Tratamiento objeto, int idTratamiento) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "update tb_tratamiento set cantidad=? where idTratamiento= '" + idTratamiento + "'");
            consulta.setInt(1, objeto.getCantidad());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar stock del tratamiento: " + e);
        }

        return respuesta;
    }

}
