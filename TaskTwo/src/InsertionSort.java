public class InsertionSort
{
    public static void main(String[] args)
    {
        int[] array = {4,3,2,10,12,1,5,6};
        sort(array);
        for (int data : array)
        {
            System.out.println(data);
        }

    }
    public static void sort(int[] array)
    {
        int n = array.length;

        for (int i = 1; i<n; i++)
        {
            int key = array[i];
            int j = i -1;

            while (j>=0 && array[j]>key)
            {
                array[j+1] = array[j];
                j=j-1;
            }
            array[j+1] = key;
        }
    }
}
