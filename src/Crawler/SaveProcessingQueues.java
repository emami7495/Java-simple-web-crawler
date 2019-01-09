package Crawler;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by Mosi on 3/7/2017.
 */
public class SaveProcessingQueues implements Runnable {
    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(Configs.timeToSaveProcessingQueues);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ///////////
            ArrayList arrayList = Arbiter.hosts;
            File file = new File(Configs.filesPath + "0_ProcessingQueues" + ".txt");
            if (file.exists()) {
                file.delete();
            }
            //serialize the List
            try (
                    OutputStream outputStream = new FileOutputStream(Configs.filesPath + "0_ProcessingQueues" + ".ser");
                    OutputStream buffer = new BufferedOutputStream(outputStream);
                    ObjectOutput output = new ObjectOutputStream(buffer);
            ) {
                output.writeObject(arrayList);
            } catch (IOException ex) {
            }
            //http://www.javapractices.com/topic/TopicAction.do?Id=57


        }

    }
}
