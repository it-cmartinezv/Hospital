INSERT INTO PAIS (ID_PAIS, NOMBRE) VALUES ('1', 'Colombia');
INSERT INTO PAIS (ID_PAIS, NOMBRE) VALUES ('2', 'Estados Unidos');
INSERT INTO PAIS (ID_PAIS, NOMBRE) VALUES ('3', 'Alemania');
INSERT INTO PAIS (ID_PAIS, NOMBRE) VALUES ('4', 'Espania');

INSERT INTO DEPARTAMENTO (ID_DEPARTAMENTO, NOMBRE, PAIS_ID_PAIS) VALUES ('1', 'Quindio', '1');
INSERT INTO DEPARTAMENTO (ID_DEPARTAMENTO, NOMBRE, PAIS_ID_PAIS) VALUES ('2', 'Valle del cauca', '1');
INSERT INTO DEPARTAMENTO (ID_DEPARTAMENTO, NOMBRE, PAIS_ID_PAIS) VALUES ('3', 'Antioquia', '1');

INSERT INTO CIUDAD (ID_CIUDAD, NOMBRE, DEPARTAMENTO_ID_DEPARTAMENTO) VALUES ('1', 'Armenia', '1');
INSERT INTO CIUDAD (ID_CIUDAD, NOMBRE, DEPARTAMENTO_ID_DEPARTAMENTO) VALUES ('2', 'Calarca', '1');
INSERT INTO CIUDAD (ID_CIUDAD, NOMBRE, DEPARTAMENTO_ID_DEPARTAMENTO) VALUES ('3', 'Circasia', '1');
INSERT INTO CIUDAD (ID_CIUDAD, NOMBRE, DEPARTAMENTO_ID_DEPARTAMENTO) VALUES ('4', 'Cali', '2');
INSERT INTO CIUDAD (ID_CIUDAD, NOMBRE, DEPARTAMENTO_ID_DEPARTAMENTO) VALUES ('5', 'Medellin', '3');

INSERT INTO TIPO_EPS (ID_TIPO_EPS, TIPO_EPS) VALUES ('1', 'Subsidiada');
INSERT INTO TIPO_EPS (ID_TIPO_EPS, TIPO_EPS) VALUES ('2', 'Postpagada');
INSERT INTO TIPO_EPS (ID_TIPO_EPS, TIPO_EPS) VALUES ('3', 'SISBEN');

INSERT INTO EPS (ID_EPS, NOMBRE, DIRECCION, TELEFONO, TIPO_EPS_ID_TIPO_EPS) VALUES ('1', 'Cafesalud', 'Cra 20 #30-10', '7422145', '1');
INSERT INTO EPS (ID_EPS, NOMBRE, DIRECCION, TELEFONO, TIPO_EPS_ID_TIPO_EPS) VALUES ('2', 'EMI', 'Call 10 A # 54-13', '77433164', '2');
INSERT INTO EPS (ID_EPS, NOMBRE, DIRECCION, TELEFONO, TIPO_EPS_ID_TIPO_EPS) VALUES ('3', 'Pasvisalud', 'Cra 25 # 43 -57', '7466159', '3');

------------------------------------------------- MEDICOS -------------------------------------------------------------------

