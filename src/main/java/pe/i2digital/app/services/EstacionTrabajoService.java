package pe.i2digital.app.services;

import pe.i2digital.app.models.entity.EstacionTrabajo;
import pe.i2digital.app.models.entity.Sucursal;

import java.util.List;

public interface EstacionTrabajoService {
    public List<EstacionTrabajo> findAll();
    public EstacionTrabajo findById(Integer id);
}
