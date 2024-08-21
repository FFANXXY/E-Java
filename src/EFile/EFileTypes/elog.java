package EFile.EFileTypes;

import EFile.Essential.DelAllFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static EFile.Essential.CreateFile.createFileAndWrite;

public class elog {
    private String log;

    public static Date currentDate = new Date();
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
    public static String date = formatter.format(currentDate);
    public elog(String[] logs) {
        for(String loge: logs) {
            log = log + "\n" +loge;
        }
        this.log = """
                E-Java Logs<RUNNING>
                ========================
                """+
                currentDate
                + "\n"
                + log;
    }

    /**
     * elog的重置写出的log的内容
     *
     * @param logs 重置的内容
     */
    public void resetLogs(String[] logs) {
        for(String loge: logs) {
            if(loge == null || loge.isEmpty()){
                loge = " ";
            }
            log = log + "\n" +loge;
        }
        this.log = """
                E-Java Logs<RUNNING>
                ========================
                """+ currentDate +
                "\n"
                + log;
    }

    public void resetLog(String[] logs) {
        for(String loge: logs) {
            log = log + "\n" +loge;
        }
    }

    public String getLog(int models) {
        List<String> par = new ArrayList<>(Arrays.asList(log.split("\n")));
        return par.get(models-1);
    }
    public List<String> getLog() {
        return new ArrayList<>(Arrays.asList(log.split("\n")));
    }

    public String getAllLog() {
        return log;
    }

    public static String getTime() {
        return date;
    }

    public void buildLog() {
        createFileAndWrite("e_log_" + this.getTime() + ".txt","logs",log);
    }

    public static void delAllLogs() {
        DelAllFile.clearDirectory("logs");
    }
}
