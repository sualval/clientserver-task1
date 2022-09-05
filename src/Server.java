import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {


        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(12345);
                 Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                long result;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) {
                        break;
                    }
                    try {
                        long number = Long.parseLong(line);
                        result = calculation(number);
                        out.println(result);
                    } catch (NumberFormatException numberFormatException) {
                        out.println("NumberFormatException");
                    }
                }
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static long calculation(long number) {
        if (number == 0) {
            return 0;
        }
        int value = 0;
        int result = 1;
        for (int i = 1; i < number; i++) {
            result = value + result;
            value = result - value;
        }
        return result;
    }
}

