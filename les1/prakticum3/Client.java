package les1.prakticum3;

import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client {
    public static void main(String[] arg) throws Exception {
        // Socket s = new Socket("145.89.77.95", 4711);
        Socket s = new Socket("127.0.0.1", 4711); // daan

        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os);

        Scanner myObj = new Scanner(System.in);

        while (true) {
            System.out.println("Enter username");
            String userName = myObj.nextLine();

            if (userName.equals("stop")) {
                myObj.close();
                pw.close();
                s.close();
                break;
            }

            else {
                pw.write(userName + " ");
                pw.flush();
            }
        }
    }
}