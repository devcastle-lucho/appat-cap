package pe.i2digital.app.services;

import pe.i2digital.app.models.entity.OperacionTesoreria;

public interface OperacionTesoreriaService {
    public Iterable<OperacionTesoreria> findAll();
    public OperacionTesoreria findById(Integer id);
}
