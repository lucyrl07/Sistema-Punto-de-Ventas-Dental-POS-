
package modelo;

public class Paciente {

    //Atributos
    private int idPaciente;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
    private int estado;

    //constructor
    public Paciente() {
        this.idPaciente = 0;
        this.nombre = "";
        this.apellido = "";
        this.dni = "";
        this.telefono = "";
        this.direccion = "";
        this.estado = 0;
    }

    //constructor sobrecargado
    public Paciente(int idPaciente, String nombre, String apellido, String dni, String telefono, String direccion, int estado) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
    }

    //set and get

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
   

}


