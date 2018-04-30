import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
    public static void main(String[] args) throws Exception {
        HuffmanEncoder hc = new HuffmanEncoder();
        File myF = new File("randTest.txt");
        System.out.println(hc.getFrequencies(myF));
        HuffTree myTree = (hc.buildTree(myF));

        System.out.println(hc.traverseHuffmanTree(myTree));
        String enc = hc.encodeFile(myF, myTree);

        System.out.println(enc);

        System.out.println(hc.decodeFile(enc, myTree));

//  System.out.println(generateTestStrring(10));


    }

    static ArrayList<Integer> generateFreqs(int num){
        ArrayList<Integer> myList = new ArrayList<Integer>(num);
        myList.add(1);
        myList.add(2);
        ZacMinHeap<Integer> heap = new ZacMinHeap<Integer>(num);

        heap.push(1);
        heap.push(2);
        int count = 2;

        int left;
        int right;
        while(count < num){
            left = heap.pop();
            right = heap.pop();

            heap.push(left + right);
            heap.push(left + right + 1);
            myList.add(left+right+1);
            count++;
        }

        return myList;
    }

    static String generateTestStrring(int num){
        StringBuilder writeString = new StringBuilder();
        int length = 0;

        ArrayList<ZacCharFreq> freqs = makeFreqs(num);
        for(ZacCharFreq cf : freqs){
            for(int i = 0; i < cf.getFrequency(); i++){
                int randomNum = ThreadLocalRandom.current().nextInt(0, length + 1);
                writeString.insert(randomNum, cf.getChar());
                length++;
            }
        }

        return writeString.toString();
    }

    static ArrayList<ZacCharFreq> makeFreqs(int num){
        ArrayList<ZacCharFreq> cf = new ArrayList<ZacCharFreq>(8);
        HashSet<Character> set = new HashSet<Character>();
        for(Integer i : generateFreqs(num)){
            Character c = (char)ThreadLocalRandom.current().nextInt(32, 128);
            while (set.contains(c)){
                c = (char)ThreadLocalRandom.current().nextInt(32, 128);
            }

            cf.add(new ZacCharFreq(i, c));
        }

        return cf;
    }
}
