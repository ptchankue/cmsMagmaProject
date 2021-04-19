package za.co.magma.cmsproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Link {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 
    private String title;
 
    private String body;
    private boolean footer;
    private boolean header;
    private String icon;
    // Page to be opened by this link, external?
    // internal: id=202, external: http://google.com
    private String pageUrl;


}