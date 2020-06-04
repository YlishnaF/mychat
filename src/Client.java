import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                socket = new Socket("localhost", 4000);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                while (true) {
                    String word = reader.readLine();
                    if(word.equals("/end")) {
                        System.out.println("Клиент отключился");
                        break;
                    }
                    out.write(word + "\n");
                    out.flush();
                    String serverWord = in.readLine();
                    System.out.println(serverWord);
                }

            } finally {
                System.out.println("Клиент был закрыт...");
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}