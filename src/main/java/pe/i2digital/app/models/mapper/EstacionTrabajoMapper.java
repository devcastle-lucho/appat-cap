package pe.i2digital.app.models.mapper;

import pe.i2digital.app.models.entity.EstacionTrabajo;

import java.util.Map;
import java.util.Objects;

public class EstacionTrabajoMapper {
    public EstacionTrabajo  mapperRowbusquedaPersonalizada(Map<String, Object> values) {
        Integer id =Objects.nonNull(values.get("tb_estaciontrabajo_id"))? (Integer)values.get("tb_estaciontrabajo_id"):null;
        String nombre=Objects.nonNull(values.get("tb_estaciontrabajo_nom"))? (String)values.get("tb_estaciontrabajo_nom"):null;
        Character codigo=Objects.nonNull(values.get("tb_estaciontrabajo_cod"))? (Character)values.get("tb_estaciontrabajo_cod"):null;
        EstacionTrabajo oMapper = EstacionTrabajo.builder()
                .id(id)
                .codigo(codigo.toString())
                .nombre(nombre)
                .build();
        return oMapper;
    }

}
