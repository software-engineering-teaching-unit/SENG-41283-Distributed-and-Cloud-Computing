package example.udp;

import java.net.*;
import java.io.*;

public class UDPClient {
    private static DatagramSocket aSocket;

    public static void main(String args[]) {
        // args give message contents and server hostname DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            String message = "this is the first message for the server";

            byte[] m = message.getBytes();

            String serverHost = "localhost";
            InetAddress aHost = InetAddress.getByName(serverHost);

            int serverPort = 6789;

            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Received: " + new String(reply.getData()));

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) aSocket.close();
        }
    }
}
