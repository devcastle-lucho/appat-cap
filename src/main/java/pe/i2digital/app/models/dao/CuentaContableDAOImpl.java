package pe.i2digital.app.models.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.i2digital.app.models.dto.ExcelAsientoContableDTO;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.udt.DestinoCompraUDT;

import java.sql.*;
import java.util.Map;
import java.util.Objects;

@Repository
public class CuentaContableDAOImpl implements CuentaContableDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String iudJsonV2(String schema, String accion, CuentaContable objeto, Boolean updateD, DestinoCompraUDT[] aDestinoCompra) throws Exception{
        var session = sessionFactory.openSession();
        try(session) {
            ObjectMapper mapper = new ObjectMapper();
            session.getTransaction().begin();
            String answer = session.doReturningWork(cnx -> {
                try{
                    //https://www.postgresql.org/docs/14/datatype-character.html "char"<> character(n)/char(n)
                    String sql = String.format("select %s.fn_at_json_iud_row_cuentacontablev2(?::character,?,?,?,?::character,?,?,?)",schema);
                    PreparedStatement ps = cnx.prepareStatement(sql);
                    int index=0;
                    ps.setObject(++index,accion, Types.CHAR);
                    ps.setObject(++index,objeto.getId(), Types.INTEGER);
                    ps.setObject(++index,objeto.getNumero(), Types.VARCHAR);
                    ps.setObject(++index,objeto.getNombre(), Types.VARCHAR);
                    ps.setObject(++index,objeto.getMoneda(), Types.CHAR);
                    ps.setObject(++index,objeto.getUsaDocumento(), Types.BOOLEAN);
                    ps.setObject(++index,updateD, Types.BOOLEAN);
                    Array inArray =Objects.nonNull(aDestinoCompra)?cnx.createArrayOf(DestinoCompraUDT.TYPE_NAME,aDestinoCompra):null;
                    ps.setObject(++index,inArray, Types.ARRAY);

                    ResultSet rs = ps.executeQuery();
                    String dato=null;
                    if(Objects.nonNull(rs)) {
                        if (rs.next()) {
                            dato=rs.getString(1);
                        }
                        rs.close();
                    }
                    ps.close();

                    return dato;
                }catch (Exception ex) {
                    throw new HibernateException(ex);
                }
            });
            Map<String,Object> map = mapper.readValue(answer, new TypeReference<Map<String, Object>>() {});
            if(Objects.nonNull(map) && Objects.equals(map.get("msj"),"OK"))
                session.getTransaction().commit();
            else session.getTransaction().rollback();
            return answer;
        }
    }

    @Override
    public String iudJson(String schema, String accion, CuentaContable objeto, Boolean updateD, DestinoCompraUDT[] aDestinoCompra) throws Exception {
        try(Connection cnx =jdbcTemplate.getDataSource().getConnection()) {
            cnx.setSchema(schema);
            cnx.setAutoCommit(false);
            ObjectMapper mapper = new ObjectMapper();

            String sql = String.format("{ ? = call fn_at_json_iud_row_cuentacontablev2(?::character,?,?,?,?::character,?,?,?::%s.tp_at_destinocompra[]) }",schema);
            CallableStatement cstm =  cnx.prepareCall(sql);
            int index = 0;
            cstm.registerOutParameter(++index, Types.VARCHAR);
            cstm.setObject(++index,accion, Types.CHAR);
            cstm.setObject(++index,objeto.getId(), Types.INTEGER);
            cstm.setObject(++index,objeto.getNumero(), Types.VARCHAR);
            cstm.setObject(++index,objeto.getNombre(), Types.VARCHAR);
            cstm.setObject(++index,objeto.getMoneda(), Types.CHAR);
            cstm.setObject(++index,objeto.getUsaDocumento(), Types.BOOLEAN);
            cstm.setObject(++index,updateD, Types.BOOLEAN);
            Array inArray =Objects.nonNull(aDestinoCompra)?cnx.createArrayOf(DestinoCompraUDT.TYPE_NAME,aDestinoCompra):null;
            cstm.setObject(++index,inArray, Types.ARRAY);

            cstm.execute();
            String answer = cstm.getString(1);
            Map<String,Object> map = mapper.readValue(answer, new TypeReference<Map<String, Object>>() {});
            if(Objects.nonNull(map) && Objects.equals(map.get("msj"),"OK"))
                cnx.commit();
            else cnx.rollback();
            return answer;
        }
    }
}
