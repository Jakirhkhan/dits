package dits.gov.bd.auth.enumeration;

public enum Gender {
    M("Male"),
    F("Female"),
    O("Other");
    private String text;

    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
