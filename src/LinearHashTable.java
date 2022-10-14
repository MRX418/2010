public class LinearHashTable<AnyType> extends HashTable<AnyType> {

    public int col=0;
    LinearHashTable() {
        super(DEFAULT_TABLE_SIZE);
    }
    LinearHashTable(int size) {
        super(size);
    }
    public int findPos(AnyType x )
    {
        int currentPos = myhash( x );
        int offset=1;
        while( array[ currentPos ] != null &&
                !array[ currentPos ].element.equals( x ) )
        {
            col++;
            currentPos+=offset;
            if( currentPos >= array.length )
                currentPos -= array.length;
        }

        return currentPos;
    }
}
