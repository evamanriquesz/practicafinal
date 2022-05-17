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
        return Objects.equals(artistaId, that.artistaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistaId);
    }
}
