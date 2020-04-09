package Model;

import java.util.List;

public class Chart {
    int id;

    @Override
    public String toString() {
        return "Chart{" +
                "id=" + id +
                ", album_ids=" + album_ids +
                '}';
    }

    List<Integer> album_ids;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getAlbum_ids() {
        return album_ids;
    }

    public void setAlbum_ids(List<Integer> album_ids) {
        this.album_ids = album_ids;
    }

    public Chart(int id, List<Integer> album_ids) {
        this.id = id;
        this.album_ids = album_ids;
    }

    public Chart(List<Integer> albumsIdList) {
        this.album_ids=albumsIdList;
    }
}
