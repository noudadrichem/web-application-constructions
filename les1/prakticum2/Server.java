package les1.prakticum2;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.util.Scanner;

class Server {
  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(4711);
    Socket s = ss.accept();
    InputStream is = s.getInputStream();
    Scanner scanner = new Scanner(is);

    while(scanner.hasNextLine()) {
      System.out.println(scanner.nextLine());
    }

    scanner.close();
    s.close();
    ss.close();
  }
}