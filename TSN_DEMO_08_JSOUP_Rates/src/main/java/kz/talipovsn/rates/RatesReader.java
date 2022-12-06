package kz.talipovsn.rates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// СОЗДАТЕЛЬ КОТИРОВОК ВАЛЮТ
public class RatesReader {

    private static final String BASE_URL = "https://github.com/proffix4?tab=repositories"; // Адрес с котировками

    // Парсинг котировок из формата html web-страницы банка, при ошибке доступа возвращаем null
    public static String getRatesData() {
        StringBuilder data = new StringBuilder();
        try {

              Document doc = Jsoup.connect(BASE_URL).timeout(5000).get();
              data.append("Репозиториии proffix4: \n");
              Elements v = doc.select("h3.wb-break-all");


              Elements date = doc.select("relative-time.no-wrap");

              Elements box = doc.select("div.position-relative");
              for (int i = 0; i < box.size(); i++) {
                  Element v2 = v.get(i);

                  String date2 = date.get(i).attr(("datetime"));



                  DateFormat df_GH = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                  DateFormat df_KZ = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                  df_GH.setTimeZone(TimeZone.getTimeZone("UTC"));

                  String date3 = df_KZ.format(df_GH.parse(date2));


                  data.append("Name: " + v2.text() + "\n");

                  data.append("Date update: " + date3 + "\n");
                  data.append(" \n");
              }
        } catch (Exception e) {
      //      System.out.println(e.toString());
            return null; // При ошибке доступа возвращаем null
        }
        return data.toString().trim(); // Возвращаем результат
    }

}