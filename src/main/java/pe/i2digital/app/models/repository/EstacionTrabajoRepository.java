package pe.i2digital.app.models.repository;

import org.springframework.data.repository.CrudRepository;
import pe.i2digital.app.models.dto.projection.EstacionTrabajoVista;
import pe.i2digital.app.models.entity.EstacionTrabajo;

import java.util.Optional;

public interface EstacionTrabajoRepository  extends CrudRepository<EstacionTrabajo, Integer> {
    //Uso de DTO basado en interfaces o Proyeccios -> proyeccion cerrada
    public Optional<EstacionTrabajoVista> findByCodigo(String codigo);
}
