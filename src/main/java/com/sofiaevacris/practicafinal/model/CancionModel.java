package com.sofiaevacris.practicafinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("CANCIONES")
public class CancionModel {
    private @Column("CANCION_ID") @Id Long cancionId;
    private @Column("NOMBRE_CANCION") String nombreCancion;
    private @Column("ARTISTA_ID") Long artistaId;
    private @Column("ALBUM") String album;

}
