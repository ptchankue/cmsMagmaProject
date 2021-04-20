package za.co.magma.cmsproject.domain.graphs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Node {
    int n;
    String name;

    Node(int n, String name){
        this.n = n;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "n=" + n +
                ", name='" + name + '\'' +
                '}';
    }
}