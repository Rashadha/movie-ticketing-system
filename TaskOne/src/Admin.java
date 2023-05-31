import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Admin
{
    private int adminOption;
    final private String adminPassword = "adminPassword";
    private Scanner sc = new Scanner(System.in);

    Admin()
    {
        adminOption = 0;
    }

    public void enterPassword()
    {
        String password = "";
        while (!Objects.equals(password, adminPassword))
        {
            System.out.print("Enter your password: ");
            try
            {
                password = sc.next();
                if (!Objects.equals(password, adminPassword))
                {
                    throw new Exception("Your password is incorrect, Enter again");
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    public int enterOption()
    {
                adminOption = 0;
                System.out.println("If you want to issue a ticket for another buyer with in same day: press 1\nIf you want to issue a ticket for another buyer for another day: press2\nIf not: press 3");
                while (adminOption!=1 && adminOption!=2 && adminOption!=3)
                {
                    System.out.print("Enter your option: ");
                    try
                    {
                        adminOption = sc.nextInt();
                        if (adminOption != 1 && adminOption != 2 && adminOption!=3) {
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
                return adminOption;
    }


}
