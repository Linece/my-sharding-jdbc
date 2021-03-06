package com.zdc.sharding.java;

import com.zdc.sharding.java.config.DataSourceConfig;
import com.zdc.sharding.java.entity.UserInfo;
import com.zdc.sharding.java.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 演示取模的分库分表策略
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserShardingTest {
	@Resource
	UserService userService;

	@Test
	public void insert(){
		userService.insert();
	}

	@Test
	public void select(){
		UserInfo userInfo1= userService.getUserInfoByUserId(1L);
		System.out.println("------userInfo1:"+userInfo1);

        UserInfo userInfo2= userService.getUserInfoByUserId(2L);
        System.out.println("------userInfo2:"+userInfo2);
	}

	@Test
	public void users(){
		UserInfo userInfo = this.userService.selectByid(1L);
		System.out.println(userInfo);
	}

}
