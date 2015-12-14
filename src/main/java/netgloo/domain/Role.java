package netgloo.domain;

public enum Role {

    USER ("Sinh Viên"), ADMIN("Quản Trị Viên"), GIAO_VIEN("Giáo Viên");

    private final String text;

    Role(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
