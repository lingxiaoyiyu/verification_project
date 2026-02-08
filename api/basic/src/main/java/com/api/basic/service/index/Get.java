package com.api.basic.service.index;

import cn.hutool.core.util.StrUtil;
import com.api.basic.data.dto.index.GetDto;
import com.api.basic.data.vo.index.GetVo;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.utils.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.lionsoul.ip2region.Config;
import org.lionsoul.ip2region.Ip2Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * 获取指定获取IP信息信息
 *
 * @author 裴金伟
 */
@Service("BasicIndexGetServiceImpl")
@RequiredArgsConstructor
public class Get extends AbstractService {

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * 参数检查
     *
     * @param dto 请求参数
     */
    public void check(GetDto dto) {
    }

    /**
     * 业务主体
     *
     * @param dto 请求参数
     * @return 处理结果
     */
    public Result<?> service(GetDto dto) {
        GetVo vo = new GetVo();

        try {
            final Config v4Config = Config.custom()
                    .setCachePolicy(Config.VIndexCache)
                    .setXdbPath("./ip2region_v4.xdb")
                    .asV4();
            final Ip2Region ip2Region = Ip2Region.create(v4Config, null);
            String ip = StrUtil.isBlank(dto.getIp()) ? FunctionUtil.getIpAddress() : dto.getIp();
            final  String v4Region = ip2Region.search(ip);
            System.out.println(v4Region);
            // 将v4Region根据 | 拆分成数组
            String[] v4RegionArray = v4Region.split("\\|");
            vo.setCountry(v4RegionArray.length > 0 ? v4RegionArray[0] : "");
            vo.setProvince(v4RegionArray.length > 1 ? v4RegionArray[1] : "");
            vo.setCity(v4RegionArray.length > 2 ? v4RegionArray[2] : "");
            vo.setIsp(v4RegionArray.length > 3 ? v4RegionArray[3] : "");
            vo.setIp(ip);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }

        return Result.ok(vo);
    }
}
