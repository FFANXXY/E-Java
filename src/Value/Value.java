package Value;

import ColorBack.error;
import Values.WriteValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Value {
    public static void add(String parameter,String value) {
        List<String> par = getPar(parameter + "," + value);
        int pars = par.size();
        if (pars == 2) {
            try {
                WriteValue.write(par.get(0), par.get(1));
            } catch (Exception e2) {
                error.sys("Filer:Value:add<write.error>", "Please check parameters of the \"Value\" (Unexpected errors|Maybe you should check your parameters)\n | <Value:warn>_x1i1e");
            }
        } else {
            error.sys("Filer:Value:add<pars.error>", "Must be 2 parameters with \"Value\"(Unexpected errors|Maybe you should check your parameters) \n | <Value:warn>_x1i1e");
        }
    }
    public static void add(String parameter,int valueInt) {
        String value = String.valueOf(valueInt);
        List<String> par = getPar(parameter + "," + value);
        int pars = par.size();
        if (pars == 2) {
            try {
                WriteValue.write(par.get(0), par.get(1));
            } catch (Exception e2) {
                error.sys("Filer:Value:add<write.error>", "Please check parameters of the \"Value\" (Unexpected errors|Maybe you should check your parameters)\n | <Value:warn>_x1i1e");
            }
        } else {
            error.sys("Filer:Value:add<pars.error>", "Must be 2 parameters with \"Value\"(Unexpected errors|Maybe you should check your parameters) \n | <Value:warn>_x1i1e");
        }
    }

    public static void add(String parameter,double valueDouble) {
        String value = String.valueOf(valueDouble);
        List<String> par = getPar(parameter + "," + value);
        int pars = par.size();
        if (pars == 2) {
            try {
                WriteValue.write(par.get(0), par.get(1));
            } catch (Exception e2) {
                error.sys("Filer:Value:add<write.error>", "Please check parameters of the \"Value\" (Unexpected errors|Maybe you should check your parameters)\n | <Value:warn>_x1i1e");
            }
        } else {
            error.sys("Filer:Value:add<pars.error>", "Must be 2 parameters with \"Value\"(Unexpected errors|Maybe you should check your parameters) \n | <Value:warn>_x1i1e");
        }
    }

    private static List<String> getPar(String str) {
        return new ArrayList<>(Arrays.asList(str.split(",")));
    }

    //获取指定变量位置
    public static Optional<String> readValue(String content) {
        String filePath = "src/Value/value.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(content)) {
                    String value = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    return Optional.of(value);
                }
            }
        } catch (IOException e) {
            // 报错
            System.err.println("PackageError " + e.getMessage());
        }
        return Optional.empty(); //返回空

    }
}

