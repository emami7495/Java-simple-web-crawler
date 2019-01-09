package Indexer;

import java.io.Serializable;

/**
 * Created by Mosi on 4/19/2017.
 */
public class WebPage implements Serializable {
    public String title;
    public String url;
    public String body;
    public int id;

    public WebPage(String title, String url, String body, int id) {
        this.title = title;
        this.url = url;
        this.body = body;
        this.id = id;
    }
}
