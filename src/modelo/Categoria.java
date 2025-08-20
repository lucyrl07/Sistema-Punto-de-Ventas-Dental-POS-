package modelo;

/**
 *
 * @author Lucy
 */
public class Categoria {
    //atributos
    private int idCategoria;
    private String descripcion;
    private int estado;
    //constructor
    public Categoria(){
        this.idCategoria=0;
        this.descripcion="";
        this.estado=0;
    }

    public Categoria(int idCategoria, String descripcion, int estado) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
