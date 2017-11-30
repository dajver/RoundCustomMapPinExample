package project.dajver.com.roundpinwithavatarexample.view.model;

/**
 * Created by gleb on 11/30/17.
 */

public class PinsModel {

    private Integer id;
    private String fullName;
    private String avatarUrl;
    private String mapStatus;

    public PinsModel(Integer id, String fullName, String avatarUrl, String mapStatus) {
        this.id = id;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.mapStatus = mapStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMapStatus() {
        return mapStatus;
    }

    public void setMapStatus(String mapStatus) {
        this.mapStatus = mapStatus;
    }
}
