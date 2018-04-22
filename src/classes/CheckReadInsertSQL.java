/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes;

import classes.findXML.AllPath;
import classes.findXML.ReadXMLForPath;
import dao.InsertQueryDaoSQL;
import dao.SelectQueryDaoSQL;
import dbConnection.conRs;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckReadInsertSQL {

    private static File dir;
    private static File[] files;
    private static long lastMod;
    private static File choice;
    private static String fileName;
    private static Scanner scanner;
    private static String USERID;
    private static String CHECKTIME1;
    private static String CHECKTIME2;
    private static String CHECKTYPE;
    private static String VERIFYCODE;
    private static String SENSORID;
    private static String Memoinfo;
    private static String WorkCode;
    private static String sn;
    private static String UserExtFmt;
    private static String CHECKTIME;
    private static String columnName;
    private static Date date;
    private static long time;
    private static Timestamp oldTime;
    private static SimpleDateFormat fromTxtFile;
    private static SimpleDateFormat myFormat;
    private static String formatLastDateTime;
    private static String values;
    private static String tableName;
    private static conRs addEmployeeLog;
    private static Connection con;
    private static String whereCondition;
    private static conRs conrs;
    private static ResultSet rs;
    private static PreparedStatement pstm;
    private static Connection con1;
    private static FileWriter writer;
    private static BufferedWriter bufferedWriter;
    private static SimpleDateFormat dateFormat;
    private static String inputDate;
    private static AllPath rxfp;
    private static String newFilePath;
    private static String newLogFilePath;

//    public static void main(String[] args) throws IOException {
//        isEmpty();
//        lastFileModified();
//        openFile(fileName);
//        readFile();
//    }
    public static boolean isEmpty() throws IOException {

        rxfp = ReadXMLForPath.ReadXMLForPath();
        newFilePath = rxfp.getNewFilePath();
        newLogFilePath = rxfp.getNewLogFilePath();

        File file = new File(newFilePath);
        if (file.isDirectory()) {
            if (file.list().length > 0) {
                System.out.println("There is files");
                return false;
            } else {
                System.out.println("There is no files");
                return true;
            }
        } else {
            System.out.println("Can't Find The Directory");
            return true;
        }
    }

    public static String lastFileModified() {
        dir = new File(newFilePath);
        files = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });
        lastMod = Long.MIN_VALUE;
        for (File file : files) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
                fileName = choice.getName();
            }
        }
        System.out.println(fileName);
        return fileName;
    }

    public static void openFile(String fileName) {

        String dirFile = newFilePath + fileName;
        try {
            scanner = new Scanner(new File(dirFile));
        } catch (Exception e) {
            System.out.println("Could not find file");
            e.printStackTrace();
        }
        System.out.println(dirFile);
    }

    public static void readFile() throws IOException {

        dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss.S");
        date = new Date();
        inputDate = dateFormat.format(date);
        while (scanner.hasNext()) {
            USERID = scanner.next();
            CHECKTIME1 = scanner.next();
            CHECKTIME2 = scanner.next();
            CHECKTYPE = scanner.next();
            VERIFYCODE = scanner.next();
            SENSORID = scanner.next();
            Memoinfo = scanner.next();
            WorkCode = scanner.next();
            sn = scanner.next();
            UserExtFmt = scanner.next();
            CHECKTIME = CHECKTIME1 + " " + CHECKTIME2;

            fromTxtFile = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            try {
                formatLastDateTime = myFormat.format(fromTxtFile.parse(CHECKTIME));
                date = myFormat.parse(formatLastDateTime);
            } catch (ParseException ex) {
                Logger.getLogger(CheckReadInsertSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            time = date.getTime();
            oldTime = new Timestamp(time);

            columnName = " * ";
            tableName = " EMPLOYEE_LOG ";
            whereCondition = " LOGTIME_STAMP = '" + oldTime + "'";
            try {
                conrs = SelectQueryDaoSQL.selectQueryWithWhereClause(columnName, tableName, whereCondition);
            } catch (SQLException ex) {
                Logger.getLogger(CheckReadInsertSQL.class.getName()).log(Level.SEVERE, null, ex);
            }

            rs = conrs.getRs();
            pstm = conrs.getPstm();
            con = conrs.getCon();

            writer = new FileWriter(newLogFilePath, true);
            bufferedWriter = new BufferedWriter(writer);

            try {
                if (rs.next()) {
                    values = "No New Data Write.";
                    bufferedWriter.write(inputDate);
                    bufferedWriter.write("  Info:   " + values);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                    System.out.println("Already Data Are Inserted");
                    System.out.println(values);
                    break;
                } else {

                    columnName = " LOG_ID, USER_LOGIN_ID, PARTY_ID, LOGTIME_STAMP, EVENT_TYPE, TERMINAL_S_N ";
                    values = "'" + USERID + "', " + "'"  + USERID + "', " + "'"  + USERID + "', " + "'" + oldTime + "', " + "'" + CHECKTYPE + "', " + "'" + sn + "'";
                    bufferedWriter.write(inputDate);
                    bufferedWriter.write("  Info:   " + values);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                    System.out.println(values);

                    try {
                        addEmployeeLog = InsertQueryDaoSQL.insertQueryWithOutWhereClause(tableName, columnName, values);
                        con1 = addEmployeeLog.getCon();
                    } catch (SQLException ex) {
                        Logger.getLogger(CheckReadInsertSQL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (Exception e) {
                Logger.getLogger(CheckReadInsertSQL.class.getName()).log(Level.SEVERE, null, e);
            }

            try {
                con1.close();
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(CheckReadInsertSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(oldTime);
        }
    }

    public static void closeFile() {
        scanner.close();
    }
}
