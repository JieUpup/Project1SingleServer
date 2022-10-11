import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Server extends Thread{
    static HashMap<String, String> map = new HashMap<>();
    private String hostName;
    private String IP;
    private String portNumber;

    public Server() {

    }

    public Server(String hostName, String IP, String portNumber) {
        this.hostName = hostName;
        this.IP = IP;
        this.portNumber = portNumber;

    }

    public static HashMap<String, String> getMap() {
        return map;
    }

    public static void setMap(HashMap<String, String> map) {
        Server.map = map;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public String toString() {
        return "Server{" +
                "hostName='" + hostName + '\'' +
                ", IP='" + IP + '\'' +
                ", portNumber='" + portNumber + '\'' +
                '}';
    }

    public String getHostName() {
        return hostName;
    }

    public void login(String hostName) {

    }
    public static String reverseString(String input) {
        StringBuilder sb = new StringBuilder();

        for (Character c : input.toCharArray()) {
            if ( (int)'a' < (int)c && (int)c < (int)'z') {
                sb.append(Character.toUpperCase(c));
            } else if (( (int)'A' < (int)c && (int)c < (int)'Z')) {
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        sb.reverse();

        return sb.toString();
    }
    public void run(){
        System.out.println();
    }
    public static void main(String[] args) {
        ServerSocket server = null;
        int port = Integer.parseInt(args[0]);
        try {
            server = new ServerSocket(port);
            byte[] readBuffer = new byte[1024];

            Socket socket = server.accept();
            int off = socket.getInputStream().read(readBuffer);
            String inputString = new String(Arrays.copyOfRange(readBuffer, 0, off), StandardCharsets.UTF_8);
            String outputString = reverseString(inputString);
            socket.getOutputStream().write(outputString.getBytes(StandardCharsets.UTF_8));

            socket.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

