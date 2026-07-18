package undo;

import graph.Graph;
import graph.Station;

public class RemoveStationCommand implements UndoCommand {

    private Station station;

    public RemoveStationCommand(Station station) {

        this.station = station;

    }

    @Override
    public void undo(Graph graph) {

        graph.removeStation(station.getName());

        
        System.out.println("Station " + station.getName() + " removed successfully");


    }

}