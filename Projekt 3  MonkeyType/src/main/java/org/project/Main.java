package org.project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    private ArrayList<WordSet> wordSets = Logic.prepareData();
    private WordSet choosenWordSet;
    private int currentWordIndex = 0;
    private int currentCharIndex = 0;
    private ArrayList<Text> texts = new ArrayList<>();
    private ArrayList<String> currentlyChoosedWords = new ArrayList<>();
    private Mode mode;
    private TextFlow tf;
    private int time;
    private XYChart.Series series = new XYChart.Series();
    private XYChart.Series seriesAvg = new XYChart.Series();

    private int completeWords = 0;
    private int completeWordsPerSec = 0;
    private int charSum = 0;
    private int correctChars = 0;
    private int incorrectChars = 0;
    private int extraChars = 0;
    private int missedChars = 0;

    public static void main(String[] args) {
        Logic.prepareData();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        addMenu(root);
        setGroupApp(root);
        primaryStage.setTitle("Monkey type");
        primaryStage.setScene(new Scene(root, 650, 200));
        primaryStage.show();
        catchKey(primaryStage.getScene(), root);
    }

    public void addMenu(Group root) {
        Menu language = new Menu("Język");
        Menu options = new Menu("Opcje");

        MenuBar menuBar = new MenuBar();

        VBox vBox = new VBox(menuBar);

        menuBar.getMenus().add(language);
        menuBar.getMenus().add(options);

        for (WordSet ws : wordSets) {
            MenuItem mi = new MenuItem(ws.fileName.substring(0, ws.fileName.length() - 4));
            mi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    choosenWordSet = wordSets.stream().filter(e -> e.fileName.equals(ws.fileName)).findFirst().get();
                }
            });
            language.getItems().add(mi);
        }

        Menu playTime = new Menu("Czas rozgrywki");

        MenuItem t1 = new MenuItem("15");
        t1.setOnAction((e) -> {
            setUpTime(15, root);
        });
        MenuItem t2 = new MenuItem("20");
        t2.setOnAction((e) -> {
            setUpTime(20, root);
        });
        MenuItem t3 = new MenuItem("45");
        t3.setOnAction((e) -> {
            setUpTime(45, root);
        });
        MenuItem t4 = new MenuItem("60");
        t4.setOnAction((e) -> {
            setUpTime(60, root);
        });
        MenuItem t5 = new MenuItem("90");
        t5.setOnAction((e) -> {
            setUpTime(90, root);
        });
        MenuItem t6 = new MenuItem("120");
        t6.setOnAction((e) -> {
            setUpTime(120, root);
        });
        MenuItem t7 = new MenuItem("300");
        t7.setOnAction((e) -> {
            setUpTime(300, root);
        });
        playTime.getItems().addAll(t1, t2, t3, t4, t5, t6, t7);
        options.getItems().add(playTime);

        //DODANIE DO GŁÓWNEGO PANELU
        root.getChildren().add(menuBar);
        root.getChildren().add(vBox);
    }

    public void setGroupApp(Group root) {

        if (choosenWordSet == null) {
            choosenWordSet = wordSets.get(0);
        }

        mode = Mode.APP;
        tf = new TextFlow();

        charSum = 0;
        completeWords = 0;
        completeWordsPerSec = 0;
        correctChars = 0;
        incorrectChars = 0;
        missedChars = 0;
        extraChars = 0;
        currentCharIndex = 0;
        currentWordIndex = 0;
        textIndex = 0;
        currentlyChoosedWords.clear();
        texts.clear();

        ArrayList<String> words;
        words = choosenWordSet.getRandomWords();
        currentlyChoosedWords.addAll(words);

        for (String w : words) {
            for (char c : w.toCharArray()) {
                Text t = new Text(Character.toString(c));
                Font f = Font.font("Arial", FontWeight.BOLD, 14);
                t.setFont(f);
                t.setFill(Color.GREY);

                texts.add(t);
            }

            Text space = new Text(" ");
            texts.add(space);
        }

        tf.setLayoutX(20);
        tf.setLayoutY(50);
        tf.getChildren().addAll(texts);
        root.getChildren().add(tf);
    }

    public void setGroupSummary(Group root) {
        mode = Mode.SUMMARY;
        addMenu(root);
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Czas (s.)");
        yAxis.setLabel("Ilość słów");

        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.getData().add(series);
        lineChart.getData().add(seriesAvg);
        lineChart.setLayoutY(50.0);

        root.getChildren().add(lineChart);

        Label wpm = new Label("WPM:" + ((double) completeWords * (60 / time)) + "\n"
                + "Language:" + choosenWordSet.fileName + "\n" + "Time:" + time + "\n" +
                "Accuracy:" + ((double) correctChars / (double) charSum) + "\n" +
                "Characters:" + correctChars + "/" + incorrectChars + "/" + extraChars + "/" + missedChars);
        Font f = Font.font("Arial", FontWeight.BOLD, 14);
        wpm.setFont(f);
        wpm.setLayoutX(30.0);
        wpm.setLayoutY(450);
        root.getChildren().add(wpm);
    }

    public void clearRoot(Group root) {
        root.getChildren().clear();
    }

    private void setUpTime(int time, Group root) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), event -> {
            clearRoot(root);
            setGroupSummary(root);
        }));
        timeline.setCycleCount(1);
        timeline.play();

        AtomicInteger current = new AtomicInteger(1);
        Timeline setValues = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            series.getData().add(new XYChart.Data<>(current.get(), completeWordsPerSec));
            seriesAvg.getData().add(new XYChart.Data<Integer, Long>(current.get(),((long)completeWords / current.get())));
            completeWordsPerSec = 0;
            current.getAndIncrement();
        }));

        setValues.setCycleCount(time);
        setValues.play();

        this.time = time;

        clearRoot(root);
        setGroupApp(root);
    }

    private int textIndex = 0;

    public void catchKey(Scene main, Group root) {
        main.setOnKeyTyped(e -> {
            if (mode == Mode.APP) {
                String word = currentlyChoosedWords.get(currentWordIndex);

                if (e.getCharacter().equals("\t")) {
                    clearRoot(root);
                    setGroupApp(root);
                    addMenu(root);
                }

                System.out.println("word -> " + word);
                System.out.println("text_index->" + textIndex);
                if (currentCharIndex < word.length()) {
                    char c = word.charAt(currentCharIndex);
                    charSum++;

                    if (e.getCharacter().toLowerCase().charAt(0) == c) {
                        Text t = texts.get(textIndex);
                        t.setFill(Color.GREEN);
                        textIndex++;
                        correctChars++;
                    } else {
                        Text t = texts.get(textIndex);
                        t.setFill(Color.RED);
                        textIndex++;
                        incorrectChars++;
                    }

                    currentCharIndex++;
                }
                else if (e.getCharacter().toLowerCase().charAt(0) == texts.get(textIndex - 1).getText().charAt(0)) {
                    Text t = texts.get(textIndex - 1);
                    t.setText(t.getText() + t.getText().charAt(0));
                    t.setFill(Color.ORANGE);
                    extraChars++;
                }

                if (e.getCharacter().equals(" ") && currentCharIndex == word.length()) {
                    currentCharIndex = 0;
                    currentWordIndex++;
                    textIndex++;
                    completeWords++;
                    completeWordsPerSec++;
                }

                if (currentWordIndex == 29 && currentlyChoosedWords.get(currentWordIndex).length() == currentCharIndex) {
                    clearRoot(root);
                    addMenu(root);
                    setGroupApp(root);
                }
            }
        });
    }
}