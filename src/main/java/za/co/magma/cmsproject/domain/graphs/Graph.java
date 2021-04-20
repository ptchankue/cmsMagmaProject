package za.co.magma.cmsproject.domain.graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class Graph {

  // Each node maps to a list of all his neighbors
  private HashMap<Node, LinkedList<Node>> adjacencyMap;
  private boolean directed;
  public Map<String, Node> nodes = new HashMap<>();

  public Graph(boolean directed) {
    this.directed = directed;
    adjacencyMap = new HashMap<>();
  }

  public void addEdgeHelper(Node a, Node b) {
    LinkedList<Node> tmp = adjacencyMap.get(a);

    if (tmp != null) {
      tmp.remove(b);
    } else tmp = new LinkedList<>();
    tmp.add(b);
    adjacencyMap.put(a, tmp);
  }

  public void addEdge(Node source, Node destination) {

    // We make sure that every used node shows up in our .keySet()
    if (!adjacencyMap.keySet().contains(source))
      adjacencyMap.put(source, null);

    if (!adjacencyMap.keySet().contains(destination))
      adjacencyMap.put(destination, null);

    addEdgeHelper(source, destination);

    // If a graph is undirected, we want to add an edge from destination to source as well
    if (!directed) {
      addEdgeHelper(destination, source);
    }
  }

  public void printEdges() {
    for (Node node : adjacencyMap.keySet()) {
      System.out.print("The " + node.name + " (" + node.n + ") has an edge towards: ");
      if (adjacencyMap.get(node) != null) {
        for (Node neighbor : adjacencyMap.get(node)) {
          System.out.print(neighbor.name + " ");
        }
        System.out.println();
      } else {
        System.out.println("none");
      }
    }
  }

  public boolean hasEdge(Node source, Node destination) {
    return adjacencyMap.containsKey(source) && adjacencyMap.get(source) != null && adjacencyMap.get(source).contains(destination);
  }

  public void populateGraph(String fileName) {
    int i = 0;
    Node ori, des;
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(fileName));
      String line = reader.readLine();
      while (line != null) {
//        System.out.println(line);
        String[] edges = line.split("-");
        String origin = edges[0].toLowerCase(Locale.ROOT);
        String destination = edges[1].toLowerCase(Locale.ROOT);

        if (!nodes.containsKey(origin)) {
          ori = new Node(i++, origin);
          nodes.put(origin, ori);
        } else {
          ori = nodes.get(origin);
        }
        if (!nodes.containsKey(destination)) {
          des = new Node(i++, destination);
          nodes.put(destination, des);
        } else {
          des = nodes.get(destination);
        }

        addEdge(ori, des);
        // read next line
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // A function used by DFS
  void DFSUtil(Node currentNode, Node origin, Node destination,
               boolean visited[], List<Node> pathList,
               Map<Integer, List> allPaths) {
    // Mark the current node as visited and print it
    visited[currentNode.n] = true;
//        System.out.print(node.name + " ");

    if (currentNode.n == destination.n) {
      System.out.println("Result: " + pathList.size() + " nodes");
      List<Node> clone = pathList.stream().collect(toList());
      allPaths.put(allPaths.size()+1, clone);

      for (Node x : pathList) {
        System.out.print(x.name + " ");
      }
      System.out.println();
      System.out.println(allPaths.size() + "  ");
    }
    // Recur for all the vertices adjacent to this
    // vertex
    Iterator<Node> i = adjacencyMap.get(currentNode).listIterator();
    while (i.hasNext()) {
      Node n = i.next();
      if (!visited[n.n] && n != origin) {
        pathList.add(n);
        DFSUtil(n, origin, destination, visited, pathList, allPaths);
      }
    }
    pathList.remove(currentNode);
    visited[currentNode.n] = false;
  }

  // The function to do DFS traversal. It uses recursive
  // DFSUtil()
  public Map<Integer, List> DFS(Node start, Node destination) {
    Map<Integer, List> allPaths = new HashMap<>();

    System.out.println("Path from \"" + start.name + "\" to \"" + destination.name + "\"");
    // Mark all the vertices as not visited(set as
    // false by default in java)
    int V = adjacencyMap.size();
    boolean visited[] = new boolean[V];
    List<Node> pathList = new ArrayList<>();

    pathList.add(start);

    // Call the recursive helper function to print DFS
    // traversal starting from all vertices one by one
    Iterator<Node> it = adjacencyMap.get(start).listIterator();
    while (it.hasNext()) {
      Node node = it.next();
      if (!visited[node.n]) {
        DFSUtil(node, start, destination, visited, pathList, allPaths);
      }
    }
    return allPaths;
  } // DFS

}