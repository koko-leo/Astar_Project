

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Consumer;

public class AdjGraph implements Graph {

	private final ArrayList < LinkedList <Edge> > adj ;
	private final int n; // number of vertices
	
	public AdjGraph(int n) {
		
		if (n < 0) 
			throw new IllegalArgumentException();
		
		this.n = n;
		this.adj = new ArrayList<>(n);
		
		for(int i=0; i< this.n; i++)
			this.adj.add(new LinkedList<>());
	}
	
	
	@Override
	public int numberOfEdges() {
		var total = 0;
		
		for(LinkedList l: this.adj)
			total+= l.size();
		
		return total;
	}
	
	@Override
	public int numberOfVertices() {
		return this.n;
	}
	
	@Override
	public void addEdge(int i, int j, int value) {
		this.adj.get(i).add(j, new Edge(i,j,value));
	}
	
	@Override
	public boolean isEdge(int i, int j) {

		return this.adj.get(i).contains(new Edge(i,j));
	}
	
	@Override
	public int getWeight(int i, int j) {
		return this.adj.get(i).get(j).getValue();
	}

	@Override
	public Iterator<Edge> edgeIterator(int i) {
		return this.adj.get(i).iterator();
	}

	@Override
	public String toGraphviz() {
		
		StringBuilder displayG = new StringBuilder("digraph G {/n");
		
		for (int i = 0; i < adj.size(); i++) {
			
            displayG.append(i).append(";\n");
            
            for(int j = i; j < numberOfEdges(); j++) 
            	displayG.append(i).append(" -> ").append("[ label=\"").append(getWeight(i,j)).append("\" ] ;\n");
            
		}
		
		displayG.append("}");
		
		return displayG.toString();
	}
}
