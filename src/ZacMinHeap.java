import java.util.ArrayList;
import java.util.List;

public class ZacMinHeap<E extends Comparable<E>> {

    private List<E> heap;
    private int count;

    public ZacMinHeap(){
        this.heap = new ArrayList<E>(10);
        this.count = 0;
    }

    public ZacMinHeap(int init){
        this.heap = new ArrayList<E>(init);
        this.count = 0;
    }

    private int getLeft(int i){
        return (i*2)+1;
    }

    private int getRight(int i){
        return (i*2)+2;
    }

    private int getParent(int i){
        return (i-1)/2;
    }

    private E getLeftNode(int i){
        int index = getLeft(i);
        if(index < count){
            return this.heap.get(index);
        }

        return null;
    }

    private E getRightNode(int i){
        int index = getRight(i);
        if(index < count){
            return this.heap.get(index);
        }

        return null;
    }

    private E getParentNode(int i){
        int index = getParent(i);
        if(index >= 0){
            return this.heap.get(index);
        }

        return null;
    }

    public E pop(){
        if(count == 0){
            return null;
        }
        E top = heap.get(0);
        count--;

        heap.set(0, heap.get(count));
        heap.remove(count);

        int currIndex = 0;

        //Trickle down here
        while(currIndex < count){
            E left = getLeftNode(currIndex);
            E right = getRightNode(currIndex);

            if(left != null && right != null){
                E min = (left.compareTo(right)) < 0 ? left:right;
                int mindex = (left.compareTo(right)) < 0 ? getLeft(currIndex):getRight(currIndex);

                heap.set(mindex, heap.get(currIndex));
                heap.set(currIndex, min);
                currIndex = mindex;
            }
            else if(left != null && left.compareTo(heap.get(currIndex)) < 0){
                heap.set(getLeft(currIndex), heap.get(currIndex));
                heap.set(currIndex, left);
                currIndex = getLeft(currIndex);
            }
            else if(right != null && right.compareTo(heap.get(currIndex)) < 0){
                heap.set(getRight(currIndex), heap.get(currIndex));
                heap.set(currIndex, right);
                currIndex = getRight(currIndex);
            }
            else{
                break;
            }
        }

        return top;
    }

    public void push(E added){
        this.heap.add(added);

        int currIndex = count;
        count++;
        while(currIndex > 0){
            int parent = getParent(currIndex);


            //If my parent is greater than me swap
            if(heap.get(parent).compareTo(heap.get(currIndex)) > 0){
                E temp = heap.get(currIndex);
                heap.set(currIndex, heap.get(parent));
                heap.set(parent, temp);
            }
            else{
                break;
            }

            //Move up
            currIndex = parent;
        }
    }

    public E peek(){
        if(count > 0) {
            return heap.get(0);
        }
        return null;
    }

    public int getCount(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void heapify(List<E> freqList){
        for(E cf : freqList){
            this.push(cf);
        }
    }
}
