package netgloo.domain.EnumStatus;

/**
 * Created by G551 on 12/14/2015.
 */
public enum PictureType {

    LOP("Lớp học"), SV("Sinh viên");

    private final String text;

    PictureType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
