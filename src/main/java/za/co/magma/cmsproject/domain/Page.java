package za.co.magma.cmsproject.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Page {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String body;
    @JoinColumn
    @ManyToOne
    private Site site;

    private String url;
    private boolean online = false;

    private Date visibleFrom;
    private Date visibleTo;

    private Date created;
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getVisibleFrom() {
        return visibleFrom;
    }

    public void setVisibleFrom(Date visibleFrom) {
        this.visibleFrom = visibleFrom;
    }

    public Date getVisibleTo() {
        return visibleTo;
    }

    public void setVisibleTo(Date visibleTo) {
        this.visibleTo = visibleTo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}