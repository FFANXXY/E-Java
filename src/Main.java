import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import ColorBack.error;
import Filer.WriteValue;

public class Main {

    public static boolean isInMain = false;
    public static String parameter;
    public static String WayName;
    public static int useCount = 0;

    public static void LineReadFile(String filePath) throws IOException {

        try (Scanner sc = new Scanner(new FileReader(filePath))) {
            useCount++;
            if (useCount == 1){WayName = "Main";}
            int reading = 0;
            while (sc.hasNextLine()) {
                reading++;
                String line = sc.nextLine();
                if (line.startsWith("$")) {
                    isInMain = !isInMain;
                }
                if (isInMain) {
                    if (line.startsWith("prints")) {
                        try {
                            try {
                                parameter = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                                List<String> par = getPar(parameter);
                                int pars = par.size();
                                if (pars == 1) {
                                    System.out.println("===E-=JAVA-loading===");
                                    System.out.println("Authors:" + par.getFirst());
                                    System.out.println("-----------------------");
                                }else if (pars == 2) {
                                    System.out.println("===E-=JAVA-loading===");
                                    System.out.println("Authors:" + par.get(0));
                                    System.out.println("Version:" + par.get(1));
                                    System.out.println("-----------------------");
                                } else {
                                    System.out.println("===E-=JAVA-loading===");
                                    System.out.println("Authors:" + par.get(0));
                                    System.out.println("Version:" + par.get(1));
                                    System.out.println("-----------------------");
                                    //将正确的输出填入
                                    error.warn(reading,"There are too many parameters in \"prints\" \n | <prints:warn>_x1i1e");
                                }
                            } catch (Exception e) {error.out(reading, "Can't be empty with \"prints\" \n | <prints:error>_x1c");}
                        } catch (Exception e) {
                            error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                        }
                    }else if (line.startsWith("printf")) {
                        try {
                            parameter = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                            System.out.println(parameter);
                        } catch (Exception e1) { error.out(reading, "Can't be empty with \"printf\" \n | <printf:error>_x0"); }
                    }else if (line.startsWith("Value")) {
                        try {
                            parameter = line.substring(line.indexOf("(") + 1, line.indexOf(")")); //找到参数
                            List<String> par = getPar(parameter);
                            int pars = par.size();
                            if (pars == 2) {
                                WriteValue.write(par.get(0), par.get(1));
                            }else {
                                error.out(reading, "Must be 2 parameters with \"Value\" \n | <Value:warn>_x1i1e");
                            }

                        } catch (Exception e) {error.out(reading, "Can't be empty with \"Value\" \n | <prints:error>_x1c");}
                    }else if (line.startsWith("printv")) {
                        try {
                            parameter = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                            String o = (WriteValue.readValue(parameter)).orElse("Default Value");
                            System.out.println(o);
                        } catch (Exception e1) { error.out(reading, "Can't be empty with \"printv\" \n | <printv:error>_x0"); }}
                } else {

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        runFile();
    }

    public static List<String> getPar(String str) {
        return new ArrayList<>(Arrays.asList(str.split(",")));
    }

    public static void runFile() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String readyPath = scanner.nextLine();
        if (readyPath.startsWith("%")) {

            if (readyPath.endsWith(".txt")) {
                readyPath = "src/runs/" + readyPath.substring(1);
            }else {
                readyPath = "src/runs/" + readyPath.substring(1) + ".txt";
            }
        }
        LineReadFile(readyPath);
    }
}