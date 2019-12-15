import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP5 {

	public static void main (String[] args) throws Exception {

		// Puerto por el cual nos conectaremos
		int numPort = 9000;
		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";

		// Variables que usaremos
		int clientes = 0;
		PrintWriter fsortida;
		BufferedReader fentrada;

		// Mostramos el número de clientes según lo que nos pasen por parámetros
		System.out.println("Número de clientes permitidos: " + Integer.parseInt(args[0]));

		// Bucle para ir contando los clientes, mientras sea menor a lo recibido
		// por parámetros, sigue
		while (clientes < Integer.parseInt(args[0])) {

			// Conectamos al cliente
			System.out.println("Esperando conexión...");
			Socket clienteConectado = servidor.accept();

			// Creamos el hilo
			Hilo hilo = new Hilo(clienteConectado, clientes);
			Thread hiloThread = new Thread(hilo);
			hiloThread.start();

			clientes++;
		}

		servidor.close();
	}
}