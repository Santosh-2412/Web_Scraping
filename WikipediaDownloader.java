package Tech.CodingClub.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WikipediaDownloader  implements Runnable {
    public WikipediaDownloader(){

    }

    public WikipediaDownloader(String keyword) {
        this.keyword = keyword;
    }

    private  String keyword;
    public void run() {
        //1.  Get clean Keyword
        //2.  Get the URL for Wikipedia
        //3.  Make a Get request to wikipedia!
        //4. Parsing the useful results using JSOUP
        //5. Showing result!
        if (this.keyword == null || this.keyword.length() == 0) {
            return;
        }

        // Step 1
        this.keyword = this.keyword.trim().replaceAll("[ ]+","_");

        //Step 2
        String wikiUrl = getWikipediaUrlForQuery(this.keyword);
        String response = "";
        String imageUrl = null;

        try {
            // Step 3
            String wikipediaResponseHTML = HttpURLConnectionExample.sendGet(wikiUrl);
            // System.out.println(wikipediaResponseHTML);

            // Step 4
            Document document = Jsoup.parse(wikipediaResponseHTML,"https://en.wikipedia.org" );

            Elements childElements = (Elements) document.body().select(".mw-parser-output > *");

            // Autometa the concept of finding particular element
            int state = 0;
            for (Element childElement : childElements) {

                if (state == 0) {
                    if (childElement.tagName().equals("table")) {
                        state = 1;

                    }
                }else if(state == 1){
                    if (childElement.tagName().equals("p")) {
                        state = 2;
                        response = childElement.text();
                        break;
                    }

                }

                try {
                    imageUrl = document.body().select(".infobox img").get(0).attr("src");
                } catch (Exception ex) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        WikiResult wikiResult =new WikiResult(this.keyword,response,imageUrl);
        //Push result into database now we will only print json

        // For printing in preety format
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(wikiResult);
        System.out.println(json);
    }

    private String getWikipediaUrlForQuery(String cleanKeyword) {
        return "https://en.wikipedia.org/wiki/"+cleanKeyword;
    }


    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(20);

        String arr[] = {"India" ,"Ratan Tata", "United States" , "Sundar Pichai"};
        for(String Keyword : arr) {

            WikipediaDownloader wikipediaDownloader = new WikipediaDownloader(Keyword);
            taskManager.waitTillQueueIsFreeAndAddTask(wikipediaDownloader);

        }

    }
}
