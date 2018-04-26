import org.junit.jupiter.api.Test;

import java.io.File;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

class HuffEncoderTest {

    @Test
    void testNonCollisionsGetFrequencies() {
        File nonCollider = new File("NonCollidingTest.txt");
        assertTimeoutPreemptively(ofMillis(1000), () -> {


        });
    }

    @Test
    void testCollisionsGetFrequencies() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {

        });
    }

    @Test
    void testNonCollisionsEncodeToDecodeFile() {
        File nonCollider = new File("NonCollidingTest.txt");
        assertTimeoutPreemptively(ofMillis(1000), () -> {

        });
    }

    @Test
    void testCollisionsEncodeDecodeFile() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {

        });
    }

    @Test
    void testAllASCIITraverseHuffmanTree() {
        File nonCollider = new File("NonCollidingTest.txt");
        assertTimeoutPreemptively(ofMillis(1000), () -> {

        });
    }

    @Test
    void testAlphaTraverseHuffmanTree() {
        File nonCollider = new File("NonCollidingAlphaTest.txt");
        assertTimeoutPreemptively(ofMillis(1000), () -> {

        });
    }
}