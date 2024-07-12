CREATE TABLE topicos(
   id BIGINT NOT NULL AUTO_INCREMENT,
   titulo VARCHAR(100) NOT NULL UNIQUE,
   mensaje VARCHAR(300) NOT NULL UNIQUE,
   fecha_creacion DATETIME NOT NULL,
   estado TINYINT NOT NULL,
   autor_id BIGINT NOT NULL,
   curso_id BIGINT NOT NULL,

   PRIMARY KEY(id),

   CONSTRAINT fk_topicos_autor_id FOREIGN KEY(autor_id) REFERENCES usuarios(id),
   CONSTRAINT fk_topicos_curso_id FOREIGN KEY(curso_id) REFERENCES cursos(id)
    );