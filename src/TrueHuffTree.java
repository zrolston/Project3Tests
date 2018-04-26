import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrueHuffTree {

    ZacCharFreq root;

    public TrueHuffTree(){
            root = null;
        }

    public TrueHuffTree(ZacCharFreq root){
            this.root = root;
        }

    public TrueHuffTree(String fileString){
            setTree(fileString);
        }

        private ZacCharFreq buildHuffTree(ZacMinHeap<ZacCharFreq> myHeap) {
            ZacCharFreq left;
        ZacCharFreq right;
        ZacCharFreq sumNode;

        while(myHeap.getCount() > 1){
            left = myHeap.pop();
            right = myHeap.pop();

            sumNode = new ZacCharFreq();
            sumNode.setChar('*');
            sumNode.setFrequency(left.getFrequency() + right.getFrequency());
            sumNode.setLeft(left);
            sumNode.setRight(right);

            myHeap.push(sumNode);
        }

        return myHeap.pop();
    }

    public void setTree(String inputFile){
        ZacMinHeap<ZacCharFreq> myHeap = buildFreqHeap(inputFile);

        root = buildHuffTree(myHeap);
    }

    private ZacMinHeap<ZacCharFreq> buildFreqHeap(String fileString){
        List<ZacCharFreq> myFreqs = getFreqs(fileString);

        ZacMinHeap<ZacCharFreq> heap = new ZacMinHeap<ZacCharFreq>();

        for(ZacCharFreq c : myFreqs){
            heap.push(c);
        }
        return heap;
    }

    public HashMap<Character, String> getEncodeTable(){
        HashMap<Character, String> encodeTable = new HashMap<Character, String>();

        recurseEncode(root, "", encodeTable);
        return encodeTable;
    }

    private void recurseEncode(ZacCharFreq node, String prefixCode, HashMap<Character, String> encodeTable){

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

    public ZacCharFreq getRoot(){
        return root;
    }
}
