package pe.i2digital.app.models.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.i2digital.app.models.entity.CentroCostos;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Map;
import java.util.Objects;

@Repository
public class CentroCostosDAOImpl implements CentroCostosDAO{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String iudJson(CentroCostos objeto, String accion, String schema) throws Exception {
        var session = sessionFactory.openSession();
        try{
            ObjectMapper mapper = new ObjectMapper();
            session.getTransaction().begin();
            String answer = session.doReturningWork((cnx) -> {
                try {
                    String sql = "{ ? = call "+schema+".fn_at_json_iud_row_centrocostos(?,?,?,?) }";
                    CallableStatement cstm =  cnx.prepareCall(sql);
                    int index = 0;
                    cstm.registerOutParameter(++index, Types.VARCHAR);
                    cstm.setObject(++index,accion, Types.CHAR);
                    cstm.setObject(++index,objeto.getId(), Types.INTEGER);
                    cstm.setObject(++index,objeto.getCodigo(), Types.CHAR);
                    cstm.setObject(++index,objeto.getNombre(), Types.VARCHAR);
                    cstm.execute();

                    String fact = cstm.getString(1);
                    cstm.close();
                    return fact;
                } catch (Exception ex) {
                    throw new HibernateException(ex);
                }
            });
            Map<String,Object> map = mapper.readValue(answer, new TypeReference<Map<String, Object>>() {});
            if(Objects.nonNull(map) && Objects.equals(map.get("msj"),"OK"))
                session.getTransaction().commit();
            else session.getTransaction().rollback();
            return answer;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw ex;
        } finally {
            session.close();
        }
    }
}
