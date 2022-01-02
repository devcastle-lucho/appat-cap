package pe.i2digital.app.models.dto.projection;

//DTO basada en Interfase anidadas / proyeccion -> Proyeccion cerrada
public interface EstacionTrabajoVista {
    public Integer getId();
    public String getCodigo();
    public String getNombre();
    public SucursalDTO getSucursal();

    interface SucursalDTO {
        public String getNombre();
    }
}
