import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

public class LoginApp {
    public static void main(String[] a) throws Exception {
        HttpServer s = HttpServer.create(new InetSocketAddress(4080), 0);

        s.createContext("/", e -> {
            String h =
            "<html><body style='display:flex;justify-content:center;align-items:center;height:100vh;'>" +
            "<form method=post action=/l>" +
            "User:<input name=u><br><br>" +
            "Pass:<input type=password name=p><br><br>" +
            "<input type=submit value=Login>" +
            "</form></body></html>";

            e.getResponseHeaders().add("Content-Type","text/html");
            e.sendResponseHeaders(200, h.length());
            e.getResponseBody().write(h.getBytes());
            e.close();
        });

        s.createContext("/l", e -> {
            String d = new String(e.getRequestBody().readAllBytes());
            String r = d.contains("u=admin") && d.contains("p=1234")
                     ? "<h2 style='text-align:center;'>Login Successful</h2>"
                     : "<h2 style='text-align:center;'>Access Denied</h2>";

            e.getResponseHeaders().add("Content-Type","text/html");
            e.sendResponseHeaders(200, r.length());
            e.getResponseBody().write(r.getBytes());
            e.close();
        });

        s.start();
        System.out.println("Server running at http://localhost:4080");
    }
}