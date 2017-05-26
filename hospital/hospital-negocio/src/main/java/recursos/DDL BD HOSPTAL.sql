-- Generado por Oracle SQL Developer Data Modeler 4.1.3.901
--   en:        2017-05-25 17:56:57 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g




CREATE TABLE Acceso
  (
    id     NUMBER NOT NULL ,
    nombre VARCHAR2 (100) NOT NULL ,
    url    VARCHAR2 (300) NOT NULL
  ) ;
ALTER TABLE Acceso ADD CONSTRAINT Acceso_PK PRIMARY KEY ( id ) ;


CREATE TABLE AccesoRol
  ( Rol NUMBER NOT NULL , Acceso NUMBER NOT NULL
  ) ;
ALTER TABLE AccesoRol ADD CONSTRAINT AccesoRol_PK PRIMARY KEY ( Acceso, Rol ) ;


CREATE TABLE Camas
  (
    Id_Cama     INTEGER NOT NULL ,
    Descripcion VARCHAR2 (50) NOT NULL ,
    Estado      CHAR (1) NOT NULL
  ) ;
ALTER TABLE Camas ADD CONSTRAINT Camas_PK PRIMARY KEY ( Id_Cama ) ;


CREATE TABLE Cirugia
  (
    Id_Cirugia  INTEGER NOT NULL ,
    Nombre      VARCHAR2 (50) NOT NULL ,
    Descripcion VARCHAR2 (100) NOT NULL ,
    Quirofano   INTEGER NOT NULL
  ) ;
ALTER TABLE Cirugia ADD CONSTRAINT Cirugia_PK PRIMARY KEY ( Id_Cirugia ) ;


CREATE TABLE Citas_Medicas
  (
    id       NUMBER NOT NULL ,
    Caracter CHAR (1) NOT NULL ,
    Fecha    DATE NOT NULL ,
    Hora     DATE NOT NULL ,
    tipo     VARCHAR2 (50) ,
    Sintomas INTEGER NOT NULL ,
    Medico   NUMBER NOT NULL ,
    Paciente NUMBER NOT NULL
  ) ;
ALTER TABLE Citas_Medicas ADD CONSTRAINT Citas_Medicas_PK PRIMARY KEY ( id ) ;


CREATE TABLE Ciudad
  (
    Id_Ciudad                    INTEGER NOT NULL ,
    Nombre                       VARCHAR2 (30) NOT NULL ,
    Departamento_Id_Departamento INTEGER NOT NULL
  ) ;
ALTER TABLE Ciudad ADD CONSTRAINT Ciudad_PK PRIMARY KEY ( Id_Ciudad ) ;


CREATE TABLE Departamento
  (
    Id_Departamento INTEGER NOT NULL ,
    Nombre          VARCHAR2 (30) NOT NULL ,
    Pais_Id_Pais    INTEGER NOT NULL
  ) ;
ALTER TABLE Departamento ADD CONSTRAINT Departamento_PK PRIMARY KEY ( Id_Departamento ) ;


CREATE TABLE Detalle_Cirugia
  (
    Fecha_Cirugia          DATE NOT NULL ,
    Descripcion            VARCHAR2 (2000) NOT NULL ,
    Orden_Cirugia_Id_Orden INTEGER NOT NULL ,
    Medico                 NUMBER NOT NULL
  ) ;


CREATE TABLE Detalle_Examen
  (
    Fecha_Realizacion     DATE NOT NULL ,
    Descripcion           VARCHAR2 (2000) NOT NULL ,
    Orden_Examen_Id_Orden INTEGER NOT NULL ,
    Medico                NUMBER NOT NULL
  ) ;


CREATE TABLE EPS
  (
    Id_Eps               INTEGER NOT NULL ,
    Nombre               VARCHAR2 (30) NOT NULL ,
    Direccion            VARCHAR2 (30) NOT NULL ,
    Telefono             VARCHAR2 (15) NOT NULL ,
    Tipo_Eps_Id_Tipo_Eps INTEGER NOT NULL
  ) ;
ALTER TABLE EPS ADD CONSTRAINT EPS_PK PRIMARY KEY ( Id_Eps ) ;


CREATE TABLE Enfermedad
  (
    Id_Enfermedad        INTEGER NOT NULL ,
    Nombre               VARCHAR2 (50) NOT NULL ,
    Sintomas_Id_Sintomas INTEGER NOT NULL ,
    Tratamiento          INTEGER NOT NULL
  ) ;
