import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HuffEncoder implements HuffmanCoding {

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

    @Override
    public String getFrequencies(File inputFile){
        String fString = fileToString(inputFile);

        List<CharFreq> freqs = getFreqs(fString);

        CharFreq[] sort = new CharFreq[128];
        for(CharFreq c : freqs){
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

    @Override
    public HuffTree buildTree(File inputFile) {
        return new HuffTree(fileToString(inputFile));
    }

    @Override
    public String encodeFile(File inputFile, HuffTree huffTree) {
        String encoded = "";
        String fileString = fileToString(inputFile);
        HashMap<Character, String> encodeTable = huffTree.getEncodeTable();

        for(int i = 0; i < inputFile.length(); i++){
            encoded += encodeTable.get(fileString.charAt(i));
        }

        return encoded;
    }

    @Override
    public String decodeFile(String code, HuffTree huffTree) {
        CharFreq root = huffTree.getRoot();
        CharFreq curr = root;

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

    @Override
    public String traverseHuffmanTree(HuffTree huffTree) {
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

    private List<CharFreq> getFreqs(String inputString){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for(int i = 0; i < inputString.length(); i++){
            if(!map.containsKey(inputString.charAt(i))){
                map.put(inputString.charAt(i), 0);
            }

            map.put(inputString.charAt(i), map.get(inputString.charAt(i))+1);
        }

        ArrayList<CharFreq> list = new ArrayList<CharFreq>();

        for(Character c : map.keySet()){
            list.add(new CharFreq(map.get(c), c));
        }

        return list;
    }
}
