package pe.i2digital.app.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.i2digital.app.models.entity.AsientoContable;
import pe.i2digital.app.models.entity.DetalleAsientoContable;
import pe.i2digital.app.models.entity.Documento;
import pe.i2digital.app.models.entity.TipoOperacionContable;
import pe.i2digital.app.models.mapper.SeguimientoDocumentoMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return namedParameterJdbcTemplate.query("select * from " + esquema + ".fn_at_select_seguimiento_documentos(:periodo,:idDocumento)", parameters, (rs, rowNum) -> {
            var oMapper =  DetalleAsientoContable.builder()
                    .oAsientoContable(AsientoContable.builder()
                            .id(rs.getInt("tb_asientocontable_id"))
                            .numero(rs.getString("tb_asientocontable_num"))
                            .fecha(rs.getDate("tb_asientocontable_fec"))
                            .glosa(rs.getString("tb_asientocontable_glo"))
                            .estado(rs.getString("tb_asientocontable_est"))
                            .oTipoOperacionContable(TipoOperacionContable.builder()
                                    .codigo(rs.getString("tb_tipooperacioncontable_cod"))
                                    .build())
                            .build()
                    )
                    .oDocumento(Documento.builder()
                            .moneda(rs.getString("tb_documento_mon"))
                            .build())
                    .proposito(rs.getString("tb_detalleasientocontable_pro"))
                    .tipo(rs.getString("tb_detalleasientocontable_tip"))
                    .modo(rs.getString("tb_detalleasientocontable_mod"))
                    .importeSoles(rs.getDouble("tb_detalleasientocontable_impsol"))
                    .importeDolares(rs.getDouble("tb_detalleasientocontable_impdol"))
                    .build();
            return oMapper;
        });
    }
}
