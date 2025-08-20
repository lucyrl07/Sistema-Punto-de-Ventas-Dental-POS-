
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Paciente;

public class Ctrl_Paciente {

//Metodo para guardar nuevo cliente

    public boolean guardar(Paciente objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            System.out.println("Conectado a la base de datos.");
            // Especificar los nombres de las columnas en la instrucción INSERT INTO
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_paciente (nombre, apellido, dni, telefono, direccion, estado) VALUES (?, ?, ?, ?, ?, ?)");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getDni());
            consulta.setString(4, objeto.getTelefono());
            consulta.setString(5, objeto.getDireccion());
            consulta.setInt(6, objeto.getEstado());
            System.out.println("Ejecutando consulta de inserción...");
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Paciente guardado exitosamente.");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar Paciente: " + e.getMessage());
        }
        return respuesta;
    }

    // Método para consultar si ya existe cliente en la base de datos
    public boolean existePaciente(String dni) {
        boolean respuesta = false;
        String sql = "SELECT dni FROM tb_paciente WHERE dni = ?";
        try {
            Connection cn = Conexion.conectar();
            System.out.println("Conectado a la base de datos para consultar paciente.");
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, dni);
            System.out.println("Ejecutando consulta de existencia del paciente...");
            ResultSet rs = consulta.executeQuery();
            if (rs.next()) {
                respuesta = true;
                System.out.println("Paciente encontrado: " + rs.getString("dni"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar paciente: " + e.getMessage());
        }
        return respuesta;
    }
    
    //Metodo para actualizar un cliente

    public boolean actualizar(Paciente objeto, int idPaciente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            String sql = "UPDATE tb_paciente SET nombre=?, apellido=?, dni=?, telefono=?, direccion=?, estado=? WHERE idPaciente=?";
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getDni());
            consulta.setString(4, objeto.getTelefono());
            consulta.setString(5, objeto.getDireccion());
            consulta.setInt(6, objeto.getEstado());
            consulta.setInt(7, idPaciente);  // idCliente es el séptimo parámetro

            System.out.println("Ejecutando consulta de actualización...");
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
                System.out.println("Cliente actualizado exitosamente.");
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar paciente: " + e);
        }

        return respuesta;
    }

    //Metodo para eliminar un cliente
    public boolean eliminar(int idPaciente) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_paciente where idPaciente= '" + idPaciente + "'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar paciente: " + e);
        }

        return respuesta;
    }

}


