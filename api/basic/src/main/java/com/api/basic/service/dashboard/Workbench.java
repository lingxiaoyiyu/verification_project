package com.api.basic.service.dashboard;

import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作台
 */
@Service("BasicWorkbenchService")
@RequiredArgsConstructor
public class Workbench extends AbstractService {

    /**
     * 参数检查
     */
    public void check() {

    }

    /**
     * 业务主体
     *
     * @param request 请求
     * @return 处理结果
     */
    public Result<?> service(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("projectItems", getProjectItems());
        return Result.ok(data);
    }

    /**
     * 项目
     *
     * @return 数据
     */
    private List<Object> getProjectItems() {
        List<Object> data = new ArrayList<>();

        Map<String, Object> item = new HashMap<>();
        item.put("color", "");
        item.put("content", "不要等待机会，而要创造机会");
        item.put("date", "2021-04-01");
        item.put("group", "开源组");
        item.put("icon", "carbon:logo-github");
        item.put("title", "Github");
        item.put("url", "https://github.com");
        data.add(item);

        item = new HashMap<>();
        item.put("color", "#3fb27f");
        item.put("content", "现在的你决定将来的你。");
        item.put("date", "2021-04-01");
        item.put("group", "算法组");
        item.put("icon", "ion:logo-vue");
        item.put("title", "Vue");
        item.put("url", "https://vuejs.org");
        data.add(item);

        item = new HashMap<>();
        item.put("color", "#e18525");
        item.put("content", "没有什么才能比努力更重要。");
        item.put("date", "2021-04-01");
        item.put("group", "上班摸鱼");
        item.put("icon", "ion:logo-html5");
        item.put("title", "Html5");
        item.put("url", "https://developer.mozilla.org/zh-CN/docs/Web/HTML");
        data.add(item);

        item = new HashMap<>();
        item.put("color", "#bf0c2c");
        item.put("content", "热情和欲望可以突破一切难关。");
        item.put("date", "2021-04-01");
        item.put("group", "UI");
        item.put("icon", "ion:logo-angular");
        item.put("title", "Angular");
        item.put("url", "https://angular.io");
        data.add(item);

        item = new HashMap<>();
        item.put("color", "#00d8ff");
        item.put("content", "健康的身体是实现目标的基石。");
        item.put("date", "2021-04-01");
        item.put("group", "技术牛");
        item.put("icon", "bx:bxl-react");
        item.put("title", "React");
        item.put("url", "https://reactjs.org");
        data.add(item);

        item = new HashMap<>();
        item.put("color", "#EBD94E");
        item.put("content", "路是走出来的，而不是空想出来的。");
        item.put("date", "2021-04-01");
        item.put("group", "架构组");
        item.put("icon", "ion:logo-javascript");
        item.put("title", "Js");
        item.put("url", "https://developer.mozilla.org/zh-CN/docs/Web/JavaScript");
        data.add(item);

        return data;
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
