import java.io.*;
import java.net.*;

public class EchoServer{
    public static void closeConnection(Socket client){
        try {
	    client.close(); 
	} catch (Exception e){}
    }
    public static void handleClient(Socket client){
        try(
	    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())); // input is coming from socket
            PrintWriter out = new PrintWriter(client.getOutputStream(),true) //it can print to any destination like console , file, socket etc.	
	) {
	    String line;
            while((line = in.readLine()) != null){
		System.out.println("Recieved this from client: " + line);
		out.println("Echo back: " + line); // it prints out on socket
	    }
	} catch (Exception e){
            e.printStackTrace();
	} finally {
	    closeConnection(client);
	}
    }
    public static void main(String[] args){
        int port = 12345;
	try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) { // you can limit number of clients too
                Socket client = server.accept(); // accept a client connection
                System.out.println("Client Address: " + client.getRemoteSocketAddress());

                Thread t = new Thread(() -> handleClient(client));
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
