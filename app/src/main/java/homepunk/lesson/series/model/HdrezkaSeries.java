package homepunk.lesson.series.model;

import com.ekchang.jsouper.SoupAdapter;

import java.io.Serializable;

import homepunk.lesson.series.data.parser.HdrezkaJsoupAdapter;

@SoupAdapter(HdrezkaJsoupAdapter.class)
public class HdrezkaSeries implements Serializable{
    private String[] titles;
    private String season;
    private String series;
    private String recordStudio;

    public HdrezkaSeries(String[] titles, String season, String series, String recordStudio) {
        this.titles = titles;
        this.season = season;
        this.series = series;
        this.recordStudio = recordStudio;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] title) {
        this.titles = title;
    }

    public String getMainTitle(){
        return titles[0];
    }

    public String getAlternativeTitle(){
        return titles[1];
    }

    public boolean hasAlternativeTitle(){
        return titles.length > 1 ? true : false;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getRecordStudio() {
        return recordStudio;
    }

    public void setRecordStudio(String recordStudio) {
        this.recordStudio = recordStudio;
    }

    @Override
    public String toString() {
        return getMainTitle() + " " + getSeason() + " " + getSeries() + " Озвучка: " + getRecordStudio();
    }
}
