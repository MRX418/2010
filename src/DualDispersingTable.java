public class DualDispersingTable<AnyType> extends HashTable<AnyType> {
    public int col=0;
    DualDispersingTable() {
        super(DEFAULT_TABLE_SIZE);
    }
    DualDispersingTable(int size) {
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
            currentPos+=offset*hashCalculator(x);
            currentPos%= array.length;
            offset+=1;

        }
        return currentPos;
    }
    private int previousPrime(){
        for(int i = 1; i<array.length;++i )
            if(isPrime(array.length-i)) {
                return array.length - i;
            }
        return array.length;
    }
    private int hashCalculator(AnyType x){
        return previousPrime()-(x.hashCode()%previousPrime());
    }
}
