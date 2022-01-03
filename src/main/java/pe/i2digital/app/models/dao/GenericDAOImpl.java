package pe.i2digital.app.models.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

@Repository
public class GenericDAOImpl implements GenericDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String getVersion() {
        return jdbcTemplate.queryForObject("select version()",String.class);
    }

    @Override
    public String getFechaActual() {
        return jdbcTemplate.queryForObject("select cast(now() as varchar(10))",String.class);
    }

    @Override
    public String version() {
        Session session = sessionFactory.openSession();
        try {
            String r = session.doReturningWork( cnx ->{
                String rpta =null;
                PreparedStatement ps = cnx.prepareStatement("select version();");
                ResultSet rs= ps.executeQuery();
                if(Objects.nonNull(rs)) {
                    if(rs.next())
                        rpta = rs.getString(1);
                    rs.close();
                }
                ps.close();
                return rpta;
            });
            return r;
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }
    }
}
