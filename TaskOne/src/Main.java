import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main
{
    static int day = 0;
    static int option = 0;
    static String excelFilePathOne = ".\\dataFiles\\TicketingSystem.xlsx";
    static Scanner sc = new Scanner(System.in);
    static String[] buyerArray = new String[1000];
    static int front = 0;
    static int rear = -1;
    static int size = 1000;

    static String excelFilePathTwo = ".\\dataFiles\\PurchaseDetails.xlsx";
    static FileInputStream inputStreamOne;
    static int adminOption = 2;
    static int buyerOption = 2;

    static {
        try {
            inputStreamOne = new FileInputStream(excelFilePathOne);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static XSSFWorkbook workbookOne;

    static {
        try {
            workbookOne = new XSSFWorkbook(inputStreamOne);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static XSSFSheet sheetOne = null;

    static FileInputStream inputStreamTwo;

    static {
        try {
            inputStreamTwo = new FileInputStream(excelFilePathTwo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static XSSFWorkbook workbookTwo;

    static {
        try {
            workbookTwo = new XSSFWorkbook(inputStreamTwo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static XSSFSheet sheetTwo = null;
    public static void main(String[] args) throws IOException {

        System.out.println("If you are the admin: press 1\nIf you are a ticket buyer: press 2");
        while (option!=1 && option!=2) {
            System.out.println("Enter your option: ");
            try {
                option = sc.nextInt();
                if (option != 1 && option != 2) {
                    throw new Exception("Enter an integer according to the option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter an integer according to the option");
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                sc.nextLine();
            }
        }




        if (option == 1)
        {
            Admin admin = new Admin();
            admin.enterPassword();

            while (adminOption == 2)
            {
                enterDay();
                referSheet();
                buyerArray = new String[1000];
                int noOfBuyers = loadBuyerNames();
                TicketingSystem tc = new TicketingSystem(size,front,rear,noOfBuyers,buyerArray);
                performAdminTask(tc);
                adminOption = admin.enterOption();
                while (adminOption == 1)
                {
                    performAdminTask(tc);
                    adminOption = admin.enterOption();
                }
            }

        }
        else
        {
            Buyer buyer = new Buyer();
            System.out.print("Ticket price is Rs: 1500\nIf you want to buy: press 1\nIf not: press 2\n");
            int buyTicketOption = buyer.selectBuyTicketOption();
            if (buyTicketOption == 1)
            {
                while (buyerOption == 2)
                {
                    enterDay();
                    referSheet();
                    buyerArray = new String[1000];
                    int noOfBuyers = loadBuyerNames();
                    TicketingSystem tc = new TicketingSystem(size,front,rear,noOfBuyers,buyerArray);
                    performBuyerTask(buyer,tc);
                    buyerOption = buyer.buyerOption();
                    while (buyerOption == 1)
                    {
                        performBuyerTask(buyer,tc);
                        buyerOption = buyer.buyerOption();
                    }
                }

            }
        }
    }
    public static void enterDay()
    {
        day = 0;
        while (day!=1 && day!=2 && day!=3)
        {
            System.out.println("Enter the day using numbers: ");
            try
            {
                day = sc.nextInt();
                if (day!=1 && day!=2 && day!=3)
                {
                    throw new Exception("Enter an integer according to the day");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Enter an integer according to the day");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                sc.nextLine();
            }
        }
    }

    public static void referSheet()
    {
        if (day == 1)
        {
            sheetOne = workbookOne.getSheet("dayOne");
        }
        if (day == 2)
        {
            sheetOne = workbookOne.getSheet("dayTwo");
        }
        if (day == 3)
        {
            sheetOne = workbookOne.getSheet("dayThree");
        }
        sheetTwo = workbookTwo.getSheet("sheetOne");
    }

    public static int loadBuyerNames()
    {
        int rows = size;
        int noOfBuyers = 0;

        for (int r = 0; r<rows; r++)
        {
            XSSFRow row = sheetOne.getRow(r);

            if (row == null)
            {
                buyerArray[r] = null;
            }
            else
            {
                XSSFCell cell = row.getCell(0);
                if (Objects.equals(cell.getStringCellValue(), "__"))
                {
                    buyerArray[r] = "__";
                }
                else
                {
                    buyerArray[r] = cell.getStringCellValue();
                }
            }

        }

//        for (int i = 0; i<buyerArray.length; i++)
//        {
//            System.out.println(buyerArray[i]);
//        }


        for (int i = 0; i<buyerArray.length; i++)
        {
            if (Objects.equals(buyerArray[i], "__"))
            {
                for (int j = i; j<buyerArray.length; j++)
                {
                    if(buyerArray[j] == null)
                    {
                        front = j-1;
                        rear = j-1;
                        noOfBuyers = 0;
                        break;
                    }
                    else if (buyerArray[j] != null && !Objects.equals(buyerArray[j], "__"))
                    {
                        front = j;
                        rear = j;
                        for (int k = rear; k<buyerArray.length; k++)
                        {
                            int temp = k-1;
                            rear = temp;
                            noOfBuyers = (rear - front)+1;
                            if (buyerArray[k] == null)
                                break;
                        }
                        break;
                    }
                }
                break;
            }
            else if (buyerArray[i] != null && !Objects.equals(buyerArray[i], "__"))
            {
                front = 0;
                rear = 0;
                for (int l = rear; l<buyerArray.length; l++)
                {
                    int temp = l-1;
                    rear = temp;
                    noOfBuyers = (rear - front)+1;
                    if (buyerArray[l] == null)
                        break;
                }
                break;
            }
            else if(buyerArray[i] == null)
            {
                front = 0;
                rear = -1;
                noOfBuyers = 0;
                break;
            }
        }

        return noOfBuyers;
    }

    public static int checkCardNumber(String[] cardNumberArray, String cardNumber)
    {
        for (int i = 0; i<cardNumberArray.length; i++)
        {
            if (Objects.equals(cardNumberArray[i], cardNumber))
            {
                return i;
            }
        }
        return -1;
    }

    public static void enterPurchaseDetails(String buyerName, String cardNumber, XSSFSheet sheetTwo, XSSFWorkbook workbook, String filePath) throws IOException {
        int maxRow = sheetTwo.getLastRowNum();
        XSSFRow row = sheetTwo.createRow(maxRow+1);

        Cell cell1 = row.createCell(0);
        cell1.setCellValue(buyerName);

        Cell cell2 = row.createCell(1);
        cell2.setCellValue(cardNumber);

        FileOutputStream outStream = new FileOutputStream(filePath);
        workbook.write(outStream);
        outStream.close();
    }

    public static void performBuyerTask(Buyer buyer,TicketingSystem tc) throws IOException {

        boolean queueStatus = tc.isFull();
        if(queueStatus)
        {
            if (day==3)
            {
                System.out.println("Sorry, All the tickets were issued");
                return;
            }
            else
            {
                System.out.println("Sorry , All the tickets for the day were issued, try for another day");
            }
        }
        else
        {
            String[] accountNumberArray = buyer.loadAccountNumbers(sheetTwo);
            String buyerName = buyer.enterBuyerName();
            String accountNumber = buyer.enterCardNumber();

            int status = checkCardNumber(accountNumberArray,accountNumber);
            if (status != -1)
            {
                XSSFRow row = sheetTwo.getRow(status);
                XSSFCell cell = row.getCell(0);
                if (!Objects.equals(cell.getStringCellValue(), buyerName))
                {
                    System.out.println("Sorry, You cannot transfer your ticket for another person");
                }
                else
                {
                    buyer.enterCardExpiryDate();
                    buyer.enterCVCNumber();
                    enterPurchaseDetails(buyerName,accountNumber,sheetTwo,workbookTwo,excelFilePathTwo);
                    tc.enqueue(sheetOne,buyerName,workbookOne,excelFilePathOne);
                }
            }
            else
            {
                buyer.enterCardExpiryDate();
                buyer.enterCVCNumber();
                enterPurchaseDetails(buyerName,accountNumber,sheetTwo,workbookTwo,excelFilePathTwo);
                tc.enqueue(sheetOne,buyerName,workbookOne,excelFilePathOne);
            }
        }
    }

    public static void performAdminTask(TicketingSystem tc ) throws IOException {
        boolean queueStatus = tc.isEmpty();
        if (queueStatus)
        {
            System.out.println("The customer queue is empty");
        }
        else {
            System.out.println("You issued a ticket for " + tc.dequeue(sheetOne,workbookOne,excelFilePathOne));
        }
    }

}
