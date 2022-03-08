DROP DATABASE IF EXISTS examen_kanban;
CREATE DATABASE examen_kanban;
USE examen_kanban;

CREATE TABLE usuario (
id_usuario  BIGINT PRIMARY KEY AUTO_INCREMENT,
nombre_usuario   VARCHAR(60) UNIQUE,
contrasena VARCHAR(60) NOT NULL
);

CREATE TABLE rol (
id_rol   BIGINT PRIMARY KEY AUTO_INCREMENT,
nombre_rol    VARCHAR(60) UNIQUE
);

CREATE TABLE proyecto (
id_proyecto   BIGINT PRIMARY KEY AUTO_INCREMENT,
nombre    VARCHAR(60) UNIQUE,
descripcion  VARCHAR(400)
);

CREATE TABLE tarea (
id_tarea   BIGINT PRIMARY KEY AUTO_INCREMENT,
id_proyecto    BIGINT NOT NULL,
titulo  VARCHAR(60),
descripcion  VARCHAR(300),
prioridad  VARCHAR(10),
estado VARCHAR(15),
CONSTRAINT tarea_proyecto FOREIGN KEY (id_proyecto) REFERENCES proyecto (id_proyecto)
);

CREATE TABLE usuario_rol (
id_usuario BIGINT NOT NULL,
   id_rol BIGINT NOT NULL,
      PRIMARY KEY
   (
      id_usuario,
      id_rol
   ),
   CONSTRAINT FK_USUARIO_ROL_IDUSUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON DELETE CASCADE,
   CONSTRAINT FK_USUARIO_ROL_IDROL FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
);

CREATE TABLE proyecto_usuario (
   id_proyecto BIGINT NOT NULL,
   id_usuario BIGINT NOT NULL,
      PRIMARY KEY
   (
      id_proyecto,
      id_usuario
   ),
   CONSTRAINT FK_PROYECTO_USUARIO_IDPROYECTO FOREIGN KEY (id_proyecto) REFERENCES proyecto (id_proyecto) ON DELETE CASCADE,
   CONSTRAINT FK_PROYECTO_USUARIO_IDUSUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
);


