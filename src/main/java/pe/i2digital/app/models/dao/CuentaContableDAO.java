package pe.i2digital.app.models.dao;

import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.udt.DestinoCompraUDT;

public interface CuentaContableDAO {
    public String iudJsonV2(String schema, String accion, CuentaContable objeto, Boolean updateD, DestinoCompraUDT[] aDestinoCompra)throws Exception;
    public String iudJson(String schema, String accion, CuentaContable objeto, Boolean updateD, DestinoCompraUDT[] aDestinoCompra)throws Exception;
}
