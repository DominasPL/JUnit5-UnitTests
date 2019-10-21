package com.github.DominasPL.junit5.order;

import com.github.DominasPL.junit5.order.Order;

import java.io.*;

public class OrderBackup {

    private Writer writer;

    public Writer getWriter() {
        return writer;
    }

    public void createFile() throws FileNotFoundException {
        File file = new File("order_backup.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        writer = new BufferedWriter(outputStreamWriter);
    }

    public void backupOrder(Order order) throws IOException {
        writer.append(order.toString());
    }

    public void closeFile() throws IOException {
        writer.close();
    }
}
