package za.co.magma.cmsProject.utils;

import org.junit.Test;
import za.co.magma.cmsproject.utils.CMSUtils;

import java.io.IOException;

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

}
