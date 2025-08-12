LibroApp
Aplicación Java que permite buscar libros por título usando la API de Open Library, almacenar libros y autores en memoria, y consultar información como autores vivos en un año o libros por idioma.

Funcionalidades
Buscar y agregar libros por título
Listar libros y autores almacenados
Consultar autores vivos en un año específico
Filtrar libros por idioma

Tecnologías
Java 17
Apache HttpClient 5
Gson para JSON
Maven para gestión de dependencias

Estructura
model: Clases Libro y Autor
repository: Base de datos en memoria (BaseDatos)
service: Lógica principal (LibroService)
util: Cliente HTTP para la API (ApiLibroClient)

Uso
El uso de la aplicación consiste en seguir el menú para buscar, listar y filtrar libros y autores.
