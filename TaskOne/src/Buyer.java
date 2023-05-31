import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Buyer
{
    int buyTicketOption;
    final Scanner sc = new Scanner(System.in);
    int buyerOption;
    String[] accountNumberArray = new String[3000] ;
    public int selectBuyTicketOption()
    {
        while (buyTicketOption!=1 && buyTicketOption!=2)
        {
            System.out.print("Enter your option: ");
            try {
                buyTicketOption = sc.nextInt();
                if (buyTicketOption != 1 && buyTicketOption != 2) {
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
        return buyTicketOption;
    }

    public int buyerOption()
    {
        buyerOption = 0;
        System.out.println("If you want to buy a ticket again with in same day: press 1\nIf you want to buy a ticket for another day: press2\nIf not: press 3");
        while (buyerOption!=1 && buyerOption!=2 && buyerOption!=3)
        {
            System.out.print("Enter your option: ");
            try
            {
                buyerOption = sc.nextInt();
                if (buyerOption != 1 && buyerOption != 2 && buyerOption!=3) {
                    throw new Exception("Enter an integer according to the option");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Enter an integer according to the option");
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                sc.nextLine();
            }
        }
        return buyerOption;
    }


    public String[] loadAccountNumbers(XSSFSheet sheetTwo)
    {
        int rows = sheetTwo.getLastRowNum();
        for (int r = 0; r<=rows; r++)
        {
            XSSFRow row = sheetTwo.getRow(r);
            XSSFCell cell = row.getCell(1);
            accountNumberArray[r] = cell.getStringCellValue();
        }
        return accountNumberArray;
    }
    public String enterCardNumber()
    {
        String cardNumber = "0";
        System.out.print("Card Number: ");
        while (cardNumber.length() != 16)
        {
            try
            {
                cardNumber = sc.next();
                if (cardNumber.length() != 16)
                    throw new Exception("Your card number must be contained 16 numbers");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        return cardNumber;
    }

    public String enterBuyerName()
    {
        System.out.print("Name: ");
        String name = sc.next();
        return name;
    }

    public void enterCVCNumber()
    {
        String CVCNumber = "0";
        System.out.print("Enter CVC number: ");
        while (CVCNumber.length() != 3)
        {
            try
            {
                CVCNumber = sc.next();
                if (CVCNumber.length() != 3)
                    throw new Exception("Your CVC number must be contained 3 numbers");
                try
                {
                    Integer.parseInt(CVCNumber);
                }
                catch (Exception e)
                {
                    System.out.println("Your CVC number must be contained only numbers");
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    public void enterCardExpiryDate()
    {
        System.out.print("Enter the expiry date of your card: ");
        String expiryDate = sc.next();
    }

    public  int getMaxRows(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        int maxRow = 0;
        for (int i = 0; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                maxRow = i + 1;
            }
        }
        return maxRow;
    }
}
