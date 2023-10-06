# RecuerDO_V2

Durante el desarrollo de la aplicación Recuerdo tuve varios desafíos que requerían soluciones creativas y técnicas. Aquí están algunos de los desafíos clave que enfrentamos y cómo los resolvimos:

### 1. Implementación de la base de datos Room

**Desafío:** La gestión de datos de las tareas requería una base de datos local para almacenar las tareas de los usuarios de manera eficiente. Para eso se implementó la base de datos utilizando la biblioteca Room de Android.

**Solución:** Creamos una entidad Task y una interfaz TaskDao para definir las operaciones de base de datos. Luego, configuramos una base de datos Room que permitía el acceso a los datos de manera segura y eficiente. Esto nos permitió almacenar y recuperar las tareas de manera confiable.


### 2. Administración de elementos en un RecyclerView

**Desafío:** Mostrar una lista de tareas en un RecyclerView es bastante sencillo, requería la implementación de un adaptador eficiente que pudiera manejar la actualización de elementos y eventos de clic, el problema surgía al conectar estas dos cosas con el view model.

**Solución:** Implementamos un adaptador personalizado que extendía ListAdapter y utilizaba DiffUtil para calcular las diferencias entre las listas antes y después de una actualización y sobre todo se crearon dos consumer para manejar las acciones tanto de eliminar una tarea como de marcarla como realizada. Esto mejoró significativamente el rendimiento al actualizar elementos en el RecyclerView.

## Características Adicionales

- **Vista en modo paisaje:** La aplicación es compatible con la vista en modo paisaje, lo que permite a los usuarios ver sus tareas en dos columnas en dispositivos en modo horizontal, para esto se usó un gridLayout dentro del RecyclerView con un contador span.
