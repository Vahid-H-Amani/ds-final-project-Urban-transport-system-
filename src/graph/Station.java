package graph;


import dataStructure.LinkedList;
import java.util.Objects;


public class Station implements Comparable<Station> {


    private int id;
    private String name;
    private LinkedList<Edge> edges;



    public Station(int id, String name) {
        this.id = id;
        this.name = name;
        this.edges = new LinkedList<>();
    }



    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public LinkedList<Edge> getEdges() {
        return edges;
    }



    public void addEdge(Edge edge) {
        edges.addLast(edge);
    }




    public boolean hasEdge(Station destination) {

        for (int i = 0; i < edges.getSize(); i++) {

            Edge edge = edges.get(i);

            if (edge.getDestination().equals(destination))
                return true;

        }

        return false;
    }




    public boolean updateEdge(Edge newEdge) {

        Station destination = null;
        
        if(this.equals(newEdge.getSource())){
            destination = newEdge.getDestination();
        }
        
        else if(this.equals(newEdge.getDestination())){
            destination = newEdge.getSource();
        }
        if(destination != null){
            for (int i = 0; i < edges.getSize(); i++) {
                
                Edge edge = edges.get(i);

                if (edge.getDestination().equals(destination)){

                    edge.setWeight(newEdge.getWeight());
                    return true;
                
                }

            }
        }
        return false;
    }





    public boolean removeEdge(Station destination) {

        for(int i = 0; i < edges.getSize(); i++) {

            Edge edge = edges.get(i);

            if(edge.getDestination().equals(destination)) {

                edges.remove(edge);
                return true ;

            }
        }

        return false;
    }








    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Station other = (Station) obj;
        return id == other.id;
    }



    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    @Override
    public int compareTo(Station other) {

        return this.name.compareTo(other.name);

    }


    
}