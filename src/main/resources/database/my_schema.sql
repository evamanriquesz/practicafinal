DROP TABLE IF EXISTS STOCK;

CREATE TABLE "STOCK" (
    "IDENTIFICADOR"  IDENTITY  NOT NULL PRIMARY KEY,
    "MODELO" VARCHAR2(40) NOT NULL,
    "COLOR" VARCHAR2(40) NOT NULL,
    "EXISTENCIAS" NUMBER NOT NULL
    );

DROP TABLE IF EXISTS VOTOS;

CREATE TABLE "VOTOS" (
   "ARTISTA_ID" IDENTITY NOT NULL PRIMARY KEY,
   "NOMBRE_ARTISTA" VARCHAR2(50) NOT NULL,
   "FAVORITOS" NUMBER NOT NULL
);
--esto va con ArtistaModel


DROP TABLE IF EXISTS CANCIONES;
CREATE TABLE "CANCIONES" (
   "CANCION_ID" IDENTITY NOT NULL PRIMARY KEY,
   "NOMBRE_CANCION" VARCHAR2(50) NOT NULL,
   "ARTISTA_ID" NUMBER NOT NULL,
   "ALBUM" VARCHAR2(50) NOT NULL
);
--esto va con CancionModel



--Tabla de jugadores de marvel
DROP TABLE IF EXISTS JUGADORES;
CREATE TABLE "JUGADORES" (
   "JUGADOR_ID" IDENTITY NOT NULL PRIMARY KEY,
   "NOMBRE" VARCHAR2(50) NOT NULL,
   "APELLIDOS" VARCHAR2(80) NOT NULL,
   "EDAD" NUMBER NOT NULL,
   "GENERO" VARCHAR2(10) NOT NULL,
   "EMAIL" VARCHAR2(50) NOT NULL,
   "NIVEL" VARCHAR2(50) NOT NULL,
   "ACIERTOS" NUMBER NOT NULL,
);
