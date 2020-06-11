package com.debug.kill.server.controller;/**
 * Created by Administrator on 2019/6/13.
 */

import com.debug.kill.api.enums.StatusCode;
import com.debug.kill.api.response.BaseResponse;
import com.debug.kill.model.mapper.RandomCodeMapper;
import com.debug.kill.server.thread.CodeGenerateSnowThread;
import com.debug.kill.server.thread.CodeGenerateThread;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基础controller
 * @Author:debug (SteadyJack)
 * @Date: 2019/6/13 23:36
 **/
@Controller
@RequestMapping("base")
public class BaseController {

    private static final Logger log= LoggerFactory.getLogger(BaseController.class);

    /**
     * 跳转页面
     * @param name
     * @param modelMap
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(String name, ModelMap modelMap){
        if (StringUtils.isBlank(name)){
            name="这是welcome!";
        }
        modelMap.put("name",name);
        return "welcome";
    }

    /**
     * 前端发起请求获取数据
     * @param name
     * @return
     */
    @RequestMapping(value = "/data",method = RequestMethod.GET)
    @ResponseBody
    public String data(String name){
        if (StringUtils.isBlank(name)){
            name="这是welcome!";
        }
        return name;
    }

    /**
     * 标准请求-响应数据格式
     * @param name
     * @return
     */
    @RequestMapping(value = "/response",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse response(String name){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        if (StringUtils.isBlank(name)){
            name="这是welcome!";
        }
        response.setData(name);
        return response;
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "error";
    }


    @Autowired
    private RandomCodeMapper randomCodeMapper;

    /**
     * 测试在高并发下多线程生成订单编号-传统的随机数生成方法
     * @return
     */
    @RequestMapping(value = "/code/generate/thread",method = RequestMethod.GET)
    public BaseResponse codeThread(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            ExecutorService executorService=Executors.newFixedThreadPool(10);
            for (int i=0;i<1000;i++){
                executorService.execute(new CodeGenerateThread(randomCodeMapper));
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * 测试在高并发下多线程生成订单编号-雪花算法
     * @return
     */
    @RequestMapping(value = "/code/generate/thread/snow",method = RequestMethod.GET)
    public BaseResponse codeThreadSnowFlake(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            ExecutorService executorService=Executors.newFixedThreadPool(10);
            for (int i=0;i<10000;i++){
                executorService.execute(new CodeGenerateSnowThread(randomCodeMapper));
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}























