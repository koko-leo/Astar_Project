

import java.util.Arrays;

public class ShortestPathFromAllVertices {
	private final int[][] d;
	private final int[][] pi;

	ShortestPathFromAllVertices(int[][] d, int[][] pi) {
		this.d = d;
		this.pi = pi;
	}

	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < d.length; i++) {
			bf.append(Arrays.toString(d[i])).append("\t").append(Arrays.toString(pi[i])).append("\n");
		}

		return bf.toString();
	}
	
	public void printShortestPath(int source, int destination) {
		
		var path = new StringBuilder();
		path.append(source).append("-->");
		
		for(int i= source; i!= destination; i = pi[i][destination]) {
			for(int j= destination; j!= source; j = pi[source][j]) {
				if(i != pi[source][destination])
					path.append(i).append("-->");
			}
		}
		path.append(destination);		
		System.out.println(path.toString());
	}
}
