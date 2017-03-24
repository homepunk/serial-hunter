package homepunk.lesson.series.data.parser;

import com.ekchang.jsouper.ElementAdapter;

import org.jsoup.nodes.Element;

import java.io.IOException;

import homepunk.lesson.series.model.HdrezkaSeries;

public class HdrezkaJsoupAdapter extends ElementAdapter<HdrezkaSeries> {
    private static final String ITEM_BLOCK_CLASS = "li.b-seriesupdate__block_list_item";
    private static final String regex = " / ";

    @Override
    public HdrezkaSeries fromElement(Element e) throws IOException {
        String[] titles = e.select("a.b-seriesupdate__block_list_link")
                .first()
                .text()
                .trim()
                .split(regex);

        String season = deleteBrackets(e
                .select("span.season")
                .first()
                .text()
                .trim());

        String series = e
                .select("span.cell.cell-2")
                .first()
                .ownText()
                .trim();

        String recordStudio;

        if (e.select("span.cell.cell-2").select("i").hasText()) {
            recordStudio = deleteBrackets(e
                    .select("span.cell.cell-2")
                    .select("i")
                    .text());
        } else
            recordStudio = "Оригинал";

        return new HdrezkaSeries(titles, season, series, recordStudio);
    }

    @Override
    public String query() {
        return ITEM_BLOCK_CLASS;
    }

    private String deleteBrackets(String string){
        return string.substring(1, string.length() - 1);
    }
}
