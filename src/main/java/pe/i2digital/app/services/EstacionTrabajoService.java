package pe.i2digital.app.services;

import pe.i2digital.app.models.dto.projection.EstacionTrabajoVista;
import pe.i2digital.app.models.entity.EstacionTrabajo;
import pe.i2digital.app.models.entity.Sucursal;

import java.util.List;
import java.util.Map;

public interface EstacionTrabajoService {
    public List<EstacionTrabajo> findAll();
    public EstacionTrabajo findById(Integer id);
    public EstacionTrabajoVista findByCodigo(String codigo);
    public List<EstacionTrabajo> busquedaPersonalizada();
}
