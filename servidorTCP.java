package comunicacionTCP;

import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {
    public static void main(String[] args) {
        try {
            ServerSocket socket_servidor = new ServerSocket(1234);
            System.out.println("Servidor Encendido :D");
            
            while (true) {
                // Iniciar la conexión
                Socket socket_cliente = socket_servidor.accept();
                // Crear el hilo para manejar la conexión con el cliente
                hiloClienteServ hilo = new hiloClienteServ(socket_cliente);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
