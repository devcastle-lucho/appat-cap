package pe.i2digital.app.models.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.i2digital.app.models.entity.DetalleAsientoContable;

import java.util.List;


public interface DetalleAsientoContableDAO {
    public List<DetalleAsientoContable> seguimientoDocumentos(String esquema,String periodo, Integer idDocumento);
    public List<DetalleAsientoContable> seguimientoDocumentos2(String esquema,String periodo, Integer idDocumento);
}
