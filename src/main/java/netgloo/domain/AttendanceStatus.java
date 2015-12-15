package netgloo.domain;

/**
 * Created by G551 on 12/15/2015.
 */
public enum AttendanceStatus {

    VANG("Vắng"), THAM_GIA("Có");

    private final String text;

    AttendanceStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
