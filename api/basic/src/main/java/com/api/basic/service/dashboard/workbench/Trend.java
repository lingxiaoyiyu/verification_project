package com.api.basic.service.dashboard.workbench;

import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最新动态
 */
@Service("BasicDashboardWorkbenchTrendService")
@RequiredArgsConstructor
public class Trend extends AbstractService {

    /**
     * 参数检查
     */
    public void check() {

    }

    /**
     * 业务主体
     *
     * @return 处理结果
     */
    public Result<?> service() {
        return Result.ok(getTrendItems());
    }

    /**
     * 最新动态
     *
     * @return 数据
     */
    private List<Object> getTrendItems() {
        List<Object> data = new ArrayList<>();

        Map<String, Object> item = new HashMap<>();
        item.put("avatar", "svg:avatar-1");
        item.put("content", "在 <a>开源组</a> 创建了项目 <a>Vue</a>");
        item.put("date", "刚刚");
        item.put("title", "威廉");
        data.add(item);

        item = new HashMap<>();
        item.put("avatar", "svg:avatar-2");
        item.put("content", "关注了 <a>威廉</a>");
        item.put("date", "1个小时前");
        item.put("title", "艾文");
        data.add(item);

        item = new HashMap<>();
        item.put("avatar", "svg:avatar-3");
        item.put("content", "发布了 <a>个人动态</a>");
        item.put("date", "1天前");
        item.put("title", "克里斯");
        data.add(item);

        item = new HashMap<>();
        item.put("avatar", "svg:avatar-4");
        item.put("content", "发表文章 <a>如何编写一个Vite插件</a>");
        item.put("date", "2天前");
        item.put("title", "Vben");
        data.add(item);

        item = new HashMap<>();
        item.put("avatar", "svg:avatar-1");
        item.put("content", "回复了 <a>杰克</a> 的问题 <a>如何进行项目优化？</a>");
        item.put("date", "3天前");
        item.put("title", "皮特");
        data.add(item);

        item = new HashMap<>();
        item.put("avatar", "svg:avatar-2");
        item.put("content", "关闭了问题 <a>如何运行项目</a>");
        item.put("date", "1周前");
        item.put("title", "杰克");
        data.add(item);

        item = new HashMap<>();
        item.put("avatar", "svg:avatar-3");
        item.put("content", "发布了 <a>个人动态</a>");
        item.put("date", "1周前");
        item.put("title", "威廉");
        data.add(item);

        item = new HashMap<>();
        item.put("avatar", "svg:avatar-4");
        item.put("content", "推送了代码到 <a>Github</a>");
        item.put("date", "2021-04-01 20:00");
        item.put("title", "威廉");
        data.add(item);

        item = new HashMap<>();
        item.put("avatar", "svg:avatar-4");
        item.put("content", "发表文章 <a>如何编写使用 Admin Vben</a>");
        item.put("date", "2021-03-01 20:00");
        item.put("title", "Vben");
        data.add(item);
        return data;
    }
}
