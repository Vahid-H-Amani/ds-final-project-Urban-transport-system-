package undo;

import graph.Graph;
import graph.Station;
public class AddEdgeCommand implements UndoCommand {

    private Station source;

    private Station destination;

    private double weight;

    public AddEdgeCommand(
            Station source,
            Station destination ,
            double weight) {

        this.source = source;
        this.destination = destination;
        this.weight = weight;

    }

    @Override
    public void undo(Graph graph) {

        graph.addEdge(source.getName(), destination.getName(), weight);
        
        System.out.println("weight of edge between " + source.getName() + " and " + destination.getName() + " changed to past value");

    }

}
