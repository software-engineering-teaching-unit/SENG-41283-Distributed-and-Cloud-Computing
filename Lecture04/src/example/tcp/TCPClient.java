package example.tcp;

import java.net.*;
import java.io.*;

public class TCPClient {
    private static Socket s;
    public static void main (String args[]) {
        // arguments supply message and hostname of destination Socket s = null;
        try{
            int serverPort = 7896;
            String serverHost = "localhost";
            String message = "this is the first message for the server";

            s = new Socket(serverHost, serverPort);

            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(message); // UTF is a string encoding;

            String data = in.readUTF();
            System.out.println("Received: "+ data) ;

        } catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage());
        } catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        } catch (IOException e){System.out.println("IO:"+e.getMessage());
        } finally {
            if(s!=null)
                try {
                    s.close();
                }
                catch (IOException e){
                    /*close failed*/}
        }
    } }