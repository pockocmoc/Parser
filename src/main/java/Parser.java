import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.SplittableRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static org.jsoup.nodes.Document getPage() throws Exception {
        String url = "https://mail.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    private static Pattern pattern = Pattern.compile("\\d{2}\\u00B0");

    private static String getTempFromString(String stringTemp) throws Exception {
        Matcher matcher = pattern.matcher(stringTemp);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Can't extract temp from string");


    }


    public static void main(String[] args) throws Exception {
        Document page = getPage();

        Element tableWth = page.selectFirst("span[class =weather__temp svelte-1g9jevh]");
        //Elements names = tableWth.select("span[data-testid =weather__info-period svelte-1g9jevh]");
        //Elements values = tableWth.select("span[data-testid =weather-temp]");
        //System.out.println(tableWth);

        String temp = String.valueOf(tableWth);

        String t = getTempFromString(temp);
        System.out.println("Температеру воздуха, ощущается как: " +t);



    }
}
