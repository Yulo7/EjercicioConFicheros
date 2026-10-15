import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
public class Ejercicio7 {

        // Estructura de precio.dat (ejercicio anterior)
        static final int LONG_NOMBRE = 10;
        static final int LONG_DNI = 9;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            try (RandomAccessFile precio = new RandomAccessFile("precio.dat", "r");
                 RandomAccessFile descuento = new RandomAccessFile("descuento.dat", "rw")) {


                System.out.print("Porcentaje de descuento a aplicar: ");
                double porcentaje = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));

                if (porcentaje < 0 || porcentaje > 100) {
                    System.out.println("El porcentaje debe estar entre 0 y 100.");
                    return;
                }
                descuento.setLength(0);

                while (precio.getFilePointer() < precio.length()) {
                    precio.skipBytes(LONG_NOMBRE * 2);
                    String dni = leerCadena(precio, LONG_DNI);
                    double anterior = precio.readDouble();

                    double conDescuento = Math.round(anterior * (1 - porcentaje / 100) * 100) / 100.0;

                    descuento.writeChars(String.format("%-9.9s", dni));
                    descuento.writeDouble(anterior);
                    descuento.writeDouble(conDescuento);
                }


                System.out.println("\n=== CONTENIDO DE descuento.dat ===");
                descuento.seek(0);

                while (descuento.getFilePointer() < descuento.length()) {
                    String dni = leerCadena(descuento, LONG_DNI);
                    double anterior = descuento.readDouble();
                    double conDescuento = descuento.readDouble();

                    System.out.printf("DNI: %-9s | Antes: %.2f € | Con descuento: %.2f €%n",
                            dni, anterior, conDescuento);
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: el porcentaje debe ser un número.");
            } catch (IOException e) {
                System.out.println("Error de E/S: " + e.getMessage());
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

