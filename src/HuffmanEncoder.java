//Arnold Baburam
//COP3530 Project 3
//Spring 2018

import java.util.*;
import java.io.*;

class HuffTree {
	char data;
	int freq;
	String code;
	HuffTree left;
	HuffTree right;
}

public class HuffmanEncoder implements HuffmanCoding {
	
	int[] asciiTable = new int[128];
	
	//Take a file as input and create a table with characters and frequencies.
    //Print the characters and their frequencies.
	public String getFrequencies(File inputFile) {
		String w = "";
		for (int a = 32; a < 128; a++) { 							//Ensure that the table is fresh.
			asciiTable[a] = 0;
		}
		FileReader read = null;
		BufferedReader parse = null;
		try {
			read = new FileReader(inputFile);
			parse = new BufferedReader(read);
		}
		catch (FileNotFoundException fail) {
		}
		int i;    
        try {
			while ((i = parse.read()) != -1) {  					//Increment the appropriate table index for each character.
				asciiTable[i]++; 
			}
		} 
        catch (IOException e) {
			e.printStackTrace();
		}
		for (int j = 32; j < 128; j++) {
			char ch = (char) j;
			if (asciiTable[j] > 0) {
				w += ch + " " + asciiTable[j] + "\n";				//Print the character and its frequency.
			}	
		}
		return w;
	}
	
	//Take a file as input and create a Huffman tree.
	public HuffTree buildTree(File inputFile) {
		LinkedList<HuffTree> myTrees = new LinkedList<HuffTree>();
		for (int a = 32; a < 128; a++) { 							//Ensure that the table is fresh.
			asciiTable[a] = 0;
		}
		getFrequencies(inputFile);									//Regenerate the frequencies.
		for (int k = 32; k < 128; k++) {							//Create a tree for each character.
			if (asciiTable[k] > 0) {
				HuffTree addition = new HuffTree();
				addition.data = (char) k;
				addition.freq = asciiTable[k];
				myTrees.addLast(addition);
			}
		}
		while (myTrees.size() > 1) {								//Combine trees until one remains.
			int minFreq = (int) Double.POSITIVE_INFINITY;
			int minIndex = 0;
			for (int i = 0; i < myTrees.size(); i++) {
				if (myTrees.get(i).freq < minFreq) {
					minFreq = myTrees.get(i).freq;
					minIndex = i;
				}
			}
			HuffTree x = myTrees.remove(minIndex);					//Establish the left child.
			minFreq = (int) Double.POSITIVE_INFINITY;
			minIndex = 0;
			for (int i = 0; i < myTrees.size(); i++) {
				if (myTrees.get(i).freq < minFreq) {
					minFreq = myTrees.get(i).freq;
					minIndex = i;
				}
			}
			HuffTree y = myTrees.remove(minIndex);					//Establish the right child.
			HuffTree addition = new HuffTree();
			addition.freq = x.freq + y.freq;
			addition.left = x;
			addition.right = y;
			addition.code = "";
			myTrees.addLast(addition);
		}
		HuffTree z = myTrees.remove(0);								//Return the final tree.
		return z;
	}
    
	//Take a file and a HuffTree, then encode the file.  
    //Output a String of 1's and 0's representing the file.
	public String encodeFile(File inputFile, HuffTree huffTree) {
		String comparable = traverseHuffmanTree(huffTree);			//Retrieve the list of character codes.
		String[] lines = comparable.split("\\n");					//Split the character codes into array indices.
		String y = "";
		FileReader read = null;
		BufferedReader parse = null;
		try {
			read = new FileReader(inputFile);
			parse = new BufferedReader(read);
		}
		catch (FileNotFoundException fail) {
		}
		int i;    
        try {
			while ((i = parse.read()) != -1) {  					//Skim the string array until the index with the value is found.
				char value;
				value = (char) i;
				int d = 0;
				while (d < lines.length) {
					if (lines[d].length() > 0) {
						if (lines[d].charAt(0) == value) {
							break;
						}
						else {
							d++;
						}
					}
				}
				if (d < lines.length) {
					if (lines[d].length() > 2) {
						y += lines[d].substring(2);
					}
				}
			}
		} 
        catch (IOException e) {
			e.printStackTrace();
		}
    	return y;
    }
    
	//Take a String and a HuffTree, then output the decoded words.
	public String decodeFile(String code, HuffTree huffTree) {
    	String v = "";
    	HuffTree initial = huffTree;
    	for (int i = 0; i < code.length(); i++) {
    		char value;
    		value = code.charAt(i);
    		if (value == '0') {
    			huffTree = huffTree.left;
    		}
    		else if (value == '1'){
    			huffTree = huffTree.right;
    		}
    		else {													//Stop decoding if an ineligible character is spotted.
    			return v;
    		}
    		if (huffTree.data != 0) {
    			v += huffTree.data;
    			huffTree = initial;
    		}
    	}
		return v;
    }
    
	//Print the characters and their codes.
	public String traverseHuffmanTree(HuffTree huffTree) {
		String x = "";
		if (huffTree == null) { 
			return ""; 
		} 
		Stack<HuffTree> traversal = new Stack<HuffTree>();			//Utilize a stack for DFS traversal.
		huffTree.code = "";
		traversal.push(huffTree); 
		while (traversal.isEmpty() == false) { 
			HuffTree popper = traversal.pop(); 
			if (popper.right != null) { 
				popper.right.code = popper.code + "1";
				traversal.push(popper.right);
			} 
			if (popper.left != null) { 
				popper.left.code = popper.code + "0";
				traversal.push(popper.left); 
			} 
			if (popper.left == null && popper.right == null) { 
				x += popper.data + " " + popper.code + "\n";
			} 
		}
		return x;
    }
	
}