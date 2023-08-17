package edu.wkd.fakelocation.models.obj;

public class Location {
    private int id;
    private String link;
    private String categories;
    private String location;

    public Location() {
    }

    public Location(int id, String link, String categories, String location) {
        this.id = id;
        this.link = link;
        this.categories = categories;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", categories='" + categories + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
