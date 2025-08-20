package controlador;

import modelo.CabeceraVenta;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import modelo.DetalleVenta;
import modelo.Tratamiento;

/**
 * @author Lucy
 */
public class Ctrl_RegistrarVenta {
//ultimo id de la cabecera

    public static int idCabeceraRegistrada; // Corregido el nombre de la variable

    // Método para guardar registro de venta
    public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            System.out.println("Conectado a la base de datos.");
            // Especificar los nombres de las columnas en la instrucción INSERT INTO
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_cabecera_venta (idPaciente, valorPagar, fechaVenta, estado) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS); // Usar RETURN_GENERATED_KEYS

            consulta.setInt(1, objeto.getIdPaciente());
            consulta.setDouble(2, objeto.getValorPagar());
            consulta.setString(3, objeto.getFechaVenta());
            consulta.setInt(4, objeto.getEstado());

            System.out.println("Ejecutando consulta de inserción...");
            if (consulta.executeUpdate() > 0) {
                ResultSet rs = consulta.getGeneratedKeys(); // Obtener las llaves generadas
                if (rs.next()) {
                    idCabeceraRegistrada = rs.getInt(1); // Obtener el ID generado
                    System.out.println("ID de Cabecera Registrada: " + idCabeceraRegistrada);
                }
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta: " + e.getMessage());
        }
        return respuesta;
    }
    

    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            System.out.println("Conectado a la base de datos.");
            // Especificar los nombres de las columnas en la instrucción INSERT INTO
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_detalle_venta (idDetalleVenta, idCabeceraVenta, idTratamiento, cantidad, precioUnitario, subtotal, descuento, igv, totalPagar, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            consulta.setInt(1, objeto.getIdDetalleVenta());
            consulta.setInt(2, idCabeceraRegistrada); // Usando idCabeceraRegistrada correctamente
            consulta.setInt(3, objeto.getIdTratamiento()); // Asegurando que idProducto esté correctamente configurado en objeto
            consulta.setInt(4, objeto.getCantidad());
            consulta.setDouble(5, objeto.getPrecioUnitario());
            consulta.setDouble(6, objeto.getSubTotal());
            consulta.setDouble(7, objeto.getDescuento());
            consulta.setDouble(8, objeto.getIgv());
            consulta.setDouble(9, objeto.getTotalPagar());
            consulta.setInt(10, objeto.getEstado());

// Asegúrate de ejecutar la consulta correctamente
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Detalle de venta guardado exitosamente.");
            }

            System.out.println("Ejecutando consulta de inserción...");
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Paciente guardado exitosamente.");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e.getMessage());
        }
        return respuesta;
    }

    //Metodo para actualizar un producto
    public boolean actualizar(CabeceraVenta objeto, int idCabecera) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "UPDATE tb_cabecera_venta SET idPaciente=?, estado=? WHERE idCabeceraVenta=?";
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setInt(1, objeto.getIdPaciente());
            consulta.setInt(2, objeto.getEstado());
            consulta.setInt(3, idCabecera); // Este es el parámetro que faltaba

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cabecera de venta: " + e);
        }
        return respuesta;
    }
}
