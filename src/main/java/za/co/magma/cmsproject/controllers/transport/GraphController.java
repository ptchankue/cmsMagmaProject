package za.co.magma.cmsproject.controllers.transport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import za.co.magma.cmsproject.domain.graphs.Graph;
import za.co.magma.cmsproject.domain.graphs.Node;

import java.util.*;

@RestController
@RequestMapping("api/graph")
public class GraphController {
  private static Map<String, Node> nodes = new HashMap<>();

  @GetMapping("routes")
  public ResponseEntity searchRoutes(@RequestParam String from,
                                     @RequestParam String to) {
    System.out.println("in searchRoutes");
    System.out.println("From: " + from);
    System.out.println("\nTo: " + to);
    // get template
    Graph graph = new Graph(false);

    graph.populateGraph("cameroon.txt");
    Map<Integer, List> paths = graph.DFS(graph.nodes.get(from.toLowerCase(Locale.ROOT)), graph.nodes.get(to));

//    for (Map.Entry<Integer, List> e : paths.entrySet()) {
//      System.out.println("Key: " + e.getKey());
//      List<Node> pathList = e.getValue();
//      for (Node x : pathList) {
//        System.out.print(x + " ");
//      }
//      System.out.println();
//    }
    Map<String, Map<Integer, List>> result = new HashMap<>();
    result.put("paths", paths);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

//  public static void main(String[] args) {
//    Graph graph = new Graph(false);
//
//    graph.populateGraph("cameroon.txt");
//
//    // Print graph
//    graph.printEdges();
//
//    // Paths
//    String s = "yaounde";
//    String d = "bafang";
//    Map<Integer, List> paths = graph.DFS(graph.nodes.get(s), graph.nodes.get(d));
//    System.out.println(paths.size() + " paths founds from \""+s+"\" to \"" + d +"\"");
//  }

  @GetMapping("foos")
  @ResponseBody
  public String addFoo(@RequestParam(name = "id") String fooId, @RequestParam String name) {
    return "ID: " + fooId + " Name: " + name;
  }

}
