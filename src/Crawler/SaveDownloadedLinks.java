package Crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Mosi on 3/6/2017.
 */
public class SaveDownloadedLinks implements Runnable {
    String url;

    public SaveDownloadedLinks(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        File downloaded_Links = new File(Configs.filesPath + "downloaded_Links" + ".txt");
        if (!downloaded_Links.exists()) {
            try {
                downloaded_Links.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw_downloaded_Links = null;
        try {
            fw_downloaded_Links = new FileWriter(downloaded_Links, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //BufferedWriter writer give better performance
        BufferedWriter bw_downloaded_Links = new BufferedWriter(fw_downloaded_Links);
        try {
            bw_downloaded_Links.write("\n");
            bw_downloaded_Links.write(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Closing BufferedWriter Stream
        try {
            bw_downloaded_Links.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
