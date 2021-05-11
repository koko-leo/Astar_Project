

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graphs {
	
	private static boolean negative = false;
	private static int nodes;

	public static Graph readFile(String grFile, String coFile) {

		Graph graph = new AdjGraph(900174);
		
		try (BufferedReader br = new BufferedReader(new FileReader("/Users/koko/Downloads/" + grFile + ".gr"))) {

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {

				if(!sCurrentLine.startsWith("c")) {
					if(sCurrentLine.startsWith("p")) {
						String[] line = sCurrentLine.split(" ");
						nodes = Integer.parseInt(line[3])+2;
System.out.println(nodes);
					} else {
						String[] line = sCurrentLine.split(" ");
						System.out.println(line[1]);
						System.out.println(line[2]);
						System.out.println(line[3]);
						graph.addEdge(Integer.parseInt(line[1]), Integer.parseInt(line[2]),(int) Math.ceil(Integer.parseInt(line[3])*1.6));
					}	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}   

		try (BufferedReader br = new BufferedReader(new FileReader("/Users/koko/Downloads/" + coFile + ".co"))) {

			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return graph;
	}

	
	
	public static Graph createRandomGraph(int edges) {
		
		var graph = new AdjGraph(edges);
		
		for(int i=0; i < edges*edges; i++) 
			graph.addEdge((int) Math.random(), (int) Math.random(), (int) Math.random());
		
		return graph;
		
	}
      

	public static boolean hasNegativeCycle(Graph g, int source, int[] d) {
		
		g.forEachEdge(source, edge -> {
            if((d[edge.getStart()] != Integer.MAX_VALUE) && (d[edge.getStart()] + edge.getValue() < d[edge.getEnd()] )) 
            	negative= true;	
        });
		
        return negative;
	}
	
	public static ShortestPathFromOneVertex bellmanFord(Graph g, int source) {
		
		var d = new int[g.numberOfVertices()];
		var pi = new int[g.numberOfVertices()];

		if(hasNegativeCycle(g, source, d)) 
			throw new IllegalStateException("Negative Cycle detected!");
		else {
			for(int i= 0; i < g.numberOfVertices(); i++) {
				d[i] = Integer.MAX_VALUE;
				pi[i]= -1;
			}

			d[source] = 0;
			
			for(int i= 1; i < g.numberOfVertices(); i++) {
				g.forEachEdge(source, edge -> {
		            if((d[edge.getStart()] != Integer.MAX_VALUE) && (d[edge.getStart()] + edge.getValue() < d[edge.getEnd()] )) {
		            	d[edge.getEnd()] = d[edge.getStart()] + edge.getValue();
		            	pi[edge.getEnd()] = edge.getStart(); 												//verificar  
		            }
		        });
			}
		}
		return new ShortestPathFromOneVertex(source, d, pi);
	}
	
	//This functions allows to verify if the Graph has negative weights, if it does we can not perform Dijkstra Algorithm
	
	public static boolean hasNegativeWeights(Graph g) { 
		
		for(int i = 0; i<g.numberOfEdges(); i++) {
			for(int j=0; j<g.numberOfEdges(); j++) {
				if(g.getWeight(i, j) < 0)
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		
		System.out.println(readFile("USA-road-d.BAY", "USA-road-d.NW").toGraphviz());
		
	}

}

