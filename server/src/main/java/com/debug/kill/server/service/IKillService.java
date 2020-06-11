package com.debug.kill.server.service;

import java.util.Map;

/**
 * Created by Administrator on 2019/6/17.
 */
public interface IKillService {

    Boolean killItem(Integer killId, Integer userId) throws Exception;

    Boolean killItemV2(Integer killId, Integer userId) throws Exception;

    Boolean killItemV3(Integer killId, Integer userId) throws Exception;

    Boolean killItemV4(Integer killId, Integer userId) throws Exception;

    Boolean killItemV5(Integer killId, Integer userId) throws Exception;

    Map<String,Object> checkUserKillResult(Integer killId, Integer userId) throws Exception;
}
