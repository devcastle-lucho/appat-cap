package pe.i2digital.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.models.repository.CentroCostosRepository;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CentroCostosServiceImpl implements CentroCostosService {
    @Autowired
    private CentroCostosRepository repository;

    @Override
    public List<CentroCostos> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public CentroCostos findByCodigo(String codigo) {
        return repository.findByCodigo(codigo).orElse(null);
    }

    @Override
    public CentroCostos findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Iterable<CentroCostos> findAll() {
        log.info("Listando centro costos..");//Pruebas
        return repository.findAll();
    }

    @Override
    public CentroCostos save(CentroCostos entity) {
        //Proceso 1
        log.info("Procesando..");
        //Proceso 2
        //log.info("Procesando 2..");
        //Proceso 3
        //log.info("Procesando 2..");
        return repository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
