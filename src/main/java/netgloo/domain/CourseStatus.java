package netgloo.domain;

/**
 * Created by G551 on 12/14/2015.
 */
public enum CourseStatus {

    MO("Mở"), DONG("Đóng");

    private final String text;

    CourseStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
