package za.co.magma.cmsproject.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PageSection {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String html;
    @Column(columnDefinition = "boolean default true")
    private boolean online;
    @JoinColumn
    @ManyToOne
    private Page page;

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

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
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

    @PrePersist
    private void onCreate(){
        this.created = new Date();
    }

    @PreUpdate
    private void onUpdate(){
        this.updated = new Date();
    }

    @Override
    public String toString() {
        return "PageSection{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", html='" + html + '\'' +
            ", online=" + online +
            ", page=" + page +
            ", created=" + created +
            ", updated=" + updated +
            '}';
    }
}