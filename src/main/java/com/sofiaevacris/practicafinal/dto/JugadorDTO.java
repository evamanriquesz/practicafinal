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
public class JugadorDTO {
    @NotNull
    @Size(min=1, max=50)
    @Id
    private Long jugadorId;

    @Size(min=1, max=50)
    private Long aciertos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JugadorDTO that = (JugadorDTO) o;
        return Objects.equals(jugadorId, that.jugadorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jugadorId);
    }
}

