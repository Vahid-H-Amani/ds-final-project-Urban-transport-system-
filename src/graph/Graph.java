package graph;


import dataStructure.HashTable;
import dataStructure.LinkedList;
import dataStructure.RedBlackTree;
import dataStructure.Stack;
import undo.*;


public class Graph {


    private LinkedList<Station> stations;

    private LinkedList<Edge> edges;

    private HashTable<String, Station > stationMap;

    private RedBlackTree<Station> stationTree;

    private int nextId;


    private Stack<UndoCommand> undoStack;

    private boolean isUndoing = false;



    public Graph() {


        stations = new LinkedList<>();

        edges = new LinkedList<>();

        stationMap = new HashTable<>(100);

        stationTree = new RedBlackTree<>();

        nextId = 0;


        undoStack = new Stack<>();

    }


    public LinkedList<Edge> getEdges(){
        return edges;
    }


    public boolean addStation(String name) {


        if(stationMap.containsKey(name)) {

            return false;

        }


        Station newStation = new Station(nextId, name);


        stations.addLast(newStation);


        stationMap.put(name, newStation );

        stationTree.insert(newStation);


        nextId++;


        undoStack.push(new RemoveStationCommand(newStation));

        return true;

    }



    public Station getStation(String name) {
        return stationMap.get(name);
    }



    public boolean containsStation(String name) {


        return stationMap.containsKey(name);


    }



    public int getStationCount() {

        return stations.getSize();

    }








/////////////////---------------///////////////////
    public void printStations() {

        for (int i = 0; i < stations.getSize(); i++) {

            Station station = stations.get(i);

            System.out.print(
                    station.getId() +
                    " : " +
                    station.getName()
            );

            LinkedList<Edge> edges = station.getEdges();

            if (!edges.isEmpty()) {

                System.out.print(" -> ");

                for (int j = 0; j < edges.getSize(); j++) {

                    Edge edge = edges.get(j);

                    System.out.print(
                            edge.getDestination().getName() +
                            "(" +
                            edge.getWeight() +
                            ")"
                    );

                    if (j != edges.getSize() - 1) {
                        System.out.print(" , ");
                    }
                }
            }

            System.out.println();
        }
    }





    public void printEdges() {

        LinkedList<Edge> edges = getEdges();

        for (int i = 0; i < edges.getSize(); i++) {

            Edge edge = edges.get(i);

            System.out.println(
                    "\t" 
                    + edge.getSource().getName()
                    + " <-> "
                    + edge.getDestination().getName()
                    + " ("
                    + edge.getWeight()
                    + ")"
            );
        }
    }




    public boolean addEdge(String sourceName, String destinationName, double weight) {


        Station source = getStation(sourceName);
        Station destination = getStation(destinationName);

        Edge searchedEdge;        

        if(source == null || destination == null) {
            return false;
        }
        
        if((searchedEdge = edgeExists(source, destination)) != null) {
            if(!isUndoing){

                undoStack.push(new AddEdgeCommand(source, destination, searchedEdge.getWeight()));
            }

            searchedEdge.setWeight(weight);
            
            source.updateEdge(searchedEdge);
            destination.updateEdge(searchedEdge);


            return true;
        }


        Edge edge1 = new Edge(source , destination, weight);
        Edge edge2 = new Edge(destination , source, weight);

        source.addEdge(edge1);
        destination.addEdge(edge2);
        edges.addLast(edge1);
        
        if(!isUndoing){
            
            undoStack.push(new RemoveEdgeCommand(source,destination));
        }

        return true;

    }





    public void printGraph() {


        for(int i = 0; i < stations.getSize(); i++) {


            Station currentStation = stations.get(i);

            System.out.println( currentStation.getName() + ":");


            LinkedList<Edge> edges = currentStation.getEdges();

            for(int j = 0; j < edges.getSize(); j++) {


                Edge edge = edges.get(j);



                System.out.println(
                    " -> " + edge.getDestination().getName()
                    + " (" + edge.getWeight() + ")"
                );


            }

            System.out.println();

        }

    }




    public boolean removeEdge(String sourceName, String destinationName) {

        Station source = getStation(sourceName);
        Station destination = getStation(destinationName);

        if (source == null || destination == null) {
            return false;
        }

        boolean removed = source.removeEdge(destination);

        if (!removed) {
            return false;
        }

        destination.removeEdge(source);

        for (int i = 0; i < edges.getSize() ; i++) {


            Edge edge = edges.get(i);

            if ((edge.getSource().equals(source) && edge.getDestination().equals(destination)) ||
                (edge.getSource().equals(destination) && edge.getDestination().equals(source))) {

                boolean ok = edges.remove(edge);
                
            }
        }

        return true;
    }
        
    


    public boolean removeStation(String name) {

        Station station = getStation(name);

        if (station == null) {
            return false;
        }

        while (!station.getEdges().isEmpty()) {

            Edge edge = station.getEdges().get(0);

            removeEdge(
                    station.getName(),
                    edge.getDestination().getName()
            );
        }

        stations.remove(station);

        stationMap.remove(name);

        stationTree.delete(station);

        return true;
    }



    public LinkedList<Station> getStations () {
        return stations;
    }


    private Edge edgeExists(Station source, Station destination) {

        for (int i = 0; i < edges.getSize(); i++) {

            Edge edge = edges.get(i);

            if ((edge.getSource().getId() == source.getId() &&
                edge.getDestination().getId() == destination.getId())

                    ||

                (edge.getSource().getId() == destination.getId() &&
                edge.getDestination().getId() == source.getId())) {

                return edge;
            }
        }

        return null;
    }
    




//search in red black tree
    public LinkedList<Station> searchStations(String prefix) {

        return stationTree.searchPrefix(prefix);

    }




//undo
    public boolean undo() {

        isUndoing = true ;

        if (undoStack.isEmpty()) {
            return false;
        }

        UndoCommand command = undoStack.pop();

        command.undo(this);

        isUndoing = false;

        return true;

    }













//methods for final print
public void printSearchResult(String prefix) {

    LinkedList<Station> result = searchStations(prefix);

    if (result.isEmpty()) {
        System.out.println("No station found.");
        return;
    }

    for (int i = 0; i < result.getSize(); i++) {

        System.out.println(result.get(i).getName());

    }

}





public void clear(){
    stations.clear();
    edges.clear();
    stationMap.clear();
    stationTree.clear();
    nextId = 0;
    undoStack.clear();

}



}