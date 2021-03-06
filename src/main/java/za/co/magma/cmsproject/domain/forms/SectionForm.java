package za.co.magma.cmsproject.domain.forms;

public class SectionForm {
  private String type;
  private String title;
  private String text;

  public SectionForm(String type, String title, String text) {
    this.type = type;
    this.title = title;
    this.text = text;
  }

  public SectionForm(){}
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "SectionForm{" +
        "type='" + type + '\'' +
        ", title='" + title + '\'' +
        ", text='" + text + '\'' +
        '}';
  }
}
