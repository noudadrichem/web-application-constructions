package les1.prakticum2;

import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;

class Client {
  public static void main(String[] args) throws Exception {
    Socket s = new Socket("127.0.0.1", 4711); // noud
    // Socket s = new Socket("145.89.252.236", 4711); // daan
    // Socket s = new Socket("145.89.84.114", 4713); // redie
    OutputStream os = s.getOutputStream();
    PrintWriter printWriter = new PrintWriter(os);

    String message = "Hello world \n moetje hebben? \n 3e line"; 

    printWriter.write(message);
    printWriter.flush();

    printWriter.close();
    s.close();
  }
}
