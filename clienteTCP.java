package comunicacionTCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class clienteTCP {
    public static void main(String[] args) {
        try {

            // Socket socket_cliente = new Socket("172.29.34.11", 1234);
            Socket socket_cliente = new Socket("localhost", 1234);
            System.out.println("Conectado al servidor");

            // Crear el buffer para enviar y recibir datos
            BufferedReader buffer_entrada = new BufferedReader(new InputStreamReader(socket_cliente.getInputStream()));
            PrintWriter buffer_salida = new PrintWriter(socket_cliente.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Recibir pregunta o fin del juego
                String mensaje_recibido = buffer_entrada.readLine();

                if (mensaje_recibido.equals("Fin del juego")) {
                    System.out.println("Servidor: " + mensaje_recibido);

                    // Recibir el puntaje final después del mensaje de fin
                    String puntaje_final = buffer_entrada.readLine();
                    System.out.println("Servidor: " + puntaje_final);
                    break;
                }

                System.out.println("Pregunta: " + mensaje_recibido);

                // Enviar respuesta
                System.out.print("Escriba una respuesta al servidor: ");
                String mensaje_enviar = scanner.nextLine();
                buffer_salida.println(mensaje_enviar);

                // Recibir resultado de la respuesta
                String resultado = buffer_entrada.readLine();
                System.out.println("Servidor: " + resultado);
            }

            socket_cliente.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
