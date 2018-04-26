import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TrueHuffEncoder {

    public String fileToString(File inputFile){
        if(!inputFile.canRead()){
            throw new Error("File unreadable");
        }
        Scanner fScan = null;
        try {
            fScan = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new Error("File not found");
        }

        String fileString = "";

        while(fScan.hasNextLine()){
            fileString += fScan.nextLine();
        }

        return fileString;
    }

    
    public String getFrequencies(File inputFile){
        String fString = fileToString(inputFile);

        List<ZacCharFreq> freqs = getFreqs(fString);

        ZacCharFreq[] sort = new ZacCharFreq[128];
        for(ZacCharFreq c : freqs){
            sort[c.getChar()] = c;
        }

        String freqString = "";
        for(int i = 0; i < sort.length; i++) {
            if(sort[i] != null) {
                freqString += sort[i] + "\n";
            }
        }

        return freqString;
    }

    
    public TrueHuffTree buildTree(File inputFile) {
        return new TrueHuffTree(fileToString(inputFile));
    }

    
    public String encodeFile(File inputFile, TrueHuffTree huffTree) {
        String encoded = "";
        String fileString = fileToString(inputFile);
        HashMap<Character, String> encodeTable = huffTree.getEncodeTable();

        for(int i = 0; i < inputFile.length(); i++){
            encoded += encodeTable.get(fileString.charAt(i));
        }

        return encoded;
    }

    
    public String decodeFile(String code, TrueHuffTree huffTree) {
        ZacCharFreq root = huffTree.getRoot();
        ZacCharFreq curr = root;

        String decode = "";

        if(root == null || (root.getLeft() == null && root.getRight() == null)){
            return "";
        }

        for(int i = 0; i < code.length(); i++){
            if(code.charAt(i) == '0'){
                curr = curr.getLeft();
            }
            else{
                curr = curr.getRight();
            }

            if(curr.getLeft() == null && curr.getRight() == null){
                decode += curr.getChar();

                curr = root;
            }
        }

        return decode;
    }

    
    public String traverseHuffmanTree(TrueHuffTree huffTree) {
        HashMap<Character, String> encodeTable = huffTree.getEncodeTable();
        String printString = "";

        int[] sort = new int[128];
        for(Character c : encodeTable.keySet()){
            char nc = c;
            sort[nc] = 1;
        }

        for(int i = 0; i < sort.length; i++) {
            if(sort[i] == 1) {
                printString += (char)i + " " + encodeTable.get((char)i) + "\n";
            }
        }
        return printString;
    }

    private List<ZacCharFreq> getFreqs(String inputString){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for(int i = 0; i < inputString.length(); i++){
            if(!map.containsKey(inputString.charAt(i))){
                map.put(inputString.charAt(i), 0);
            }

            map.put(inputString.charAt(i), map.get(inputString.charAt(i))+1);
        }

        ArrayList<ZacCharFreq> list = new ArrayList<ZacCharFreq>();

        for(Character c : map.keySet()){
            list.add(new ZacCharFreq(map.get(c), c));
        }

        return list;
    }
}
