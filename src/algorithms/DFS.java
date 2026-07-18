package algorithms;

import dataStructure.HashTable;
import dataStructure.LinkedList;
import graph.Edge;
import graph.Graph;
import graph.Station;

public class DFS {


//for find path of DFS
    public  LinkedList<Station> traverse(Graph graph, String startName) {

        LinkedList<Station> result = new LinkedList<>();

        Station start = graph.getStation(startName);

        if (start == null) {
            return result;
        }

        HashTable<Station, Boolean> visited = new HashTable<>(100);

        dfs(start, visited, result);

        return result;
    }



    private  void dfs(Station current,
                            HashTable<Station, Boolean> visited,
                            LinkedList<Station> result) {

        visited.put(current, true);

        result.addLast(current);

        LinkedList<Edge> edges = current.getEdges();


        for (int i = 0; i < edges.getSize(); i++) {

            Station neighbor = edges.get(i).getDestination();

            if (!visited.containsKey(neighbor)) {

                dfs(neighbor, visited, result);
            }
            

        }


    }
    






    

//for find corners
    public static  LinkedList<Station> findCorners(Graph graph, String startName) {

        LinkedList<Station> corners = new LinkedList<>();

        Station start = graph.getStation(startName);

        if (start == null) {
            return corners;
        }

        if(start.getEdges().getSize() == 0 ){
            corners.addFirst(start);
            return corners;
        }

        

        HashTable<Station, Boolean> visited = new HashTable<>(100);

        dfsToFindCorners(start, visited, corners);

        return corners;
        
    }






    public static void dfsToFindCorners(Station current,
                            HashTable<Station, Boolean> visited,
                            LinkedList<Station> corners) {

        visited.put(current, true);

        LinkedList<Edge> edges = current.getEdges();

        if(edges.getSize() == 1) corners.addFirst(current);


        for (int i = 0; i < edges.getSize(); i++) {

            Station neighbor = edges.get(i).getDestination();

            if (!visited.containsKey(neighbor)) {

                dfsToFindCorners(neighbor, visited, corners);

            }
            

        }


    }


    public static void printDFSCorners(Graph graph, String startName){
        
        LinkedList<Station> corners = findCorners(graph, startName);

        System.out.println("Dead-ends reachable from Sadeghieh :  ");
        
        if(corners.isEmpty()){
            System.out.println("No dead-ends found");
            return;
        }

        while(!corners.isEmpty()){
            
            Station currnet = corners.removeFirst();
            
            System.out.print(currnet.getName());
            
            if(corners.isEmpty() == false){
            
                System.out.print("  ,  ");

                continue;
            }

            break;
        }
        
        System.out.println();

    }

}