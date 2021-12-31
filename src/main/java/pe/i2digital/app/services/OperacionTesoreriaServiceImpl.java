package pe.i2digital.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.models.entity.OperacionTesoreria;
import pe.i2digital.app.models.repository.OperacionTesoreriaRepository;

@Service
public class OperacionTesoreriaServiceImpl implements OperacionTesoreriaService {
    @Autowired
    OperacionTesoreriaRepository repository;
    @Override
    public Iterable<OperacionTesoreria> findAll() {
        return repository.findAll();
    }

    @Override
    public OperacionTesoreria findById(Integer id) {
        return repository.findById(id).orElseGet(null);
    }
}
