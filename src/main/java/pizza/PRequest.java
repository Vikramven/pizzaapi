package pizza;

public class PRequest {

    private long id;
    private String note;

    public PRequest()
    {

    }

    public PRequest(long id, String note)
    {
        this.id = id;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

