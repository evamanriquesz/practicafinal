package com.sofiaevacris.practicafinal.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTarjetaDTO {

    Long usuario_id;
    String nombre;
    String apellidos;
    String email;
    String n_tarjeta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioTarjetaDTO that = (UsuarioTarjetaDTO) o;
        return Objects.equals(usuario_id, that.usuario_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario_id);
    }

}
