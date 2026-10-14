import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5 {


    static final int LONG_NOMBRE = 10;
    static final int LONG_APELLIDOS = 35;
    static final int LONG_DNI = 9; // 8 dígitos + 1 letra


    static final int TAM_REGISTRO = (LONG_NOMBRE + LONG_APELLIDOS + LONG_DNI) * 2 + 4 + 4; // 116 bytes

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (RandomAccessFile fichero = new RandomAccessFile("src/Ficheros/practicas.dat", "rw")) {

            System.out.print("¿Cuántos alumnos quieres guardar? ");
            int cantidad = Integer.parseInt(sc.nextLine().trim());


            fichero.seek(fichero.length());

            for (int i = 1; i <= cantidad; i++) {
                System.out.println("\n--- Alumno " + i + " ---");

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Apellidos: ");
                String apellidos = sc.nextLine();

                System.out.print("DNI (8 dígitos y letra): ");
                String dni = sc.nextLine();

                System.out.print("Vehículo de prácticas (2 dígitos): ");
                int vehiculo = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Nº de clases de prácticas (2 dígitos): ");
                int clases = Integer.parseInt(sc.nextLine().trim());


                fichero.writeChars(String.format("%-10.10s", nombre));
                fichero.writeChars(String.format("%-35.35s", apellidos));
                fichero.writeChars(String.format("%-9.9s", dni));
                fichero.writeInt(vehiculo);
                fichero.writeInt(clases);
            }

            mostrarFichero(fichero);

        } catch (NumberFormatException e) {
            System.out.println("Error: debes introducir un número entero válido.");
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }


    static void mostrarFichero(RandomAccessFile fichero) throws IOException {
        System.out.println("\n=== CONTENIDO DE practicas.dat ===");
        fichero.seek(0);

        int numero = 1;
        while (fichero.getFilePointer() < fichero.length()) {
            String nombre = leerCadena(fichero, LONG_NOMBRE);
            String apellidos = leerCadena(fichero, LONG_APELLIDOS);
            String dni = leerCadena(fichero, LONG_DNI);
            int vehiculo = fichero.readInt();
            int clases = fichero.readInt();

            System.out.printf("%d. %s %s | DNI: %s | Vehículo: %02d | Clases: %02d%n",
                    numero++, nombre, apellidos, dni, vehiculo, clases);
        }
    }

    static String leerCadena(RandomAccessFile fichero, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(fichero.readChar());
        }
        return sb.toString().trim();
    }
}

