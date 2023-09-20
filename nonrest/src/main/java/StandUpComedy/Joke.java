package StandUpComedy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {
    private long id;
    private Category cat;
    private String Text;
    public void setId(long id) {
        this.id = id;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public void setText(String text) {
        Text = text;
    }

    public long getId() {
        return id;
    }

    public Category getCat() {
        return cat;
    }

    public String getText() {
        return Text;
    }
}