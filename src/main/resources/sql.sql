DROP DATABASE aulaspringrest
GO
CREATE DATABASE aulaspringrest
GO
USE aulaspringrest
GO
CREATE TABLE depto (
codigo		INT				NOT NULL,
nome		VARCHAR(30)		NOT NULL
PRIMARY KEY (codigo)
)
GO
CREATE TABLE usuario (
id			INT										NOT NULL,
nome		VARCHAR(100)							NOT NULL,
login		CHAR(8)			CHECK (LEN(login) = 8)	NOT NULL,
senha		CHAR(8)			DEFAULT('123mudar')		NOT NULL,
depto		INT										NOT NULL
PRIMARY KEY(id)
FOREIGN KEY(depto) REFERENCES depto(codigo)
)

EXEC sp_help depto
EXEC sp_help usuario

SELECT * FROM depto
SELECT * FROM usuario

INSERT INTO depto(codigo, nome) VALUES (1, 'DTI')
INSERT INTO usuario(id, nome, login, senha, depto) VALUES (1001, 'Fulano', 'Ful@empr', 'senhaful', 1)

CREATE PROCEDURE sp_valida_login(@login CHAR(8), @senha CHAR(8), @id INT OUTPUT)
AS
	DECLARE @cont			INT,
			@login_busca	INT
	SELECT @login_busca = COUNT(*) FROM usuario WHERE login = @login
	IF (@login_busca = 0)
	BEGIN
		SET @id = 0
	END
	ELSE
	BEGIN
		SELECT @cont = COUNT(*) FROM usuario WHERE login = @login AND senha = @senha
		IF (@cont = 1)
			SET @id = (SELECT id FROM usuario WHERE login = @login AND senha = @senha)
		ELSE
			SET @id = 0
	END 

DECLARE @ident INT
EXEC sp_valida_login 'Cic@empr', 'senhacic', @ident OUTPUT
PRINT @identt

CREATE FUNCTION fn_nome_depto(@codigo INT)
RETURNS @tabela TABLE (
codigo	INT,
nome	VARCHAR(100)
)
AS
BEGIN
	DECLARE @nome_depto		VARCHAR(30),
			@nome_comp		VARCHAR(100)
	SET @nome_depto = (SELECT nome FROM depto WHERE codigo = @codigo)
	IF (@nome_depto LIKE '%TI%')
	BEGIN
		SET @nome_comp = 'Departamento de Informática'
	END
	ELSE
	BEGIN
		IF (@nome_depto LIKE '%RH%')
		BEGIN
			SET @nome_comp = 'Departamento de Recursos Humanos'
		END
		ELSE
		BEGIN
			IF (@nome_depto LIKE '%Finan%')
			BEGIN
				SET @nome_comp = 'Departamento Financeiro'
			END
			ELSE
			BEGIN
				SET @nome_comp = @nome_depto
			END
		END
	END
	INSERT INTO @tabela VALUES (@codigo, @nome_comp)
	RETURN
END

SELECT codigo, nome FROM fn_nome_depto(1)