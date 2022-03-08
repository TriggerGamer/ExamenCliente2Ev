/*
	La contraseña cifrada es: 12345
    Para todos los usuarios
*/

INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('luis', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('erick', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('sergio', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('alejandro', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('moha', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('david', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('jessica', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('salma', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('christopher', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('marcos', '$2a$10$1xHnGOubg.Bwpnfa9eiwbOjHZMpDuibWOJwducDqSr1R7K3jiSVpK');

INSERT INTO rol (id_rol, nombre_rol) VALUES ('1', 'manager');
INSERT INTO rol (id_rol, nombre_rol) VALUES ('2', 'trabajador');

insert into usuario_rol (id_usuario, id_rol ) values (1,1);
insert into usuario_rol (id_usuario, id_rol ) values (2,1);
insert into usuario_rol (id_usuario, id_rol ) values (3,1);
insert into usuario_rol (id_usuario, id_rol ) values (4,1);
insert into usuario_rol (id_usuario, id_rol ) values (5,1);
insert into usuario_rol (id_usuario, id_rol ) values (6,1);
insert into usuario_rol (id_usuario, id_rol ) values (7,1);
insert into usuario_rol (id_usuario, id_rol ) values (8,1);
insert into usuario_rol (id_usuario, id_rol ) values (9,1);
insert into usuario_rol (id_usuario, id_rol ) values (10,2);

INSERT INTO proyecto (id_proyecto, nombre, descripcion) VALUES ('1', 'Examen Entorno Servidor', 'Evaluar los conocimientos de desarrollo en el lado servidor');
INSERT INTO proyecto (id_proyecto, nombre, descripcion) VALUES ('2', 'Examen Entorno Cliente', 'Evaluar el mnaejo de Javascript y del DOM');
INSERT INTO proyecto (id_proyecto, nombre, descripcion) VALUES ('3', 'Diseño de interfaces', 'Conseguir que aprendan a diseñar una interfaz');
INSERT INTO proyecto (id_proyecto, nombre, descripcion) VALUES ('4', 'Fol', 'Aprender como ser tu propio jefe');
INSERT INTO proyecto (id_proyecto, nombre, descripcion) VALUES ('5', 'Ingles', 'Aprender que raton es mouse.');
INSERT INTO proyecto (id_proyecto, nombre, descripcion) VALUES ('6', 'FCT', 'Bienvenido a la vida de adulto');

INSERT INTO tarea (id_tarea, id_proyecto, id_usuario, titulo, descripcion, prioridad, estado) VALUES ('1', '1', '3' ,'Implementar servicios', 'Desarrollar los servicios ', 'MEDIA', 'Preparada');
INSERT INTO tarea (id_tarea, id_proyecto, id_usuario, titulo, descripcion, prioridad, estado) VALUES ('2', '1', '5', 'Spring security', 'Configurar login', 'ALTA', 'Finalizada');
INSERT INTO tarea (id_tarea, id_proyecto, id_usuario,  titulo, descripcion, prioridad, estado) VALUES ('3', '1', '2', 'Implementar modelo', 'Realizar las relaciones con hibernate', 'MEDIA', 'En revisión');
INSERT INTO tarea (id_tarea, id_proyecto, id_usuario, titulo, descripcion, prioridad, estado) VALUES ('4', '1', '4', 'Implementar templates', 'Implmentar Thimeleaf en los templates', 'BAJA', 'En Curso');
INSERT INTO tarea (id_tarea, id_proyecto, id_usuario, titulo, descripcion, prioridad, estado) VALUES ('5', '1', '6', 'UI', 'diseñar bonito', 'BAJA', 'Preparada');

INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '1'),('1', '2'),('1', '3'), ('1', '4'), ('1', '5'), ('1', '6'), ('1', '7'), ('1', '8'), ('1', '9');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('2', '1'),('2', '2'),('2', '3'), ('2', '4'), ('2', '5'), ('2', '6'), ('2', '7'), ('2', '8'), ('2', '9');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('3', '1'),('3', '2'),('3', '3'), ('3', '4'), ('3', '5'), ('3', '6'), ('3', '7'), ('3', '8'), ('3', '9');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('4', '1'),('4', '2'),('4', '3'), ('4', '4'), ('4', '5'), ('4', '6'), ('4', '7'), ('4', '8'), ('4', '9');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('5', '1'),('5', '2'),('5', '3'), ('5', '4'), ('5', '5'), ('5', '6'), ('5', '7'), ('5', '8'), ('5', '9');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('6', '1'),('6', '2'),('6', '3'), ('6', '4'), ('6', '5'), ('6', '6'), ('6', '7'), ('6', '8'), ('6', '9');

