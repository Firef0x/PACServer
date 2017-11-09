package pacserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PACHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase("GET") || requestMethod.equalsIgnoreCase("POST")) {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "application/x-ns-proxy-autoconfig");
            exchange.sendResponseHeaders(200, 0);

            OutputStream responseBody = exchange.getResponseBody();
            String strPAC = readFileIntoString();
            responseBody.write(strPAC.getBytes());
            responseBody.close();

            System.out.println(Commons.simpleDateFormat.format(new Date()) + " :");
            Headers requestHeaders = exchange.getRequestHeaders();
            Set<String> keySet = requestHeaders.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                List values = requestHeaders.get(key);
                System.out.println(key + " = " + values.toString());
            }
            System.out.println();
            System.out.println();
        }
    }

    private String readFileIntoString() {
        StringBuilder sbResult = new StringBuilder();
        try {
            File file = new File(Commons.pacPath);
            if (file.isFile() && file.exists()) {
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(new FileInputStream(new File(Commons.pacPath)), StandardCharsets.UTF_8));
                /*
                 * Read file into string by character using method
                 * BufferedReader.read()
                 */
                while (bufferedReader.ready()) {
                    sbResult.append((char) bufferedReader.read());
                }
                bufferedReader.close();
            } else {
                System.out.println(Commons.ERR_FILE_NOT_FOUND + Commons.pacPath);
            }
        } catch (FileNotFoundException e) {
            System.out.println(Commons.ERR_FILE_NOT_FOUND + Commons.pacPath);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(Commons.ERR_READ_FILE_ERROR + Commons.pacPath);
            e.printStackTrace();
        }
        return sbResult.toString();
    }
}
