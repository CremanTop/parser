import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Random;

public class Request extends Thread
{
    private final String url;
    private Document document;

    public Request(String url)
    {
        this.url = url;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(new Random().nextInt(5000));
            this.document = Jsoup.connect(url).get();
        }
        catch (IOException | InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Document getDocument() {return document;}
}
