package netgloo.domain.EnumStatus;

/**
 * Created by G551 on 12/14/2015.
 */
public enum ModelStatus {

    AP_DUNG("Áp dụng"), DA_XOA("Đã xóa");

    private final String text;

    ModelStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
