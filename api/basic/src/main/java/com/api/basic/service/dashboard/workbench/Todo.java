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
 * 代办事项
 */
@Service("BasicDashboardWorkbenchTodoService")
@RequiredArgsConstructor
public class Todo extends AbstractService {

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
        return Result.ok(getTodoItems());
    }

    /**
     * 待办事项
     *
     * @return 数据
     */
    private List<Object> getTodoItems() {
        List<Object> data = new ArrayList<>();

        Map<String, Object> item = new HashMap<>();
        item.put("completed", false);
        item.put("content", "审查最近提交到Git仓库的前端代码，确保代码质量和规范。");
        item.put("date", "2024-07-30 11:00:00");
        item.put("title", "审查前端代码提交");
        data.add(item);

        item = new HashMap<>();
        item.put("completed", true);
        item.put("content", "检查并优化系统性能，降低CPU使用率。");
        item.put("date", "2024-07-30 11:00:00");
        item.put("title", "系统性能优化");
        data.add(item);

        item = new HashMap<>();
        item.put("completed", false);
        item.put("content", "进行系统安全检查，确保没有安全漏洞或未授权的访问。 ");
        item.put("date", "2024-07-30 11:00:00");
        item.put("title", "安全检查");
        data.add(item);

        item = new HashMap<>();
        item.put("completed", false);
        item.put("content", "更新项目中的所有npm依赖包，确保使用最新版本。");
        item.put("date", "2024-07-30 11:00:00");
        item.put("title", "更新项目依赖");
        data.add(item);

        item = new HashMap<>();
        item.put("completed", false);
        item.put("content", "修复用户报告的页面UI显示问题，确保在不同浏览器中显示一致。 ");
        item.put("date", "2024-07-30 11:00:00");
        item.put("title", "修复UI显示问题");
        data.add(item);

        return data;
    }
}
