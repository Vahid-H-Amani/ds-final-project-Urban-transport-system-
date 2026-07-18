package graph;

import java.util.Objects;


public class Edge implements Comparable<Edge>{

    private Station source;
    private Station destination;
    private double weight;


    public Edge(Station source , Station destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    public Station getSource() {
        return source;
    }


    public Station getDestination() {
        return destination;
    }


    public double getWeight() {
        return weight;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }



    @Override
    public int compareTo(Edge other) {

        return Double.compare(this.weight, other.weight);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.source);
        hash = 13 * hash + Objects.hashCode(this.destination);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        return Objects.equals(this.destination, other.destination);
    }



    

}