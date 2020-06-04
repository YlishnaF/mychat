import javax.sound.sampled.Port;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket socket;
    private static ServerSocket server;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;


    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4000);
                System.out.println("Сервер запущен!");
                socket = server.accept();

                try {
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    while (true) {

                        String word = in.readLine();
                        if (word.equals("/end")) {
                            System.out.println("Клиент отключился");
                            break;
                        }
                        System.out.println(word);
                        String serverWord = reader.readLine();
                        out.write(serverWord + "\n");
                        out.flush();
                    }
                } finally {
                    socket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
