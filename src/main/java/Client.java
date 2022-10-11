import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket client = null;
        String serverIp = args[0];
        Server thread1 = new Server("localhost","","4080");
        thread1.start();
        int port = Integer.parseInt(args[1]);
        try {
            client = new Socket(serverIp, port);
            OutputStream os = new DataOutputStream(client.getOutputStream());
            // read string from terminal and write to server through socket
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            os.write(new String(input).getBytes(StandardCharsets.UTF_8));

            // read response from socket
            byte[] ret = client.getInputStream().readAllBytes();
            System.out.println(new String(ret, StandardCharsets.UTF_8));
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
