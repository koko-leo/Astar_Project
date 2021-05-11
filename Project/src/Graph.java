
import java.util.Iterator;
import java.util.function.Consumer;

public interface Graph {
	
	int numberOfEdges();
	
	int numberOfVertices();
	
	void addEdge (int i, int j, int value ) ;
	
	boolean isEdge (int i, int j) ;
	
	int getWeight (int i, int j) ;
	
	Iterator <Edge > edgeIterator (int i) ;

	default void forEachEdge(int i, Consumer<Edge> consumer) {
		
		if (i >= numberOfVertices()) 
			throw new IllegalArgumentException();
		
		edgeIterator(i).forEachRemaining(consumer);
	}
	
	String toGraphviz () ;
	
	default public Graph transpose() {

		var g = new AdjGraph(numberOfVertices());

		for(int i = 0; i < numberOfVertices(); i++) {
			g.forEachEdge(0, edge -> {
				g.addEdge(edge.getEnd(), edge.getStart(), edge.getValue());

			});
		}

		return g;

	}
	
}
