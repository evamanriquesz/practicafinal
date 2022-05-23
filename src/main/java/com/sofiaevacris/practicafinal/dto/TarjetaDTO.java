package com.sofiaevacris.practicafinal.dto;

import com.sofiaevacris.practicafinal.model.TarjetaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
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
    String numeroTarjeta;

    @NotNull
    @Size(min=1, max=50)
    String fechaCaducidad;


    @NotNull
    @Size(min=1, max=4)
    Long cvv;

    @NotNull
    @Size(min=1, max=50)
    Long usuarioId;

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
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numero_tarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Long getCvv() {
        return cvv;
    }

    public void setCcv(Long cvv) {
        this.cvv = cvv;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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
