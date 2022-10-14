public class HashTable<AnyType> implements BaseHashTable<AnyType>{
    /**
     * Construct the hash table.
     */
    public HashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * Construct the hash table.
     *
     * @param size the approximate initial size.
     */
    public HashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     *
     * @param x the item to insert.
     */

    @Override
    public void insert(AnyType x) {
        // Insert x as active
        int currentPos = findPos(x);
        if (isActive(currentPos))
            return;

        array[currentPos] = new HashEntry<>(x, true);

        // Rehash; see Section 5.5
         if (++currentSize > array.length)
           rehash();
    }

    /**
     * Expand the hash table.
     */

    @Override
    public void rehash() {
        HashEntry<AnyType>[] oldArray = array;

        // Create a new double-sized, empty table
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        // Copy table over
        for (int i = 0; i < oldArray.length; i++)
            if (oldArray[i] != null && oldArray[i].isActive)
                insert(oldArray[i].element);
    }

    /**
     * Method that performs quadratic probing resolution.
     * Assumes table is at least half empty and table length is prime.
     *
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    @Override
    public int findPos(AnyType x){return 1;}
    /**
     * Remove from the hash table.
     *
     * @param x the item to remove.
     */
    @Override
    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            array[currentPos].isActive = false;
    }

    /**
     * Find an item in the hash table.
     *
     * @param x the item to search for.
     * @return the matching item.
     */
    @Override
    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    /**
     * Return true if currentPos exists and is active.
     *
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    @Override
    public boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    /**
     * Make the hash table logically empty.
     */
    @Override
    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
    }
    @Override
    public int myhash(AnyType x) {
        int hashVal = x.hashCode();
        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }

    public static class HashEntry<AnyType> {
        public AnyType element;   // the element
        public boolean isActive;  // false if marked deleted

        public HashEntry(AnyType e) {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean i) {
            element = e;
            isActive = i;
        }
    }

    public static final int DEFAULT_TABLE_SIZE = 11;
    public HashEntry<AnyType>[] array; // The array of elements
    public int currentSize;              // The number of occupied cells
    public int amas =0;
    @Override
    public void print() {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                ++amas;
                System.out.println(i+" "+array[i].element );
            } else {
                System.out.println(i+" "+"null");
            }
        }
    }


    /**
     * Internal method to allocate array.
     *
     * @param arraySize the size of the array.
     */
    @SuppressWarnings("unchecked")
    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     *
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n <= 0)
            n = 3;

        if (n % 2 == 0)
            n++;

        for (; !isPrime(n); n += 2)
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     *
     * @param n the number to test.
     * @return the result of the test.
     */
    protected static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }
}


