package za.co.magma.cmsProject;

import org.junit.BeforeClass;
import org.junit.Test;
import za.co.magma.cmsproject.domain.graphs.Graph;
import za.co.magma.cmsproject.domain.graphs.Node;

import java.util.List;
import java.util.Map;

public class GraphTest {

    static Graph graph;

    @BeforeClass
    public static void setUp() {
        graph = new Graph(false);

        graph.populateGraph("cameroon.txt");
    }

    @Test
    public void testDFS_Success() {
//        graph.printEdges();

        String s = "yaounde";
        String d = "bafang";
        Map<Integer, List> paths = graph.DFS(graph.nodes.get(s), graph.nodes.get(d));

        for (Map.Entry<Integer, List> e : paths.entrySet()) {
            System.out.println("Key: " + e.getKey());
            List<Node> pathList = e.getValue();
            for (Node x : pathList) {
                System.out.print(x + " ");
            }
            System.out.println();
        }

    }
}
