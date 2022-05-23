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
@Table("JUGADORES")
public class JugadorModel {
    private @Column("JUGADOR_ID") @Id Long jugadorId;
    private @Column("NOMBRE") String nombre;
    private @Column("APELLIDOS") String apellidos;
    private @Column("EDAD") Long edad;
    private @Column("GENERO") String genero;
    private @Column("EMAIL") String email;
    private @Column("NIVEL") String nivel;
    private @Column("ACIERTOS") Long aciertos;
}