import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio1 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        int[] numeros = new int[16];
        numeros= new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        try(RandomAccessFile archivo = new RandomAccessFile("src/Ficheros/enteros.dat", "rw")) {
            for (int i = 0; i < numeros.length; i++) {
                archivo.writeInt(numeros[i]);
            }
            System.out.println("Por que numero deseas susituir el 2?");
            int numero = sc.nextInt();
            archivo.seek(0);
            for (int i = 0; i < numeros.length; i++) {
                archivo.seek(i * 4);
                int valor = archivo.readInt();
                if (valor == 2) {
                    archivo.seek(archivo.getFilePointer() - 4);
                    archivo.writeInt(numero);
                }
            }
            System.out.println("Se han sustituido todos los 2 por " + numero);
            for (int i = 0; i < numeros.length; i++) {
                archivo.seek(i * 4);
                System.out.println(archivo.readInt());
            }
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
