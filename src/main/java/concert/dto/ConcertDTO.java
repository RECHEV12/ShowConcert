package concert.dto;

public class ConcertDTO {
    private String co_period; /*공연 기간*/
    private String co_tel;/*문의 안내*/
    private long co_evPeriod;/*개최기간을 숫자로*/
    private String co_title;/*제목*/
    private String co_url; /*해당 소개 링크*/
    private String co_imgUrl; /*이미지 url*/

    public ConcertDTO() {
    }

    public ConcertDTO(String co_period, String co_tel, long co_evPeriod, String co_title, String co_url) {
        this.co_period = co_period;
        this.co_tel = co_tel;
        this.co_evPeriod = co_evPeriod;
        this.co_title = co_title;
        this.co_url = co_url;
        this.co_imgUrl = "";
    }

    @Override
    public String toString() {
        return "ConcertDTO{" +
                "co_period='" + co_period + '\'' +
                ", co_tel='" + co_tel + '\'' +
                ", co_evPeriod=" + co_evPeriod +
                ", co_title='" + co_title + '\'' +
                ", co_url='" + co_url + '\'' +
                ", co_imgUrl='" + co_imgUrl + '\'' +
                '}';
    }

    public String getCo_period() {
        return co_period;
    }

    public void setCo_period(String co_period) {
        this.co_period = co_period;
    }

    public String getCo_tel() {
        return co_tel;
    }

    public void setCo_tel(String co_tel) {
        this.co_tel = co_tel;
    }

    public long getCo_evPeriod() {
        return co_evPeriod;
    }

    public void setCo_evPeriod(int co_evPeriod) {
        this.co_evPeriod = co_evPeriod;
    }

    public String getCo_title() {
        return co_title;
    }

    public void setCo_title(String co_title) {
        this.co_title = co_title;
    }

    public String getCo_url() {
        return co_url;
    }

    public void setCo_url(String co_url) {
        this.co_url = co_url;
    }

    public String getCo_imgUrl() {
        return co_imgUrl;
    }

    public void setCo_imgUrl(String co_imgUrl) {
        this.co_imgUrl = co_imgUrl;
    }
}

