
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import ColorBack.error;
import EFile.EFileTypes.elog;
import Value.Value;
import Values.WriteValue;
import Value.Opera;

public class Main {

    public static boolean isInMain = false;
    public static String parameter;
    public static boolean isinIf;
    public static String WayName;
    public static int useCount = 0;

    public static Random random = new Random();
    public static int ran = 0;
    public static double rt_double;

    public static Scanner scanner = new Scanner(System.in);
//    public static String rt_str = "";

    public static String at_path = "";

    //    public static int isNumbers;
    public static boolean isRunIf = false;

//    public static boolean[] ifs;

    public static void LineReadFile(String filePath) throws IOException {
        elog log = new elog(new String[]{"========================","isStarting!", filePath});
        log.buildLog();
        try (Scanner sc = new Scanner(new FileReader(filePath))) {
            at_path = filePath;
            useCount++;
            if (useCount == 1){WayName = "Main";}
            int reading = 0;
            while (sc.hasNextLine()) {
                reading++;
                String line = sc.nextLine();
                if (line.startsWith("$")) {
                    isInMain = !isInMain;
                }
                if(line.startsWith(">") && isinIf) {
                    isRunIf = true;
                    line = line.substring(1);
                    if(line.startsWith(">")) {
                        line = line.substring(1);
                    }
                } else {
                    isinIf = false;
                    isRunIf = false;
                }
                //THERE
                if (isInMain && isInIf_check(line,isinIf)) {

                    /*
                    prints语法
                        prints(<s> Author)
                        输出Author:Author

                        prints(<s> Author,<s> Version)
                        额外输出Version:Version

                     */
                    if (line.startsWith("prints")) {
                        try {
                            try {
                                int start = line.indexOf('('); // 查找第一个左括号的位置
                                int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                                if (start != -1 && end != -1) {
                                    parameter = line.substring(start + 1, end);
                                } else {
                                    error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                                }
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
                    }
                    /*
                    printf语法
                        ==printf(<s> String)
                            换行并输出String

                        ==print
                            换行，和@n用途相同
                     */
                    else if (line.startsWith("printf")) {
                        try {
                            int start = line.indexOf('('); // 查找第一个左括号的位置
                            int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                            if (start != -1 && end != -1) {
                                parameter = line.substring(start + 1, end);
                            } else {
                                error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                            }
                            if(parameter.startsWith("\\@n"))
                            {
                                System.out.println(parameter.substring(1));
                            }if(parameter.startsWith("@n")){
                                System.out.println();
                                System.out.println(parameter.substring(2));
                            }else if(!parameter.startsWith("\\@n")){
                                System.out.println(parameter);
                            }
                        } catch (Exception e1) {System.out.println();}
                    }
                    /*
                    Value语法
                        Value(<v> ValueName,<object> Value)
                        定义ValueName为Value
                     */
                    else if (line.startsWith("Value")) {
                        try {
                            int start = line.indexOf('('); // 查找第一个左括号的位置
                            int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                            if (start != -1 && end != -1) {
                                parameter = line.substring(start + 1, end);
                            } else {
                                error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                            } //找到参数
                            List<String> par = getPar(parameter);
                            int pars = par.size();
                            if (pars == 2) {
                                try {
                                    WriteValue.write(par.get(0), par.get(1));
                                }catch (Exception e2) {error.warn(reading,e2.toString());}
                            }else {
                                error.out(reading, "Must be 2 parameters with \"Value\" \n | <Value:warn>_x1i1e");
                            }

                        } catch (Exception e) {error.out(reading, "Can't write value with \"Value\" \n | <prints:error>_x0_\n>>System.error");}
                    }
                    /*
                    printv语法
                        printv(<v> Value)
                        输出Value
                     */
                    else if (line.startsWith("printv")) {
                        try {
                            int start = line.indexOf('('); // 查找第一个左括号的位置
                            int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                            if (start != -1 && end != -1) {
                                parameter = line.substring(start + 1, end);
                            } else {
                                error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                            }
                            String o = (WriteValue.readValue(parameter)).orElse("Default Value");
                            System.out.print(o);
                        } catch (Exception e1) { error.out(reading, "Can't be empty with \"printv\" \n | <printv:error>_x0"); }}

                    else if(line.startsWith("printE")) {
                        int start = line.indexOf('('); // 查找第一个左括号的位置
                        int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                        if (start != -1 && end != -1) {
                            parameter = line.substring(start + 1, end);
                        } else {
                            error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                        }
                        List<String> par = getPar(parameter);
                        for (String thisL : par) {
                            if(thisL.startsWith("&")) {
                                try {
                                    System.out.print(WriteValue.readValue(thisL.substring(1)).orElse("!ERROR"));
                                } catch (Exception e) {
                                    System.out.print("!Can't find Value!");
                                }
                            } else if(thisL.startsWith("\\")){
                                System.out.print(thisL.substring(1));
                            } else {
                                System.out.print(thisL);
                            }

                        }
                    }

                    else if (line.startsWith("print")) {
                        try {
                            int start = line.indexOf('('); // 查找第一个左括号的位置
                            int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                            if (start != -1 && end != -1) {
                                parameter = line.substring(start + 1, end);
                            } else {
                                error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                            }
                            System.out.print(parameter);
                        } catch (Exception e1) { error.out(reading, "Can't be empty with \"print\" \n | <print:error>_x0"); }}

                    /*
                    random语法
                        -random(<v>ToValue,MaxValue)
                        将0-MaxValue存储到ToValue

                        -random(<v>ToValue,MinValue,MaxValue)
                        将MinValue-MaxValue存储到ToValue

                     */
                    else if (line.startsWith("random")) {
                        try {
                            int start = line.indexOf('('); // 查找第一个左括号的位置
                            int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                            if (start != -1 && end != -1) {
                                parameter = line.substring(start + 1, end);
                            } else {
                                error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                            }
                            List<String> par = getPar(parameter);
                            int pars = par.size();
                            if (pars == 1) {
                                error.out(reading, "Can't be ONLY ONE PARAMETER with \"random\" \n | <prints:error>_x1c");
                            }else if (pars == 2) {
                                try {
                                    ran = random.nextInt(Integer.parseInt(par.get(1)));
                                }catch (Exception e2) {
                                    error.out(reading, e2.toString());
                                }
                                Value.add(par.getFirst(),String.valueOf(ran));
                            } else if (pars == 3){
                                try {
                                    ran = randoms(Integer.parseInt(par.get(1)),Integer.parseInt(par.get(2)));
                                }catch (Exception e2) {
                                    error.out(reading, e2.toString());
                                }
                                Value.add(par.getFirst(),String.valueOf(ran));
                            }else {
                                error.out(reading, "Must be 2 or 3 parameters with \"random\" \n | <prints:error>_x1i1e");
                            }
                        } catch (Exception e) {error.out(reading, "Can't be empty with \"random\" \n | <prints:error>_x1c");}}
                    /*
                    opera语法
                        opera(<il>way,<v>First,<v>Second,<v>Over)
                        <il>备选项目
                        add 加法 a+b = c
                        sub 减法 a-b = c
                        mul 乘法 a*b = c
                        div 除法 a/b = c

                        <v> First=a
                        <v> Second=b

                        <v> Over = result = c

                        如果不是数字:
                        将字符串连接(第一项将失效)
                     */
                    else if (line.startsWith("opera")) {
                        int start = line.indexOf('('); // 查找第一个左括号的位置
                        int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                        if (start != -1 && end != -1) {
                            parameter = line.substring(start + 1, end);
                        } else {
                            error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                        }
                        List<String> par = getPar(parameter);
                        int pars = par.size();
                        if (pars == 4) {
                            String ya = WriteValue.readValue(par.get(1)).orElse("!ERROR");
                            String yb = WriteValue.readValue(par.get(2)).orElse("!ERROR");
                            if (ya.equals("!ERROR") || yb.equals("!ERROR")) {
                                error.out(reading, "Can't find Value");
                                return;
                            }
                            try {
                                double a = Double.parseDouble(ya);
                                double b = Double.parseDouble(yb);
                                if (par.getFirst().equals("add")) {
                                    rt_double = Opera.add(a,b);
                                } else if (par.get(1).equals("sub")) {
                                    rt_double = Opera.sub(a,b);
                                }else if (par.get(1).equals("mul")) {
                                    rt_double = Opera.mul(a,b);
                                }else if (par.get(1).equals("div")) {
                                    rt_double = Opera.div(a,b);
                                }else {
                                    error.warn(reading,"Can't find the OPERA to use");
                                    rt_double = Opera.add(a,b);
                                }
                                Value.add(par.get(3),rt_double);
                            } catch (Exception e3) {
                                String Over = ya + yb;
                                Value.add(par.get(3),Over);
                            }
                        }else {
                            error.out(reading, "Must be 4 paras in \"opera\" " + line);
                        }}
                    /*
                    @n语法
                        ==>  @n
                        作用：换行
                     */
                    else if(line.startsWith("@n")){
                        System.out.println();
                    }
                    /*
                    &reload语法
                        ==>  %reload
                        作用：清空所有变量
                     */
                    else if(line.startsWith("&reload")){
                        WriteValue.voidFile();
                    }
                    /*
                    Scan语法
                     */
                    else if(line.startsWith("Scan")){
                        int start = line.indexOf('('); // 查找第一个左括号的位置
                        int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                        if (start != -1 && end != -1) {
                            parameter = line.substring(start + 1, end);
                        } else {
                            error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                        }
                        List<String> par = getPar(parameter);
                        int pars = par.size();
                        if (pars == 1) {
                            String str = scanner.nextLine();
                            Value.add(par.getFirst(), str);
                        } else {
                            error.out(reading, "Must be 1 parameter in \"Scan\" " + line);
                        }
                    }
                    else if(line.startsWith("if")){
                        int start = line.indexOf('('); // 查找第一个左括号的位置
                        int end = line.indexOf(')', start); // 从左括号的位置开始查找第一个右括号

                        if (start != -1 && end != -1) {
                            parameter = line.substring(start + 1, end);
                        } else {
                            error.out(reading,"Can't enter next \"try\" \n | <prints:error>_x0");
                        }
                        isinIf = ifo(parameter);
                    }

                    else if(line.startsWith("Over") && isinIf) {
                        isinIf = false;
                        isRunIf = false;
                    }



                } else {
                    /*
                    ;外部;&reload语法
                        ==>  %reload
                        作用：清空所有变量
                     */
                    if(line.startsWith("&reload")){
                        WriteValue.voidFile();
                    }
                }
            }
        } catch (Exception e) {
            at_path = "";
            Scanner s = new Scanner(System.in);
            String str = s.nextLine();
            if (!str.isEmpty()) {
                runFile();
            }
        }
        //THERE
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
            LineReadFile(readyPath);
        } else if (readyPath.startsWith("/")) {

            readyPath = readyPath.substring(1);
            if(readyPath.startsWith("relogs")) {
                elog.delAllLogs();
                System.out.println("已清空日志文件！");
            }
        }
    }

    public static int randoms(int a,int b)
    {
        int ran = random.nextInt(b-a);
        return ran+a;

    }


    //if的条件表达式
    public static String ifoPara = "";
    public static boolean ifo(String ifn) {
        if(ifn.startsWith("is=")) {
            int start = ifn.indexOf('[');
            int end = ifn.indexOf(']', start);

            if (start != -1 && end != -1) {
                ifoPara = ifn.substring(start + 1, end);
            } else {
                error.sys("Main.if-check","Can't find the []");
            }
            List<String> par = getPar(ifoPara);
            int pars = par.size();
            if (pars == 2) {
                String ls = WriteValue.readValue(par.getFirst()).orElse("!ERROR!"); {
                    if (!ls.equals("!ERROR!")) {
                        return ls.equals(par.get(1));
                    }else {
                        error.sys("Main.if-check","ls.writeError");
                    }
                }
            }
        }else if (ifn.startsWith("V")) {
            int start = ifn.indexOf('['); // 查找第一个左括号的位置
            int end = ifn.indexOf(']', start); // 从左括号的位置开始查找第一个右括号

            if (start != -1 && end != -1) {
                ifoPara = ifn.substring(start + 1, end);
            } else {
                error.sys("Main.if-check","Can't find the []");
            }
            String s = WriteValue.readValue(ifoPara).orElse("false");
            return Boolean.parseBoolean(s);
        }
        return true;
    }


    public static boolean isInIf_check(String lines, boolean isInIf) {
        // 如果lines以">"开头并且isInIf为true，或者lines不以">"开头
        if (lines.startsWith(">"))
        {
            return isInIf;
        }else {
            return true;
        }
    }
}