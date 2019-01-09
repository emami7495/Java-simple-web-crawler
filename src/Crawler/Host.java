package Crawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Mosi on 3/5/2017.
 */
public class Host {
    public String hostName;
    public BlockingQueue<Link> links;

    public Host(String hostName) {
        links = new LinkedBlockingQueue<>();
        this.hostName = hostName;
    }
}
