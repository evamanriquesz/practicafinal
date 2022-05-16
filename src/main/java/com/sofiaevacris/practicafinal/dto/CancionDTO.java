package com.sofiaevacris.practicafinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancionDTO {

    @NotNull
    @Size(min=1, max=50)
    @Id
    private Long cancionId;

    @NotNull
    @Size(min=1, max=50)
    private String nombreCancion;

    @NotNull
    @Size(min=1, max=50)
    private Long artistaId;

    @NotNull
    @Size(min=1, max=50)
    private String album;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancionDTO that = (CancionDTO) o;
        return Objects.equals(cancionId, that.cancionId) && Objects.equals(nombreCancion, that.nombreCancion) && Objects.equals(artistaId, that.artistaId) && Objects.equals(album, that.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cancionId, nombreCancion, artistaId, album);
    }
}










