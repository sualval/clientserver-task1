import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); Scanner scanner = new Scanner(System.in)) {
            String scan;
            while (true) {
                System.out.println("enter number: ");
                scan = scanner.nextLine();
                out.println(scan);
                if (scan.equals("end")) {
                    break;
                }
                System.out.println(in.readLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
