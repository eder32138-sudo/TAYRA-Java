import com.tayra.dao.ProductoDAO;
import com.tayra.model.Producto;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);
    private static final ProductoDAO dao = new ProductoDAO();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n========== TAYRA - INVENTARIO ==========");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            try {
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> listar();
                    case 3 -> actualizar();
                    case 4 -> eliminar();
                    case 0 -> System.out.println("Programa finalizado.");
                    default -> System.out.println("Opcion no valida.");
                }
            } catch (SQLException e) {
                System.out.println("Error de base de datos: " + e.getMessage());
            }
        } while (opcion != 0);

        teclado.close();
    }

    private static void registrar() throws SQLException {
        System.out.println("\n--- REGISTRAR PRODUCTO ---");
        System.out.print("EAN: ");
        String ean = teclado.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine().trim();

        System.out.print("Descripcion: ");
        String descripcion = teclado.nextLine().trim();

        System.out.print("Cantidad: ");
        int cantidad = leerEntero();

        System.out.print("Precio: ");
        double precio = leerDecimal();

        if (ean.isEmpty() || nombre.isEmpty() || cantidad < 0 || precio < 0) {
            System.out.println("Datos invalidos. Revise los campos.");
            return;
        }

        Producto producto = new Producto(
            ean, nombre, descripcion, cantidad, precio
        );

        if (dao.crear(producto)) {
            System.out.println("Producto registrado correctamente.");
        }
    }

    private static void listar() throws SQLException {
        System.out.println("\n--- INVENTARIO DE TAYRA ---");
        List<Producto> productos = dao.listar();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto p : productos) {
            System.out.printf(
                "ID: %d | EAN: %s | Nombre: %s | Cantidad: %d | Precio: %.2f%n",
                p.getId(), p.getEan(), p.getNombre(),
                p.getCantidad(), p.getPrecio()
            );
        }
    }

    private static void actualizar() throws SQLException {
        System.out.print("ID del producto a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo EAN: ");
        String ean = teclado.nextLine().trim();

        System.out.print("Nuevo nombre: ");
        String nombre = teclado.nextLine().trim();

        System.out.print("Nueva descripcion: ");
        String descripcion = teclado.nextLine().trim();

        System.out.print("Nueva cantidad: ");
        int cantidad = leerEntero();

        System.out.print("Nuevo precio: ");
        double precio = leerDecimal();

        if (id <= 0 || ean.isEmpty() || nombre.isEmpty()
                || cantidad < 0 || precio < 0) {
            System.out.println("Datos invalidos. Revise los campos.");
            return;
        }

        Producto producto = new Producto(
            id, ean, nombre, descripcion, cantidad, precio
        );

        if (dao.actualizar(producto)) {
            System.out.println("Producto actualizado.");
        } else {
            System.out.println("No se encontro el ID indicado.");
        }
    }

    private static void eliminar() throws SQLException {
        System.out.print("ID del producto a eliminar: ");
        int id = leerEntero();

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return;
        }

        System.out.print("Confirme eliminacion escribiendo SI: ");
        String confirmacion = teclado.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("SI")) {
            if (dao.eliminar(id)) {
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("No se encontro el ID indicado.");
            }
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    private static int leerEntero() {
        while (true) {
            try {
                int valor = Integer.parseInt(teclado.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero valido: ");
            }
        }
    }

    private static double leerDecimal() {
        while (true) {
            try {
                String entrada = teclado.nextLine().trim().replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un precio valido: ");
            }
        }
    }
}
