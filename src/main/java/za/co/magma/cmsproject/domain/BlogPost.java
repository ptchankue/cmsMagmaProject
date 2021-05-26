package za.co.magma.cmsproject.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String body;

	@JoinColumn
	@ManyToOne
	private Person createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at;

	public String getBody() {
		return body;
	}
	public String getTitle() {
		return title;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Person getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}

	@PrePersist
	private void onCreate(){
		this.created_at = new Date();
	}

	@PreUpdate
	private void onUpdate(){
		this.updated_at = new Date();
	}

	@Override
	public String toString() {
		return "BlogPost{" +
				"id=" + id +
				", title='" + title + '\'' +
				", body='" + body + '\'' +
				", createdBy=" + createdBy +
				", created_at=" + created_at +
				", updated_at=" + updated_at +
				'}';
	}
}