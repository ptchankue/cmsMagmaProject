package za.co.magma.cmsproject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.repository.LinkRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CMSUtils {

//  @Autowired
//  private LinkRepository linkRepository;
//
//  public List<Link> getHeaderMenusBySite(List<Page> pageList){
//    List<Link> linkList = new ArrayList<>();
//    for(Page page: pageList){
//      Link link = linkRepository.findByPage(page);
//      if(link.isHeader()){
//        System.out.println("\t"+ link.getTitle());
//        linkList.add(link);
//      }
//    }
//    return linkList;
//  }
//
//  public List<Link> getFooterMenusBySite(List<Page> pageList){
//    List<Link> linkList = new ArrayList<>();
//    for(Page page: pageList){
//      Link link = linkRepository.findByPage(page);
//      if(link.isFooter()){
//        linkList.add(link);
//      }
//    }
//    return linkList;
//  }

}
