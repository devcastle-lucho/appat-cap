package pe.i2digital.app.services;

import pe.i2digital.app.models.dto.CuentaContableDTO;
import pe.i2digital.app.models.dto.projection.CuentaContableCustom;
import pe.i2digital.app.models.dto.projection.CuentaContableVista;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.request.CuentaContableRequest;

import java.util.List;

public interface CuentaContableService {
    public Iterable<CuentaContable> findAll();
    public CuentaContable findById(Integer id);
    public List<CuentaContableDTO> findByNumeroStartingWithOrderByIdAsc(String numero);
    public List<CuentaContableDTO> busquedaPersonalizadaNumero(String numero);
    public List<CuentaContableVista> findByNumeroStartingWithAndUsaDocumentoTrueOrderById(String numero);
    public List<CuentaContableCustom> busquedaNumeroOperacionTesoreria(String numero);
    public String iudJson(String ruc, String accion, CuentaContableRequest objeto) throws Exception;
}
