package algorithms;

import dataStructure.DisjointSet;
import dataStructure.LinkedList;
import graph.Edge;
import graph.Graph;
import graph.Station;

public class Kruskal {

    private Graph graph;

    private Graph mst;

    private DisjointSet<Station> disjointSet;

    private LinkedList<Edge> sortedEdges;

    private int edgeCount;

    public Kruskal(Graph graph) {

        this.graph = graph;

    }



    
    public Graph getMST() {
        return mst;
    }

    public double getTotalWeight() {

        double totalWeight = 0.0;

        LinkedList<Edge> edges = mst.getEdges();

        for (int i = 0; i < edges.getSize(); i++) {

            totalWeight += edges.get(i).getWeight();
        }

        return totalWeight;
    }



    public Graph run() {

        initialize();

        for (int i = 0; i < sortedEdges.getSize(); i++) {

            Edge edge = sortedEdges.get(i);

            Station source = edge.getSource();
            Station destination = edge.getDestination();

            if (!disjointSet.isConnected(source, destination)) {

                disjointSet.union(source, destination);

                mst.addEdge(
                        source.getName(),
                        destination.getName(),
                        edge.getWeight()
                );

                edgeCount++;

                if (edgeCount == graph.getStations().getSize() - 1) {
                    break;
                }
            }
        }

        LinkedList<Station> stations = graph.getStations();
        Station source = stations.get(0);

        for(int i = 1 ; i < stations.getSize() ; i++ ){
            if(!disjointSet.isConnected(source, stations.get(i))){
                return null;
            }
        }

        return mst;
    }

    private void initialize() {

        mst = new Graph();

        disjointSet = new DisjointSet<>();

        edgeCount = 0;

        LinkedList<Station> stations = graph.getStations();

        for (int i = 0; i < stations.getSize(); i++) {

            Station station = stations.get(i);

            mst.addStation(station.getName());

            disjointSet.makeSet(station);
        }

        MergeSort<Edge> mergeSort = new MergeSort<>();

        sortedEdges = mergeSort.sort(graph.getEdges());
    }

}