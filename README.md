# TAYRA - Modulo de Inventario Java

## Descripcion
Modulo de inventario desarrollado en Java con JDBC y MySQL.
Permite registrar, consultar, actualizar y eliminar productos.

## Tecnologias
- Java JDK 21
- JDBC
- MySQL
- MySQL Connector/J

## Estructura
- src/Main.java: menu principal.
- src/com/tayra/config/Conexion.java: conexion a MySQL.
- src/com/tayra/model/Producto.java: modelo de producto.
- src/com/tayra/dao/ProductoDAO.java: operaciones CRUD.
- lib/: conector JDBC.

## Base de datos
Crear una base de datos llamada tayra_java_test y una tabla productos
con los campos id, ean, nombre, descripcion, cantidad y precio.

## Configuracion
La conexion local esta configurada para localhost:3306,
usuario root y contrasena vacia. Cambiar estos datos si tu
instalacion de MySQL utiliza otras credenciales.

## Compilar
Desde la carpeta principal del proyecto:

```powershell
javac -encoding UTF-8 -cp "lib\mysql-connector-j-26.10.0.jar" -d out src\com\tayra\config\Conexion.java src\com\tayra\model\Producto.java src\com\tayra\dao\ProductoDAO.java src\Main.java
```

## Ejecutar
```powershell
java -cp "out;lib\mysql-connector-j-26.10.0.jar" Main
```

## Funcionalidades CRUD
1. Crear productos.
2. Consultar productos.
3. Actualizar productos.
4. Eliminar productos con confirmacion.
