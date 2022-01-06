package pe.i2digital.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.i2digital.app.models.dto.projection.EstacionTrabajoVista;
import pe.i2digital.app.models.entity.EstacionTrabajo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EstacionTrabajoRepository  extends CrudRepository<EstacionTrabajo, Integer> {
    //Uso de DTO basado en interfaces o Proyecciones -> proyeccion cerrada /interfaces anidadas
    public Optional<EstacionTrabajoVista> findByCodigo(String codigo);

    @Query(value = "select tb_estaciontrabajo_id, tb_estaciontrabajo_cod,tb_estaciontrabajo_nom, tb_sucursal_nom from sh_empresa_20441636831.fn_select_estaciontrabajo()", nativeQuery = true)
    public List<Map<String,Object>> busquedaPersonalizada();
}
