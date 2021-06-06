package za.co.magma.cmsproject.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class PageSection {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String template; // cta[text:""<->button:"Contact Us"]
    private String html;
    @Column(columnDefinition = "int default 0")
    private int position;
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

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageSection that = (PageSection) o;
        return online == that.online && id.equals(that.id) && title.equals(that.title) && Objects.equals(template, that.template) && html.equals(that.html) && page.equals(that.page) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, template, html, online, page, created, updated);
    }

    @Override
    public String toString() {
        return "PageSection{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", template='" + template + '\'' +
            ", html='" + html + '\'' +
            ", position=" + position +
            ", online=" + online +
            ", page=" + page +
            ", created=" + created +
            ", updated=" + updated +
            '}';
    }
}