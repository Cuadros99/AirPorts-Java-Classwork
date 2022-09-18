package Dijkstra;

import Principal.Airport;
import java.util.*;

public class Graph {
    
// ATTRIBUTES
    private HashSet<Node> airPortNodes = new HashSet<>();
    
// METHODS
    //Constructor
    public Graph(ArrayList<Airport> airportsList) {
        for(int i=0; i < airportsList.size(); i++) {
            Node aux = new Node(airportsList.get(i));
            addNode(aux);
        }
        addAdjacentNodes();
    }
    
    //Add a node in the airPortNodes HashSet
    private void addNode(Node nodeA) {
        airPortNodes.add(nodeA);
    }
    
    //Add the Adjacent nodes to each Node in the HashSet
    private void addAdjacentNodes() {
      for(Node originNode: airPortNodes) {
        Airport originAp = originNode.getAirport();
        for(Node destinyNode: airPortNodes) {
          if(destinyNode != originNode) {
            Double distance = originAp.getDirectDistanceBetween(destinyNode.getAirport());
            originNode.addAdjacentNodes(destinyNode, distance);
          }
        }
      }
    }
    
    // Remove the Destiny node from the list of Adjacent nodes of the Source node 
    public void filterSourceNode(Node sourceNode, Node destinyNode) {
        for(Node apNode: airPortNodes) {
            if(apNode == sourceNode)
                apNode.removeAdjacentNode(destinyNode);
        }
    }

    //Getters
    public Node getNode(Node requestedNode) {
        for(Node apNode: airPortNodes) {
            if(apNode.getAirport().getCode() == requestedNode.getAirport().getCode())
                return apNode;
        }
        return null;
    }
    public Node getNodeByCode(String code) {
        for(Node apNode: airPortNodes) {
            if(code.equals(apNode.getAirport().getCode()))
                return apNode;
        }
        return null;
    }
    public HashSet<Node> getAirPortNodes() { return airPortNodes; }

}
