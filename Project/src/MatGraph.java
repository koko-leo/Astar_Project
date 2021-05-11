

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class MatGraph implements Graph {
	
	private final int [][] mat;
	private final int n; // number of vertices
	
	public MatGraph(int n) {
		this.n = n;
		mat = new int[n][n];
	}
	
	@Override
	public int numberOfEdges() {

		var num = 0;
		
		for(int i =0; i <mat.length; i++) {
			for(int j=0; j<mat[i].length; j++) {
				
				if(mat[i][j]!= 0)
					num++;
			}
		}
		return num;
	}

	@Override
	public int numberOfVertices() {
		
		return n;
	}

	@Override
	public void addEdge(int i, int j, int value) {
		mat[i][j] = value;
	}

	@Override
	public boolean isEdge(int i, int j) {
		
		if(mat[i][j] == 0)
			return false;
		
		return true;
	}

	@Override
	public int getWeight(int i, int j) {
		
		return mat[i][j];
	}

	@Override
	public Iterator<Edge> edgeIterator(int i) {

		return new Iterator<Edge>() {

			@Override
			public Edge next() {
				if(!hasNext())
					throw new NoSuchElementException();
				
				return new Edge(mat[i][i], mat[i][i+1]) ;
			}

			@Override
			public boolean hasNext() {
				
				return mat[i+1].length < mat.length ;
			}
		};

	}

	@Override
    public String toGraphviz() {
        var displayG = new StringBuilder("digraph G {\n");
        
        IntStream.range(0, n).forEach(i -> {
                displayG.append('\t').append(i).append(";\n");
                
                IntStream.range(0, n).forEach(j -> {
                        if (mat[i][j] != 0) 
                            displayG.append('\t').append(i).append(" -> ").append(j).append(" [label=\"").append(mat[i][j]).append("\" ] ;\n");
                    }
                );
            }
        );
        
        displayG.append('}');
        
        return displayG.toString();
    }

}
