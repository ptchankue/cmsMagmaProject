package za.co.magma.cmsProject.utils;

import org.junit.Test;
import za.co.magma.cmsproject.domain.forms.SectionForm;
import za.co.magma.cmsproject.utils.CMSUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CMSUtilsTest {
  CMSUtils cmsUtils = new CMSUtils();
  @Test
  public void testFileCompare_Success() {
    boolean resp = cmsUtils.filesEquals("static/cms1/css/style.css", "static/cms1/css/style.css");
    assertTrue(resp);

    resp = cmsUtils.filesEquals("static/cms1/css/style.css", "static/cms1/css/_style.css");
    assertFalse(resp);
  }

  @Test
  public void testSaveFile_Success() {
    String content="<html></html>";
    cmsUtils.saveContentInFile(content, "templates/xtests/cmsUtilsTest.html");
  }

  @Test
  public void testUpdateColors_Success() throws IOException {
//    cmsUtils.updateCSSColors("_primary_color_=#5b8037;_secondary_color_=#081624");
  }

  //about[img:"/cms1/img/about.jpg"<>title:"We produce or supply Goods & Services"<>text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Nam ornare ipsum ac accumsan auctor. Donec consequat arcu et commodo interdum. Vivamus posuere lorem lacus.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Nam ornare ipsum ac accumsan auctor. "<>sign-img:"/cms1/img/sign.png"<>sign-name:"Michael Smith"<>sing-title:"CEO Industrial Inc"]
  @Test
  public void testSessionVariables_Success() throws IOException {
    String template="about{imgTop:/cms1/img/about.jpg<>textTitle:We produce or supply Goods & Services<>textContext=Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Nam ornare ipsum ac accumsan auctor. Donec consequat arcu et commodo interdum. Vivamus posuere lorem lacus.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Nam ornare ipsum ac accumsan auctor. <>imgSign:/cms1/img/sign.png<>textSignName:Michael Smith<>textSign:CEO Industrial Inc}";
    cmsUtils.getSectionVariables(template);
  }
  @Test
  public void testGenerateHtml_Success() {
    Map<String,Object> v = new HashMap<>();
    v.put("textBody", new SectionForm("text","CTA Body", "We produce or supply Goods, Services, or Sources"));
    v.put("textButton", new SectionForm("text","CTA Button", "Contact Us"));
    cmsUtils.generateHtml("cta", v);
  }
}
