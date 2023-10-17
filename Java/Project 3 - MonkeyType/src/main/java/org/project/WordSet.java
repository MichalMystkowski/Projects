package org.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordSet {

    private ArrayList<String> words = new ArrayList<>();
    public String fileName;
    public WordSet(String fileName) {
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {

        Scanner scanner;

        try
        {
            scanner = new Scanner(new File("dictionary/"+ fileName));
            while (scanner.hasNextLine())
            {
                String word = scanner.nextLine();
                words.add(word);
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getRandomWords(){
        ArrayList<String> strings = new ArrayList<>();

        Random random = new Random();
        for(int i = 0; i < 30; i++)
        {
            strings.add(words.get(random.nextInt(words.size())));
        }
        return strings;
    }
}
