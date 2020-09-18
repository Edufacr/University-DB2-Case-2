USE Practica
go

-- Create a new stored procedure called 'spLogin' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'spLogin'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.spLogin
GO

-- Proc para verificar las credenciales del login de un usuario
-- Retorna error si no es login valido o la lista de permisos de ser valido
CREATE PROCEDURE dbo.spLogin
    @username NCHAR(100),
    @pass VARCHAR(100)
AS
    BEGIN
        DECLARE @uid int;
        select @uid = Id from [dbo].[Usuario]
        where Deleted=0 and Email = @username and PassHash = HASHBYTES('SHA2_256',@pass)
        IF @uid is NULL
            THROW 50410,'Login Invalido',1;
        ELSE
        BEGIN
            Select distinct P.Codigo, P.Descripcion
            from [dbo].[Permiso] P INNER JOIN [dbo].[PermisosXUsuario] PU on P.Id = PU.IdPermiso
            where PU.IdUsuario = @uid and P.Deleted = 0
            UNION
            SELECT distinct P.Codigo, P.Descripcion
            from [dbo].[Permiso] P INNER JOIN [dbo].[PermisosXRol] PR on P.Id = PR.IdPermiso
            INNER JOIN [dbo].[RolXUsuario] RU ON RU.IdRol = PR.IdRol
            WHERE RU.IdUsuario = @uid and RU.Deleted = 0
        END
    END
GO

--insert into [dbo].[Usuario] (Nombre,Apellidos,PassHash,Email,Deleted)
--VALUEs ('Daniel','Mabo',HASHBYTES('SHA2_256','pass'),'mabo.daniel',0)

--insert into CategoriaPermiso (Descripcion,Deleted)
--values ('Crear',0), ('Destruir',0)
--GO

--insert into Permiso (Codigo,Descripcion,Deleted,IdCategoria)
--VALUEs (1,'Permiso P1',0,1),(2,'Permiso P2',0,1),(3,'Permiso R1',0,2),(4,'Permiso R2',0,2),(5,'Permiso P3',0,1)
--GO

--INSERT into PermisosXUsuario (IdPermiso,IdUsuario,Deleted,PostTime)
--VALUEs (1,1,0,GETDATE()),(2,1,0,GETDATE()),(4,1,0,GETDATE())


-- example to execute the stored procedure we just created
EXECUTE dbo.spLogin 'mabo.daniel', 'pass'
GO
