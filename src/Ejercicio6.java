import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio6 {


        static final int LONG_NOMBRE = 10;
        static final int LONG_APELLIDOS = 35;
        static final int LONG_DNI = 9;
        static final double PRECIO_CLASE = 45.7;

        public static void main(String[] args) {

            try (RandomAccessFile practicas = new RandomAccessFile("src/Ficheros/practicas.dat", "r");
                 RandomAccessFile precio = new RandomAccessFile("src/Ficheros/precio.dat", "rw")) {

                precio.setLength(0);

                // 1) Leer practicas.dat y escribir precio.dat
                while (practicas.getFilePointer() < practicas.length()) {
                    String nombre = leerCadena(practicas, LONG_NOMBRE);
                    practicas.skipBytes(LONG_APELLIDOS * 2);
                    String dni = leerCadena(practicas, LONG_DNI);
                    practicas.skipBytes(4);
                    int clases = practicas.readInt();

                    double importe = Math.round(clases * PRECIO_CLASE * 100) / 100.0;

                    precio.writeChars(String.format("%-10.10s", nombre));
                    precio.writeChars(String.format("%-9.9s", dni));
                    precio.writeDouble(importe);
                }

                System.out.println("=== CONTENIDO DE precio.dat ===");
                precio.seek(0);

                while (precio.getFilePointer() < precio.length()) {
                    String nombre = leerCadena(precio, LONG_NOMBRE);
                    String dni = leerCadena(precio, LONG_DNI);
                    double importe = precio.readDouble();

                    System.out.printf("%-10s | DNI: %-9s | A pagar: %.2f €%n", nombre, dni, importe);
                }

            } catch (IOException e) {
                System.out.println("Error de E/S: " + e.getMessage());
            }
        }

        /** Lee 'longitud' caracteres del fichero y los devuelve sin espacios de relleno. */
        static String leerCadena(RandomAccessFile fichero, int longitud) throws IOException {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < longitud; i++) {
                sb.append(fichero.readChar());
            }
            return sb.toString().trim();
        }
    }

