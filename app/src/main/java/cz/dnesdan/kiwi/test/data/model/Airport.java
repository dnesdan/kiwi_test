package cz.dnesdan.kiwi.test.data.model;

public class Airport {
    private String filterName;
    private String name;

    public String getFilterName() {
        return filterName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "filterName='" + filterName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
