SELECT count(1)
FROM RESERVA
WHERE (HORA_INICIO <= :horaInicio AND HORA_FIN > :horaInicio )
      OR
      (HORA_INICIO < :horaFin AND HORA_FIN >= :horaFin)
      OR
      (HORA_INICIO >= :horaInicio AND HORA_INICIO < :horaFin)