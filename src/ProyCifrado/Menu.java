package ProyCifrado;

import java.util.Scanner;

public class Menu {
    public static void opciones(){
        Boolean opcionValida = false;
        while (!opcionValida ) {
            System.out.println("Bienvenido a Encriptado César");
            System.out.println("Elija una Opción");
            System.out.println("1.- Encriptar");
            System.out.println("2.- Desencriptar");
            System.out.println("3.- Desencriptar usando fuerza bruta");
            System.out.println("4.- Análisis Estadísticos");
            System.out.println("0.- Salir del Programa");

            Scanner consola = new Scanner(System.in);
            int opcion = consola.nextInt();

            String runProces = switch (opcion) {
                //switch (opcion) {
                case 1 -> "Opcion1";
                //System.out.println("Seleccionaste Opción 1");
                case 2 -> "Opcion2";
                //System.out.println("Seleccionaste Opción 2");
                case 3 -> "Opcion3";
                //System.out.println("Seleccionaste Opción 3");
                case 4 -> "Opcion4";
                //System.out.println("Seleccionaste Opción 4");
                case 5 -> "Opcion5";
                //System.out.println("Seleccionaste Opción 5");
                default -> "Opción no valida";
                //System.out.println("Opción no valida");
            };
        }
    }
}
