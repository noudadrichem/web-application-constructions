package les1.prakticum3;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {

        while (true) {
            ServerSocket ss = new ServerSocket(4711);
            Socket s = ss.accept();

            InputStream is = s.getInputStream();
            Scanner scan = new Scanner(is);

            String name = scan.nextLine();
            System.out.println(name);

            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }

            scan.close();
            s.close();
            ss.close();
        }
    }
}