package les1.prakticum5;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(4711);
        Socket s = ss.accept();
        System.out.println("New connection from " + s.getInetAddress());

        MyServlet thread1 = new MyServlet(s);
        // MyServlet thread2 = new MyServlet(s);
        thread1.start();
        // thread2.start();
        
        // while(s.isConnected()) {
        // }

        
        ss.close();

    }
}