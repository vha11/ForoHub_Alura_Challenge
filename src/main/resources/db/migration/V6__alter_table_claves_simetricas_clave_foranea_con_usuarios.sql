ALTER TABLE claves_simetricas
ADD CONSTRAINT fk_claves_simetricas_usuarios
FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
ON DELETE CASCADE;