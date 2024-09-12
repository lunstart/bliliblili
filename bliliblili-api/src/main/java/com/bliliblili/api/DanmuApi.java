package com.bliliblili.api;


import com.bliliblili.api.support.UserSupport;
import com.bliliblili.domain.entity.Danmu;
import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.service.DanmuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "弹幕接口")
public class DanmuApi {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private DanmuService danmuService;

    //查询弹幕
    @GetMapping("/danmus")
    @ApiOperation("查询弹幕")
    public JsonResponse<List<Danmu>> getDanmus(@RequestParam Long videoId,
                                               String startTime,
                                               String endTime) throws Exception {
        List<Danmu> list;
        try {
            userSupport.getCurrentUserId();
            //登录模式可以使用按时间筛选
            Map<String, Object> map = new HashMap<>();
            map.put("startDate", startTime);
            map.put("endDate", endTime);
            map.put("videoId", videoId);
            list = danmuService.getDanmus(videoId, startTime, endTime);

        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("videoId", videoId);
            list = danmuService.getDanmus(videoId, null, null);
        }
        return new JsonResponse<>(list);
    }

}
