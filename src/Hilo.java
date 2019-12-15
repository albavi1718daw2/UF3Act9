import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Hilo implements Runnable {

	Socket clienteConectado;
	int numCliente;
	String cadena;
	PrintWriter fsalida;
	BufferedReader fentrada;
	
	public Hilo (Socket clienteConectado, int numCliente) {
		
		this.clienteConectado = clienteConectado;
		this.numCliente = numCliente;
		this.fsalida = null;
	}

	@Override
	public void run() {
		
		// Controlamos si el cliente quiere salir o no
		boolean salir = false;
		
		// Controlamos que algo pueda salir mal
		try {
			
			// Flujo de salida al cliente
			fsalida = new PrintWriter(this.clienteConectado.getOutputStream(), true);
			
			// Mostramos el cliente que se acaba de conectar
			fsalida.println(clienteConectado());
			
			// Flujo de entrada del cliente
			fentrada = new BufferedReader(new InputStreamReader(this.clienteConectado.getInputStream()));
			
			while ((cadena = fentrada.readLine()) != null && !salir) {
				fsalida.println(cadena);
				System.out.println(numCliente + ": " + cadena);
				if (cadena.equals("exit")){ 
					fentrada.close();
					fsalida.close();
					salir = true;
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public String clienteConectado() {
		
		return "Cliente " + numCliente + " conectado.";
	}
}