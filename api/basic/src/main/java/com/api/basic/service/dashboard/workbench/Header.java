package com.api.basic.service.dashboard.workbench;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.api.basic.dao.TbBasicSysUserDao;
import com.api.basic.dao.TbBasicWorkbenchHeaderDao;
import com.api.basic.data.entity.TbBasicSysUser;
import com.api.basic.data.po.TbBasicSysUserPo;
import com.api.basic.data.po.TbBasicWorkbenchHeaderPo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.utils.FunctionUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 工作台头部
 */
@Service("BasicDashboardWorkbenchHeaderService")
@RequiredArgsConstructor
public class Header extends AbstractService {

    private final TbBasicSysUserDao tbBasicSysUserDao;
    private final TbBasicWorkbenchHeaderDao tbBasicWorkbenchHeaderDao;

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
        data.put("welcomeMessage", getWelcomeMessage());
        data.put("weather", getWeather(request));

        List<Map<String, Object>> items = tbBasicWorkbenchHeaderDao.query(new TbBasicWorkbenchHeaderPo()).stream()
                .map(header -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("title", header.getTitle()); // 假设 TbBasicWorkbenchHeader 有一个 getTitle 方法
                    item.put("description", header.getDescription()); // 假设 TbBasicWorkbenchHeader 有一个 getDescription 方法
                    return item;
                })
                .collect(Collectors.toList());
        data.put("items", items);

        return Result.ok(data);
    }

    /**
     * 欢迎信息
     *
     * @return 欢迎信息
     */
    private String getWelcomeMessage() {
        // 获取用户信息
        TbBasicSysUserPo userPo = new TbBasicSysUserPo();
        userPo.setWhereId(StpUtil.getLoginIdAsInt());
        TbBasicSysUser user = tbBasicSysUserDao.get(userPo);
        String name = user.getRealName();
        if (StrUtil.isBlank(name)) {
            name = user.getNickName();
        }
        name = StrUtil.blankToDefault(name, "");

        LocalTime currentTime = LocalTime.now();
        List<String> greetings = new ArrayList<>();

        if (currentTime.isBefore(LocalTime.of(11, 0))) {
            greetings.add("早上好");
            if(StrUtil.isNotBlank(name))
                greetings.add(name);
            greetings.add("开始您一天的工作吧！");
        } else if (currentTime.isBefore(LocalTime.of(13, 0))) {
            greetings.add("中午好");
            if(StrUtil.isNotBlank(name))
                greetings.add(name);
            greetings.add("继续加油！");
        } else if (currentTime.isBefore(LocalTime.of(18, 0))) {
            greetings.add("下午好");
            if(StrUtil.isNotBlank(name))
                greetings.add(name);
            greetings.add("继续加油！");
        } else if (currentTime.isBefore(LocalTime.of(22, 0))) {
            greetings.add("下午好");
            if(StrUtil.isNotBlank(name))
                greetings.add(name);
            greetings.add("注意休息！");
        } else {
            greetings.add("晚上好");
            if(StrUtil.isNotBlank(name))
                greetings.add(name);
            greetings.add("注意休息！");
        }

        // 数组转字符串，用,连接
        return String.join("，", greetings);
    }

    /**
     * 天气信息
     *
     * @param request 请求
     * @return 天气信息
     */
    private String getWeather(HttpServletRequest request) {
        String ip = FunctionUtil.getIpAddress();
        String weatherInfo = "";
        if (StrUtil.isNotBlank(ip)) {
            String url = "https://api.map.baidu.com/location/ip?ak=ZQLygu5qKnlN5GmFekj4qbvV";
            // 不是局域网IP，则拼接IP参数
            if (!"127.0.0.1".equals(ip) && !isPrivateIp(ip)) {
                url += "&ip="+ ip;
            }
            String ipResult = HttpUtil.get(url);
            // 解析JSON响应
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode ipRootNode = objectMapper.readTree(ipResult);
                int status = ipRootNode.path("status").asInt();
                if (status == 0) {
                    JsonNode contentNode = ipRootNode.path("content");
                    JsonNode addressDetailNode = contentNode.path("address_detail");
                    String adcode = addressDetailNode.path("adcode").asText();
                    String province = addressDetailNode.path("province").asText();
                    String city = addressDetailNode.path("city").asText();
                    String district = addressDetailNode.path("district").asText();

                    String weatherResult = HttpUtil.get("https://restapi.amap.com/v3/weather/weatherInfo?key=a45ad1bd83bb5d1f323381bd47d62f3d&city="+ adcode);
                    JsonNode jsonRootNode = objectMapper.readTree(weatherResult);
                    if (jsonRootNode.path("status").asInt() == 1) {
                        JsonNode livesNode = jsonRootNode.path("lives");
                        JsonNode liveNode = livesNode.get(0);
                        String weather = liveNode.path("weather").asText();
                        String temperatureFloat = liveNode.path("temperature_float").asText();
                        String humidityFloat = liveNode.path("humidity_float").asText();
                        String winddirection = liveNode.path("winddirection").asText();
                        String windpower = liveNode.path("windpower").asText();
                        weatherInfo = String.format("%s %s, 今日%s，%s℃，空气湿度：%s%%，%s风%s级",
                                province, city, weather, temperatureFloat, humidityFloat, winddirection, windpower);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return weatherInfo;
    }

    /**
     * 判断是否为局域网IP
     * @param ip IP地址
     * @return 判断结果
     */
    private boolean isPrivateIp(String ip) {
        if (ip.startsWith("10.")) {
            return true;
        }
        if (ip.startsWith("172.")) {
            String[] parts = ip.split("\\.");
            if (parts.length == 4) {
                int secondPart = Integer.parseInt(parts[1]);
                return secondPart >= 16 && secondPart <= 31;
            }
        }
        if (ip.startsWith("192.168.")) {
            return true;
        }
        return false;
    }
}
