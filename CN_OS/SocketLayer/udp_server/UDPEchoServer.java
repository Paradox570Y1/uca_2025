import java.net.*;

public class UDPEchoServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(12345);
        byte[] buffer = new byte[1024];

        System.out.println("UDP Echo Server running on port 12345");

        while (true) {
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);

            String msg = new String(request.getData(), 0, request.getLength());
            System.out.println("Client: " + msg);

            DatagramPacket response = new DatagramPacket(
                request.getData(), request.getLength(),
                request.getAddress(), request.getPort()
            );
            socket.send(response); 
        }
    }
}
