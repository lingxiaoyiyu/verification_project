package com.api.basic.common;

import java.util.ArrayList;
import java.util.List;

public class JavaFileModifier {


    // 添加import语句
    public static List<String> addImport(List<String> lines, String importStatement) {
        List<String> modifiedLines = new ArrayList<>(lines);
        for (int i = 0; i < modifiedLines.size(); i++) {
            if (importStatement.equals(modifiedLines.get(i))) {
                return modifiedLines;
            }
        }

        int lastImportIndex = 1;
        // 查找最后一个import语句的位置
        for (int i = 0; i < modifiedLines.size(); i++) {
            if (importStatement.equals(modifiedLines.get(i))) {
                lastImportIndex = -1;
                break;
            }
            if (modifiedLines.get(i).trim().startsWith("import ")) {
                lastImportIndex = i;
            } else if (modifiedLines.get(i).trim().startsWith("package ")) {
                // 确保不会在package声明前添加import
                lastImportIndex = Math.max(lastImportIndex, i);
            }
        }

        if (lastImportIndex > 0) {
            // 在最后一个import语句后添加新的import
            modifiedLines.add(lastImportIndex + 1, importStatement);
        }
        return modifiedLines;
    }

    // 添加@Resource注解和方法
    public static List<String> addResourceField(List<String> lines,
                                                String controllerClassName, String serviceClassPackage, String serviceClassName, String serviceName) {
        List<String> modifiedLines = new ArrayList<>(lines);
        for (int i = 0; i < modifiedLines.size(); i++) {
            if (modifiedLines.get(i).contains(serviceName+"ServiceImpl")) {
                return  modifiedLines;
            }
        }

        int classStartIndex = 0;
        // 查找类定义的开始位置
        for (int i = 0; i < modifiedLines.size(); i++) {
            if (modifiedLines.get(i).contains("class " + controllerClassName)) {
                classStartIndex = i;
                break;
            }
        }
        int resourceEndIndex = 0;
        // 查找resource定义的结束位置
        for (int i = 0; i < modifiedLines.size(); i++) {
            if (modifiedLines.get(i).contains("@Resource(")) {
                resourceEndIndex = i;
            }
        }
        if (resourceEndIndex > 0) {
            classStartIndex = resourceEndIndex + 2;
        } else {
            classStartIndex = classStartIndex + 2;
        }

        if (classStartIndex > 0) {
            // 在类体中添加字段
            String resourceLine = "    @Resource(name = \"" + serviceName + "ServiceImpl\")";
            String fieldLine = "    private " + serviceClassPackage + " " + serviceClassName + ";";

            // 查找合适的位置插入（通常在已有字段之后）
            modifiedLines.add(classStartIndex, resourceLine);
            modifiedLines.add(classStartIndex+1, fieldLine);
        }

        return modifiedLines;
    }

    private static int findLastFieldPosition(List<String> lines, int startIndex) {
        for (int i = startIndex; i < lines.size(); i++) {
            if (lines.get(i).contains("}")) {
                return i - 1;
            }
            if (lines.get(i).trim().startsWith("@Resource")) {
                int j = i + 1;
                while (j < lines.size() && !lines.get(j).trim().startsWith("private ")) {
                    j++;
                }
                if (j < lines.size()) {
                    i = j;
                }
            }
        }
        return startIndex;
    }

    // 添加新方法
    public static List<String> addMethod(List<String> lines,
                                         String serviceClassName, String methodContent) {
        List<String> modifiedLines = new ArrayList<>(lines);
        int resourceIndex = 0;
        int returnIndex = 0;

        // 查找类最后一个Resource的位置
        for (int i = 0; i < modifiedLines.size(); i++) {
            if (modifiedLines.get(i).contains("return " + serviceClassName + ".service(dto)")) {
                return modifiedLines;
            }
            if (modifiedLines.get(i).contains("@Resource(")) {
                resourceIndex = i;
            }
            if (modifiedLines.get(i).contains("return ")) {
                returnIndex = i;
            }
        }
        int classEndIndex = 0;

        if (returnIndex > 0) { // 没有找到return
            classEndIndex = returnIndex + 3;
        } else {
            classEndIndex = resourceIndex + 3;
        }

        // 查找类体的结束位置
//        int classEndIndex = findClassEndIndex(modifiedLines, returnIndex);
        if (classEndIndex > 2) {
            // 在类结束前添加方法
            modifiedLines.add(classEndIndex - 1, methodContent);
        }

        return modifiedLines;
    }

    private static int findClassEndIndex(List<String> lines, int startIndex) {
        int braceCount = 0;
        boolean foundOpeningBrace = false;

        for (int i = startIndex; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("{")) {
                braceCount++;
                foundOpeningBrace = true;
            }
            if (line.contains("}")) {
                braceCount--;
            }

            if (foundOpeningBrace && braceCount == 0) {
                return i;
            }
        }
        return -1;
    }
}
