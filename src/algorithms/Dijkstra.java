package algorithms;


import dataStructure.HashTable;
import dataStructure.HeapNode;
import dataStructure.LinkedList;
import dataStructure.MinHeap;
import graph.Edge;
import graph.Graph;
import graph.Station;

public class Dijkstra {

    private Graph graph;

    private HashTable<Station, Double> distance;
    private HashTable<Station, Station> previous;

    private MinHeap<Station> heap;

    public Dijkstra(Graph graph) {

        this.graph = graph;

        distance = new HashTable<>(100);
        previous = new HashTable<>(100);

        heap = new MinHeap<>();
    }

    
    public Graph getGraph() {
        return graph;
    }





    public void run(String sourceName) {

        Station source = graph.getStation(sourceName);

        if (source == null) {
            return;
        }

        initialize(source);

        while (!heap.isEmpty()) {

            HeapNode<Station> heapNode = heap.removeMin();

            Station current = heapNode.getData();

            double heapDistance = heapNode.getPriority();

            Double currentDistance = distance.get(current);


            if (Double.compare(heapDistance, currentDistance) != 0) {
                continue;
            }

            relax(current);
        }
    }




    private void initialize(Station source) {

        distance = new HashTable<>(100);
        previous = new HashTable<>(100);
        heap = new MinHeap<>();
        
        LinkedList<Station> stations = graph.getStations();

        for (int i = 0; i < stations.getSize(); i++) {

            Station station = stations.get(i);

            distance.put(station, Double.POSITIVE_INFINITY);
            previous.put(station, null);
        }

        distance.put(source, 0.0);

        heap.insert(source, 0.0);
    }




    private void relax(Station current) {

        LinkedList<Edge> edges = current.getEdges();

        double currentDistance = distance.get(current);

        for (int i = 0; i < edges.getSize(); i++) {

            Edge edge = edges.get(i);

            Station neighbor = edge.getDestination();

            double newDistance = currentDistance + edge.getWeight();

            double oldDistance = distance.get(neighbor);

            if (newDistance < oldDistance) {

                distance.put(neighbor, newDistance);

                previous.put(neighbor, current);

                heap.insert(neighbor, newDistance);
            }
        }
    }



//get path details    

    public double getDistance(String destinationName) {

        Station destination = graph.getStation(destinationName);

        if (destination == null) {
            return -1;
        }

        double result = distance.get(destination);

        if (result == Double.POSITIVE_INFINITY) {
            return -1;
        }

        return result;
    }



    public LinkedList<Station> getShortestPath(String destinationName) {

        Station destination = graph.getStation(destinationName);

        if (destination == null) {
            return null;
        }

        Double d = distance.get(destination);

        if (d ==  Double.POSITIVE_INFINITY) {
            return null;
        }

        LinkedList<Station> path = new LinkedList<>();

        Station current = destination;

        while (current != null) {

            path.addFirst(current);

            current = previous.get(current);
        }

        return path;
    }





//print

    public void printDistances() {

        LinkedList<Station> stations = graph.getStations();

        for (int i = 0; i < stations.getSize(); i++) {

            Station station = stations.get(i);

            Double d = distance.get(station);

            if (d == null) {
                System.out.println(station.getName() + " : INF");
            } else {
                System.out.println(station.getName() + " : " + d);
            }
        }
    }

    public void printShortestPath(String destinationName) {

        LinkedList<Station> path = getShortestPath(destinationName);

        if (path == null || path.isEmpty()) {
            System.out.println("No Path");
            return;
        }

        for (int i = 0; i < path.getSize(); i++) {

            System.out.print(path.get(i).getName());

            if (i != path.getSize() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }




}