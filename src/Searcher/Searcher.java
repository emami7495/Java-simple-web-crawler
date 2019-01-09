package Searcher;

import Indexer.WebPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Mosi on 5/22/2017.
 */
public class Searcher {
    //
    private static HashMap<Integer, WebPage> webPages = new HashMap<>();//documents
    private static HashMap<String, ArrayList<Integer>> dictionary = new HashMap<>();//dictionary

    //
    public static void main(String[] args) {
        try {
            File dictionaryFile = new File("C:\\0_Projects\\Java\\MabaniBazyabiEttlaatVaJostojoyeWeb\\Project1\\Crawler\\WebPagesFiles\\dictionary.obj");
            FileInputStream dictionaryFileInputStream = new FileInputStream(dictionaryFile);
            ObjectInputStream dictionaryObjectInputStream = new ObjectInputStream(dictionaryFileInputStream);
            dictionary = (HashMap<String, ArrayList<Integer>>) dictionaryObjectInputStream.readObject();
            dictionaryObjectInputStream.close();
            dictionaryFileInputStream.close();
            //
            File webPagesFile = new File("C:\\0_Projects\\Java\\MabaniBazyabiEttlaatVaJostojoyeWeb\\Project1\\Crawler\\WebPagesFiles\\webPages.obj");
            FileInputStream webPagesFileInputStream = new FileInputStream(webPagesFile);
            ObjectInputStream webPagesObjectInputStream = new ObjectInputStream(webPagesFileInputStream);
            webPages = (HashMap<Integer, WebPage>) webPagesObjectInputStream.readObject();
            //
            webPagesObjectInputStream.close();
            webPagesFileInputStream.close();
            //
            System.out.println("web pages number : " + webPages.size());
            System.out.println("dictionary word number : " + dictionary.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ////////////
        while (true) {
            System.out.println("***************** welcome to mosi search *****************");
            System.out.println("Enter Your query : ");
            Scanner input = new Scanner(System.in);
            String query = input.nextLine();
            //
            if (!query.equals("")) {
                String[] words = query.split(" ");
                ArrayList<ArrayList<Integer>> allWordWebPagesNumbers = new ArrayList<>();
                for (String word : words) {
                    ArrayList<Integer> thisWordWebPagesNumbers = dictionary.get(word);
                    if (thisWordWebPagesNumbers != null) {
                        allWordWebPagesNumbers.add(thisWordWebPagesNumbers);
                    }
                }
                System.out.println(allWordWebPagesNumbers.size());
                //
                for (ArrayList<Integer> thisWordWebPagesNumbers : allWordWebPagesNumbers) {
                    for (Integer thisWordWebPagesNumber : thisWordWebPagesNumbers) {
                        WebPage webPage = webPages.get(thisWordWebPagesNumber);
                        System.out.println(webPage.url);
                    }
                    System.out.println("///////////////////////");
                }
                //
                try {
                    if (allWordWebPagesNumbers.get(0) != null) {
                        ArrayList<Integer> common = new ArrayList<>(allWordWebPagesNumbers.get(0));
                        for (int i = 1; i < allWordWebPagesNumbers.size(); i++) {
                            common.retainAll(allWordWebPagesNumbers.get(i));
                        }
                        //
                        System.out.println("result page number : " + common.size());
                        for (Integer aCommon : common) {
                            WebPage webPage = webPages.get(aCommon);
                            System.out.println("---------------------------------------------------------");
                            System.out.println(webPage.title);
                            System.out.println(webPage.url);
                            System.out.println(webPage.body.substring(0, 200));
                        }
                    }
                } catch (Exception e) {
                    System.out.println("no result");
                }
            } else {
                System.out.println("no result");
            }//
        }
    }
}