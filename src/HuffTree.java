import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HuffTree {

    CharFreq root;

    public HuffTree(){
        root = null;
    }

    public HuffTree(CharFreq root){
        this.root = root;
    }

    public HuffTree(String fileString){
        setTree(fileString);
    }

    private CharFreq buildHuffTree(MinHeap<CharFreq> myHeap) {
        CharFreq left;
        CharFreq right;
        CharFreq sumNode;

        while(myHeap.getCount() > 1){
            left = myHeap.pop();
            right = myHeap.pop();

            sumNode = new CharFreq();
            sumNode.setChar('*');
            sumNode.setFrequency(left.getFrequency() + right.getFrequency());
            sumNode.setLeft(left);
            sumNode.setRight(right);

            myHeap.push(sumNode);
        }

        return myHeap.pop();
    }

    public void setTree(String inputFile){
        MinHeap<CharFreq> myHeap = buildFreqHeap(inputFile);

        root = buildHuffTree(myHeap);
    }

    private MinHeap<CharFreq> buildFreqHeap(String fileString){
        List<CharFreq> myFreqs = getFreqs(fileString);

        MinHeap<CharFreq> heap = new MinHeap<CharFreq>();

        for(CharFreq c : myFreqs){
            heap.push(c);
        }
        return heap;
    }

    public HashMap<Character, String> getEncodeTable(){
        HashMap<Character, String> encodeTable = new HashMap<Character, String>();

        recurseEncode(root, "", encodeTable);
        return encodeTable;
    }

    private void recurseEncode(CharFreq node, String prefixCode, HashMap<Character, String> encodeTable){

        //Base case (a leaf node)
        if(node.getLeft() == null && node.getRight() == null){
            encodeTable.put(node.getChar(), prefixCode);
            return;
        }

        if(node.getLeft() != null){
            recurseEncode(node.getLeft(), prefixCode + "0", encodeTable);
        }
        if(node.getRight() != null){
            recurseEncode(node.getRight(), prefixCode + "1", encodeTable);
        }
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

    public CharFreq getRoot(){
        return root;
    }
}
