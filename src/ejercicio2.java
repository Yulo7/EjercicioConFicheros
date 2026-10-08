import java.io.RandomAccessFile;
import java.util.Scanner;

public class ejercicio2 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un numero del 0 al 9:");
        int numero = sc.nextInt();
        System.out.println("Cuantas veces quieres que se repita el numero?");
        int repeticiones = sc.nextInt();
        try (RandomAccessFile archivo = new RandomAccessFile("src/Ficheros/enteros.dat", "rw")) {
            for (int i = 0; i < repeticiones; i++) {
                archivo.seek(archivo.length());
                archivo.writeInt(numero);
            }
            System.out.println("Se han escrito " + repeticiones + " veces el numero " + numero);
            archivo.seek(0);
            System.out.println("Contenido del archivo:");
            while (archivo.getFilePointer() < archivo.length()) {
                System.out.println(archivo.readInt());
            }
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());

        }
    }
}
