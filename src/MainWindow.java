import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainWindow extends JFrame {
    JPanel myPanel;
    JTextField textField1;
    JTextField textField2;
    JTextField textField3;
    JTextField textField4;
    JRadioButton radioButton1;
    JRadioButton radioButton2;
    JRadioButton radioButton3;
    JRadioButton radioButton4;
    JButton OKButton;
    JButton startButton;
    JButton NextButton;
    JTextField textField6;
    JTextField textField7;
    JRadioButton radioButton6;
    JRadioButton radioButton7;
    JLabel label;
    JPanel myPanel2;
    JLabel errorLabel;
    JTextPane textField5;
    JButton examModeButton;
    JLabel examResult;
    JButton examRepeatButton;
    JPanel examResultPanel;
    JButton practiceModeButton;
    JComboBox<String> comboBox1;
    private JButton zurückZumHauptmenüButton;
    int correctAnswers = 0;
    // Damit die Fragen auch dann geprüft werden, wenn nicht explizit auf check, sondern auf next geklickt wurde
    boolean checkedAnswers = false;
    boolean examStarted = false;
    // Für die Fragen Nummern im Prüfungsmodus
    ArrayList<Integer> questions = new ArrayList<>();
    // Sekundenzähler für den Prüfungsmodus
    int time = 0;
    // Dauer der Prüfung in Sekunden
    int maxTimeSeconds = 3600;
    // Anzahl der Fragen in einer Prüfung
    int questionCount = 60;


    //Index zur überprüfung der Antworten
    int checkAnswerIdx = 0;
    //Index für den Prüfungsmodus
    int examIdx = 0;
    String fileName = "";
    // Pfad zum Ordner mit den Fragen
    final String dirPath = "./fragen/";
    ReadWriteJson json = new ReadWriteJson();

    // Liest den Ordner mit den Fragen Dateien aus und schreibt alle json Dateien in die ComboBox
    public void scanDir(){
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile() && listOfFile.toString().endsWith(".json")) {
                    comboBox1.addItem(listOfFile.getName());
                }
            }
        }
    }

    public void setDefaultState(){
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
    }

    public void checkAnswers(int n) throws IOException {
        checkedAnswers = true;
        // Für den Prüfungsmodus zum Zählen der korrekten Fragen
        boolean allCorrect = true;

        ArrayList<String> solutionsArrayList = json.nodeToArrayList(json.readObject("solutions", n));
        // RadioButton.isEnabled() == True, wenn im dazugehörigen Textfeld eine Antwort steht
        if (radioButton1.isEnabled() && radioButton1.isSelected() && solutionsArrayList.contains(textField1.getText())){
            textField1.setBackground(Color.GREEN);
        }
        if (radioButton2.isEnabled() && radioButton2.isSelected() && solutionsArrayList.contains(textField2.getText())){
            textField2.setBackground(Color.GREEN);
        }
        if (radioButton3.isEnabled() && radioButton3.isSelected() && solutionsArrayList.contains(textField3.getText())){
            textField3.setBackground(Color.GREEN);
        }
        if (radioButton4.isEnabled() && radioButton4.isSelected() && solutionsArrayList.contains(textField4.getText())){
            textField4.setBackground(Color.GREEN);
        }
        if (radioButton6.isEnabled() && radioButton6.isSelected() && solutionsArrayList.contains(textField6.getText())){
            textField6.setBackground(Color.GREEN);
        }
        if (radioButton7.isEnabled() && radioButton7.isSelected() && solutionsArrayList.contains(textField7.getText())){
            textField7.setBackground(Color.GREEN);
        }

        if (radioButton1.isEnabled() && !radioButton1.isSelected() && solutionsArrayList.contains(textField1.getText())){
            textField1.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton2.isEnabled() && !radioButton2.isSelected() && solutionsArrayList.contains(textField2.getText())){
            textField2.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton3.isEnabled() && !radioButton3.isSelected() && solutionsArrayList.contains(textField3.getText())){
            textField3.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton4.isEnabled() && !radioButton4.isSelected() && solutionsArrayList.contains(textField4.getText())){
            textField4.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton6.isEnabled() && !radioButton6.isSelected() && solutionsArrayList.contains(textField6.getText())){
            textField6.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton7.isEnabled() && !radioButton7.isSelected() && solutionsArrayList.contains(textField7.getText())){
            textField7.setBackground(Color.RED);
            allCorrect = false;
        }

        if (radioButton1.isEnabled() && radioButton1.isSelected() && !solutionsArrayList.contains(textField1.getText())){
            textField1.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton2.isEnabled() && radioButton2.isSelected() && !solutionsArrayList.contains(textField2.getText())){
            textField2.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton3.isEnabled() && radioButton3.isSelected() && !solutionsArrayList.contains(textField3.getText())){
            textField3.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton4.isEnabled() && radioButton4.isSelected() && !solutionsArrayList.contains(textField4.getText())){
            textField4.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton6.isEnabled() && radioButton6.isSelected() && !solutionsArrayList.contains(textField6.getText())){
            textField6.setBackground(Color.RED);
            allCorrect = false;
        }
        if (radioButton7.isEnabled() && radioButton7.isSelected() && !solutionsArrayList.contains(textField7.getText())){
            textField7.setBackground(Color.RED);
            allCorrect = false;
        }

        if (radioButton1.isEnabled() && !radioButton1.isSelected() && !solutionsArrayList.contains(textField1.getText())){
            textField1.setBackground(Color.WHITE);
        }
        if (radioButton2.isEnabled() && !radioButton2.isSelected() && !solutionsArrayList.contains(textField2.getText())){
            textField2.setBackground(Color.WHITE);
        }
        if (radioButton3.isEnabled() && !radioButton3.isSelected() && !solutionsArrayList.contains(textField3.getText())){
            textField3.setBackground(Color.WHITE);
        }
        if (radioButton4.isEnabled() && !radioButton4.isSelected() && !solutionsArrayList.contains(textField4.getText())){
            textField4.setBackground(Color.WHITE);
        }
        if (radioButton6.isEnabled() && !radioButton6.isSelected() && !solutionsArrayList.contains(textField6.getText())){
            textField6.setBackground(Color.WHITE);
        }
        if (radioButton7.isEnabled() && !radioButton7.isSelected() && !solutionsArrayList.contains(textField7.getText())){
            textField7.setBackground(Color.WHITE);
        }

        // Radio Buttons werden im Prüfungsmodus gesperrt damit Antworten nicht korrigiert werden können
        if (examStarted) {
            radioButton1.setEnabled(false);
            radioButton2.setEnabled(false);
            radioButton3.setEnabled(false);
            radioButton4.setEnabled(false);
            radioButton6.setEnabled(false);
            radioButton7.setEnabled(false);
        }

        if (allCorrect){
            correctAnswers++;
        }
    }

    public void endExam(){
        // Zeige das Ergebnis
        myPanel.setVisible(false);
        myPanel2.setVisible(false);
        examResultPanel.setVisible(true);
        setContentPane(examResultPanel);
        // Prozentsatz an richtigen Antworten
        double percentage = (double) correctAnswers / questionCount *100;
        boolean bestanden = percentage >= 50;
        if (bestanden){
            examResult.setText("Du hast "+correctAnswers+" von "+questionCount+" Punkten erreicht und damit bestanden");
        }else {
            examResult.setText("Du hast "+correctAnswers+" von "+questionCount+" Punkten erreicht und damit nicht bestanden");
        }
        examIdx = 0;
        questions.clear();
        correctAnswers = 0;
        checkedAnswers = false;
        examStarted = false;
        time = 0;
    }
    public void startExam() throws IOException {

        // Anzahl an Fragen
        int len = json.readObject("solutions").size();

        if (len<=questionCount){
            throw new IndexOutOfBoundsException("Nicht genug Fragen für den Prüfungsmodus in der Datei");
        }

        Random rd = new Random();
        int i = 1;
        // Einmalig nach Starten der Prüfung
        if (!examStarted) {
            // questionCount unterschiedliche Fragen werden vorbereitet
            while (i <= questionCount) {
                int n = rd.nextInt(0, len);
                if (!questions.contains(n)) {
                    questions.add(n);
                    i++;
                }
            }
        }
        // Für die überprüfung der Frage
        checkAnswerIdx = questions.get(examIdx);
        examStarted = true;
        // Anzahl an richtigen Antworten
        int lengthS = json.nodeToArrayList(json.readObject("solutions", questions.get(examIdx))).size();
        label.setVisible(true);
        if (lengthS > 1) {
            label.setText(lengthS + " Antworten sind richtig");
        } else {
            label.setText("1 Antwort ist richtig");
        }

        // Lädt eine Frage
        String question = json.readObject("questions", questions.get(examIdx)).toString();
        ArrayList<String> answersArrayList = json.nodeToArrayList(json.readObject("answers", questions.get(examIdx)));
        examIdx++;
        // Alle Textfelder werden Weiß und RadioButtons auf nicht ausgewählt gesetzt
        setDefaultState();
        int answersCount = answersArrayList.size();
        ArrayList<Integer> randomAnswers = json.randomizeAnswerOrder(answersCount);
        switch (answersArrayList.size()) {
            case 2 -> {
                textField1.setText(answersArrayList.get(randomAnswers.get(0)));
                textField2.setText(answersArrayList.get(randomAnswers.get(1)));
                textField3.setText("");
                textField4.setText("");
                textField5.setText("Frage: " + question);
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(false);
                radioButton4.setEnabled(false);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
            }
            case 3 -> {
                textField1.setText(answersArrayList.get(randomAnswers.get(0)));
                textField2.setText(answersArrayList.get(randomAnswers.get(1)));
                textField3.setText(answersArrayList.get(randomAnswers.get(2)));
                textField4.setText("");
                textField5.setText("Frage: " + question);
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(false);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
            }
            case 4 -> {
                textField1.setText(answersArrayList.get(randomAnswers.get(0)));
                textField2.setText(answersArrayList.get(randomAnswers.get(1)));
                textField3.setText(answersArrayList.get(randomAnswers.get(2)));
                textField4.setText(answersArrayList.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
            }
            case 5 -> {
                textField1.setText(answersArrayList.get(randomAnswers.get(0)));
                textField2.setText(answersArrayList.get(randomAnswers.get(1)));
                textField3.setText(answersArrayList.get(randomAnswers.get(2)));
                textField4.setText(answersArrayList.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText(answersArrayList.get(randomAnswers.get(4)));
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(true);
                radioButton7.setEnabled(false);
            }
            case 6 -> {
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(true);
                radioButton7.setEnabled(true);
                textField1.setText(answersArrayList.get(randomAnswers.get(0)));
                textField2.setText(answersArrayList.get(randomAnswers.get(1)));
                textField3.setText(answersArrayList.get(randomAnswers.get(2)));
                textField4.setText(answersArrayList.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText(answersArrayList.get(randomAnswers.get(4)));
                textField7.setText(answersArrayList.get(randomAnswers.get(5)));
            }
        }
    }
    public void startPractice() throws IOException {

        Random rd = new Random();
        // Anzahl der Fragen
        int len = json.readJson().get("solutions").size();
        // Zufälliger index von 0 bis einschließlich len-1
        int n = rd.nextInt(0, len);
        // Zur späteren kontrolle
        checkAnswerIdx = n;
        // Anzahl der richtigen Antworten zur aktuellen Frage
        int lengthS = json.readObject("solutions", n).size();
        label.setVisible(true);
        if (lengthS>1){
            label.setText(lengthS+" Antworten sind richtig");
        }else {
            label.setText("1 Antwort ist richtig");
        }

        // Lädt eine Frage
        String question = json.readObject("questions", n).toString();
        ArrayList<String> answerArrayList = json.nodeToArrayList(json.readObject("answers", n));

        // Alle Textfelder werden Weiß und RadioButtons auf nicht ausgewählt gesetzt
        setDefaultState();

        ArrayList<Integer> randomAnswers = new ArrayList<>();
        switch (answerArrayList.size()) {
            case 2 -> {
                while (randomAnswers.size() < answerArrayList.size()){
                    int num = rd.nextInt(0,2);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                textField1.setText(answerArrayList.get(randomAnswers.get(0)));
                textField2.setText(answerArrayList.get(randomAnswers.get(1)));
                textField3.setText("");
                textField4.setText("");
                textField5.setText("Frage: " + question);
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(false);
                radioButton4.setEnabled(false);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
            }
            case 3 -> {
                while (randomAnswers.size() < answerArrayList.size()){
                    int num = rd.nextInt(0,3);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                textField1.setText(answerArrayList.get(randomAnswers.get(0)));
                textField2.setText(answerArrayList.get(randomAnswers.get(1)));
                textField3.setText(answerArrayList.get(randomAnswers.get(2)));
                textField4.setText("");
                textField5.setText("Frage: " + question);
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(false);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
            }
            case 4 -> {
                while (randomAnswers.size() < answerArrayList.size()){
                    int num = rd.nextInt(0,4);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                textField1.setText(answerArrayList.get(randomAnswers.get(0)));
                textField2.setText(answerArrayList.get(randomAnswers.get(1)));
                textField3.setText(answerArrayList.get(randomAnswers.get(2)));
                textField4.setText(answerArrayList.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText("");
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(false);
                radioButton7.setEnabled(false);
            }
            case 5 -> {
                while (randomAnswers.size() < answerArrayList.size()){
                    int num = rd.nextInt(0,5);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                textField1.setText(answerArrayList.get(randomAnswers.get(0)));
                textField2.setText(answerArrayList.get(randomAnswers.get(1)));
                textField3.setText(answerArrayList.get(randomAnswers.get(2)));
                textField4.setText(answerArrayList.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText(answerArrayList.get(randomAnswers.get(4)));
                textField7.setText("");
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(true);
                radioButton7.setEnabled(false);
            }
            case 6 -> {
                while (randomAnswers.size() < answerArrayList.size()){
                    int num = rd.nextInt(0,6);
                    if (!randomAnswers.contains(num)){
                        randomAnswers.add(num);
                    }
                }
                textField1.setText(answerArrayList.get(randomAnswers.get(0)));
                textField2.setText(answerArrayList.get(randomAnswers.get(1)));
                textField3.setText(answerArrayList.get(randomAnswers.get(2)));
                textField4.setText(answerArrayList.get(randomAnswers.get(3)));
                textField5.setText("Frage: " + question);
                textField6.setText(answerArrayList.get(randomAnswers.get(4)));
                textField7.setText(answerArrayList.get(randomAnswers.get(5)));
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                radioButton3.setEnabled(true);
                radioButton4.setEnabled(true);
                radioButton6.setEnabled(true);
                radioButton7.setEnabled(true);
            }
        }
    }
    public void launchGUI() {
        setContentPane(myPanel2);
        setTitle("Lernprogramm");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        scanDir();

        zurückZumHauptmenüButton.addActionListener((e ->{
            myPanel2.setVisible(true);
            myPanel.setVisible(false);
            setContentPane(myPanel2);
            startButton.setVisible(true);
            OKButton.setVisible(false);
            NextButton.setVisible(false);
            examIdx = 0;
            questions.clear();
            correctAnswers = 0;
            checkedAnswers = false;
            examStarted = false;
            time = 0;
        }));
        examModeButton.addActionListener(e -> {
            if (Objects.requireNonNull(comboBox1.getSelectedItem()).toString().strip().length() > 1) {
                try {
                    fileName = dirPath + comboBox1.getSelectedItem();
                    json.setFileName(fileName);
                    startExam();
                    myPanel2.setVisible(false);
                    myPanel.setVisible(true);
                    setContentPane(myPanel);
                    startButton.setVisible(false);
                    OKButton.setVisible(true);
                    NextButton.setVisible(true);
                }catch (FileNotFoundException ee) {
                    errorLabel.setVisible(true);
                    errorLabel.setText("Datei nicht gefunden");
                }catch(IOException | IndexOutOfBoundsException i) {
                    errorLabel.setVisible(true);
                    errorLabel.setText("Die Datei enthält nicht genug Fragen für den Prüfungsmodus bitte wähle eine andere Datei oder nutze den Übungsmodus");
                }
            }else{
                errorLabel.setVisible(true);
                errorLabel.setText("Bitte erst einen Dateinamen eingeben");
            }
        });

        startButton.addActionListener(e -> {
            if (Objects.requireNonNull(comboBox1.getSelectedItem()).toString().strip().length() > 1){
                try {
                    fileName = dirPath + comboBox1.getSelectedItem();
                    json.setFileName(fileName);
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
                checkAnswers(checkAnswerIdx);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        NextButton.addActionListener(e -> {
            if (!checkedAnswers){

                try {
                    checkAnswers(checkAnswerIdx);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (examIdx >= questionCount){
                    endExam();
                }
            }
            checkedAnswers = false;
            if (!examStarted) {
                try {
                    startPractice();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else if(examIdx >= questionCount) {
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
                examStarted = false;
                myPanel2.setVisible(false);
                myPanel.setVisible(true);
                setContentPane(myPanel);
                OKButton.setVisible(true);
                NextButton.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            if (examStarted) {
                time++;
                if (time > maxTimeSeconds){
                    endExam();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

    }
}