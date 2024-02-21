# GameHubConnect - Documentación 

## Paquete: `com.pabloat.GameHubConnect.data.remote`

### `ApiService`

Interfaz que define las llamadas a la API para obtener videojuegos.

#### Métodos

-   `suspend fun getVideoJuegos(): List<VideoJuegoDTO>`: Método suspendido que realiza una llamada GET a la API para obtener una lista de videojuegos.
- 
### `RemoteVideojuegoDataSource`

Clase que utiliza `ApiService` para obtener datos de videojuegos de forma remota.
#### Constructor

-   `apiService: ApiService`: Instancia de `ApiService` para realizar llamadas a la API.

#### Métodos

-   `suspend fun getVideojuegos()`: Método suspendido que delega la llamada a `ApiService.getVideoJuegos()` para obtener una lista de videojuegos.

----------

### `RetrofitBuilder`

Objeto que proporciona una instancia de Retrofit configurada para realizar llamadas a la API.
#### Métodos

-   `private fun getRetrofit(): Retrofit`: Método privado que configura y retorna una instancia de `Retrofit`.

#### Propiedades

-   `val apiService: ApiService`: Proporciona una instancia de `ApiService` creada a partir de la configuración de Retrofit.

----------

### `VideoJuegoDTO` y `VideoJuegosDTO`

Clases de transferencia de datos (DTO) para la serialización y deserialización de datos de videojuegos obtenidos de la API.

#### Propiedades y Métodos

-   `VideoJuegoDTO`: Representa los datos de un videojuego individual.
-   `toVideojuego()`: Método de extensión que convierte `VideoJuegoDTO` a `Videojuego`, un modelo para la base de datos local.
-   `VideoJuegosDTO`: Contiene una lista de `VideoJuegoDTO`.
-   `toLocalList()`: Método de extensión que convierte una lista de `VideoJuegoDTO` en una lista de `Videojuego`, limitando los resultados a los primeros 5 elementos.
- 
## Paquete: `com.pabloat.GameHubConnect.data.local`

### `AppDataBase`

Base de datos local de la aplicación que gestiona el almacenamiento de videojuegos.

-   **Descripción**: Define la base de datos para la aplicación utilizando Room, incluyendo la versión de la base de datos y las entidades asociadas.
-   **Métodos Importantes**:
    -   `videojuegoDao()`: Devuelve la instancia de `LocalVideojuegoDao` para acceder a las operaciones de la base de datos.
    -   `getDatabase(context: Context)`: Método estático para obtener la instancia singleton de la base de datos.

### `LocalVideojuegoDao`

Interfaz que define las operaciones de la base de datos para la entidad `Videojuego`.

-   **Descripción**: Contiene métodos para insertar, actualizar, eliminar y consultar videojuegos en la base de datos local.
-   **Operaciones**:
    -   Consultas para obtener todos los videojuegos, por género, por título y por ID.
    -   Insertar, actualizar y eliminar videojuegos.

### `User`

Clase que representa un usuario dentro de la aplicación.

-   **Descripción**: Incluye información del usuario como ID, nombre de usuario, avatar, cita y rol.
-   **Método Importante**:
    -   `toMap()`: Convierte la información del usuario en un mapa para facilitar su uso, por ejemplo, en operaciones de base de datos o transferencia de datos.

### `Videojuego`

Clase que representa la entidad de un videojuego dentro de la base de datos.

-   **Descripción**: Contiene detalles sobre el videojuego como ID, título, descripción, género, plataforma, fecha de lanzamiento y valoración.
-   **Información**: Es la entidad principal que se almacena en la base de datos local y se utiliza para representar videojuegos en la aplicación.

### `VideojuegoDatasource`

Clase que actúa como intermediario entre la base de datos y la lógica de negocio de la aplicación.

-   **Descripción**: Proporciona métodos para realizar operaciones de base de datos como obtener todos los videojuegos, insertar, actualizar, eliminar videojuegos, y consultas específicas como por género o título.
-   **Uso**: Facilita la abstracción del acceso a datos, permitiendo a otras partes de la aplicación interactuar con la base de datos local sin conocer los detalles de implementación de Room.
- 
## Paquete: `com.pabloat.GameHubConnect.data`

## `VideojuegoRepository`

Esta clase se encarga de manejar las operaciones de datos para los videojuegos, consolidando las llamadas a las fuentes de datos local (`VideojuegoDatasource`) y remota (`RemoteVideojuegoDataSource`). Facilita la obtención, inserción, actualización y eliminación de videojuegos, abstrayendo la lógica específica de cada fuente de datos.

### Constructor

El constructor de `VideojuegoRepository` acepta dos parámetros:

-   `localds: VideojuegoDatasource`: La fuente de datos local para operaciones de base de datos con Room.
-   `remoteds: RemoteVideojuegoDataSource`: La fuente de datos remota para obtener videojuegos de una API externa.

### Métodos

-   `suspend fun getRemoteVideojuego(): List<Videojuego>`: Obtiene videojuegos de la fuente de datos remota, los convierte a entidades `Videojuego` locales y los inserta en la base de datos local.
    
-   `suspend fun getLocalVideojuego(): Flow<List<Videojuego>>`: Devuelve un `Flow` de la lista de todos los videojuegos almacenados localmente.
    
-   `suspend fun insertone(videojuego: Videojuego)`: Inserta un videojuego específico en la base de datos local. Utiliza `Log.d` para registrar la operación.
    
-   `suspend fun searchVideojuegoByTitle(title: String): Videojuego?`: Busca un videojuego por título en la base de datos local.
    
-   `suspend fun deleteVideojuego(id: Int)`: Elimina un videojuego específico de la base de datos local usando su ID.
    
-   `suspend fun updateVideojuego(videojuego: Videojuego)`: Actualiza un videojuego específico en la base de datos local.
    
-   `fun getAllGenres(): Flow<List<String>>`: Devuelve un `Flow` con todos los géneros únicos de los videojuegos almacenados localmente.
    
-   `fun getVideojuegosByGenre(genre: String): Flow<List<Videojuego>>`: Devuelve un `Flow` de videojuegos que coinciden con un género específico.
    
-   `suspend fun getVideojuegoById(videojuegoId: Int?): Videojuego?`: Obtiene un videojuego por su ID.
    

### Uso

`VideojuegoRepository` se utiliza en la capa de lógica de negocio (por ejemplo, en ViewModels) para interactuar con los datos de videojuegos sin tener que preocuparse directamente por la fuente de esos datos, permitiendo una arquitectura limpia y mantenible.

