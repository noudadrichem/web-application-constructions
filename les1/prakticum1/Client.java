package les1.prakticum1;

import java.net.Socket;
import java.io.OutputStream;

class Client {
  public static void main(String[] args) throws Exception {
    Socket s = new Socket("145.89.84.114", 4713); // redie
    OutputStream os = s.getOutputStream();
    os.write("Hello redie".getBytes());

    s.close();

  }
}
