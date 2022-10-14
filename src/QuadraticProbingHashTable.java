
// QuadraticProbing Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items


/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */
public class QuadraticProbingHashTable<AnyType> extends HashTable<AnyType>
{
    public int col=0;
     QuadraticProbingHashTable() {
         super(DEFAULT_TABLE_SIZE);
    }
    QuadraticProbingHashTable(int size) {
        super(size);
    }
    public int findPos(AnyType x )
    {
        int currentPos = myhash(x);
        int offset=1;

        while( array[ currentPos ] != null &&
                !array[ currentPos ].element.equals( x ) )
        {
            col++;
            currentPos = myhash( x );
            currentPos+=(int)Math.pow(Double.valueOf(offset),2);
            currentPos%= array.length;
            offset+=1;
        }
        return currentPos;
    }

}
