package com.github.utils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Class2FinalField implements TemplateMethodModelEx {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    public static String class2finalField(String str) {
        String s = str.substring(0, 1).toLowerCase() + str.substring(1);
        Matcher matcher = humpPattern.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(class2finalField("RoleUserPivot"));
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        String pre = ((SimpleScalar) list.get(0)).getAsString();
        return class2finalField(pre);
    }
}
