package com.mdj;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

@Service(interfaceName="com.mdj.TestService")
@org.springframework.stereotype.Service("testService")
public class TestServiceImpl implements TestService{
    @Override
    public Map getData() {
        Map map = new HashMap();
        map.put("data","hhhh");
        System.out.println(111);
    	return map;
    }
}
