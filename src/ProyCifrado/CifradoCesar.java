package ProyCifrado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CifradoCesar {
  /*  private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J',
            'K', 'L', 'M', 'N','Ñ','O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z', '.', ',', ':', ' '}; */
    private static final String ALPHABET = "abcdefghijklmnñopqrstuvwxyzáéíóúABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ";

    //***** Método para Encriptar *****
    public static void encrypt(String archivoEntrada, int clave) throws IOException {
        final String archiEncriptado = "ArchiCifrado.txt";
        Path pathEntrada = Paths.get(archivoEntrada);
        Path pathSalida = Paths.get(archiEncriptado);

        if (!Files.exists(pathEntrada)) {
            throw new IOException("El archivo de entrada no existe.");
        }

        String textoPlano = new String(Files.readAllBytes(pathEntrada), StandardCharsets.UTF_8);
        String textoCifrado = cifrarArchivo(textoPlano, clave);
        Files.write(pathSalida, textoCifrado.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Revisar archivo encriptado: " + archiEncriptado +"\n");
    }

    private static String cifrarArchivo(String texto, int clave) {
        StringBuilder textoCifrado = new StringBuilder();
        for (char caracter : texto.toCharArray()) {
            if (Character.isLetter(caracter)) {
                int posicion = ALPHABET.indexOf(caracter);
                int nuevaPosicion = (posicion + clave) % ALPHABET.length();
                char caracterCifrado = ALPHABET.charAt(nuevaPosicion);
                textoCifrado.append(caracterCifrado);
            } else {
                textoCifrado.append(caracter);
            }
        }
        return textoCifrado.toString();
    }

    //***** Método para Descifrar *****

    public static void decrypt(String archivoEntrada, int clave) throws IOException {
        final String archiDesencriptado = "ArchiDescifrado.txt";
        Path pathEntrada = Paths.get(archivoEntrada);
        Path pathSalidaDescifrada = Paths.get(archiDesencriptado);

        if (!Files.exists(pathEntrada)) {
            throw new IOException("El archivo de entrada no existe.");
        }

        // Leer el archivo en UTF-8
        String textoCifrado = Files.readString(pathEntrada, StandardCharsets.UTF_8);
        String textoDescifrado = descifrarArchivo(textoCifrado, clave);

        // Escribir el archivo en UTF-8
        Files.writeString(pathSalidaDescifrada, textoDescifrado, StandardCharsets.UTF_8);

        System.out.println("Revisar archivo descifrado: " + archiDesencriptado +"\n");
    }

    public static String descifrarArchivo(String textoCifrado, int clave) {
        StringBuilder textoDescifrado = new StringBuilder();

        for (char caracter : textoCifrado.toCharArray()) {
            int index = ALPHABET.indexOf(caracter);
            if (index != -1) {
                // Desplazar hacia la izquierda en el alfabeto
                int nuevaPosicion = (index - clave) % ALPHABET.length();
                if (nuevaPosicion < 0) {
                    nuevaPosicion += ALPHABET.length();
                }
                textoDescifrado.append(ALPHABET.charAt(nuevaPosicion));
            } else {
                textoDescifrado.append(caracter); // Mantener caracteres que no están en el alfabeto
            }
        }
        return textoDescifrado.toString();
    }

    //***** Método para Descifrar usando Fuerza Bruta *****

    public static void decryptFzaBruta(String archivoEntrada) throws IOException {
        Path pathArchiCifrado = Paths.get(archivoEntrada);
        if (!Files.exists(pathArchiCifrado)) {
            throw new IOException("El archivo de entrada no existe.");
        }

        String encryptedText = readFile(archivoEntrada);

            for (int key = 0; key < ALPHABET.length(); key++) {
                String decryptedAttempt = decryptWithKey(encryptedText, key, ALPHABET);
                System.out.println("Clave " + key + ":\n" + decryptedAttempt + "\n");
            }

            int bestKey = bruteForceDecrypt(encryptedText, ALPHABET);
            if (bestKey != -1) {
                System.out.println("Clave más probable: " + bestKey);
                System.out.println("Texto descifrado con clave " + bestKey + ":\n" + decryptWithKey(encryptedText, bestKey, ALPHABET));
                System.out.println();
            } else {
                System.out.println("No se encontró una clave válida.\n");
            }
            /*
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }*/
    }

    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
        return content.toString().trim();
    }

    private static String decryptWithKey(String text, int key, String ALPHABET) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            int pos = ALPHABET.indexOf(c);
            if (pos != -1) {
                int newPos = (pos - key + ALPHABET.length()) % ALPHABET.length();
                decrypted.append(ALPHABET.charAt(newPos));
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }

    private static int bruteForceDecrypt(String text, String ALPHABET) {
        int bestKey = -1;
        int maxValidWords = 0;

        for (int key = 1; key < ALPHABET.length(); key++) {
            String decryptedAttempt = decryptWithKey(text, key, ALPHABET);
            int validWords = countValidWords(decryptedAttempt);

            if (validWords > maxValidWords) {
                maxValidWords = validWords;
                bestKey = key;
            }
        }
        return bestKey;
    }

    private static int countValidWords(String text) {
        String[] commonWords = {"el", "la", "de", "y", "que", "en", "los", "las", "un", "una", "es", "por", "con", "no",
                "su", "para", "se", "del", "lo", "como", "más", "pero", "sus", "le", "ha", "me", "al", "del", "hola",
                "á", "é", "í", "ó", "ú", "ñ", "¿", "?"};
        int count = 0;

        for (String word : commonWords) {
            if (text.toLowerCase().contains(" " + word + " ")) {
                count++;
            }
        }

        return count;
    }

}