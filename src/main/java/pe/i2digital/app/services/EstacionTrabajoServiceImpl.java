package pe.i2digital.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.models.entity.EstacionTrabajo;
import pe.i2digital.app.models.repository.EstacionTrabajoRepository;

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
}
