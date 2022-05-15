package com.sofiaevacris.practicafinal.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistaDTO {

    //voy a querer que el DTO tenga solo el nombr y el fav, el id me lo quedo solo para mi

    @NotNull
    @Size(min=1, max=50)
    @Id
    private Long artistaId;

    @NotNull
    @Size(min=1, max=50)
    private String nombreArtista;

    @NotNull
    @Size (min=1, max=5)
    private Long favoritos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistaDTO that = (ArtistaDTO) o;
        return Objects.equals(artistaId, that.artistaId) && Objects.equals(nombreArtista, that.nombreArtista) && Objects.equals(favoritos, that.favoritos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistaId, nombreArtista, favoritos);
    }
}
