package undo;

import graph.Graph;

public interface UndoCommand {

    void undo(Graph graph);

}