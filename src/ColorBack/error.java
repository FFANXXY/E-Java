package ColorBack;

public class error {
    public static void out(int line,String log){
        System.out.println("\033[31m" + "---------------------\nError in Lines:" + line + " \n<Logs>\n" + log + "\n-------------------" + "\033[0m");
    }
    public static void warn(int line,String log){
        System.out.println("\033[93m" + "---------------------\nError in Lines:" + line + " \n<Logs>\n" + log + "\n-------------------" + "\033[0m");
    }
}
