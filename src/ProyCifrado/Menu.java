package ProyCifrado;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Menu {
    public static void opciones(){
        Boolean opcionValida = false;
        while (!opcionValida ) {
            System.out.println("<<< Menú de Opciones para Encriptado César! >>>");
            System.out.println("1.- Encriptar");
            System.out.println("2.- Desencriptar");
            System.out.println("3.- Desencriptar usando fuerza bruta");
            System.out.println("4.- Análisis Estadísticos");
            System.out.println("0.- Salir del Programa");
            System.out.print("Elije una Opción: ");

            Scanner consola = new Scanner(System.in);
            int opcion = consola.nextInt();
            switch (opcion) {
                case 0 -> {
                    System.out.println("Vuelve Pronto! Bye!\n");
                    return;
                }
                case 1 -> {
                    System.out.println("Elegiste opción 1");
                    System.out.print("¿Nombre de archivo a encriptar? ");
                    Scanner scanner = new Scanner(System.in);
                    String fileTxt = scanner.nextLine();
                    System.out.print("¿Clave para encriptar? ");
                    int cve = consola.nextInt();
                    try {
                        Path filePath = Path.of(fileTxt);
                        Validator.fileExists(filePath);
                        CifradoCesar.encrypt(fileTxt, cve);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    System.out.println("Elegiste opción 2");
                    System.out.print("¿Nombre de archivo a desencriptar? ");
                    Scanner scanner = new Scanner(System.in);
                    String fileTxt = scanner.nextLine();
                    System.out.print("¿Clave para desencriptar? ");
                    int cve = consola.nextInt();
                    try {
                        Path filePath = Path.of(fileTxt);
                        Validator.fileExists(filePath);
                        CifradoCesar.decrypt(fileTxt, cve);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {  //Desencriptar conm Fuerza Bruta
                    System.out.println("Elegiste opción 3");
                    System.out.print("¿Nombre de archivo a desencriptar? ");
                    Scanner scanner = new Scanner(System.in);
                    String fileTxt = scanner.nextLine();
                    try {
                        Path filePath = Path.of(fileTxt);
                        Validator.fileExists(filePath);
                        CifradoCesar.decryptFzaBruta(fileTxt);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 -> {
                    System.out.println("Opción en Proceso de Desarrollo \n");
                }
                default -> {
                    System.out.println("Opción no valida \n");
                }
            };
        }
    }
}
