public class CharFreq implements Comparable<CharFreq> {

    private int frequency;
    private char myChar;
    private CharFreq left;
    private CharFreq right;

    public CharFreq(){}

    public CharFreq(int freq, char myChar){
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

    public CharFreq getRight() {
        return right;
    }

    public CharFreq getLeft() {
        return left;
    }

    public void setLeft(CharFreq l){
        this.left = l;
    }

    public void setRight(CharFreq r){
        this.right = r;
    }

    @Override
    public int compareTo(CharFreq comp) {
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
