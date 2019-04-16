package les1.prakticum4;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Server {

    private static final String OUTPUT = "<html><head><title>Het werkt!</title></head><body><p>HIJ WEKRT!</p></body></html>";
    private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "Content-Length: ";
    private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(4711);
        Socket s = ss.accept();

        InputStream is = s.getInputStream();
        Scanner scan = new Scanner(is);

        BufferedWriter out = new BufferedWriter(
            new OutputStreamWriter(
                new BufferedOutputStream(s.getOutputStream()),
                "UTF-8"
            )
        );
        out.write(OUTPUT_HEADERS + OUTPUT.length() + OUTPUT_END_OF_HEADERS + OUTPUT);
        out.flush();
        out.close();

        scan.close();
        s.close();
        ss.close();

    }
}