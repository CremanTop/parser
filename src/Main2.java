import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import parser.Parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class Main2
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        for (int i = 1; i <= 173000; i++) {
            //int id = rnd(1, 172000);

            System.out.println(i);

            String url = "https://www.russianfood.com/recipes/recipe.php?rid=" + i;
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (iPad; CPU OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148").referrer("http://www.google.com").get();
            //System.out.println(doc);
            Thread.sleep(100);

            if (Objects.equals(doc.baseUri(), url))
            {
                String text = Parser.getRecipe(doc).createJSON();
                try(PrintWriter writer = new PrintWriter("src/files/" + i + ".json", "UTF-8"))
                {
                    writer.write(text);
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
