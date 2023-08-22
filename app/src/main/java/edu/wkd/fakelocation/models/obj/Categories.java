package edu.wkd.fakelocation.models.obj;

import java.util.List;

public class Categories {
    private List<String> categories;

    public Categories() {
    }

    public Categories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categories=" + categories +
                '}';
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
