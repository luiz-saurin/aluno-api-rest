-- Tipos de perfil
INSERT INTO PERFIL(tipo) VALUES('aluno');
INSERT INTO PERFIL(tipo) VALUES('admin');

-- Senha de autenticacao: 00000001
INSERT INTO USUARIO(nome, usuario, email, senha, telefone, sexo, data_nascimento, idade, perfil_id) VALUES('Gabriel Ramos','gabriel.ramos','gabriel.ramos@mail.com','$2a$10$heePuxCBPLz.l8pKkWPc2ucEwoT7uWeK8E0/8tV0Xcorhzm1085ka','98092774621','MASCULINO','19-12-1001', 35, 1);
-- Senha de autenticacao: 00000002
INSERT INTO USUARIO(nome, usuario, email, senha, telefone, sexo, data_nascimento, idade, perfil_id) VALUES('Alice Freitas','alice.freitas','alice.freitas@mail.com','$2a$10$WjSLKWeODF.mCvqmCD.1suOJqoySAVMZzHFbzJVhLItlOzCvzALZu','88600109802','FEMININO','12-08-1020', 22, 1);
-- Senha de autenticacao: 00000003
INSERT INTO USUARIO(nome, usuario, email, senha, telefone, sexo, data_nascimento, idade, perfil_id) VALUES('Maria Azevedo','maria.azevedo','maria.azevedo@mail.com','$2a$10$ZFCoWmk8SYer8RdFu6G3YOUkDk6ysjXBSn3NAhXjkZLxBmKVvIo9W','77927356473','FEMININO','23-12-0022', 28, 1);
-- Senha de autenticacao: 00000004
INSERT INTO USUARIO(nome, usuario, email, senha, telefone, sexo, data_nascimento, idade, perfil_id) VALUES('Hernanes Gimenez','hernanes.gimenez','hernanes.gimenez@mail.com','$2a$10$.tuOPuOt/VYNd3Rv8rFp4OLzmUcSLmWCK5z2qimg7xlRsWKfrp5Ku','90017850295','NAO_INFORMADO','19-12-0022', 30, 1);
-- Senha de autenticacao: 00000005
INSERT INTO USUARIO(nome, usuario, email, senha, telefone, sexo, data_nascimento, idade, perfil_id) VALUES('Antonio Valdes','antonio.valdes','antonio.valdes@mail.com','$2a$10$y8w9CAYAWHC5rZxiQZpK0.RK8/O9Rh8rM7x5nTAYoaY8xgAYCzLHa','10019802794','MASCULINO','12-04-1001', 47, 1);
-- Senha de autenticacao: 00000006
INSERT INTO USUARIO(nome, usuario, email, senha, telefone, sexo, data_nascimento, idade, perfil_id) VALUES('Claudio Peixoto','claudio.peixoto','claudio.peixoto@mail.com','$2a$10$acTJsDLWKSHWWWflnSEAoOWU9vgz8sU3ljrw.gGdG9xjdeCVppNka','80016002983','NAO_INFORMADO','31-03-1020', 32, 2);