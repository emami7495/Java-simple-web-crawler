package Crawler;

/**
 * Created by Mosi on 3/6/2017.
 */
public class Link {
    public String link;
    public int depth;

    public Link(String link, int depth) {
        this.link = link;
        this.depth = depth;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
