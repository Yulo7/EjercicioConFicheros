import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿De qué edad máxima desea ver los juegos?");
        int edadMaxima = sc.nextInt();
        if (edadMaxima < 0) {
            System.out.println("La edad no puede ser negativa.");
            return;
        }
        try (RandomAccessFile archivo = new RandomAccessFile("src/Ficheros/juegos.dat", "r")) {
            boolean hayJuegos = false;
            while (archivo.getFilePointer() < archivo.length()) {
                archivo.readInt(); // Identificador del juego

                char[] nombreChars = new char[10];
                for (int i = 0; i < nombreChars.length; i++) {
                    nombreChars[i] = archivo.readChar();
                }
                String nombre = new String(nombreChars).trim();

                int edadRecomendada = archivo.readInt();

                char[] tematicaChars = new char[10];
                for (int i = 0; i < tematicaChars.length; i++) {
                    tematicaChars[i] = archivo.readChar();
                }
                String tematica = new String(tematicaChars).trim();

                if (edadRecomendada <= edadMaxima) {
                    System.out.printf(nombre + " - " + tematica + "\n");
                    hayJuegos = true;
                }
            }

            if (!hayJuegos) {
                System.out.println("No hay juegos recomendados para esa edad máxima.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
