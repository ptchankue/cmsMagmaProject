package za.co.magma.cmsproject.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import za.co.magma.cmsproject.domain.forms.SectionForm;


import static za.co.magma.cmsproject.constants.Constants.*;

public class CMSUtils {

  public boolean filesEquals(String path1, String path2){
    System.out.println(path1 + " = " + path2 + " ?");
    boolean r = false;
    File f1 = new File(getClass().getClassLoader().getResource(path1).getFile());
    File f2 = new File(getClass().getClassLoader().getResource(path2).getFile());
    try {
      r = FileUtils.contentEquals(f1, f2);
      System.out.println(r);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return r;
  }

  public void updateCSSColors(String colors) throws IOException {
    String primary="";
    String secondary="";
    String[] types = colors.split(";");
    for(int i=0; i<types.length; i++){
      String[] item = types[i].split("=");
      if(item.length==2 && PRIMARY_COLOR.equalsIgnoreCase(item[0])) {
        primary = item[1];
      } else if(item.length==2 && SECONDARY_COLOR.equalsIgnoreCase(item[0])) {
        secondary = item[1];
      }
    }//
    System.out.println(primary +" " + secondary);

    String content = IOUtils.toString(getFileFromResourceAsStream(CSS_LOCATION), StandardCharsets.UTF_8.name());

    content = content.replaceAll(PRIMARY_COLOR, primary);
    content = content.replaceAll(SECONDARY_COLOR, secondary);
//    System.out.println(content);

    saveContentInFile(content, CSS_TEMP);

    if(this.filesEquals(CSS_FILE, CSS_TEMP)) {
      System.out.println("Files are equals");
    } else {
      // replace the css file by temp
      Date date = new Date() ;
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss") ;
      String newloc = "static/cms1/css/style_"+dateFormat.format(date) + ".css";

      saveContentInFile(IOUtils.toString(getFileFromResourceAsStream(CSS_FILE), StandardCharsets.UTF_8.name()),
          newloc);

      saveContentInFile(content, CSS_FILE);
    }

  }

  private InputStream getFileFromResourceAsStream(String fileName) {

    // The class loader that loaded the class
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    // the stream holding the file content
    if (inputStream == null) {
      throw new IllegalArgumentException("file not found! " + fileName);
    } else {
      return inputStream;
    }

  }

  public void saveContentInFile(String content, String filePath){
    String p = "src/main/resources/" + filePath;
    try (OutputStream out = new FileOutputStream(p)) {
      out.write(content.getBytes(StandardCharsets.UTF_8.name()));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getTemplateIdFromTemplate(String template){
    template = template.trim();
    String variableString= StringUtils.substringBetween(template,"{", "}");
    System.out.println(variableString);
    return template.replace(variableString, "").replace("{}", "");
  }
  public Map<String, Object> getSectionVariables(String template){
    // cta{text:""<>button:"Contact Us"}
    // validate template
    Map<String, Object> resp = new HashMap<>();

    template = template.trim();
    String variableString= StringUtils.substringBetween(template,"{", "}");
    String tempId=getTemplateIdFromTemplate(template);
    System.out.println("Template ID: " + tempId);
    //if(SESSION_TEMPLATES.)
    String[] vars = variableString.split(VARIABLE_SEPARATOR);
    for(String v: vars){
      String[] l = v.split(":");
      if(l.length==2){
        SectionForm sectionForm = new SectionForm();
        sectionForm.setText(l[1].trim());
        sectionForm.setType(l[0].trim().startsWith("text")?"text":"file");
        sectionForm.setTitle(l[0].trim());

        resp.put(l[0].trim(), sectionForm);
        System.out.println(l[0]+" --> "+resp.get(l[0]));

      }
    }
    return resp;
  }
  public String generateHtml(String tempId, Map<String, Object> variablesMap){
    String fileName="templates/admin/sections/"+ tempId +".html";
    String content="";
    try {
      content = IOUtils.toString(getFileFromResourceAsStream(fileName), StandardCharsets.UTF_8.name());

//      System.out.println(content);

      for(Map.Entry<String, Object>e:variablesMap.entrySet()){
        String reg="@"+e.getKey()+"@";
        SectionForm sectionForm = (SectionForm) e.getValue();
        content = content.replaceAll(reg, sectionForm.getText());
      }
//      System.out.println(content);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return content;
  }
}
