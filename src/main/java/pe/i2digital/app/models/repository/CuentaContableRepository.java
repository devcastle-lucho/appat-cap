package pe.i2digital.app.models.repository;

import org.springframework.data.repository.CrudRepository;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.models.entity.CuentaContable;

public interface CuentaContableRepository  extends CrudRepository<CuentaContable, Integer> {
}
