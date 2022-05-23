package com.sofiaevacris.practicafinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
    String numero_tarjeta;
    private @Column("CCV")
    Long ccv;
    private @Column("USUARIO_ID")
    Long usuario_id;
    private @Column("GASTO")
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
        return Objects.equals(tarjetaId, that.tarjetaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tarjetaId);
    }


}