ALTER TABLE Enfermedad ADD CONSTRAINT Enfermedad_PK PRIMARY KEY ( Id_Enfermedad ) ;


CREATE TABLE Examen
  (
    Id_Examen   INTEGER NOT NULL ,
    Nombre      VARCHAR2 (30) NOT NULL ,
    Descripcion VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE Examen ADD CONSTRAINT Examen_PK PRIMARY KEY ( Id_Examen ) ;


CREATE TABLE Farmaceutico
  (
    id                  NUMBER NOT NULL ,
    Tarjeta_Profesional VARCHAR2 (30) NOT NULL
  ) ;
ALTER TABLE Farmaceutico ADD CONSTRAINT Farmaceutico_PK PRIMARY KEY ( id ) ;


CREATE TABLE Farmacia
  (
    Id_Farmacia      INTEGER NOT NULL ,
    Nombre           VARCHAR2 (30) NOT NULL ,
    Direccion        VARCHAR2 (30) NOT NULL ,
    Telefono         VARCHAR2 (15) NOT NULL ,
    Ciudad_Id_Ciudad INTEGER NOT NULL ,
    Farmaceutico     NUMBER NOT NULL
  ) ;
ALTER TABLE Farmacia ADD CONSTRAINT Farmacia_PK PRIMARY KEY ( Id_Farmacia ) ;


CREATE TABLE Horario
  (
    id          INTEGER NOT NULL ,
    Dia         VARCHAR2 (15) NOT NULL ,
    Hora_Inicio DATE NOT NULL ,
    Hora_Final  DATE NOT NULL ,
    Medico      NUMBER NOT NULL
  ) ;
ALTER TABLE Horario ADD CONSTRAINT Horario_PK PRIMARY KEY ( id ) ;


CREATE TABLE Hospitalizacion
  (
    Id_Hospitalizacion INTEGER NOT NULL ,
    Fecha_Entrada      DATE NOT NULL ,
    Fecha_Salida       DATE ,
    Camas_Id_Cama      INTEGER NOT NULL ,
    Medico             NUMBER NOT NULL
  ) ;
ALTER TABLE Hospitalizacion ADD CONSTRAINT Hospitalizacion_PK PRIMARY KEY ( Id_Hospitalizacion ) ;


CREATE TABLE Medicamento
  (
    Id_Medicamento       INTEGER NOT NULL ,
    Nombre               VARCHAR2 (50) NOT NULL ,
    Descripcion          VARCHAR2 (100) NOT NULL ,
    Fecha_Vencimiento    DATE NOT NULL ,
    Cantidad             INTEGER NOT NULL ,
    Farmacia_Id_Farmacia INTEGER NOT NULL ,
    Tipo_Medicamento     INTEGER NOT NULL
  ) ;
ALTER TABLE Medicamento ADD CONSTRAINT Medicamento_PK PRIMARY KEY ( Id_Medicamento ) ;


CREATE TABLE Medico
  (
    id                  NUMBER NOT NULL ,
    Estado              CHAR (1) NOT NULL ,
    Tarjeta_Profesional VARCHAR2 (100)
  ) ;
ALTER TABLE Medico ADD CONSTRAINT Medico_PK PRIMARY KEY ( id ) ;


CREATE TABLE Orden_Cirugia
  (
    Id_Orden              INTEGER NOT NULL ,
    Citas_Medicas_Id_Cita NUMBER NOT NULL ,
    Cirugia_Id_Cirugia    INTEGER NOT NULL
  ) ;
ALTER TABLE Orden_Cirugia ADD CONSTRAINT Orden_Cirugia_PK PRIMARY KEY ( Id_Orden ) ;


CREATE TABLE Orden_Examen
  (
    Id_Orden              INTEGER NOT NULL ,
    Citas_Medicas_Id_Cita NUMBER NOT NULL ,
    Examen                INTEGER NOT NULL
  ) ;
ALTER TABLE Orden_Examen ADD CONSTRAINT Orden_Medica_PK PRIMARY KEY ( Id_Orden ) ;


CREATE TABLE Orden_Hospitalizacion
  (
    Id_Orden              INTEGER NOT NULL ,
    Citas_Medicas_Id_Cita NUMBER NOT NULL ,
    Hospitalizacion       INTEGER NOT NULL
  ) ;
ALTER TABLE Orden_Hospitalizacion ADD CONSTRAINT Orden_Hospitalizacion_PK PRIMARY KEY ( Id_Orden ) ;


CREATE TABLE Orden_Medicamento
  (
    Id            INTEGER NOT NULL ,
    fecha         DATE NOT NULL ,
    posologia     VARCHAR2 (300) NOT NULL ,
    estado        CHAR (1) NOT NULL ,
    Citas_Medicas NUMBER NOT NULL
  ) ;
ALTER TABLE Orden_Medicamento ADD CONSTRAINT Orden_Medicamento_PK PRIMARY KEY ( Id ) ;


CREATE TABLE Paciente
  ( id NUMBER NOT NULL , EPS_Id_Eps INTEGER NOT NULL
  ) ;
ALTER TABLE Paciente ADD CONSTRAINT Paciente_PK PRIMARY KEY ( id ) ;


CREATE TABLE Pais
  (
    Id_Pais INTEGER NOT NULL ,
    Nombre  VARCHAR2 (30) NOT NULL
  ) ;
ALTER TABLE Pais ADD CONSTRAINT Pais_PK PRIMARY KEY ( Id_Pais ) ;


CREATE TABLE Persona
  (
    id                    NUMBER NOT NULL ,
    Numero_Identificacion VARCHAR2 (15) NOT NULL ,
    Tipo_Identificacion   VARCHAR2 (20) NOT NULL ,
    Nombre                VARCHAR2 (20) NOT NULL ,
    Apellido              VARCHAR2 (20) NOT NULL ,
    Fecha_Nacimiento      DATE NOT NULL ,
    Genero                VARCHAR2 (50) NOT NULL ,
    Correo                VARCHAR2 (50) NOT NULL ,
    password              VARCHAR2 (200) NOT NULL ,
    telefono              VARCHAR2 (20) NOT NULL ,
    Ciudad                INTEGER NOT NULL
  ) ;
ALTER TABLE Persona ADD CONSTRAINT Persona_PK PRIMARY KEY ( id ) ;
ALTER TABLE Persona ADD CONSTRAINT Persona__UN UNIQUE ( Numero_Identificacion , Tipo_Identificacion ) ;
ALTER TABLE Persona ADD CONSTRAINT Persona__UNv1 UNIQUE ( Correo ) ;
ALTER TABLE Persona ADD CONSTRAINT Persona__UNv2 UNIQUE ( telefono ) ;


CREATE TABLE PersonaRol
  ( Persona NUMBER NOT NULL , Rol NUMBER NOT NULL
  ) ;
ALTER TABLE PersonaRol ADD CONSTRAINT PersonaRol_PK PRIMARY KEY ( Rol, Persona ) ;


CREATE TABLE Quirofano
  (
    Id_Quirofano INTEGER NOT NULL ,
    Descripcion  VARCHAR2 (20) NOT NULL ,
    Estado       CHAR (1) NOT NULL
  ) ;
ALTER TABLE Quirofano ADD CONSTRAINT Quirofano_PK PRIMARY KEY ( Id_Quirofano ) ;


CREATE TABLE Rol
  ( id NUMBER NOT NULL , nombre VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE Rol ADD CONSTRAINT Rol_PK PRIMARY KEY ( id ) ;


CREATE TABLE Sintomas
  (
    Id_Sintomas INTEGER NOT NULL ,
    Nombre      VARCHAR2 (50) NOT NULL ,
    Descripcion VARCHAR2 (100)
  ) ;
ALTER TABLE Sintomas ADD CONSTRAINT Sintomas_PK PRIMARY KEY ( Id_Sintomas ) ;


CREATE TABLE Tipo_Eps
  (
    Id_Tipo_Eps INTEGER NOT NULL ,
    Tipo_Eps    VARCHAR2 (20) NOT NULL
  ) ;
ALTER TABLE Tipo_Eps ADD CONSTRAINT Tipo_Eps_PK PRIMARY KEY ( Id_Tipo_Eps ) ;


CREATE TABLE Tipo_Medicamento
  (
    Id_Tipo_Medicamento INTEGER NOT NULL ,
    Nombre              VARCHAR2 (20) NOT NULL ,
    Descripcion         VARCHAR2 (100) NOT NULL
  ) ;
ALTER TABLE Tipo_Medicamento ADD CONSTRAINT Tipo_Medicamento_PK PRIMARY KEY ( Id_Tipo_Medicamento ) ;


CREATE TABLE Tratamiento
  (
    Id_Tratamiento INTEGER NOT NULL ,
    Nombre         VARCHAR2 (50) NOT NULL ,
    Descripcion    VARCHAR2 (100)
  ) ;
ALTER TABLE Tratamiento ADD CONSTRAINT Tratamiento_PK PRIMARY KEY ( Id_Tratamiento ) ;


CREATE TABLE detalle_ordenmedicamento
  (
    Orden_Medicamento INTEGER NOT NULL ,
    Medicamento       INTEGER NOT NULL ,
    cantidad          NUMBER NOT NULL ,
    estado            CHAR (1) NOT NULL
  ) ;
ALTER TABLE detalle_ordenmedicamento ADD CONSTRAINT detalle_ordenmedicamente_PK PRIMARY KEY ( Medicamento, Orden_Medicamento ) ;


ALTER TABLE AccesoRol ADD CONSTRAINT AccesoRol_Acceso_FK FOREIGN KEY ( Acceso ) REFERENCES Acceso ( id ) ;

ALTER TABLE Orden_Cirugia ADD CONSTRAINT Cirugia_FK FOREIGN KEY ( Cirugia_Id_Cirugia ) REFERENCES Cirugia ( Id_Cirugia ) ;

ALTER TABLE Cirugia ADD CONSTRAINT Cirugia_Quirofano_FK FOREIGN KEY ( Quirofano ) REFERENCES Quirofano ( Id_Quirofano ) ;

ALTER TABLE Orden_Examen ADD CONSTRAINT Citas_Medicas_FK FOREIGN KEY ( Citas_Medicas_Id_Cita ) REFERENCES Citas_Medicas ( id ) ;

ALTER TABLE Orden_Hospitalizacion ADD CONSTRAINT Citas_Medicas_FKv2 FOREIGN KEY ( Citas_Medicas_Id_Cita ) REFERENCES Citas_Medicas ( id ) ;

ALTER TABLE Orden_Medicamento ADD CONSTRAINT Citas_Medicas_FKv3 FOREIGN KEY ( Citas_Medicas ) REFERENCES Citas_Medicas ( id ) ;

ALTER TABLE Citas_Medicas ADD CONSTRAINT Citas_Medicas_Medico_FK FOREIGN KEY ( Medico ) REFERENCES Medico ( id ) ;

ALTER TABLE Citas_Medicas ADD CONSTRAINT Citas_Medicas_Paciente_FK FOREIGN KEY ( Paciente ) REFERENCES Paciente ( id ) ;

ALTER TABLE Citas_Medicas ADD CONSTRAINT Citas_Medicas_Sintomas_FK FOREIGN KEY ( Sintomas ) REFERENCES Sintomas ( Id_Sintomas ) ;

ALTER TABLE Persona ADD CONSTRAINT Ciudad FOREIGN KEY ( Ciudad ) REFERENCES Ciudad ( Id_Ciudad ) ;

ALTER TABLE Ciudad ADD CONSTRAINT Ciudad_Departamento_FK FOREIGN KEY ( Departamento_Id_Departamento ) REFERENCES Departamento ( Id_Departamento ) ;

ALTER TABLE Departamento ADD CONSTRAINT Departamento_Pais_FK FOREIGN KEY ( Pais_Id_Pais ) REFERENCES Pais ( Id_Pais ) ;

ALTER TABLE Detalle_Cirugia ADD CONSTRAINT Detalle_Cirugia_Medico_FK FOREIGN KEY ( Medico ) REFERENCES Medico ( id ) ;

ALTER TABLE EPS ADD CONSTRAINT EPS_Tipo_Eps_FK FOREIGN KEY ( Tipo_Eps_Id_Tipo_Eps ) REFERENCES Tipo_Eps ( Id_Tipo_Eps ) ;

ALTER TABLE Enfermedad ADD CONSTRAINT Enfermedad_Sintomas_FK FOREIGN KEY ( Sintomas_Id_Sintomas ) REFERENCES Sintomas ( Id_Sintomas ) ;

ALTER TABLE Enfermedad ADD CONSTRAINT Enfermedad_Tratamiento_FK FOREIGN KEY ( Tratamiento ) REFERENCES Tratamiento ( Id_Tratamiento ) ;

ALTER TABLE Farmacia ADD CONSTRAINT Farmacia_Ciudad_FK FOREIGN KEY ( Ciudad_Id_Ciudad ) REFERENCES Ciudad ( Id_Ciudad ) ;

ALTER TABLE Farmacia ADD CONSTRAINT Farmacia_Farmaceutico_FK FOREIGN KEY ( Farmaceutico ) REFERENCES Farmaceutico ( id ) ;

ALTER TABLE Horario ADD CONSTRAINT Horario_Medico_FK FOREIGN KEY ( Medico ) REFERENCES Medico ( id ) ;

ALTER TABLE Hospitalizacion ADD CONSTRAINT Hospitalizacion_Camas_FK FOREIGN KEY ( Camas_Id_Cama ) REFERENCES Camas ( Id_Cama ) ;

ALTER TABLE Orden_Hospitalizacion ADD CONSTRAINT Hospitalizacion_FK FOREIGN KEY ( Hospitalizacion ) REFERENCES Hospitalizacion ( Id_Hospitalizacion ) ;

ALTER TABLE Hospitalizacion ADD CONSTRAINT Hospitalizacion_Medico_FK FOREIGN KEY ( Medico ) REFERENCES Medico ( id ) ;

ALTER TABLE detalle_ordenmedicamento ADD CONSTRAINT Medicamento_FK FOREIGN KEY ( Medicamento ) REFERENCES Medicamento ( Id_Medicamento ) ;

ALTER TABLE Medicamento ADD CONSTRAINT Medicamento_Farmacia_FK FOREIGN KEY ( Farmacia_Id_Farmacia ) REFERENCES Farmacia ( Id_Farmacia ) ;

ALTER TABLE Detalle_Examen ADD CONSTRAINT Medico_FK FOREIGN KEY ( Medico ) REFERENCES Medico ( id ) ;

ALTER TABLE Medico ADD CONSTRAINT Medico_Persona_FK FOREIGN KEY ( id ) REFERENCES Persona ( id ) ;

ALTER TABLE Orden_Cirugia ADD CONSTRAINT Orden_Cirugia_Citas_Medicas_FK FOREIGN KEY ( Citas_Medicas_Id_Cita ) REFERENCES Citas_Medicas ( id ) ;

ALTER TABLE Detalle_Cirugia ADD CONSTRAINT Orden_Cirugia_FK FOREIGN KEY ( Orden_Cirugia_Id_Orden ) REFERENCES Orden_Cirugia ( Id_Orden ) ;

ALTER TABLE Detalle_Examen ADD CONSTRAINT Orden_Examen_FK FOREIGN KEY ( Orden_Examen_Id_Orden ) REFERENCES Orden_Examen ( Id_Orden ) ;

ALTER TABLE Orden_Examen ADD CONSTRAINT Orden_Medica_Examen_FK FOREIGN KEY ( Examen ) REFERENCES Examen ( Id_Examen ) ;

ALTER TABLE detalle_ordenmedicamento ADD CONSTRAINT Orden_Medicamento_FK FOREIGN KEY ( Orden_Medicamento ) REFERENCES Orden_Medicamento ( Id ) ;

ALTER TABLE Paciente ADD CONSTRAINT Paciente_EPS_FK FOREIGN KEY ( EPS_Id_Eps ) REFERENCES EPS ( Id_Eps ) ;

ALTER TABLE Paciente ADD CONSTRAINT Paciente_Persona_FK FOREIGN KEY ( id ) REFERENCES Persona ( id ) ;

ALTER TABLE Farmaceutico ADD CONSTRAINT Persona_FK FOREIGN KEY ( id ) REFERENCES Persona ( id ) ;

ALTER TABLE PersonaRol ADD CONSTRAINT Persona_FKv2 FOREIGN KEY ( Persona ) REFERENCES Persona ( id ) ;

ALTER TABLE AccesoRol ADD CONSTRAINT Rol_FK FOREIGN KEY ( Rol ) REFERENCES Rol ( id ) ;

ALTER TABLE PersonaRol ADD CONSTRAINT Rol_FKv2 FOREIGN KEY ( Rol ) REFERENCES Rol ( id ) ;

ALTER TABLE Medicamento ADD CONSTRAINT Tipo_Medicamento_FK FOREIGN KEY ( Tipo_Medicamento ) REFERENCES Tipo_Medicamento ( Id_Tipo_Medicamento ) ;


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            33
-- CREATE INDEX                             0
-- ALTER TABLE                             72
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
