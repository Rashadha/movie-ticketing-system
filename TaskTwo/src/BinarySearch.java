public class BinarySearch
{
    public static void main(String[] args)
    {
        int[] array = {2,5,8,12,16,23,38,56,72,91};
        System.out.println(search(array,0,array.length-1,72));

    }
    public static int search(int[] array, int start, int end, int key)
    {
        if (end >= start)
        {
            int mid =(start + end)/2;

            if (array[mid] == key)
            {
                return mid;
            }
            if (array[mid] > key)
            {
                return search(array, start, mid - 1, key);
            }
            return search(array, mid+1,end,key);
        }
        return -1;
    }
}
