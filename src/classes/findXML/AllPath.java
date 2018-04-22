/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes.findXML;

public class AllPath {
    
    private String newFilePath;
    private String newLogFilePath;
    private String afterSQLPath;

    public String getAfterSQLPath() {
        return afterSQLPath;
    }

    public void setAfterSQLPath(String afterSQLPath) {
        this.afterSQLPath = afterSQLPath;
    }

    public String getNewFilePath() {
        return newFilePath;
    }

    public void setNewFilePath(String newFilePath) {
        this.newFilePath = newFilePath;
    }

    public String getNewLogFilePath() {
        return newLogFilePath;
    }

    public void setNewLogFilePath(String newLogFilePath) {
        this.newLogFilePath = newLogFilePath;
    }

    public AllPath(String newFilePath, String newLogFilePath, String afterSQLPath) {
        this.newFilePath = newFilePath;
        this.newLogFilePath = newLogFilePath;
        this.afterSQLPath = afterSQLPath;
    }
}
