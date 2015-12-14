package netgloo.domain;

/**
 * Created by G551 on 12/14/2015.
 */
public enum Status {

    MO("Mở"), DONG("Đóng");

    private final String text;

    Status(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
