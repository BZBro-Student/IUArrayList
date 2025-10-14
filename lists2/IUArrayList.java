import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUArrayList<T> implements IndexedUnsortedList<T> {
    private T[] array;
    private int rear;
    public static final int DEFAULT_SIZE = 10;

    /**
     * Initialize list with default size
     */
    public IUArrayList() {
        this(DEFAULT_SIZE);
    }
    /**
     * initialize list with user chosen size.
     * @param size size of the array.
     */
    @SuppressWarnings("unchecked")
    public IUArrayList(int size) {
        array = (T[]) (new Object[size]);
        rear = 0;
    }
    /**
     * Checks to see if the array can carry the added element and if not
     * double the list size.
     */
    private void expandCheck() {
        if (rear == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }
    /**
     * Moves the array forward from the specified index
     * @param index index the array is shifting forward from
     */
    private void shiftElementForwardFrom(int index){
        for (int i = rear; i > index; i--){
            array[i] = array[i-1];
        }
    }
    /**
     * Moves the element backward from the specified index
     * @param index index the array is shifting backward from
     */
    private void shiftElementBackwardFrom(int index)
    {
        for (int i = index; i < rear-1; i++){
            array[i] = array[i+1];
        }
    }

    @Override
    public void addToFront(T element) {
        expandCheck();
        shiftElementForwardFrom(0);
        array[0] = element;
        rear++;
    }

    @Override
    public void addToRear(T element) {
        expandCheck();
        array[rear] = element;
        rear++;
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        shiftElementForwardFrom(indexOf(target)+1);
        array[indexOf(target)+1] = element;
        rear++;
    }

    @Override
    public void add(int index, T element) {
        shiftElementForwardFrom(index);
        array[index] = element;
        rear++;
    }

    @Override
    public T removeFirst() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        return remove(rear-1);
    }

    @Override
    public T remove(T element) {
        return remove(indexOf(element));
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= rear){
            throw new IndexOutOfBoundsException();
        }
        T retVal = array[index];
        shiftElementBackwardFrom(index);
        rear--;
        array[rear] = null;
        return retVal;
    }

    @Override
    public void set(int index, T element) {
        array[index] = element;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= rear) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public int indexOf(T element) {
        int returnValue = -1;
        for (int i = 0; returnValue < 0 && i < rear; i++){
            if (array[i].equals(element)){
                returnValue = i;
            }
        }
        return returnValue;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[0];
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[rear-1];
    }

    @Override
    public boolean contains(T target) {
        return indexOf(target) > -1;
    }

    @Override
    public boolean isEmpty() {
        return rear == 0;
    }

    @Override
    public int size() {
        return this.rear;
    }

    @Override
    public String toString() {
        StringBuilder arrayStringBuilder = new StringBuilder();
        arrayStringBuilder.append("[");
        for (int i = 0; i < rear; i++){
            arrayStringBuilder.append(array[i].toString());
            arrayStringBuilder.append(", ");
        }
        if (!isEmpty()){
            arrayStringBuilder.delete(arrayStringBuilder.length()-2, arrayStringBuilder.length());
        }
        arrayStringBuilder.append("]");
        return arrayStringBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }
    
}
