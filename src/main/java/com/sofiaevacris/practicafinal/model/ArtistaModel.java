package com.sofiaevacris.practicafinal.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("VOTOS")
public class ArtistaModel {
    private @Column("ARTISTA_ID") @Id Long artistaId;
    private @Column("NOMBRE_ARTISTA") String nombreArtista;
    private @Column("FAVORITOS") Long favoritos;
}
