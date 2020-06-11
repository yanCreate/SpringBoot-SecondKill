package com.debug.kill.server.thread;/**
 * Created by Administrator on 2019/7/11.
 */

import com.debug.kill.model.entity.RandomCode;
import com.debug.kill.model.mapper.RandomCodeMapper;
import com.debug.kill.server.utils.RandomUtil;
import com.debug.kill.server.utils.SnowFlake;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/7/11 10:30
 **/
public class CodeGenerateSnowThread implements Runnable{

    private static final SnowFlake SNOW_FLAKE=new SnowFlake(2,3);

    private RandomCodeMapper randomCodeMapper;

    public CodeGenerateSnowThread(RandomCodeMapper randomCodeMapper) {
        this.randomCodeMapper = randomCodeMapper;
    }

    @Override
    public void run() {
        RandomCode entity=new RandomCode();
        entity.setCode(String.valueOf(SNOW_FLAKE.nextId()));
        randomCodeMapper.insertSelective(entity);
    }
}