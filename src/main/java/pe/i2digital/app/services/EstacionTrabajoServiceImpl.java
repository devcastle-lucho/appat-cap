package pe.i2digital.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.models.dto.projection.EstacionTrabajoVista;
import pe.i2digital.app.models.entity.EstacionTrabajo;
import pe.i2digital.app.models.mapper.EstacionTrabajoMapper;
import pe.i2digital.app.models.repository.EstacionTrabajoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstacionTrabajoServiceImpl implements EstacionTrabajoService{
    @Autowired
    private EstacionTrabajoRepository repository;

    @Override
    public List<EstacionTrabajo> findAll() {
        return (List<EstacionTrabajo>) repository.findAll();
    }

    @Override
    public EstacionTrabajo findById(Integer id) {
        return repository.findById(id).orElseGet(null);
    }

    @Override
    public EstacionTrabajoVista findByCodigo(String codigo) {
        return repository.findByCodigo(codigo).orElseGet(null);
    }

    //Tener cuidado con los artificios.-> Al mapear los tipos de datos.
    @Override
    public List<EstacionTrabajo> busquedaPersonalizada() {
        var listaMap = repository.busquedaPersonalizada();
        List<EstacionTrabajo> lista = new ArrayList<>();
        listaMap.forEach(r -> lista.add(new EstacionTrabajoMapper().mapperRowbusquedaPersonalizada(r)));

        return lista;
    }
}
