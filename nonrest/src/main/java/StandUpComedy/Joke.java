package StandUpComedy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {
    private long id;
    private Category category;
    private String text;
    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return this.id;
    }



    public String getText() {
        return this.text;
    }
}