package pe.i2digital.app.models.mapper;

import org.springframework.jdbc.core.RowMapper;
import pe.i2digital.app.models.entity.AsientoContable;
import pe.i2digital.app.models.entity.DetalleAsientoContable;
import pe.i2digital.app.models.entity.Documento;
import pe.i2digital.app.models.entity.TipoOperacionContable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeguimientoDocumentoMapper implements RowMapper<DetalleAsientoContable> {
    @Override
    public DetalleAsientoContable mapRow(ResultSet rs, int rowNum) throws SQLException {
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
    }
}
