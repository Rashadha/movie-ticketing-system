import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class TicketingSystem
{
    private int front;
    private int rear;
    private int noOfBuyers;
    private int maxNoOfTickets;
    private String[] buyerArray;
    private Scanner sc = new Scanner(System.in);

    public TicketingSystem(int size, int front, int rear, int noOfBuyers,String[] buyerArray )
    {
        maxNoOfTickets = size;
        this.front = front;
        this.rear = rear;
        this.noOfBuyers = noOfBuyers;
        this.buyerArray = buyerArray;
    }

    public boolean isFull()
    {
        return (rear == maxNoOfTickets - 1);
    }

    public boolean isEmpty()
    {
        return (noOfBuyers == 0);
    }

    public void enqueue(XSSFSheet sheetOne, String name, XSSFWorkbook workbook, String filePath) throws IOException {
        buyerArray[++rear] = name;
        noOfBuyers++;
        XSSFRow row = sheetOne.createRow(rear);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue(name);

        FileOutputStream outStream = new FileOutputStream(filePath);
        workbook.write(outStream);
        outStream.close();

        System.out.println("Your name has added to the ticket queue, your ticket will be issued with in 24 hours");

    }
    public String dequeue(XSSFSheet sheetOne,XSSFWorkbook workbook, String filePath) throws IOException {
        noOfBuyers--;
        XSSFRow row = sheetOne.createRow(front);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("__");

        FileOutputStream outStream = new FileOutputStream(filePath);
        workbook.write(outStream);
        outStream.close();

        return buyerArray[front++];

    }
}

    // query to check if there is available vehicles in waiting list
    String query4 = "select queue_entry_num, vehicle_type," +
            "fuel_type from dispenser_detail" +
            "where dispenser_number = ? and rownum = 1;";
    // query to add vehicle to a queue which is in common waiting queue
    String query5 = "UPDATE dispenser_detail " +
            "SET dispenser_number = REPLACE(dispenser_number,?,?) " +
            "WHERE query_entry_num = ?;";
