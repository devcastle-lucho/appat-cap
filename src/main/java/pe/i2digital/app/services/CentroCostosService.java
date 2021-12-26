package pe.i2digital.app.services;

import pe.i2digital.app.models.entity.CentroCostos;

import java.util.List;
import java.util.Optional;

public interface CentroCostosService {
    //CRUD: CREATE-READ-UPDATE-DELETE
    public CentroCostos findByCodigo(String codigo);
    public CentroCostos findById(Integer id);
    public Iterable<CentroCostos> findAll();
    public List<CentroCostos> findByNombre(String nombre);
    public CentroCostos save(CentroCostos entity);
    public void deleteById(Integer id);
}
