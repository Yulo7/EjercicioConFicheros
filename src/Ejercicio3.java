import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.RandomAccessFile;
public class Ejercicio3 {

    private static final String RUTA_FICHERO = "src/Ficheros/";

    private static final String NOMBRE_FICHERO = "juegos.dat";
        public static void main(String[] args) {
                int[] Orden = {1, 2, 3, 4};
                String[] Nombres = {"Halo", "Teken", "Sims",
                        "Fifa"};
                int[] edades = {17, 18, 8, 16};
                String[] clasificaciones = {"Aventura", "Acción", "Sandbox", "Deportes"};

                try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "rw")) {
                    for (int i = 0; i < Orden.length; i++) {

                        fichero.writeInt(Orden[i]);

                        StringBuffer nombre = new
                                StringBuffer(Nombres[i]);
                        nombre.setLength(10);
                        fichero.writeChars(nombre.toString());
                        //Escribe edad
                        fichero.writeInt(edades[i]);
                        //Escribe clasificación
                        StringBuffer clasificacion = new
                                StringBuffer(clasificaciones[i]);
                        clasificacion.setLength(10);
                        fichero.writeChars(clasificacion.toString());
                    }
                    System.out.println("Registros almacenados correctamente.");

                } catch (IOException e) {
                    System.out.println("Error al escribir el fichero.");
                }
                try (RandomAccessFile leerf = new RandomAccessFile(RUTA_FICHERO + NOMBRE_FICHERO, "r")) {
                    long numRegistros = leerf.length() / 48;
                    System.out.println("Número de registros: " + numRegistros);
                    for (int i = 0; i < numRegistros; i++) {
                        int orden = leerf.readInt();
                        char[] nombreChars = new char[10];
                        for (int c = 0; c < 10; c++) {
                            nombreChars[c] = leerf.readChar();
                        }
                        String nombre = new String(nombreChars).trim();
                        int edad = leerf.readInt();
                        char[] clasificacionChars = new char[10];
                        for (int c = 0; c < 10; c++) {
                            clasificacionChars[c] = leerf.readChar();
                        }
                        String clasificacion = new String(clasificacionChars).trim();
                        System.out.printf("Orden:" + orden + ", Nombre: " + nombre + ", Edad: " + edad + ", Clasificación: " + clasificacion+"\n");
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer el fichero.");

                }
            }
        }

