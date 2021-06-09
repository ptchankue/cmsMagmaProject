package za.co.magma.cmsproject.domain.sections;

import org.apache.commons.lang3.StringUtils;
import za.co.magma.cmsproject.domain.PageSection;
import za.co.magma.cmsproject.utils.CMSUtils;

import java.util.Map;

public class SectionGeneric {
    private Map<String, Object> fields;
    protected PageSection pageSection;

    private static CMSUtils cmsUtils;

    public SectionGeneric(PageSection pageSection){
        this.pageSection = pageSection;
        cmsUtils = new CMSUtils();
        if(!StringUtils.isEmpty(this.pageSection.getTemplate())){
            this.fields = cmsUtils.getSectionVariables(pageSection.getTemplate());
        }
    }
    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }
}
