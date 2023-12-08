import com.sun.xml.internal.ws.message.stream.StreamHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        for (int i = 0; i < 10; i++){
            System.out.println(i);
            Thread.sleep(100);
        }

//        Document doc = Jsoup.connect("https://4pda.to/")
////                .userAgent("Chrome/4.0.249.0 Safari/532.5")
////                .referrer("http://www.google.com")
//                .get();
//
//        Elements elements = doc.getElementsByAttributeValue("itemprop", "url");
//
//        for (Element element : elements) {
//            System.out.println(element.attr("title"));
//        }

        //Main.main2();


//        Document doc = Jsoup.connect("https://www.russianfood.com/")
////                .userAgent("Chrome/4.0.249.0 Safari/532.5")
////                .referrer("http://www.google.com")
//                .get();
//
//        Elements elements = doc.getElementsByAttributeValue("class", "annonce annonce_orange");
//        //Elements elements = doc.getElementsByAttributeValue("class", "section");
//
//        for (Element element : elements) {
//            Element elem = element.child(1).child(0).child(1).child(1).child(0).child(1).child(0).child(0).child(0).child(1);
//            if (elem.childrenSize() > 0)
//            {
//                elem = elem.child(0);
//                System.out.println(elem.text() + " | " + elem.attr("href"));
//            }
//
//            //if (Objects.equals(element.text(), "Рецепты"))
////            {
////                System.out.println(element);
////                //System.out.println(element.child(1).child(0).child(1).child(1).child(0).child(1).child(0).child(0).child(0).child(1)/*.child(0)*/);
////            }
//            //if (Objects.equals(element.text(), "Рецепты"))
//            //{
//            //System.out.println(element.child(1).child(0).child(1).child(1).child(0).child(1).child(0).child(0).child(0).child(1)/*.child(0)*/);
//            //}
//        }

//        System.out.println(doc);
//
//        Elements listNews = doc.select("div#tabnews_newsc.content-tabs__items.content-tabs__items_active_true");
//        for (Element element : listNews.select("a"))
//            System.out.println(element.text());
    }

    public static void main2() throws IOException
    {
        //ArrayList<Request> threads = new ArrayList<>(100);

        for (int i = 1; i < 100; i++)
        {
            String url = "https://www.russianfood.com/recipes/recipe.php?rid=" + i;
//            Request thread = new Request(url);
//            threads.add(thread);
//            thread.start();
            Document doc = Jsoup.connect(url).get();

            //System.out.println(doc.baseUri());
            if (Objects.equals(doc.baseUri(), url))
            {
                System.out.println(doc.select("head > title").text());
            }
        }

//        for (Request req : threads)
//        {
//            System.out.println(req.getDocument().select("head > title").text());
//        }
    }

//    public static void write(String ) {
//
//        try(FileWriter writer = new FileWriter("notes3.txt", false))
//        {
//            // запись всей строки
//            String text = "Hello Gold!";
//            writer.write(text);
//            // запись по символам
//            writer.flush();
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }
//    }
}
