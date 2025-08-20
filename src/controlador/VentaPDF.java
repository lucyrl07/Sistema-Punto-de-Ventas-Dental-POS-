package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import vista.InterFacturacion;

public class VentaPDF {

   private String nombrePaciente;
    private String dniPaciente;
    private String telefonoPaciente;
    private String direccionPaciente;

    private String fechaActual = "";
    private String nombreArchivoPDFVenta = "";

    // Método para obtener datos del cliente
    public void DatosPaciente(int idPaciente) {
        Connection cn = Conexion.conectar();
        String sql = "SELECT * FROM tb_paciente WHERE idPaciente='" + idPaciente + "'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombrePaciente = rs.getString("nombre") + " " + rs.getString("apellido");
                dniPaciente = rs.getString("dni");
                telefonoPaciente = rs.getString("telefono");
                direccionPaciente = rs.getString("direccion");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del Paciente: " + e);
        }
    }

    // Método para generar la factura de venta
    public void generarFacturaPDF() {
        try {
            // Cargar la fecha actual
            Date date = new Date();
            String fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            // Cambiar el formato de la fecha de / a _
            String fechaNueva = fechaActual.replace("/", "_");
            String nombreArchivoPDFVenta = "Venta_" + nombrePaciente + "_" + fechaNueva + ".pdf";
            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivoPDFVenta);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Image header = Image.getInstance("src/img/logoreporte.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            doc.add(header);

            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

            // Datos del encabezado
            PdfPTable encabezadoTable = new PdfPTable(1);
            encabezadoTable.setWidthPercentage(100);
            encabezadoTable.setHorizontalAlignment(Element.ALIGN_LEFT);

            String ruc = "20123456789";
            String nombre = "Healtly Smiles";
            String telefono = "956364654 - 945234756";
            String direccion = "Calle Real 123, Distrito de El Tambo, Huancayo, Junín, Perú";
            String razon = "En Clínica Odontológica Healtly Smile brindamos atención dental de alta calidad en un entorno acogedor y profesional en Huancayo. Ofrecemos una amplia gama de servicios odontológicos.";

            // Celdas para el encabezado
            PdfPCell celdaEmpresa = new PdfPCell();
            celdaEmpresa.setBorder(0);
            celdaEmpresa.addElement(new Phrase("\nRUC: " + ruc, negrita));
            celdaEmpresa.addElement(new Phrase("Nombre: " + nombre, negrita));
            celdaEmpresa.addElement(new Phrase("Teléfono: " + telefono, negrita));
            celdaEmpresa.addElement(new Phrase("Dirección: " + direccion, negrita));
            celdaEmpresa.addElement(new Phrase("Razón Social: \n" + razon, negrita));

            encabezadoTable.addCell(celdaEmpresa);
            doc.add(encabezadoTable);

            // Separación
            Paragraph separacion = new Paragraph();
            separacion.add(Chunk.NEWLINE);
            doc.add(separacion);

            // Datos del paciente
            Paragraph datosPaciente = new Paragraph();
            datosPaciente.add(new Phrase("Datos del Paciente:", negrita));
            datosPaciente.add(Chunk.NEWLINE);
            datosPaciente.add(new Phrase("DNI/RUC: " + dniPaciente));
            datosPaciente.add(Chunk.NEWLINE);
            datosPaciente.add(new Phrase("Nombre: " + nombrePaciente));
            datosPaciente.add(Chunk.NEWLINE);
            datosPaciente.add(new Phrase("Teléfono: " + telefonoPaciente));
            datosPaciente.add(Chunk.NEWLINE);
            datosPaciente.add(new Phrase("Dirección: " + direccionPaciente));
            datosPaciente.add(Chunk.NEWLINE);
            datosPaciente.add(Chunk.NEWLINE);

            doc.add(datosPaciente);

            // Productos
            PdfPTable productosTable = new PdfPTable(4);
            productosTable.setWidthPercentage(100);
            productosTable.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell headerCantidad = new PdfPCell(new Phrase("Cantidad", negrita));
            headerCantidad.setBackgroundColor(BaseColor.LIGHT_GRAY);
            productosTable.addCell(headerCantidad);

            PdfPCell headerDescripcion = new PdfPCell(new Phrase("Descripción", negrita));
            headerDescripcion.setBackgroundColor(BaseColor.LIGHT_GRAY);
            productosTable.addCell(headerDescripcion);

            PdfPCell headerPrecioUnitario = new PdfPCell(new Phrase("Precio Unitario", negrita));
            headerPrecioUnitario.setBackgroundColor(BaseColor.LIGHT_GRAY);
            productosTable.addCell(headerPrecioUnitario);

            PdfPCell headerPrecioTotal = new PdfPCell(new Phrase("Precio Total", negrita));
            headerPrecioTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);
            productosTable.addCell(headerPrecioTotal);

            // Aquí agregarías tus datos de productos desde jTable_tratamientos
            
            for (int i = 0; i < InterFacturacion.jTable_tratamientos.getRowCount(); i++) {
                String tratamiento = InterFacturacion.jTable_tratamientos.getValueAt(i, 1).toString();
                String cantidad = InterFacturacion.jTable_tratamientos.getValueAt(i, 2).toString();
                String precio = InterFacturacion.jTable_tratamientos.getValueAt(i, 3).toString();
                String total = InterFacturacion.jTable_tratamientos.getValueAt(i, 7).toString();

                productosTable.addCell(cantidad);
                productosTable.addCell(tratamiento);
                productosTable.addCell(precio);
                productosTable.addCell(total);
            }
            

            doc.add(productosTable);

            // Total a pagar
            Paragraph totalPagar = new Paragraph();
            totalPagar.add(Chunk.NEWLINE);
            totalPagar.add(new Phrase("Total a pagar: " + InterFacturacion.txt_total_pagar.getText(), negrita));
            totalPagar.setAlignment(Element.ALIGN_RIGHT);
            doc.add(totalPagar);

            // Firma
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelación y firma \n\n");
            firma.add(Chunk.NEWLINE);
            firma.add("_______________________");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            // Mensaje final
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("Gracias por su preferencia");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            // Cerrar el documento y el archivo
            doc.close();
            archivo.close();

            // Abrir el documento generado automáticamente
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println("Error al generar la factura PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VentaPDF pdf = new VentaPDF();
        pdf.generarFacturaPDF();
    }
}
