use Practica
go

-- Create a new stored procedure called 'InfoSucursal' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'InfoSucursal'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.InfoSucursal
GO

-- retorna la informacion de una sucursal, asi como un agregado de montos
CREATE PROCEDURE dbo.InfoSucursal
    @user NVARCHAR(100)
AS
    select S.Name as Nombre, S.Phone as Telefono, S.Email as Email, SUM(M.Monto) as Monto
    from [dbo].[Movimiento] M INNER JOIN [dbo].[Rep_Sucursal] S on M.IdSucursal = S.SucursalId
    inner join [dbo].[Usuario] U on S.Administrador = U.Id
    where S.Enabled = 0 and U.Email = @user and M.Deleted = 0
    GROUP BY S.Name, S.Phone, S.Email
GO

-- example to execute the stored procedure we just created
EXECUTE dbo.InfoSucursal 'mabo.daniel'
GO
