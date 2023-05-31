public class LinearSearch
{
    public static void main(String[] args)
    {
        int[] array = {1, 2, 3, 5, 7, 8, 9, 10, 4, 6};
        int value = 9;
        int index = search(array,value);
        if (index == -1)
        {
            System.out.println(value+" is not found in the array");
        }
        else
        {
            System.out.println(value+" is found at array index "+index);
        }
    }
    public static int search(int[] array, int x)
    {
        int n = array.length;
        for (int i = 0; i<n; i++)
        {
            if (array[i] == x)
            {
                return i;
            }
        }
        return -1;
    }
}
