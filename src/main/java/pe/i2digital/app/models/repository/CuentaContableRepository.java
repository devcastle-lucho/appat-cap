package pe.i2digital.app.models.repository;

import org.springframework.data.repository.CrudRepository;
import pe.i2digital.app.models.dto.CuentaContableDTO;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.models.entity.CuentaContable;

import java.util.List;

public interface CuentaContableRepository  extends CrudRepository<CuentaContable, Integer> {

    //public List<CuentaContableDTO> findByNumeroOrderByIdIdAsc(String numero);

}
