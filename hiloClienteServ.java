package comunicacionTCP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class hiloClienteServ extends Thread {
    private Socket socket_cliente;
    
    String[] preguntas ={
        "¿Cual es el oceano mas grande?",
        "¿Cual es el pais mas grande del mundo?",
        "¿Que pais tiene forma de bota?",
        "¿Como se llama el himno nacional de Francia?",
        "¿Donde está la Casa Blanca?"
    };
    String[] respuestas = {
        "Pacifico","Rusia","Italia","Marsellesa","Washington"
    };
    public hiloClienteServ(Socket socket_cliente){
        this.socket_cliente=socket_cliente;
    }

    public void run(){
        try {
            //Crear el buffer para enviar y recibir datos
            BufferedReader buffer_entrada = new BufferedReader(new InputStreamReader(socket_cliente.getInputStream()));
            PrintWriter buffer_salida = new PrintWriter(socket_cliente.getOutputStream(), true);
            //Scanner mensaje = new Scanner(System.in);
            int puntaje = 0;
            for (int i = 0; i < preguntas.length; i++) {
                // Enviar la pregunta al cliente
                buffer_salida.println(preguntas[i]);

                // Esperar la respuesta del cliente
                String respuesta_cliente = buffer_entrada.readLine();
                System.out.println("Respuesta del cliente: " + respuesta_cliente);

                // Evaluar la respuesta
                if (respuesta_cliente != null && respuesta_cliente.equalsIgnoreCase(respuestas[i])) {
                    buffer_salida.println("Bien Hecho");
                    puntaje++;
                } else {
                    buffer_salida.println("Respuesta incorrecta. La respuesta correcta es: " + respuestas[i]);
                }
            }
            // Enviar el puntaje final y cerrar la conexión
            buffer_salida.println("Fin del juego");
            buffer_salida.println("Puntaje final: " + puntaje + " de " + preguntas.length);
            socket_cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
