use Practica
go

-- Create a new stored procedure called 'UpdateSucursal' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'UpdateSucursal'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.UpdateSucursal
GO
-- Create the stored procedure in the specified schema
CREATE PROCEDURE dbo.UpdateSucursal
    @user NVARCHAR(100),
    @nombre VARCHAR(120) NULL = NULL,
    @phone VARCHAR(12) NULL = NULL,
    @email VARCHAR(120) null = NULL
AS
    declare @sid int;
    select @sid = S.SucursalId
    from [dbo].[Rep_Sucursal] S inner join [dbo].[Usuario] U on S.Administrador = U.Id
    where S.Enabled = 0 and U.Email = @user

    BEGIN TRANSACTION
    BEGIN TRY
        UPDATE [dbo].[Rep_Sucursal]
        set Name = ISNULL(@nombre,Name),
            Phone = ISNULL(@phone,Phone), 
            Email = ISNULL(@email,Email)
        WHERE SucursalId = @sid
        
        COMMIT
    END TRY
    BEGIN CATCH
        ROLLBACK;
        THROW 50505, 'Error al actualizar la informacion de la sucursal', 1;
    END CATCH

GO

-- example to execute the stored procedure we just created
EXECUTE dbo.UpdateSucursal 'mabo.daniel'
GO