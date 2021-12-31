package pe.i2digital.app.models.repository;

import org.springframework.data.repository.CrudRepository;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.entity.OperacionTesoreria;

public interface OperacionTesoreriaRepository  extends CrudRepository<OperacionTesoreria, Integer> {
}