INSERT INTO PERSONA (ID, NUMERO_IDENTIFICACION, TIPO_IDENTIFICACION, NOMBRE, APELLIDO, FECHA_NACIMIENTO, GENERO, CORREO, PASSWORD, TELEFONO, CIUDAD) VALUES ('3', '1000', 'CEDULA_DE_CIUDADANIA', 'Diego', 'Valencia', TO_DATE('1978-10-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'HOMBRE', 'valencia@gmail.com', 'valencia123', '3214886789', '1');
INSERT INTO MEDICO (ID, ESTADO, TARJETA_PROFESIONAL) VALUES ('3', '1', '123456789');

--------------- ROLES NO CAMBIAR ------------------------------

INSERT INTO ROL (ID, NOMBRE) VALUES ('1', 'Administrador');
INSERT INTO ROL (ID, NOMBRE) VALUES ('2', 'Medico');
INSERT INTO ROL (ID, NOMBRE) VALUES ('3', 'Farmaceutico');
INSERT INTO ROL (ID, NOMBRE) VALUES ('4', 'Paciente');

--------------- PAGINAS DE ACCESO -----------------------------

INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (1,'Gestionar medicos', '/paginas/seguro/gestion-medicos.xhtml');
INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (2,'Gestionar pacientes', '/paginas/seguro/gestion-pacientes.xhtml');
INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (3,'Gestionar farmaceuticos', '/paginas/seguro/gestion-farmaceuticos.xhtml');
INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (4,'Gestionar administradores', '/paginas/seguro/gestion-administradores.xhtml');
INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (5,'Gestion eps', '/paginas/seguro/gestion-EPS.xhtml');
INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (6,'Gestionar farmacias', '/paginas/seguro/gestion-farmacia.xhtml');
INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (7,'Gestionar medicamentos', '/paginas/seguro/gestion-medicamentos.xhtml');
INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (8,'Gestionar cirugias', '/paginas/seguro/gestion-cirugia.xhtml');
INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (9,'Gestionar examenes', '/paginas/seguro/gestion-examen.xhtml');
INSERT INTO ACCESO (ID,NOMBRE, URL) VALUES (10,'Solicitar cita', '/paginas/publico/solicitar-cita.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('11', 'Citas Pendientes', '/paginas/seguro/citas-pendientes.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('12', 'Entrega Medicamento', '/paginas/seguro/gestion-entregaMedicamento.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('13', 'Orden Medicamento', '/paginas/seguro/gestion-ordenMedicamento.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('14', 'Citas canceladas', '/paginas/seguro/citas-canceladas.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('15', 'Citas atendidas', '/paginas/seguro/citas-atendidas.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('16', 'Panel paciente', '/paginas/seguro/paciente.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('17', 'Panel administrador', '/paginas/seguro/administrador.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('18', 'Panel medico', '/paginas/seguro/medico.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('19', 'Panel farmaceutico', '/paginas/seguro/farmaceutico.xhtml');
INSERT INTO ACCESO (ID, NOMBRE, URL) VALUES ('20', 'Ordenes medicamento', '/paginas/seguro/ordenes.xhtml');
--------------- ADMINISTRADOR -------------------------------------

INSERT INTO PERSONA (ID,NUMERO_IDENTIFICACION, TIPO_IDENTIFICACION, NOMBRE, APELLIDO, FECHA_NACIMIENTO, GENERO, CORREO, PASSWORD, TELEFONO, CIUDAD) VALUES ('1','1500000', 'CEDULA_DE_CIUDADANIA', 'Carlos', 'Martinez', TO_DATE('1994-05-05 03:26:09', 'YYYY-MM-DD HH24:MI:SS'), 'HOMBRE', 'admin@hospital.com', '123', '3213456789', '1');
INSERT INTO PERSONAROL (PERSONA, ROL) VALUES ('1', '1');

--------------- ASIGNAMOS LOS ACCESOS DEL ADMINISTRADOR (ID = 1) -----------

INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '1');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '2');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '3');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '4');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '5');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '6');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '7');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '8');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '9');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '10');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '11');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '12');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '13');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '14');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '15');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '16');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '17');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '18');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '19');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('1', '20');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('2', '11');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('2', '13');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('2', '14');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('2', '18');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('2', '15');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('3', '12');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('3', '19');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('3', '20');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('4', '10');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('4', '11');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('4', '14');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('4', '15');
INSERT INTO ACCESOROL (ROL, ACCESO) VALUES ('4', '16');


----------------------------------- SINTOMAS ------------------------------------------------

INSERT INTO SINTOMAS (ID, NOMBRE, DESCRIPCION) VALUES ('1', 'Ansiedad', 'Estado mental que se caracteriza por una gran inquietud, una intensa excitacióon y una extrema inseguridad.')

INSERT INTO SINTOMAS (ID, NOMBRE, DESCRIPCION) VALUES ('2', 'Fiebre', 'Aumento temporal en la temperatura del cuerpo en respuesta a alguna enfermedad o padecimiento.');

INSERT INTO SINTOMAS (ID, NOMBRE, DESCRIPCION) VALUES ('3', 'Vomitos', 'Salida, forzada o no, de los contenidos del estomago, que suben a traves del esofago para ser finalmente expulsados por la boca.');

INSERT INTO SINTOMAS (ID, NOMBRE, DESCRIPCION) VALUES ('4', 'Diarrea', 'Alteración intestinal que se caracteriza por la mayor frecuencia, fluidez y, a menudo, volumen de las deposiciones.');