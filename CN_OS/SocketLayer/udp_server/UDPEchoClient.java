import java.net.*;
import java.util.Scanner;

public class UDPEchoClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter message: ");
            String msg = sc.nextLine();
            if (msg.equalsIgnoreCase("exit")) break;

            byte[] data = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 12345);
            socket.send(packet);

            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            System.out.println("Server: " + new String(response.getData(), 0, response.getLength()));
        }

        socket.close();
    }
}

