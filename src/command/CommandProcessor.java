package command;

import algorithms.BFS;
import algorithms.DFS;
import algorithms.Dijkstra;
import algorithms.FloydWarshall;
import algorithms.Kruskal;
import graph.Graph;

public class CommandProcessor {

    private Graph graph;

    public CommandProcessor(Graph graph) {

        this.graph = graph;

    }

    public boolean execute(String line) {

        if (line == null) {
            return true;
        }

        line = line.trim();

        if (line.isEmpty()) {
            return true;
        }

        String[] tokens = line.split("\\s+");

        String command = tokens[0].toUpperCase();

        switch (command) {

            case "ADD_STATION":
                executeAddStation(tokens);
                break;

            case "ADD_EDGE":
                executeAddEdge(tokens);
                break;

            case "BFS":
                executeBFS(tokens);
                break;
            
            case "DEADENDS":
                executeDFS(tokens);
                break;
                
            case "PRINT_GRAPH":
                executePrintGraph();
                break;
                
            case "PRINT_STATIONS":
                executePrintStations();
                break;
            case "PRINT_EDGES":
                executePrintEdges();
                break;

            case "SHORTEST_PATH":
                executeDijkstra(tokens);
                break;

            case "MST":
                executeMST();
                break;

            case "DIAMETER":
                executeDIAMETER();
                break;

            case "SEARCH":
                executeSearch(tokens);
                break;

            case "UNDO":
                executeUndo();
                break;

            case "HELP":
                printHelp();
                break;

            case "EXIT":
                graph.clear();
                graph = null;
                return false;

            default:
                System.out.println("Unknown Command.");
        }

        return true;
    }

    private void executeAddStation(String[] tokens) {

        if (tokens.length != 2) {

            System.out.println(
                    "Usage : ADD_STATION <name>"
            );

            return;
        }

        if (graph.addStation(tokens[1])) {

            System.out.println("Station Added.");

        } else {

            System.out.println(
                    "Station Already Exists."
            );

        }

    }


    private void executeAddEdge(String[] tokens) {

        if (tokens.length != 4) {

            System.out.println(
                "Usage : ADD_EDGE <source> <destination> <weight>"
            );

            return;
        }

        double weight;

        try {

            weight = Double.parseDouble(tokens[3]);

        } catch (Exception e) {

            System.out.println("Invalid Weight.");

            return;

        }

        if(weight <= 0 ){
            System.out.println("cost should be positive.");
            return;
        }

        if (graph.addEdge(
                tokens[1],
                tokens[2],
                weight)) {

            System.out.println("Edge Added.");

        } else {

            System.out.println(
                    "Edge Could Not Be Added."
            );

        }

    }


    private void executeSearch(String[] tokens) {

        
        if (tokens.length == 1) {
            graph.printSearchResult("");
            return;
        }


        else if (tokens.length != 2) {

            System.out.println(
                    "Usage : SEARCH <prefix>"
            );

            return;
        }

        graph.printSearchResult(tokens[1]);

    }


    private void executeBFS(String[] tokens) {

        if (tokens.length != 3) {

            System.out.println(
                    "Usage : BFS <startStation> <destinationStation>"
            );

            return;
        }

        if (!graph.containsStation(tokens[1]) || !graph.containsStation(tokens[2])) {

            System.out.println("Station Not Found.");

            return;
        }

        BFS.printPath( graph , tokens[1] , tokens[2] );

    }

    
    private void executeDFS(String[] tokens) {

        if (tokens.length != 2) {

            System.out.println(
                    "Usage : DFS <startStation>"
            );

            return;
        }

        if (!graph.containsStation(tokens[1])) {

            System.out.println("Station Not Found.");

            return;
        }

        DFS.printDFSCorners( graph , tokens[1]);

    }
    

    private void executePrintGraph() {

        graph.printGraph();

    }

    private void executePrintStations() {

        graph.printStations();

    }

    private void executePrintEdges() {

        graph.printEdges();

    }

    private void executeUndo() {

        if (graph.undo()) {


        } else {

            System.out.println(
                    "Nothing To Undo."
            );

        }

    }

    // ---------- Algorithms ----------

 


    private void executeDijkstra(String[] tokens) {

        if (tokens.length != 3) {

            System.out.println(
                    "Usage : DIJKSTRA <source> <destination>"
            );

            return;
        }

        Dijkstra dijkstra = new Dijkstra(graph);

        if(!graph.containsStation(tokens[1]) || (!graph.containsStation(tokens[2]))){

            System.out.println("Station Not Found.");

            return;
        }

        dijkstra.run(tokens[1]);

        double distance =
                dijkstra.getDistance(tokens[2]);

        if (distance < 0) {

            System.out.println("No Path.");

            return;
        }

        System.out.println(
                "Distance : " + distance
        );

        System.out.print("Path : ");

        dijkstra.printShortestPath(tokens[2]);

    }



    private void executeMST() {

        Kruskal kruskal = new Kruskal(graph);

        Graph mst = kruskal.run();

        if(mst == null) {
            System.out.println("No MST Found");
            return;
        }

        System.out.println();
        System.out.println("Routes to build : ");

        mst.printEdges();
        System.out.println();

        System.out.println(
                "Total Weight : "
                + kruskal.getTotalWeight()
        );

    }



    private void executeDIAMETER() {

        FloydWarshall floyd =
                new FloydWarshall(graph);

        floyd.run();

        if (!floyd.isConnected()) {

            System.out.println(
                    "Disconnected Graph."
            );

            return;
        }
        if(floyd.getDiameter() > 0 ){

            System.out.println(
                "Diameter : "
                + floyd.getDiameter()
            );
        }

        System.out.print(
                "Longest Path : "
        );

        floyd.printLongestPath();

    }



    private void printHelp() {

        System.out.println();

        System.out.println("========== Commands ==========");

        System.out.println(
                "ADD_STATION <station_name>"
        );
        
        System.out.println(
                "ADD_EDGE <first_station> <second_station> <weight>"
        );

        System.out.println(
            "BFS <first_station> <second_station>"
        );
        
        System.out.println(
                "SHORTEST_PATH <first_station> <second_station>"
        );

        System.out.println(
            "DEADENDS <starting_station> "
        );
 
        // System.out.println(
        //         "PRINT_GRAPH"
        // );
 
        // System.out.println(
        //         "PRINT_STATIONS"
        // );
 
        // System.out.println(
        //         "PRINT_EDGES"
        // );

        System.out.println(
                "MST"
        );

        System.out.println(
                "DIAMETER"
        );

        System.out.println(
                "SEARCH <prefix>"
        );

        System.out.println(
                "UNDO"
        );
        
        System.out.println(
                "EXIT"
        );

        System.out.println();

    }

}