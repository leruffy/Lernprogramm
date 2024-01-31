import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ReadWriteJson {
    String fileName;
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public JsonNode readJson() throws IOException {
        return new ObjectMapper().readTree(new File(this.fileName));
    }

    public ArrayList<Integer> randomizeAnswerOrder(int arrLen){
        ArrayList<Integer> randomAnswers = new ArrayList<>();
        Random rd = new Random();

        while (randomAnswers.size() < arrLen) {
            int num = rd.nextInt(0, arrLen);
            if (!randomAnswers.contains(num)) {
                randomAnswers.add(num);
            }
        }
        return  randomAnswers;
    }

    public ArrayList<String> nodeToArrayList(JsonNode node){
        ArrayList<String> arrayList = new ArrayList<>();
        for (JsonNode i : node) {
            arrayList.add(i.toString());
        }
        return arrayList;
    }

    public JsonNode readObject(String obj) throws IOException {

        return readJson().get(obj);
    }

    public JsonNode readObject(String obj, int n) throws IOException {

        return readJson().get(obj).get(String.valueOf(n));
    }
}
