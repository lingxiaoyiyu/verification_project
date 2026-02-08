package com.api.basic.common;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PomParser {

    public List<String> getModulesFromPom(String pomFilePath) throws Exception {
        List<String> modules = new ArrayList<>();

        // 创建 DocumentBuilderFactory 实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 创建 DocumentBuilder 实例
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 解析 pom.xml 文件
        Document document = builder.parse(new File(pomFilePath));

        // 获取根元素
        Element root = document.getDocumentElement();
        root.normalize();

        // 查找 modules 节点
        NodeList moduleNodes = root.getElementsByTagName("module");
        for (int i = 0; i < moduleNodes.getLength(); i++) {
            // 提取模块名称
            String moduleName = moduleNodes.item(i).getTextContent();
            modules.add(moduleName);
        }

        return modules;
    }
}
