package Tech.CodingClub.utility;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TopKeywordAnalayzer  implements  Runnable {
    private  final String filePath;

    public TopKeywordAnalayzer(String filePath) {
        this.filePath =filePath;
    }
    public void run(){
        ArrayList<String> keywordFileData = FileUtility.readFileAsList("C:\\Users\\Santosh\\IdeaProjects\\Java\\src\\data\\practice\\IndianNationalAnthem.txt");

        //Read every row
        HashMap<String,Integer> keywordCounter = new HashMap<String,Integer>();
        for (String row : keywordFileData) {
            String[] keywords = row.split(" ");

           //Within row process each keyword
            for (String keyword : keywords) {
                String str = keyword.toLowerCase();
                //if not here in hashmap
                if (!keywordCounter.containsKey(str)) {

                    //First time any keyword found
                    keywordCounter.put(str, 1);
                }else {
                    Integer value = keywordCounter.get(str);
                    keywordCounter.put(str, value + 1);
                }
            }
        }
        ArrayList<KeywordCount> keywordCountArrayList = new ArrayList<KeywordCount>();
        for (String keyword : keywordCounter.keySet()) {
            KeywordCount keywordCount = new KeywordCount(keyword, keywordCounter.get(keyword));
            keywordCountArrayList.add(keywordCount);
        }
        Collections.sort(keywordCountArrayList, new Comparator<KeywordCount>() {
            @Override
            public int compare(KeywordCount o1, KeywordCount o2) {
                return o2.count- o1.count;
            }
        });
//        for (KeywordCount keywordCount : keywordCountArrayList) {
//            System.out.println(keywordCount.keyword+ " "+keywordCount.count);
//        }
        String json = new Gson().toJson(keywordCounter);
        System.out.println(json+"\n");
    }
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(1);
        taskManager.waitTillQueueIsFreeAndAddTask(new TopKeywordAnalayzer("C:\\Users\\Santosh\\IdeaProjects\\Java\\src\\data\\practice\\IndianNationalAnthem.txt"));

    }
}
