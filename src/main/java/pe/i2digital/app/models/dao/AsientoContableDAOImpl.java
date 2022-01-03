package pe.i2digital.app.models.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.i2digital.app.models.dto.ExcelAsientoContableDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AsientoContableDAOImpl implements AsientoContableDAO{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<ExcelAsientoContableDTO> busquedaExcel(String periodo, Integer id) {
        try(var session = sessionFactory.openSession()) {
            List<ExcelAsientoContableDTO> lista = new ArrayList<>();
            session.doWork(cnx -> {
                PreparedStatement ps = cnx.prepareStatement("select " +
                        "fecha,glosa,opecontable,numero,ctacontable,debe_soles,haber_soles,documento " +
                        "from sh_empresa_20441636831.fn_at_excel_asientocontable(?,?)");
                int index=0;
                ps.setObject(++index,periodo, Types.VARCHAR);
                ps.setObject(++index,id, Types.INTEGER);
                ResultSet rs = ps.executeQuery();
                if(Objects.nonNull(rs)) {
                    while (rs.next()) {
                        ExcelAsientoContableDTO oMapper = mapperResulsetRecord(rs);
                        lista.add(oMapper);
                    }
                    rs.close();
                }
                ps.close();
            });
            return lista;
        }
    }
    private ExcelAsientoContableDTO mapperResulsetRecord(ResultSet rs) throws SQLException {
        var mapper = ExcelAsientoContableDTO.builder()
                .fecha(rs.getDate("fecha"))
                .numero(rs.getString("numero"))
                .glosa(rs.getString("glosa"))
                .operacionContable(rs.getString("opecontable"))
                .cuentaContable(rs.getString("ctacontable"))
                .debeSoles(rs.getDouble("debe_soles"))
                .haberSoles(rs.getDouble("haber_soles"))
                .documento(rs.getString("documento"))
                .build();
        return mapper;
    }
}
