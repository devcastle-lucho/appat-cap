package pe.i2digital.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.models.dto.CuentaContableDTO;
import pe.i2digital.app.models.dto.projection.CuentaContableVista;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.repository.CuentaContableRepository;

import java.util.List;

@Service
public class CuentaContableServiceImpl implements  CuentaContableService{
    @Autowired
    private CuentaContableRepository repository;

    @Override
    public List<CuentaContable> findAll() {
        return (List<CuentaContable>) repository.findAll();
    }

    @Override
    public CuentaContable findById(Integer id) {
        return repository.findById(id).orElseGet(null);
    }

    @Override
    public List<CuentaContableDTO> findByNumeroStartingWithOrderByIdAsc(String numero) {
        return repository.findByNumeroStartingWithOrderByIdAsc(numero);
    }

    @Override
    public List<CuentaContableDTO> busquedaPersonalizadaNumero(String numero) {
        return repository.busquedaPersonalizadaNumero(numero);
    }

    @Override
    public List<CuentaContableVista> findByNumeroStartingWithAndUsaDocumentoTrueOrderById(String numero) {
        return repository.findByNumeroStartingWithAndUsaDocumentoTrueOrderById(numero);
    }
}
