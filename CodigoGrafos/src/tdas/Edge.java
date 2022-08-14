package tdas;

class Edge<E,V> {
    private Vertex<V,E> source;
    private Vertex<V,E> target;
    private int weight;
    private E metadata;

    public Edge(Vertex<V, E> source, Vertex<V, E> target, int weight, E metadata) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.metadata = metadata;
    }

    public Vertex<V, E> getSource() {
        return source;
    }

    public void setSource(Vertex<V, E> source) {
        this.source = source;
    }

    public Vertex<V, E> getTarget() {
        return target;
    }

    public void setTarget(Vertex<V, E> target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public E getMetadata() {
        return metadata;
    }

    public void setMetadata(E metadata) {
        this.metadata = metadata;
    }
    
    

    @Override
    public String toString() {
        return "[ -> " + target.getContent() + ", peso: " + weight + ", metadata: " + metadata + " ]";
    }
    
    
    
}
