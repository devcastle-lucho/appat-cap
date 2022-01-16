package pe.i2digital.app.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.udt.DestinoCompraUDT;

@ApiModel(description = "Estructura de la cuenta contable a IUD")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuentaContableRequest {    
    @JsonProperty("oCuentaContable")
    private CuentaContable oCuentaContable;
    @JsonProperty("aDestinoCompra")
    private DestinoCompraUDT[] aDestinoCompra;
    @ApiModelProperty(notes = "Indicador de que se modifica los destinos")
    private Boolean updateD;
}
