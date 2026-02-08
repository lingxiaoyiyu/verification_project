package com.api.basic.service.common.verifyCode;

import com.api.basic.data.dto.common.verifyCode.PhoneCodeDto;
import com.api.common.base.AbstractService;
import com.api.common.base.Result;
import com.api.common.exception.ServerException;
import com.api.common.utils.FunctionUtil;
import com.api.common.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 获取手机验证码
 */
@Service("VerifyCodePhoneCodeServiceImpl")
public class PhoneCode extends AbstractService {

    @Autowired
    private RedisCache redisCache; //存入redis中
    @Value("${debug:false}")
    private boolean debug;

    /**
     * 参数检查
     *
     * @param dto DTO对象
     */
    public void check(PhoneCodeDto dto) {
        Integer phoneCodeOneMinutesCnt = redisCache.getCacheObject("phone_code_one_minutes_cnt_" + dto.getPhoneNumber());
        if (phoneCodeOneMinutesCnt != null) {
            throw new ServerException("1分钟内只能发送1次验证码");
        }

        Integer phoneCodeOneHourCnt = redisCache.getCacheObject("phone_code_one_hour_cnt_" + dto.getPhoneNumber());
        if (phoneCodeOneHourCnt != null && phoneCodeOneHourCnt >= 5) {
            throw new ServerException("1小时内只能发送5次验证码");
        }

        Integer phoneCodeOneDayCnt = redisCache.getCacheObject("phone_code_one_day_cnt_" + dto.getPhoneNumber());
        if (phoneCodeOneDayCnt != null && phoneCodeOneDayCnt >= 10) {
            throw new ServerException("1天内只能发送10次验证码");
        }
    }

    /**
     * 业务主体
     *
     * @param dto DTO对象
     * @return 处理结果
     */
    public Result<?> service(PhoneCodeDto dto) {
        // 保存验证码信息
        String code = FunctionUtil.randomStr(6, false, false, true);
        if (debug) {
            code = "123456"; // 测试阶段，设置默认值
        }
        redisCache.setCacheObject("phone_code_" + dto.getPhoneNumber(), code, 2, TimeUnit.MINUTES);

        // 1分钟1次
        Integer phoneCodeOneMinutesCnt = redisCache.getCacheObject("phone_code_one_minutes_cnt_" + dto.getPhoneNumber());
        if (phoneCodeOneMinutesCnt == null) {
            phoneCodeOneMinutesCnt = 1;
        }
        redisCache.setCacheObject("phone_code_one_minutes_cnt_" + dto.getPhoneNumber(), phoneCodeOneMinutesCnt, 1, TimeUnit.MINUTES);

        // 1小时5次
        Integer phoneCodeOneHourCnt = redisCache.getCacheObject("phone_code_one_hour_cnt_" + dto.getPhoneNumber());
        if (phoneCodeOneHourCnt == null) {
            phoneCodeOneHourCnt = 1;
        } else {
            ++phoneCodeOneHourCnt;
        }
        redisCache.setCacheObject("phone_code_one_hour_cnt_" + dto.getPhoneNumber(), phoneCodeOneHourCnt, 1, TimeUnit.HOURS);

        // 1天10次
        Integer phoneCodeOneDayCnt = redisCache.getCacheObject("phone_code_one_day_cnt_" + dto.getPhoneNumber());
        if (phoneCodeOneDayCnt == null) {
            phoneCodeOneDayCnt = 1;
        } else {
            ++phoneCodeOneDayCnt;
        }
        redisCache.setCacheObject("phone_code_one_day_cnt_" + dto.getPhoneNumber(), phoneCodeOneDayCnt, 1, TimeUnit.DAYS);
        return Result.ok();
    }
}
