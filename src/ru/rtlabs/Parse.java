package ru.rtlabs;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
public class Parse {
    private String JSessionId;
    private String lt;
    private String ex;
    public void pageParse(String url) throws IOException {
    Document doc = Jsoup.connect(url)
                    .header("Accept-Encoding", "gzip, deflate")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                    .maxBodySize(0)
                    .timeout(600000)
                    .get();
    Elements jsess = doc.select("div[id=content]>form[id=fm1]");
    Elements lt = doc.select("div[id=soderg]>div[id=border]>div[id=sub_border]>input[name=lt]");
    Elements ex = doc.select("div[id=soderg]>div[id=border]>div[id=sub_border]>input[name=execution]");
        for (Element x :jsess){
            this.JSessionId = x.attr("action").substring(22);
        }
        for (Element y : lt){
            this.lt = y.attr("value");
        }
        for (Element y : ex){
            this.ex = y.attr("value");
        }
        }

    public String getJSessionId() {
        return JSessionId;
    }

    public String getLt() {
        return lt;
    }

    public String getEx() {
        return ex;
    }
}