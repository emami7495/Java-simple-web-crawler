package Indexer;

import Crawler.Configs;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Mosi on 4/19/2017.
 */
public class Indexer {
    public static int id = 0;
    //
    public static HashMap<Integer, WebPage> webPages = new HashMap<>();//documents
    public static HashMap<String, ArrayList<Integer>> dictionary = new HashMap<>();//dictionary
    //
    public static HashMap<Character, Boolean> characters = new HashMap<>();

    public static void main(String[] args) {
        readFiles();
        initialCharacters();
        createDictionary();
        sortDictionaryArraylists();
        // printWords();
        saveWebPages();
        saveDictionary();
        System.out.println("indexing finished");

    }

    private static void saveDictionary() {
        File file = new File("C:\\0_Projects\\Java\\MabaniBazyabiEttlaatVaJostojoyeWeb\\Project1\\Crawler\\WebPagesFiles\\dictionary.obj");
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(file);
            ObjectOutputStream s = new ObjectOutputStream(f);
            s.writeObject(dictionary);
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveWebPages() {
        File file = new File("C:\\0_Projects\\Java\\MabaniBazyabiEttlaatVaJostojoyeWeb\\Project1\\Crawler\\WebPagesFiles\\webPages.obj");
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(file);
            ObjectOutputStream s = new ObjectOutputStream(f);
            s.writeObject(webPages);
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void sortDictionaryArraylists() {
        dictionary.forEach((s, integers) -> {
            Collections.sort(integers);
        });
    }

    private static void printWords() {
        for (int i = 0; i < dictionary.size(); i++) {
            dictionary.forEach((s, integers) -> {
                System.out.print(s);
                System.out.println(" :");
                for (int j = 0; j < integers.size(); j++) {
                    System.out.println(webPages.get(integers.get(j)).url);
                }
            });
        }
    }

    ////////////////////////////////////////
    private static void createDictionary() {
        for (int i = 0; i < webPages.size(); i++) {
            if (webPages.get(i) != null) {
                String content = webPages.get(i).title + " " + webPages.get(i).body;
                String[] words = content.split(" ");
                for (int j = 0; j < words.length; j++) {
                    if (dictionary.containsKey(words[j])) {
                        if (!dictionary.get(words[j]).contains(i)) {
                            dictionary.get(words[j]).add(i);
                        }
                    } else {
                        ArrayList<Integer> arrayList = new ArrayList();
                        arrayList.add(i);
                        dictionary.put(words[j], arrayList);
                    }
                }
            }
        }
    }

    //////////////////////////////
    private static void readFiles() {
        for (int i = 0; i < Configs.fileNumbers; i++) {
            ////////////////////
            File file = new File("C:\\0_Projects\\Java\\MabaniBazyabiEttlaatVaJostojoyeWeb\\Project1\\Crawler\\WebPagesFiles\\" + "downloaderNumber_" + (i + 1) + ".txt");
            // if file doesn't exists, then create it
            if (file.exists()) {
                Scanner input = null;
                try {
                    input = new Scanner(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (input.hasNext()) {
                    //
                    Jsoup.parse(input.nextLine()).select("MosiCrawler").forEach(element -> {
                        String title = element.select("MosiCrawler_Title").first().text();
                        String url = element.select("MosiCrawler_Url").first().text();
                        String body = element.select("MosiCrawler_Body").first().text();
                        //
                        title = title.trim();
                        url = url.trim();
                        body = body.trim();
                        //
                        body = body.replace("  ", " ");
                        body = body.replace("   ", "");
                        //
                        WebPage webPage = new WebPage(title, url, body, id);
                        Indexer.webPages.put(id, webPage);
                    });
                    id++;
                }//
                System.out.println("Web pages Number : " + Indexer.webPages.size());
            }
        }
    }

    //////////////////////////////////////
    private static void initialCharacters() {
        characters.put('a', true);
        characters.put('b', true);
        characters.put('c', true);
        characters.put('d', true);
        characters.put('e', true);
        characters.put('f', true);
        characters.put('g', true);
        characters.put('h', true);
        characters.put('i', true);
        characters.put('j', true);
        characters.put('k', true);
        characters.put('l', true);
        characters.put('m', true);
        characters.put('n', true);
        characters.put('o', true);
        characters.put('p', true);
        characters.put('q', true);
        characters.put('r', true);
        characters.put('s', true);
        characters.put('t', true);
        characters.put('u', true);
        characters.put('v', true);
        characters.put('w', true);
        characters.put('x', true);
        characters.put('y', true);
        characters.put('z', true);
        //
        characters.put('A', true);
        characters.put('B', true);
        characters.put('C', true);
        characters.put('D', true);
        characters.put('E', true);
        characters.put('F', true);
        characters.put('G', true);
        characters.put('H', true);
        characters.put('I', true);
        characters.put('J', true);
        characters.put('K', true);
        characters.put('L', true);
        characters.put('M', true);
        characters.put('N', true);
        characters.put('O', true);
        characters.put('P', true);
        characters.put('Q', true);
        characters.put('R', true);
        characters.put('S', true);
        characters.put('T', true);
        characters.put('U', true);
        characters.put('V', true);
        characters.put('W', true);
        characters.put('X', true);
        characters.put('Y', true);
        characters.put('Z', true);
        //
        characters.put('ا', true);
        characters.put('ب', true);
        characters.put('پ', true);
        characters.put('ت', true);
        characters.put('ث', true);
        characters.put('ج', true);
        characters.put('چ', true);
        characters.put('ح', true);
        characters.put('خ', true);
        characters.put('د', true);
        characters.put('ذ', true);
        characters.put('ر', true);
        characters.put('ز', true);
        characters.put('ژ', true);
        characters.put('س', true);
        characters.put('ش', true);
        characters.put('ص', true);
        characters.put('ض', true);
        characters.put('ط', true);
        characters.put('ظ', true);
        characters.put('ع', true);
        characters.put('غ', true);
        characters.put('ف', true);
        characters.put('ق', true);
        characters.put('ک', true);
        characters.put('گ', true);
        characters.put('ل', true);
        characters.put('م', true);
        characters.put('ن', true);
        characters.put('و', true);
        characters.put('ه', true);
        characters.put('ی', true);
        characters.put('ئ', true);
        characters.put('آ', true);


        characters.put('أ', true);
        characters.put('إ', true);
        characters.put('ي', true);
        characters.put('ك', true);
        characters.put('ء', true);
        characters.put('ؤ', true);
        characters.put('ى', true);
        characters.put('ة', true);
        //
        characters.put('۰', true);
        characters.put('۱', true);
        characters.put('۲', true);
        characters.put('۳', true);
        characters.put('۴', true);
        characters.put('۵', true);
        characters.put('۶', true);
        characters.put('۷', true);
        characters.put('۸', true);
        characters.put('۹', true);
        //
        characters.put('0', true);
        characters.put('1', true);
        characters.put('2', true);
        characters.put('3', true);
        characters.put('4', true);
        characters.put('5', true);
        characters.put('6', true);
        characters.put('7', true);
        characters.put('8', true);
        characters.put('9', true);
    }
}