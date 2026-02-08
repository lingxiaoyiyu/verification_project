package com.api.example;

import net.sourceforge.pinyin4j.PinyinHelper;

public class Example {

    /**
     * 将中文字符串转换为其拼音首字母的字符串，全部小写。
     *
     * @param chineseText 中文字符串
     * @return 拼音首字母的字符串
     */
    private static String convertToPinyinInitials(String chineseText) {
        StringBuilder initials = new StringBuilder();
        char[] characters = chineseText.toCharArray();
        for (char ch : characters) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(ch);
            if (pinyinArray != null && pinyinArray.length > 0) {
                String pinyin = pinyinArray[0];
                if (!pinyin.isEmpty()) {
                    // 提取首字母并转换为小写
                    initials.append(Character.toLowerCase(pinyin.charAt(0)));
                }
            }
        }

        return initials.toString();
    }
}
