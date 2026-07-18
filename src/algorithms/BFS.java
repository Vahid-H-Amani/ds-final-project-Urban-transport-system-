package algorithms;


import dataStructure.HashTable;
import dataStructure.LinkedList;
import dataStructure.Queue;
import graph.*;


public class BFS {


    public static LinkedList<Station> traverse(Graph graph, String startName , String destination) {

        Station start = graph.getStation(startName);
        Station end = graph.getStation(destination);

        // if (start == null) {
        //     return null ;
        // }


        if (start.equals(end)) {
            LinkedList<Station> path = new LinkedList<>();
            path.addLast(start);
            return path;
        }

        Queue<Station> queue = new Queue<>();

        HashTable<Station, Boolean> visited = new HashTable<>(100);
        HashTable<Station, Station> previous = new HashTable<>(100);

        queue.enqueue(start);
        visited.put(start, true);   
        previous.put(start , null);

        while (!queue.isEmpty()) {

            Station current = queue.dequeue();

            LinkedList<Edge> edges = current.getEdges();

            for (int i = 0; i < edges.getSize(); i++) {

                Edge edge = edges.get(i);

                Station neighbor = edge.getDestination();

                if (!visited.containsKey(neighbor)) {

                    visited.put(neighbor, true);

                    previous.put(neighbor , current);

                    if(end.equals(neighbor)){

                        LinkedList <Station> path = new LinkedList<>();
                        
                        Station dynamicEnd = neighbor;
                        
                        path.addFirst(dynamicEnd);
                        
                        while((dynamicEnd = previous.get(dynamicEnd)) != null){
                        
                            path.addFirst(dynamicEnd);
                        }
                        
                        return path;

                    }

                    queue.enqueue(neighbor);

                }

            }

        }

        return null;

    }



    public static void printPath(Graph graph, String startName , String destination){
        LinkedList<Station> path = traverse(graph, startName, destination);
        
        if(path == null){
            System.out.println("No Path");
        }

        else if(path.isEmpty()){
            System.out.println("our source and destination is equal");
        }

        else {
            for ( int i = 0 ; i < path.getSize() ; i++){
                Station st = path.get(i);
                System.out.printf(st.getName());
                if(i != path.getSize() - 1){
                    System.out.printf(" -> ");
                }
            }
            System.out.println();
            System.out.println("Number Of Edges : " + (path.getSize() - 1 ));
        }

    }

    

}