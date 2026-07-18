package algorithms;

import dataStructure.HashTable;
import dataStructure.LinkedList;
import graph.Edge;
import graph.Graph;
import graph.Station;

public class FloydWarshall {

    private static final double INF = Double.POSITIVE_INFINITY;

    private Graph graph;

    private double[][] distance;

    private Station[][] next;

    // Station -> Matrix Index
    private HashTable<Station, Integer> stationIndex;

    // Matrix Index -> Station
    private  Station[] indexToStation;

    private double diameter;

    private Station diameterStart;

    private Station diameterEnd;

    
    
    
    public FloydWarshall(Graph graph) {

        this.graph = graph;

    }




    public double  getDiameter() {

        return diameter;

    }

    public Station getDiameterStart() {

        return diameterStart;

    }

    public Station getDiameterEnd() {

        return diameterEnd;

    }






    public void run() {

        initialize();

        floyd();

        findDiameter();

    }

    private void initialize() {

        int n = graph.getStationCount();

        distance = new double[n][n];
        next = new Station[n][n];

        stationIndex = new HashTable<>(n * 2);

        indexToStation = new Station[n];

        LinkedList<Station> stations = graph.getStations();

        for (int i = 0; i < stations.getSize(); i++) {

            Station station = stations.get(i);

            stationIndex.put(station, i);

            indexToStation[i] = station;

        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i == j) {

                    distance[i][j] = 0.0;

                } else {

                    distance[i][j] = INF;

                }

            }

        }

        LinkedList<Edge> edges = graph.getEdges();

        for (int i = 0; i < edges.getSize(); i++) {

            Edge edge = edges.get(i);

            int source =
                    stationIndex.get(edge.getSource());

            int destination =
                    stationIndex.get(edge.getDestination());

            distance[source][destination] = edge.getWeight();
            distance[destination][source] = edge.getWeight();

            next[source][destination] = edge.getDestination();
            next[destination][source] = edge.getSource();

        }

    }

    private void floyd() {

        int n = graph.getStationCount();

        for (int k = 0; k < n; k++) {

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    if (
                        distance[i][k] + distance[k][j] < distance[i][j]
                            && distance[i][k] < INF && distance[k][j] < INF) {

                        distance[i][j] =
                                distance[i][k] + distance[k][j];

                        next[i][j] = next[i][k];

                    }

                }

            }

        }

    }

    private void findDiameter() {

        diameter = -1.0;

        diameterStart = null;

        diameterEnd = null;

        int n = graph.getStationCount();

        for (int i = 0; i < n; i++) {

            for (int j = i + 1 ; j < n ; j++) {

                if (distance[i][j] == INF) {
                    continue;
                }

                if (distance[i][j] > diameter) {

                    diameter = distance[i][j];

                    diameterStart = indexToStation[i];

                    diameterEnd = indexToStation[j];

                }

            }

        }

    }





    
    public LinkedList<Station> getPath(
            String sourceName,
            String destinationName) {

        Station source = graph.getStation(sourceName);
        Station destination = graph.getStation(destinationName);

        if (source == null || destination == null) {
            return null;
        }

        Integer sourceIndex = stationIndex.get(source);
        Integer destinationIndex = stationIndex.get(destination);

        if (sourceIndex == null || destinationIndex == null) {
            return null;
        }

        if (next[sourceIndex][destinationIndex] == null) {
            return null;
        }

        LinkedList<Station> path = new LinkedList<>();

        path.addLast(source);

        while (!source.equals(destination)) {

            source = next[
                    stationIndex.get(source)
            ][
                    destinationIndex
            ];

            path.addLast(source);

        }

        return path;
    }

    public boolean isConnected() {

        int n = graph.getStationCount();

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n ; j++) {

                if (distance[i][j] == INF) {

                    return false;

                }

            }

        }

        return true;
    }




//print
    public void printDistanceMatrix() {

        int n = graph.getStationCount();

        System.out.print("        ");

        for (int i = 0; i < n; i++) {

            System.out.printf("%8s", indexToStation[i].getName());

        }

        System.out.println();

        for (int i = 0; i < n; i++) {

            System.out.printf("%8s",
                    indexToStation[i].getName());

            for (int j = 0; j < n; j++) {

                if (distance[i][j] == INF) {

                    System.out.printf("%8s", "INF");

                } else {

                    System.out.printf("%8s",
                            distance[i][j]);

                }

            }

            System.out.println();

        }

    }




    public void printLongestPath() {

        
        if (!isConnected()) {

            System.out.println("Graph is disconnected.");
            return;

        }

        if (diameterStart == null || diameterEnd == null) {

            System.out.println("No valid path.");
            return;

        }

        LinkedList<Station> path = getPath(
                diameterStart.getName(),
                diameterEnd.getName()
        );

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