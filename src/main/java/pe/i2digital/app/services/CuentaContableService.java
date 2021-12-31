package pe.i2digital.app.services;

import pe.i2digital.app.models.entity.CuentaContable;

import java.util.List;

public interface CuentaContableService {
    public Iterable<CuentaContable> findAll();
    public CuentaContable findById(Integer id);
}
