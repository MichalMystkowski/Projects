package org.project;

import java.io.File;
import java.util.ArrayList;

public class Logic {
    public static ArrayList<WordSet> prepareData() {
        ArrayList<WordSet> wordSets = new ArrayList<>();

        File dir = new File("dictionary");
        File[] files = dir.listFiles();

        for (File f : files) {
            wordSets.add(new WordSet(f.getName()));
        }

        return wordSets;
    }
}
