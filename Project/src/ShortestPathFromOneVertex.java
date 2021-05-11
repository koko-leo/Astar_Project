

import java.util.Arrays;

public class ShortestPathFromOneVertex {
	private final int source;
	private final int[] d;
	private final int[] pi;

	ShortestPathFromOneVertex(int vertex, int[] d, int[] pi) {
		this.source = vertex;
		this.d = d;
		this.pi = pi;
	}
	
	public void printShortestPathTo(int destination) {
		
		var path = new StringBuilder();
		path.append(this.source).append("-->");
		
		for(int i= source; i!= destination; i = pi[i]) {
			if(i != pi[destination])
				path.append(i).append("-->");
		}
		path.append(destination);		
		System.out.println(path.toString());
	}

	@Override
	public String toString() {
		return source + " " + Arrays.toString(d) + " " + Arrays.toString(pi);
	}
}
