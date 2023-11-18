import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {
    private JPanel myPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JButton OKButton;
    private JButton startButton;
    private JButton NextButton;
    private JTextField textField6;
    private JTextField textField7;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JLabel label;
    private JPanel myPanel2;
    private JLabel errorLabel;
    private JTextPane textField5;
    private JButton examModeButton;
    private JLabel examResult;
    private JButton examRepeatButton;
    private JPanel examResultPanel;
    private JButton practiceModeButton;
    private JComboBox comboBox1;
    private boolean examMode = false;
    private int correctAnswers = 0;
    private boolean checkedAnswers = false;
    private boolean threadStarted = false;
    ArrayList<Integer> questions = new ArrayList<>();
    private int time = 0;



    int num = 0;
    int idx = 0;
    String fileName = "";

    public void scanDir(){
        File folder = new File("./json/");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile() && listOfFile.toString().endsWith(".json")) {
                    comboBox1.addItem(listOfFile.getName());
                }
            }
        }
    }

    public void checkAnswers(int n) throws IOException {
        checkedAnswers = true;
        boolean allCorrect = true;
        fileName =(String) comboBox1.getSelectedItem();

        if (fileName != null && !fileName.endsWith(".json")) {
            fileName = "json/" + fileName + ".json";
        }

        JsonNode node = new ObjectMapper().readTree(new File(fileName));
        JsonNode correct = node.get("solutions").get(String.valueOf(n));
        ArrayList<String> solutions = new ArrayList<>();
        for (JsonNode i:correct) {
            solutions.add(i.toString());
        }
        if (radioButton1.isEnabled() && radioButton1.isSelected() && solutions.contains(textField1.getText())){
            textField1.setBackground(Color.GREEN);
        }
        if (radioButton2.isEnabled() && radioButton2.isSelected() && solutions.contains(textField2.getText())){
            textField2.setBackground(Color.GREEN);
        }
        if (radioButton3.isEnabled() && radioButton3.isSelected() && solutions.contains(textField3.getText())){
            textField3.setBackground(Color.GREEN);
        }
        if (radioButton4.isEnabled() && radioButton4.isSelected() && solutions.contains(textField4.getText())){
            textField4.setBackground(Color.GREEN);
        }
        if (radioButton6.isEnabled() && radioButton6.isSelected() && solutions.contains(textField6.getText())){
            textField6.setBackground(Color.GREEN);
        }
        if (radioButton7.isEnabled() && radioButton7.isSelected() && solutions.contains(textField7.getText())){
            textField7.setBackground(Color.GREEN);
        }

        if (radioButton1.isEnabled() && !radioButton1.isSelected() && solutions.contains(textField1.getText())){
            textField1.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton2.isEnabled() && !radioButton2.isSelected() && solutions.contains(textField2.getText())){
            textField2.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton3.isEnabled() && !radioButton3.isSelected() && solutions.contains(textField3.getText())){
            textField3.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton4.isEnabled() && !radioButton4.isSelected() && solutions.contains(textField4.getText())){
            textField4.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton6.isEnabled() && !radioButton6.isSelected() && solutions.contains(textField6.getText())){
            textField6.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton7.isEnabled() && !radioButton7.isSelected() && solutions.contains(textField7.getText())){
            textField7.setBackground(Color.RED);
            allCorrect = false;
        }

        if (radioButton1.isEnabled() && radioButton1.isSelected() && !solutions.contains(textField1.getText())){
            textField1.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton2.isEnabled() && radioButton2.isSelected() && !solutions.contains(textField2.getText())){
            textField2.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton3.isEnabled() && radioButton3.isSelected() && !solutions.contains(textField3.getText())){
            textField3.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton4.isEnabled() && radioButton4.isSelected() && !solutions.contains(textField4.getText())){
            textField4.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton6.isEnabled() && radioButton6.isSelected() && !solutions.contains(textField6.getText())){
            textField6.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton7.isEnabled() && radioButton7.isSelected() && !solutions.contains(textField7.getText())){
            textField7.setBackground(Color.RED);
            allCorrect = false;
        }

        if (radioButton1.isEnabled() && !radioButton1.isSelected() && !solutions.contains(textField1.getText())){
            textField1.setBackground(Color.WHITE);
        }
        if (radioButton2.isEnabled() && !radioButton2.isSelected() && !solutions.contains(textField2.getText())){
            textField2.setBackground(Color.WHITE);
        }
        if (radioButton3.isEnabled() && !radioButton3.isSelected() && !solutions.contains(textField3.getText())){
            textField3.setBackground(Color.WHITE);
        }
        if (radioButton4.isEnabled() && !radioButton4.isSelected() && !solutions.contains(textField4.getText())){
            textField4.setBackground(Color.WHITE);
        }
        if (radioButton6.isEnabled() && !radioButton6.isSelected() && !solutions.contains(textField6.getText())){
            textField6.setBackground(Color.WHITE);
        }
        if (radioButton7.isEnabled() && !radioButton7.isSelected() && !solutions.contains(textField7.getText())){
            textField7.setBackground(Color.WHITE);
        }

        radioButton1.setEnabled(false);
        radioButton2.setEnabled(false);
        radioButton3.setEnabled(false);
        radioButton4.setEnabled(false);
        radioButton6.setEnabled(false);
        radioButton7.setEnabled(false);

        if (allCorrect){
            correctAnswers++;
        }
    }

    public void endExam(){
        idx = 0;
        myPanel.setVisible(false);
        myPanel2.setVisible(false);
        examResultPanel.setVisible(true);
        setContentPane(examResultPanel);
        double percentage = (double) correctAnswers / 60 *100;
        boolean bestanden = percentage >= 50;
        if (bestanden){
            examResult.setText("Du hast "+correctAnswers+" von 60 Punkten erreicht und damit bestanden");
        }else {
        examResult.setText("Du hast "+correctAnswers+" von 60 Punkten erreicht und damit nicht bestanden");
        }
        questions.clear();
        correctAnswers = 0;
        examMode = false;
        checkedAnswers = false;
        threadStarted = false;
        time = 0;
    }
    public void startExam() throws IOException {

        fileName =(String) comboBox1.getSelectedItem();

        if (fileName != null && !fileName.endsWith(".json")) {
            fileName = "json/" + fileName + ".json";
        }

        JsonNode node = new ObjectMapper().readTree(new File(fileName));
        int len = node.get("solutions").size();

        if (len<=60){
            examMode = false;
            throw new IndexOutOfBoundsException("Nicht genug Fragen für den Prüfungsmodus in der Datei");
        }

        Random rd = new Random();
        int i = 1;
        if (!threadStarted) {
            while (i <= 60) {
                int n = rd.nextInt(0, len);
                if (!questions.contains(n)) {
                    questions.add(n);
                    i++;
                }
            }
        }
        threadStarted = true;
        int lengthS = node.get("solutions").get(String.valueOf(questions.get(idx))).size();
        num = questions.get(idx);
        label.setVisible(true);
        if (lengthS > 1) {
            label.setText(lengthS + " Antworten sind richtig");
        } else {
            label.setText("1 Antwort ist richtig");
        }

        String question = node.get("questions").get(String.valueOf(questions.get(idx))).toString();
        JsonNode answers = node.get("answers").get(String.valueOf(questions.get(idx)));
        idx++;
        ArrayList<String> antworten = new ArrayList<>();
        textField1.setBackground(Color.WHITE);
        textField2.setBackground(Color.WHITE);
        textField3.setBackground(Color.WHITE);
        textField4.setBackground(Color.WHITE);
        textField6.setBackground(Color.WHITE);
        textField7.setBackground(Color.WHITE);

        radioButton1.setSelected(false);
        radioButton2.setSelected(false);
        radioButton3.setSelected(false);
        radioButton4.setSelected(false);
        radioButton6.setSelected(false);
        radioButton7.setSelected(false);

        for (JsonNode z : answers) {
            antworten.add(z.toString());
        }
        ArrayList<Integer> randomAnswers = new ArrayList<>();
        switch (antworten.size()) {
            case 2 -> {
                while (randomAnswers.size() < antworten.size()) {
                    int num = rd.nextInt(0, 2);
                    if (!randomAnswers.contains(num)) {
                        randomAnswers.add(num);
                    }
                }
                textField3.setText("");
                textField4.setText("");
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(false);
                radioButton4.setEnabled(false);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
                myPanel.updateUI();
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField5.setText("Frage: " + question);
            }
            case 3 -> {
                while (randomAnswers.size() < antworten.size()) {
                    int num = rd.nextInt(0, 3);
                    if (!randomAnswers.contains(num)) {
                        randomAnswers.add(num);
                    }
                }
                textField4.setText("");
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(false);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
                myPanel.updateUI();
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField3.setText(antworten.get(randomAnswers.get(2)));
                textField5.setText("Frage: " + question);
            }
            case 4 -> {
                while (randomAnswers.size() < antworten.size()) {
                    int num = rd.nextInt(0, 4);
                    if (!randomAnswers.contains(num)) {
                        randomAnswers.add(num);
                    }
                }
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
                myPanel.updateUI();
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField3.setText(antworten.get(randomAnswers.get(2)));
                textField4.setText(antworten.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
            }
            case 5 -> {
                while (randomAnswers.size() < antworten.size()) {
                    int num = rd.nextInt(0, 5);
                    if (!randomAnswers.contains(num)) {
                        randomAnswers.add(num);
                    }
                }
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(true);
                radioButton7.setEnabled(false);
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField3.setText(antworten.get(randomAnswers.get(2)));
                textField4.setText(antworten.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText(antworten.get(randomAnswers.get(4)));
            }
            case 6 -> {
                while (randomAnswers.size() < antworten.size()) {
                    int num = rd.nextInt(0, 6);
                    if (!randomAnswers.contains(num)) {
                        randomAnswers.add(num);
                    }
                }
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(true);
                radioButton7.setEnabled(true);
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField3.setText(antworten.get(randomAnswers.get(2)));
                textField4.setText(antworten.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText(antworten.get(randomAnswers.get(4)));
                textField7.setText(antworten.get(randomAnswers.get(5)));
            }
        }
    }
    public void startPractice() throws IOException {

        fileName =(String) comboBox1.getSelectedItem();

        if (fileName != null && !fileName.endsWith(".json")) {
            fileName = "json/" + fileName + ".json";
        }

        Random rd = new Random();
        JsonNode node = new ObjectMapper().readTree(new File(fileName));
        int len = node.get("solutions").size();
        int n = rd.nextInt(0, len);
        num = n;
        int lengthS = node.get("solutions").get(String.valueOf(n)).size();
        label.setVisible(true);
        if (lengthS>1){
            label.setText(lengthS+" Antworten sind richtig");
        }else {
            label.setText("1 Antwort ist richtig");
        }
        num = n;
        String question = node.get("questions").get(String.valueOf(n)).toString();
        JsonNode answers = node.get("answers").get(String.valueOf(n));
        ArrayList<String> antworten = new ArrayList<>();
        textField1.setBackground(Color.WHITE);
        textField2.setBackground(Color.WHITE);
        textField3.setBackground(Color.WHITE);
        textField4.setBackground(Color.WHITE);
        textField6.setBackground(Color.WHITE);
        textField7.setBackground(Color.WHITE);

        radioButton1.setSelected(false);
        radioButton2.setSelected(false);
        radioButton3.setSelected(false);
        radioButton4.setSelected(false);
        radioButton6.setSelected(false);
        radioButton7.setSelected(false);

        for (JsonNode i:answers) {
            antworten.add(i.toString());
        }
        ArrayList<Integer> randomAnswers = new ArrayList<>();
        switch (antworten.size()) {
            case 2 -> {
                while (randomAnswers.size() < antworten.size()){
                    int num = rd.nextInt(0,2);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                textField3.setText("");
                textField4.setText("");
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(false);
                radioButton4.setEnabled(false);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
                myPanel.updateUI();
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField5.setText("Frage: " + question);
            }
            case 3 -> {
                while (randomAnswers.size() < antworten.size()){
                    int num = rd.nextInt(0,3);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                textField4.setText("");
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(false);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
                myPanel.updateUI();
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField3.setText(antworten.get(randomAnswers.get(2)));
                textField5.setText("Frage: " + question);
            }
            case 4 -> {
                while (randomAnswers.size() < antworten.size()){
                    int num = rd.nextInt(0,4);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
                myPanel.updateUI();
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField3.setText(antworten.get(randomAnswers.get(2)));
                textField4.setText(antworten.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
            }
            case 5 -> {
                while (randomAnswers.size() < antworten.size()){
                    int num = rd.nextInt(0,5);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(true);
                radioButton7.setEnabled(false);
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField3.setText(antworten.get(randomAnswers.get(2)));
                textField4.setText(antworten.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText(antworten.get(randomAnswers.get(4)));
            }
            case 6 -> {
                while (randomAnswers.size() < antworten.size()){
                    int num = rd.nextInt(0,6);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(true);
                radioButton7.setEnabled(true);
                textField1.setText(antworten.get(randomAnswers.get(0)));
                textField2.setText(antworten.get(randomAnswers.get(1)));
                textField3.setText(antworten.get(randomAnswers.get(2)));
                textField4.setText(antworten.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText(antworten.get(randomAnswers.get(4)));
                textField7.setText(antworten.get(randomAnswers.get(5)));
            }
        }
    }
    public void launchGUI() {
        setContentPane(myPanel2);
        setTitle("Learning");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        scanDir();

        examModeButton.addActionListener(e -> {
            if (comboBox1.getSelectedItem().toString().strip().length() > 1) {
                try {
                    startExam();
                    myPanel2.setVisible(false);
                    myPanel.setVisible(true);
                    setContentPane(myPanel);
                    startButton.setVisible(false);
                    OKButton.setVisible(true);
                    NextButton.setVisible(true);
                    examMode = true;
                }catch (FileNotFoundException ee) {
                    errorLabel.setVisible(true);
                    errorLabel.setText("Datei nicht gefunden");
                }catch(IOException | IndexOutOfBoundsException idx) {
                    errorLabel.setVisible(true);
                    errorLabel.setText("Die Datei enthält nicht genug Fragen für den Prüfungsmodus bitte wähle eine andere Datei oder nutze den Übungsmodus");
                }
            }else{
                errorLabel.setVisible(true);
                errorLabel.setText("Bitte erst einen Dateinamen eingeben");
            }
        });

        startButton.addActionListener(e -> {
            if (comboBox1.getSelectedItem().toString().strip().length() > 1){
            try {
                startPractice();
                myPanel2.setVisible(false);
                myPanel.setVisible(true);
                setContentPane(myPanel);
                startButton.setVisible(false);
                OKButton.setVisible(true);
                NextButton.setVisible(true);
            } catch (FileNotFoundException ex) {
                errorLabel.setVisible(true);
                errorLabel.setText("Datei nicht gefunden");
                }catch (IOException ignored){}
            }else{
                errorLabel.setVisible(true);
                errorLabel.setText("Bitte erst einen Dateinamen eingeben");
            }
        });
        OKButton.addActionListener(e -> {
            checkedAnswers = true;
            try {
                checkAnswers(num);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        NextButton.addActionListener(e -> {
            if (!checkedAnswers){

                try {
                    checkAnswers(num);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (idx >= 60){
                    endExam();
                }
            }
            checkedAnswers = false;
            if (!examMode) {
                try {
                    startPractice();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else if(idx >= 60) {
                endExam();
            }else {
                try {
                    startExam();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        examRepeatButton.addActionListener(e -> {
            try {
                startExam();
                examMode = true;
                myPanel2.setVisible(false);
                myPanel.setVisible(true);
                setContentPane(myPanel);
                OKButton.setVisible(true);
                NextButton.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        practiceModeButton.addActionListener(e -> {
            try {
                startPractice();
                examMode = false;
                myPanel2.setVisible(false);
                myPanel.setVisible(true);
                setContentPane(myPanel);
                OKButton.setVisible(true);
                NextButton.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        int maxTimeSeconds = 3600;
        Main main = new Main();
        main.launchGUI();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            if (main.examMode) {
                main.time++;
                if (main.time > maxTimeSeconds){
                    main.endExam();
                }
            }
            }, 0, 1, TimeUnit.SECONDS);

    }
}