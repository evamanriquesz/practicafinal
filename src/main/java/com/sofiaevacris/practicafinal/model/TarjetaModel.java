package com.sofiaevacris.practicafinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("TARJETA")
public class TarjetaModel {

    @Id
    private @Column("TARJETA_ID")
    Long tarjetaId;
    private @Column("NUMERO_TARJETA")
    String numeroTarjeta;
    private @Column("FECHA_CADUCIDAD")
    String fechaCaducidad;
    private @Column("CVV")
    Long cvv;
    private @Column("USUARIO_ID")
    Long usuarioId;
    private @Column("GASTO")
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

    public void setNumeroTarjeta(String numeroTarjeta) {
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

    public void setCvv(Long cvv) {
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
        return Objects.equals(tarjetaId, that.tarjetaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tarjetaId);
    }



}


