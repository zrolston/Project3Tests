public class ZacCharFreq implements Comparable<ZacCharFreq> {

    private int frequency;
    private char myChar;
    private ZacCharFreq left;
    private ZacCharFreq right;

    public ZacCharFreq(){}

    public ZacCharFreq(int freq, char myChar){
        this.frequency = freq;
        this.myChar = myChar;
    }

    public int getFrequency(){
        return frequency;
    }

    public char getChar(){
        return myChar;
    }

    public void setFrequency(int i){
        frequency = i;
    }

    public void setChar(char c){
        myChar = c;
    }

    public ZacCharFreq getRight() {
        return right;
    }

    public ZacCharFreq getLeft() {
        return left;
    }

    public void setLeft(ZacCharFreq l){
        this.left = l;
    }

    public void setRight(ZacCharFreq r){
        this.right = r;
    }

    @Override
    public int compareTo(ZacCharFreq comp) {
        if(this.frequency == comp.getFrequency()){
            return 0;
        }

        return (this.frequency > comp.getFrequency()) ? 1:-1;
    }

    @Override
    public String toString(){
        return myChar + " " + frequency;
    }
}
