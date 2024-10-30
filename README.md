# Feedback3EventosFranLopez

Una aplicación de Android que gestiona una biblioteca personal de novelas, permitiendo almacenar y organizar información sobre cada novela, incluyendo si es favorita o no. La aplicación implementa almacenamiento en SQLite, permite configuraciones de usuario, y presenta un modo oscuro.

## Características

- **Gestión de novelas**: Añade, visualiza y marca como favoritas novelas en una biblioteca personal.
- **Modo oscuro**: Ajustable en la configuración de la aplicación.
- **Configuración de preferencias**: Utiliza SharedPreferences para almacenar configuraciones del usuario.
- **Interfaz amigable**: Incluye un `RecyclerView` para listar novelas, con una interfaz intuitiva.
- **Persistencia de datos**: Almacenamiento en base de datos SQLite.

## Estructura del Proyecto

El proyecto está compuesto por varias actividades:

- **MainActivity**: Pantalla principal que lista las novelas y permite acceder a otras funcionalidades.
- **AddNovelActivity**: Permite al usuario agregar una nueva novela.
- **SettingsActivity**: Configura las preferencias de usuario, incluyendo el modo oscuro.

## Instalación

1. Clona el repositorio:
    ```bash
    git clone [https://github.com/franlopezsoto/Feedback3EventosFranL-pez]
    ```
2. Abre el proyecto en Android Studio.
3. Compila y ejecuta la aplicación en un dispositivo o emulador Android con SDK versión mínima 21.

## Dependencias

El proyecto utiliza varias bibliotecas de Android:
- `androidx.appcompat:appcompat`
- `androidx.recyclerview:recyclerview`
- `androidx.constraintlayout:constraintlayout`

Estas bibliotecas se manejan a través de los archivos `build.gradle`.
