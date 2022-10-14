interface BaseHashTable<AnyType> {


    /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x);

    /**
     * Expand the hash table.
     */
    public void rehash();

    /**
     * Method that performs quadratic probing resolution.
     * Assumes table is at least half empty and table length is prime.
     *
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
   public int findPos(AnyType x);

    /**
     * Remove from the hash table.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x);

    /**
     * Find an item in the hash table.
     *
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains(AnyType x);

    /**
     * Return true if currentPos exists and is active.
     *
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    public boolean isActive(int currentPos);

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty();

    public int myhash(AnyType x);

    public void print();


}


