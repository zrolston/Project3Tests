import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

class HuffEncoderTest {

    @Test
    void testNonCollisionsGetFrequencies() {
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanCoding studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        String expectedFrequencies = correctEncoder.getFrequencies(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            String studentFrequencies = null;
            studentFrequencies = studentEncoder.getFrequencies(nonCollider).trim();

            assertEquals(expectedFrequencies, studentFrequencies);
        });
    }

    @Test
    void testCollisionsGetFrequencies() {
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanCoding studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("HelloWorld.txt");

        String expectedFrequencies = correctEncoder.getFrequencies(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            String studentFrequencies = null;
            studentFrequencies = studentEncoder.getFrequencies(nonCollider).trim();

            assertEquals(expectedFrequencies, studentFrequencies);
        });
    }

    @Test
    void testNonCollisionsEncodeToDecodeFile() {
        HuffmanCoding studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        String fileString = stringFromFile(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            HuffTree studentHuffTree = studentEncoder.buildTree(nonCollider);
            String studentEncode = null;
            String studentDecode = null;
            studentEncode = studentEncoder.encodeFile(nonCollider, studentHuffTree).trim();
            HuffmanCoding newStudentEncoder =  new HuffmanEncoder();
            studentDecode = newStudentEncoder.decodeFile(studentEncode, studentHuffTree).trim();
            assertEquals(fileString, studentDecode);
        });
    }

    @Test
    void testNonCollisionsEncide () {
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanCoding studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        String expectedEncode = correctEncoder.encodeFile(nonCollider, correctEncoder.buildTree(nonCollider)).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            String studentEncode = null;
            HuffTree studenthufftree = studentEncoder.buildTree(nonCollider);
            studentEncode = studentEncoder.encodeFile(nonCollider, studenthufftree).trim();

            assertEquals(expectedEncode, studentEncode);
        });
    }

    @Test
    void testCollisionsEncodeDecodeFile() {
        HuffmanCoding studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("HelloWorld.txt");

        String fileString = stringFromFile(nonCollider).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            HuffTree studentHuffTree = studentEncoder.buildTree(nonCollider);
            String studentEncode = null;
            String studentDecode = null;
            studentEncode = studentEncoder.encodeFile(nonCollider, studentHuffTree).trim();
            HuffmanCoding newStudentEncoder =  new HuffmanEncoder();
            studentDecode = newStudentEncoder.decodeFile(studentEncode, studentHuffTree).trim();
            assertEquals(fileString, studentDecode);
        });
    }

    @Test
    void testAllASCIITraverseHuffmanTree() {
        TrueHuffEncoder correctEncoder = new TrueHuffEncoder();
        HuffmanCoding studentEncoder =  new HuffmanEncoder();

        File nonCollider = new File("NonCollidingTest.txt");

        TrueHuffTree correctHuffTree = correctEncoder.buildTree(nonCollider);
        String expectedTable = correctEncoder.traverseHuffmanTree(correctHuffTree).trim();

        assertTimeoutPreemptively(ofMillis(1000), () -> {
            String studentTable = null;
            HuffTree studentHuffTree = studentEncoder.buildTree(nonCollider);
            studentTable = studentEncoder.traverseHuffmanTree(studentHuffTree).trim();

            assertEquals(expectedTable, studentTable);
        });
    }

    public String stringFromFile(File input){
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