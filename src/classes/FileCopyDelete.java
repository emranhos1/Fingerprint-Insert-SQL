/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes;

import classes.findXML.AllPath;
import classes.findXML.ReadXMLForPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCopyDelete {
    private static AllPath rxfp;
    private static String afterSQLPath;
    private static String uploadFilePath;
    private static String oldFile;
    private static String newFile;


    public static boolean MoveDelete(String fileName) throws IOException {

        rxfp = ReadXMLForPath.ReadXMLForPath();
        afterSQLPath = rxfp.getAfterSQLPath();
        uploadFilePath = rxfp.getNewFilePath();

        oldFile = uploadFilePath + fileName;
        newFile = afterSQLPath + fileName + "";
        try {
            Files.move(Paths.get(oldFile), Paths.get(newFile), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileCopyDelete.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
