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
public class ArtistaCancionDTO {

    private Long  cancionId;
    private String nombreCancion;
    private Long artistaId;
    private String nombreArtista;
    private String album;
    private Long favoritos; //para que se muestre cuantos favoritos lleva el artista de est cancion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistaCancionDTO that = (ArtistaCancionDTO) o;
        return Objects.equals(artistaId, that.artistaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistaId);
    }
}
