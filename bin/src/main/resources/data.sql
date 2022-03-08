
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('luis', '12345678');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('erik', '12345678');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('sergio', '12345678');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('alejandro', '12345678');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('moha', '12345678');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('david', '12345678');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('jessica', '12345678');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('salma', '12345678');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('christopher', '12345678');
INSERT INTO usuario (nombre_usuario, contrasena) VALUES ('marcos', '12345678');

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

INSERT INTO tarea (id_tarea, id_proyecto, titulo, descripcion, prioridad, estado) VALUES ('1', '1', 'Implementar servicios', 'Desarrollar los servicios ', 'MEDIO', 'Preparada');
INSERT INTO tarea (id_tarea, id_proyecto, titulo, descripcion, prioridad, estado) VALUES ('2', '1', 'Spring security', 'Configurar login', 'ALTA', 'Finalizada');
INSERT INTO tarea (id_tarea, id_proyecto, titulo, descripcion, prioridad, estado) VALUES ('3', '1', 'Implementar modelo', 'Realizar las relaciones con hibernate', 'MEDIA', 'En revisión');
INSERT INTO tarea (id_tarea, id_proyecto, titulo, descripcion, prioridad, estado) VALUES ('4', '1', 'Implementar templates', 'Implmentar Thimeleaf en los templates', 'BAJA', 'En Curso');
INSERT INTO tarea (id_tarea, id_proyecto, titulo, descripcion, prioridad, estado) VALUES ('5', '1', 'UI', 'diseñar bonito', 'BAJA', 'Preparada');

INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '1');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '2');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '3');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '4');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '5');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '6');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '7');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '8');
INSERT INTO proyecto_usuario (id_proyecto, id_usuario) VALUES ('1', '9');

