package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
			
		 Vertex[] vert= graph.vertices;
		  
		   boolean[] visit = new boolean[vert.length];
		   PartialTreeList l = new PartialTreeList();
		  
		   PartialTree.Arc a = null;
		   int count = 0;
		   PartialTree t;
		   
		   for(int m =0 ; m < vert.length;m++)
		   {
			   visit[m] = true;
		       
		       t = new PartialTree(vert[m]);
		       MinHeap<PartialTree.Arc> P = t.getArcs(); 
		       Vertex.Neighbor neigh = vert[m].neighbors;
		      
		      
		       while(neigh!= null)
		       {
		       a = new PartialTree.Arc(vert[m], neigh.vertex,neigh.weight);
		       P.insert(a);
		       neigh = neigh.next;
		       }
		       if(visit[m])
		       {
		           l.append(t);
		       }
		       count++;
     
		   }
		   //Iterator<PartialTree> iter = l.iterator();
		  // while (iter.hasNext()) {
		    //   PartialTree pt = iter.next();
		      // System.out.println(pt);
		 //  }
		       return l;
		  
		}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		//System.out.println("FINAL");
		ArrayList<PartialTree.Arc> mstArr = new ArrayList<PartialTree.Arc>();
		while(ptlist.size() > 1) {
			PartialTree pt = ptlist.remove();
			//Iterator<PartialTree> iter = ptlist.iterator();
			   //System.out.println("Nah");
			   //while (iter.hasNext()) {
			      // PartialTree pt1 = iter.next();
			    
			      // System.out.println(pt1);
			   //}
			MinHeap<PartialTree.Arc> arcs = pt.getArcs();
			PartialTree.Arc highest = arcs.deleteMin();
			Vertex v2 = highest.v2;
			while(v2.getRoot().equals(pt.getRoot())) {
				highest = arcs.deleteMin();
				v2 = highest.v2;
			}
			mstArr.add(highest);
			//System.out.println("HI "+v2.toString());
			
			PartialTree pt2 = ptlist.removeTreeContaining(v2);
			//System.out.println("ToString " + pt2.toString());
			
			//System.out.println("V2 -------------------------------------------------------"+ pt2);
			pt2.getRoot().parent = pt.getRoot();
			pt.merge(pt2);
			ptlist.append(pt);
			//Iterator<PartialTree> itery = ptlist.iterator();
			  // while (itery.hasNext()) {
			   //    PartialTree pt1 = itery.next();
			      // System.out.println("This");
			       //System.out.println(pt1);
			  // }
		}
		 //Iterator<PartialTree> iter = ptlist.iterator();
		  // while (iter.hasNext()) {
		     //  PartialTree pt = iter.next();
		      // System.out.println("This");
		       //System.out.println(pt);
		  // }
		   
		return mstArr;
	}
}
