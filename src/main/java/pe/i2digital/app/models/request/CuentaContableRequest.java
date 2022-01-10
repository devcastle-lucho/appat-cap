package pe.i2digital.app.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.udt.DestinoCompraUDT;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuentaContableRequest {
    @JsonProperty("oCuentaContable")
    private CuentaContable oCuentaContable;
    @JsonProperty("aDestinoCompra")
    private DestinoCompraUDT[] aDestinoCompra;
    private Boolean updateD;
}
