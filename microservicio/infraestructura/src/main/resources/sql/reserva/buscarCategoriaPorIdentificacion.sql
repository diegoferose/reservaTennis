SELECT categoria FROM AFILIADO WHERE identificacion_usuario = :identificacion
UNION ALL
SELECT 'X'
WHERE NOT EXISTS (SELECT 1 FROM AFILIADO WHERE identificacion_usuario = :identificacion);