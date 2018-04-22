package fingerprintinsertsql;

import classes.CheckReadInsertSQL;
import classes.FileCopyDelete;
import classes.findXML.AllPath;
import classes.findXML.ReadXMLForPath;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FingerprintInsertSQL {

    private static String fileName;
    private static boolean empty;
    private static AllPath rxfp;
    private static String newLogFilePath;
    private static FileWriter writer;
    private static BufferedWriter bufferedWriter;
    private static SimpleDateFormat dateFormat;
    private static Date date;
    private static String inputDate;
    private static boolean moveDeleteFile;
    private static int x = 1;

    public static void main(String[] args) throws InterruptedException, IOException {

        do {
            rxfp = ReadXMLForPath.ReadXMLForPath();
            newLogFilePath = rxfp.getNewLogFilePath();

            writer = new FileWriter(newLogFilePath, true);
            bufferedWriter = new BufferedWriter(writer);

            dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss.S");
            date = new Date();
            inputDate = dateFormat.format(date);

            try {
                empty = CheckReadInsertSQL.isEmpty();
                if (empty == false) {
                    fileName = CheckReadInsertSQL.lastFileModified();
                    CheckReadInsertSQL.openFile(fileName);
                    CheckReadInsertSQL.readFile();
                    CheckReadInsertSQL.closeFile();

                    moveDeleteFile = FileCopyDelete.MoveDelete(fileName);
                    System.out.println(moveDeleteFile);
                    if (moveDeleteFile) {

                        bufferedWriter.write(inputDate);
                        bufferedWriter.write("  Info:   File Moved To 'Uploded file to ofbiz' Folder");
                        bufferedWriter.newLine();
                        bufferedWriter.close();
                    } else {
                        bufferedWriter.write(inputDate);
                        bufferedWriter.write("  Info:   File Not Moved");
                        bufferedWriter.newLine();
                        bufferedWriter.close();
                    }
                } else {
                    writer = new FileWriter(newLogFilePath, true);
                    bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write(inputDate);
                    bufferedWriter.write("  Info:   There is no new file yet.");
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                    System.out.println("There is no new file yet.");
                }
            } catch (IOException ex) {
                Logger.getLogger(FingerprintInsertSQL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Thread.sleep(15000);
            }
            x++;
        } while (x > 0);

    }
}
