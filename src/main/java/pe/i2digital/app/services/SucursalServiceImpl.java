package pe.i2digital.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.models.entity.Sucursal;
import pe.i2digital.app.models.repository.SucursalRepository;

import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService{
    @Autowired
    private SucursalRepository repository;

    @Override
    public List<Sucursal> findAll() {
        return (List<Sucursal>) repository.findAll();
    }

    @Override
    public Sucursal findById(Integer id) {
        return repository.findById(id).orElseGet(null);
    }
}
