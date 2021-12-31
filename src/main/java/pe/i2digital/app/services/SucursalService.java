package pe.i2digital.app.services;

import pe.i2digital.app.models.entity.OperacionTesoreria;
import pe.i2digital.app.models.entity.Sucursal;

import java.util.List;

public interface SucursalService {
    public List<Sucursal> findAll();
    public Sucursal findById(Integer id);
}
