package Filer;

import ColorBack.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}

