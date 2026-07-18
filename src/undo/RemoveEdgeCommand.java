package undo;

import graph.Graph;
import graph.Station;

public class RemoveEdgeCommand implements UndoCommand {

    private Station source;

    private Station destination;

    public RemoveEdgeCommand(
            Station source,
            Station destination) {

        this.source = source;
        this.destination = destination;

    }

    @Override
    public void undo(Graph graph) {

        graph.removeEdge(
                source.getName(),
                destination.getName()
        );

        System.out.println("Edge between " + source.getName() + " and " + destination.getName() + " removed successfully");

    }

}