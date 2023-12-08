package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Parser
{
    public static Recipe getRecipe(Document doc) {
        return new Recipe(getTitle(doc), getId(doc), getDescription(doc), getAnnounce(doc), getProducts(doc), getStepsWithPhoto(doc));
    }

    public static Document getDoc(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla /5.0").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static int getId(Document doc) {
        String url = doc.baseUri();
        return Integer.parseInt(url.substring(url.indexOf("=") + 1));
    }

    private static String getTitle(Document doc) {
        Element element = doc.getElementsByAttributeValue("data-in_c_id", "title").get(0);
        int index = element.elementSiblingIndex();

        return element.parent().child(index + 1).text();
    }


    public static ArrayList<Ingredient> getProducts(Document doc) {
        Elements elements = doc.getElementsByClass("prod");
        Element productTable = elements.get(0).parent().parent().parent();

        ArrayList<Ingredient> ingredients = new ArrayList<>();

        i_loop:
        for (int i = 1; i < productTable.childrenSize(); i++) {
            Element ing = productTable.child(i);
            StringBuilder count = new StringBuilder();
            Ingredient ingredient = new Ingredient();

            for (int j = 0; j < ing.childrenSize(); j++) {
                String part = ing.child(j).text();

                //System.out.println(ing);
                //System.out.println(ing.childrenSize());
                if (ing.childrenSize() == 1) {
                    if (part.contains("-"))
                    {
                        try
                        {
                            ingredient.setName(part.substring(0, part.indexOf("-") - 1).trim().toLowerCase());
                            ingredient.setCount(part.substring(part.indexOf("-") + 1).trim());
                        }
                        catch (StringIndexOutOfBoundsException e) {
                            e.printStackTrace();
                            continue i_loop;
                        }
                    }
                    else continue i_loop;
                } else if (j == 0) {
                    ingredient.setName(part.trim().toLowerCase());
                } else {
                    count.append(" ").append(part);
                }
            }
            if (!count.toString().equals("")) {
                ingredient.setCount(count.toString().trim());
            }

            ingredients.add(ingredient);
        }

        return ingredients;
    }

    public static ArrayList<String> getStepsWithPhoto(Document doc) {
        ArrayList<String> steps = new ArrayList<>();
        Elements elements = doc.getElementsByClass("step_n");

        for (Element el : elements) {
            steps.add(el.child(1).text());
        }

        return steps;
    }

    private static ArrayList<String> getStepsFromAttr(Document doc, String attr) {
        ArrayList<String> steps = new ArrayList<>();
        Elements elements = doc.getElementsByAttributeValue("data-in_c_id", attr);

        if (elements.size() == 0) return steps;

        Element element = elements.get(0);
        int index = element.elementSiblingIndex();

        for (Element step : element.parent().child(index + 1).children())
        {
            steps.add(step.text());
        }

        return steps;
    }

    public static ArrayList<String> getDescription(Document doc) {
        return getStepsFromAttr(doc, "main_text");
    }

    public static ArrayList<String> getAnnounce(Document doc) {
        return getStepsFromAttr(doc,"announce");
    }
}
