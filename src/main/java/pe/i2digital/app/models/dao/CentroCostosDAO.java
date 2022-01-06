package pe.i2digital.app.models.dao;

import pe.i2digital.app.models.entity.CentroCostos;

public interface CentroCostosDAO {
    public String iudJson(CentroCostos objeto, String accion, String schema) throws Exception;
}
