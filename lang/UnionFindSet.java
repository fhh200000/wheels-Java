package lang;

public class UnionFindSet {
	private int parent[];
	public UnionFindSet(int n) {
		parent = new int[n];
		for(int i=0;i<n;i++)
			parent[i] = -1;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for(int i:parent)
			sb.append(i+",");
		sb.delete(sb.length()-1,sb.length());
		sb.append(")");
		return sb.toString();
	}
	public int find(int i) {
		while(parent[i]>=0)
			i=parent[i];
		return i;
	}
	public boolean union(int i,int j) {
		int rooti=find(i),rootj=find(j);
		if(rooti!=rootj) {
			if(parent[rooti]<=parent[rootj]) {
				parent[rooti]+=parent[rootj];
				parent[rootj] = rooti;
			}
			else {
				parent[rootj]+=parent[rooti];
				parent[rooti]=rootj;
			}
		}
		return rooti!=rootj;
	}
	public int collapsingFind(int i) {
		int root=i;
		while(parent[root]>=0)
			root=parent[root];
		while(root!=i&&parent[i]!=root) {
			int pa=parent[i];
			parent[i] = root;
			i = pa;
		}
		return root;
	}
}
