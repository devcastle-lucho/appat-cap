package pe.i2digital.app.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.i2digital.app.models.entity.DetalleAsientoContable;
import pe.i2digital.app.models.mapper.SeguimientoDocumentoMapper;

import java.sql.Types;
import java.util.List;

@Repository
public class DetalleAsientoContableDAOImpl implements DetalleAsientoContableDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
    @Override
    public List<DetalleAsientoContable> seguimientoDocumentos(String esquema,String periodo, Integer idDocumento) {
        return jdbcTemplate.query("select * from "+esquema+".fn_at_select_seguimiento_documentos(?,?)",new SeguimientoDocumentoMapper(), new Object[] { periodo, idDocumento});
    }

    @Override
    public List<DetalleAsientoContable> seguimientoDocumentos2(String esquema, String periodo, Integer idDocumento) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("periodo",periodo, Types.VARCHAR);
        parameters.addValue("idDocumento",idDocumento);
        return namedParameterJdbcTemplate.query("select * from "+esquema+".fn_at_select_seguimiento_documentos(:periodo,:idDocumento)",parameters,new SeguimientoDocumentoMapper());
    }
}
