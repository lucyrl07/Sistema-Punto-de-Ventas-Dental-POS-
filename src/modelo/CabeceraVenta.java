package modelo;

public class CabeceraVenta {
    
    //atributos
    private int idCabeceraventa;
    private int idPaciente;
    private double valorPagar;
    private String fechaVenta;
    private int estado;
    
    //constructor
    public CabeceraVenta(){
        this.idCabeceraventa=0;
        this.idPaciente=0;
        this.valorPagar=0.0;
        this.fechaVenta="";
        this.estado=0;
    }
     //constructor sobrecargado

    public CabeceraVenta(int idCabeceraventa, int idPaciente, double valorPagar, String fechaVenta, int estado) {
        this.idCabeceraventa = idCabeceraventa;
        this.idPaciente = idPaciente;
        this.valorPagar = valorPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
    }
     //get and sentds i

        public int getIdCabeceraventa() {
            return idCabeceraventa;
        }

        public void setIdCabeceraventa(int idCabeceraventa) {
            this.idCabeceraventa = idCabeceraventa;
        }

        public int getIdPaciente() {
            return idPaciente;
        }

        public void setIdPaciente(int idPaciente) {
            this.idPaciente = idPaciente;
        }

        public double getValorPagar() {
            return valorPagar;
        }

        public void setValorPagar(double valorPagar) {
            this.valorPagar = valorPagar;
        }

        public String getFechaVenta() {
            return fechaVenta;
        }

        public void setFechaVenta(String fechaVenta) {
            this.fechaVenta = fechaVenta;
        }

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }

    @Override
    public String toString() {
        return "CabeceraVenta{" + "idCabeceraventa=" + idCabeceraventa + ", idPaciente=" + idPaciente + ", valorPagar=" + valorPagar + ", fechaVenta=" + fechaVenta + ", estado=" + estado + '}';
    }

}
