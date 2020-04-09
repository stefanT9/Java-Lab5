package Model;

public class Artist {
    private int idx;
    private String country;
    private String name;

    public Artist(String country, String name) {
        this.country=country;
        this.name=name;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist(int idx, String country, String name) {
        this.idx = idx;
        this.country = country;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "idx=" + idx +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
