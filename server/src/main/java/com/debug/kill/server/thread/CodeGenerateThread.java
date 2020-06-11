package com.debug.kill.server.thread;/**
 * Created by Administrator on 2019/7/11.
 */

import com.debug.kill.model.entity.RandomCode;
import com.debug.kill.model.mapper.RandomCodeMapper;
import com.debug.kill.server.utils.RandomUtil;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/7/11 10:30
 **/
public class CodeGenerateThread implements Runnable{

    private RandomCodeMapper randomCodeMapper;

    public CodeGenerateThread(RandomCodeMapper randomCodeMapper) {
        this.randomCodeMapper = randomCodeMapper;
    }

    @Override
    public void run() {
        RandomCode entity=new RandomCode();
        entity.setCode(RandomUtil.generateOrderCode());
        randomCodeMapper.insertSelective(entity);
    }
}