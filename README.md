# Bibliomante

## Apartado 3: operaciones de usuario

Los siguientes endpoints permiten solicitar y consultar préstamos, así como
gestionar inscripciones en eventos. Las solicitudes identifican al usuario
registrado mediante su `usuarioId`.

### Préstamos

- `POST /prestamos`: solicita un préstamo. Ejemplo:

  ```json
  {
    "usuarioId": 1,
    "libroId": 2,
    "fechaPrevistaDevolucion": "2026-11-12"
  }
  ```

  La fecha de inicio y el estado `ACTIVO` se asignan al crear el préstamo. La
  solicitud se rechaza si el usuario o el libro no existen, no hay ejemplares,
  se alcanza el máximo de préstamos activos o la fecha de devolución no es
  posterior a hoy.
- `GET /prestamos/usuario/{usuarioId}`: consulta los préstamos del usuario.

### Eventos

- `GET /eventos/disponibles`: lista eventos futuros que aún tienen plazas.
- `POST /eventos/{eventoId}/inscripciones`: inscribe al usuario. Ejemplo:

  ```json
  {
    "usuarioId": 1
  }
  ```

- `DELETE /eventos/{eventoId}/inscripciones/{usuarioId}`: cancela la
  inscripción del usuario.

Las inscripciones duplicadas y las que superan el aforo se rechazan.