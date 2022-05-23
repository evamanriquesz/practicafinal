package com.sofiaevacris.practicafinal.dto;

import com.sofiaevacris.practicafinal.model.TarjetaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaDTO {

    @NotNull
    @Size(min=1, max=50)
    Long tarjetaId;

    @NotNull
    @Size(min=1, max=50)
    String numero_tarjeta;

    @NotNull
    @Size(min=1, max=50)
    Long ccv;

    @NotNull
    @Size(min=1, max=50)
    Long usuario_id;

    @NotNull
    @Size(min=1, max=50)
    Long gasto;



    public Long getTarjetaId() {
        return tarjetaId;
    }

    public void setTarjetaId(Long tarjetaIdId) {
        this.tarjetaId = tarjetaId;
    }

    public String getNumeroTarjeta() {
        return numero_tarjeta;
    }

    public void setNumeroTarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public Long getCcv() {
        return ccv;
    }

    public void setCcv(Long ccv) {
        this.ccv = ccv;
    }

    public Long getUsuarioId() {
        return usuario_id;
    }

    public void setUsuarioId(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Long getGasto() {
        return gasto;
    }

    public void setGasto(Long gasto) {
        this.gasto = gasto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarjetaModel that = (TarjetaModel) o;
        return Objects.equals(tarjetaId, that.getTarjetaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tarjetaId);
    }
}
