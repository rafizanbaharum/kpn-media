package my.gov.kpn.media.web.model;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
public class DirectoryModel {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DirectoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
