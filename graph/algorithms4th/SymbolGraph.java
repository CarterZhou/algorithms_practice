package com.cupid.algorithm.graph.algorithms4th;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.Set;

public class SymbolGraph {
	private Map<String,Integer> symbolTable;
	private String[] keys; 
	private Graph myGraph;
	
	public int indexOfKey(String key){
		return symbolTable.get(key);
	}
	
	public String keyOfIndex(int index){
		return keys[index];
	}
	
	public SymbolGraph(String fileName,String delimiter){
		symbolTable = new HashMap<String, Integer>();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			// Build the symbol table
			while((line = reader.readLine())!=null){
				String[] inputs = line.split(delimiter);
				for(int i=0;i<inputs.length;i++){
					if(!symbolTable.containsKey(inputs[i])){
						symbolTable.put(inputs[i], symbolTable.size());
					}
				}
			}
			reader.close();
			// Build the array of keys
			keys = new String[symbolTable.size()];
			Set<Entry<String,Integer>> entries = symbolTable.entrySet();
			for (Entry<String, Integer> entry : entries) {
				keys[entry.getValue()] = entry.getKey(); 
			}
			
			//Build the Graph
			reader = new BufferedReader(new FileReader(fileName));
			myGraph = new Graph(symbolTable.size());
			while((line = reader.readLine())!=null){
				String[] inputs = line.split(delimiter);
				int u = symbolTable.get(inputs[0]);
				for(int i=1;i<inputs.length;i++){
					myGraph.addEdge(u,symbolTable.get(inputs[i]));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Graph getMyGraph() {
		return myGraph;
	}

	public static void main(String[] args) {
		String fileName = args[0];
		String delimiter = args[1];
		SymbolGraph sg = new SymbolGraph(fileName, delimiter);
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sourcePerson = null;
		String desPerson = null;
		try {
			/*sourcePerson = br.readLine();
			Iterator<Integer> list = sg.myGraph.adj(sg.indexOfKey(sourcePerson)).iterator();
			while(list.hasNext()){
				int v = list.next();
				System.out.println(sg.keyOfIndex(v));
			}*/
			System.out.println("Please enter person as source:");
			sourcePerson = br.readLine();
			BreathFirstPath bfsp = new BreathFirstPath(sg.indexOfKey(sourcePerson), sg.myGraph);
			bfsp.breadFirstSearch();
			System.out.println("Please enter the person whose degree of seperation from " + sourcePerson + " you want to know:");
			desPerson = br.readLine();
			Stack<Integer> path = (Stack<Integer>) bfsp.findPath(sg.indexOfKey(desPerson));
			int degrees = path.pop();
			if(path.size() > 0){
				while(!path.empty()){
					int v = path.pop();
					System.out.println(sg.keyOfIndex(v));
				}
				System.out.println("Degree of seperation is " + degrees );
			}else{
				System.out.println("No connections between " + sourcePerson + " and " + desPerson );
				System.out.println("Degree of seperation is " + degrees );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
