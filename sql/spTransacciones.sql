use Practica
go

-- Create a new stored procedure called 'Transacciones' in schema 'dbo'
-- Drop the stored procedure if it already exists
IF EXISTS (
SELECT *
    FROM INFORMATION_SCHEMA.ROUTINES
WHERE SPECIFIC_SCHEMA = N'dbo'
    AND SPECIFIC_NAME = N'Transacciones'
    AND ROUTINE_TYPE = N'PROCEDURE'
)
DROP PROCEDURE dbo.Transacciones
GO

-- selecciona la informacion de las transacciones/movimientos para una sucursal
-- se asume que el usuario que llama este sp tiene el permiso (capa logica se encargda de eso)
-- !!mala praxis, uno debería de verifircar los permisos acá también
CREATE PROCEDURE dbo.Transacciones
    @sucursal VARCHAR(120)
AS
    select S.Name as Sucursal, S.Phone as Telefono, M.Descripcion, M.Fecha, M.Monto
    from [dbo].[Movimiento] M INNER JOIN [dbo].[Rep_Sucursal] S on M.IdSucursal = S.SucursalId
    where S.Enabled = 0 and S.Name = @sucursal and M.Deleted = 0
GO

-- example to execute the stored procedure we just created
EXECUTE dbo.Transacciones 1
GO