import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

class HuffEncoderTest {

    //Score: 2
    @Test
    void testNonCollisionsGetFrequencies() throws Exception{
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        String expectedFrequencies = correctEncoder.getFrequencies(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            String studentFrequencies = null;
            studentFrequencies = studentEncoder.getFrequencies(nonCollider).trim();

            assertEquals(expectedFrequencies, studentFrequencies);
        });
    }

    //Score: 2
    @Test
    void testCollisionsGetFrequencies() throws Exception{
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("HelloWorld.txt");

        String expectedFrequencies = correctEncoder.getFrequencies(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            String studentFrequencies = null;
            studentFrequencies = studentEncoder.getFrequencies(nonCollider).trim();

            assertEquals(expectedFrequencies, studentFrequencies);
        });
    }

    //Score: 3
    @Test
    void testNonCollisionsGetFrequenciesContent() throws Exception{
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");
        String expectedTable = correctEncoder.getFrequencies(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(2000), () -> {
            String studentTable = null;


            studentTable = studentEncoder.getFrequencies(nonCollider);

            HashMap<Character, String> expected = getMapping(expectedTable);
            HashMap<Character, String> student = getMapping(studentTable);


            assertEquals(expected, student);
        });


    }

    //Score: 3
    @Test
    void testCollisionsGetFrequenciesContent() throws Exception{
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("HelloWorld.txt");
        String expectedTable = correctEncoder.getFrequencies(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(2000), () -> {
            String studentTable = null;


            studentTable = studentEncoder.getFrequencies(nonCollider);

            HashMap<Character, String> expected = getMapping(expectedTable);
            HashMap<Character, String> student = getMapping(studentTable);


            assertEquals(expected, student);
        });
    }

    //Score: 10
    @Test
    void testNonCollisionsEncodeToDecodeFileInOrderCalls() throws Exception{
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        String fileString = stringFromFile(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1500), () -> {
            studentEncoder.getFrequencies(nonCollider);
            HuffTree studentHuffTree = studentEncoder.buildTree(nonCollider);
            String studentEncode = null;
            String studentDecode = null;
            studentEncode = studentEncoder.encodeFile(nonCollider, studentHuffTree).trim();
            HuffmanEncoder newStudentEncoder =  new HuffmanEncoder();
            studentDecode = newStudentEncoder.decodeFile(studentEncode, studentHuffTree).trim();
            assertEquals(fileString, studentDecode);
        });
    }

    //Score: 10
    @Test
    void testNonCollisionsEncodeToDecodeFile() throws Exception{
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        String fileString = stringFromFile(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            HuffTree studentHuffTree = studentEncoder.buildTree(nonCollider);
            String studentEncode = null;
            String studentDecode = null;
            studentEncode = studentEncoder.encodeFile(nonCollider, studentHuffTree).trim();
            HuffmanEncoder newStudentEncoder =  new HuffmanEncoder();
            studentDecode = newStudentEncoder.decodeFile(studentEncode, studentHuffTree).trim();
            assertEquals(fileString, studentDecode);
        });
    }

    //Score: 10
    @Test
    void testNonCollisionsEncodeInOrderCalls () throws Exception{
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        String expectedEncode = correctEncoder.encodeFile(nonCollider, correctEncoder.buildTree(nonCollider)).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            studentEncoder.getFrequencies(nonCollider);
            String studentEncode = null;
            HuffTree studenthufftree = studentEncoder.buildTree(nonCollider);
            studentEncode = studentEncoder.encodeFile(nonCollider, studenthufftree).trim();

            assertEquals(expectedEncode, studentEncode);
        });
    }

    //Score: 10
    @Test
    void testNonCollisionsEncode () throws Exception{
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        String expectedEncode = correctEncoder.encodeFile(nonCollider, correctEncoder.buildTree(nonCollider)).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            String studentEncode = null;
            HuffTree studenthufftree = studentEncoder.buildTree(nonCollider);
            studentEncode = studentEncoder.encodeFile(nonCollider, studenthufftree).trim();

            assertEquals(expectedEncode, studentEncode);
        });
    }

    //Score: 10
    @Test
    void testCollisionsEncodeDecodeFileInOrderCalls() throws Exception {
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("HelloWorld.txt");

        String fileString = stringFromFile(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            studentEncoder.getFrequencies(nonCollider);
            HuffTree studentHuffTree = studentEncoder.buildTree(nonCollider);
            String studentEncode = null;
            String studentDecode = null;
            studentEncode = studentEncoder.encodeFile(nonCollider, studentHuffTree).trim();
            HuffmanEncoder newStudentEncoder =  new HuffmanEncoder();
            studentDecode = newStudentEncoder.decodeFile(studentEncode, studentHuffTree).trim();
            assertEquals(fileString, studentDecode);
        });
    }

    //Score: 10
    @Test
    void testCollisionsEncodeDecodeFile() throws Exception {
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("HelloWorld.txt");

        String fileString = stringFromFile(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            HuffTree studentHuffTree = studentEncoder.buildTree(nonCollider);
            String studentEncode = null;
            String studentDecode = null;
            studentEncode = studentEncoder.encodeFile(nonCollider, studentHuffTree).trim();
            HuffmanEncoder newStudentEncoder =  new HuffmanEncoder();
            studentDecode = newStudentEncoder.decodeFile(studentEncode, studentHuffTree).trim();
            assertEquals(fileString, studentDecode);
        });
    }

    //Score: 2
    @Test
    void testTraverseHuffmanTreeFormat() throws Exception{
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        TrueHuffTree correctHuffTree = correctEncoder.buildTree(nonCollider);
        String expectedTable = correctEncoder.traverseHuffmanTree(correctHuffTree).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            String studentTable = null;
            studentEncoder.getFrequencies(nonCollider);
            HuffTree studentHuffTree = studentEncoder.buildTree(nonCollider);
            studentTable = studentEncoder.traverseHuffmanTree(studentHuffTree).trim();

            assertEquals(expectedTable, studentTable);
        });
    }

    //Score: 8
    @Test
    void testTraverseHuffmanTreeContent() throws Exception{
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanEncoder studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        TrueHuffTree correctHuffTree = correctEncoder.buildTree(nonCollider);
        String expectedTable = correctEncoder.traverseHuffmanTree(correctHuffTree).trim();

        assertTimeoutPreemptively(ofMillis(2000), () -> {
            String studentTable = null;
            studentEncoder.getFrequencies(nonCollider);
            HuffTree studentHuffTree = studentEncoder.buildTree(nonCollider);
            studentTable = studentEncoder.traverseHuffmanTree(studentHuffTree).trim();

            HashMap<Character, String> expected = getMapping(expectedTable);
            HashMap<Character, String> student = getMapping(studentTable);


            assertEquals(expected, student);
        });


    }

    private HashMap<Character,String> getMapping(String expectedTable) {
        HashMap<Character,String> table = new HashMap<Character, String>();
        String[] encodePairs = expectedTable.split("\n");
        for(String pair : encodePairs){
            table.put(pair.charAt(0), pair.substring(2));
        }
        return table;
    }

    public String stringFromFile(File input) throws Exception{
        Scanner s = null;
        try {
            s = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String fileString = "";
        while(s.hasNextLine()){
            fileString += s.nextLine();
        }

        return fileString;
    }
}