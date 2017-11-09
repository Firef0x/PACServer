package pacserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.Executors;

public class PACServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println(Commons.ERR_ARGUMENT);
            System.exit(33);
        }
        try {
            int port = Integer.parseInt(args[0]);
            Commons.pacPath = Commons.getJavaPath() + args[1].trim();
            InetSocketAddress address = new InetSocketAddress(port);
            HttpServer httpServer = HttpServer.create(address, 0);
            httpServer.createContext("/proxy.pac", new PACHandler());
            httpServer.setExecutor(Executors.newCachedThreadPool());
            httpServer.start();
            System.out.println(Commons.simpleDateFormat.format(
                    new Date()) + " : PACServer is listening on port " + port);
            System.out.println();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
