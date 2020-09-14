/*
 * Copyright (c) 2018-2028, snailscoder (huaxin803@gmail.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snailscoder.core.test;


import org.junit.runners.model.InitializationError;
import com.snailscoder.core.launch.StartApplication;
import com.snailscoder.core.launch.constant.AppConstant;
import com.snailscoder.core.launch.constant.NacosConstant;
import com.snailscoder.core.launch.constant.SentinelConstant;
import com.snailscoder.core.launch.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 设置启动参数
 *
 * @author L.cm
 */
public class SnailSpringRunner extends SpringJUnit4ClassRunner {

	public SnailSpringRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		setUpTestClass(clazz);
	}

	private void setUpTestClass(Class<?> clazz) {
		SnailBootTest snailBootTest = AnnotationUtils.getAnnotation(clazz, SnailBootTest.class);
		if (snailBootTest == null) {
			throw new SnailBootTestException(String.format("%s must be @SnailBootTest .", clazz));
		}
		String appName = snailBootTest.appName();
		String profile = snailBootTest.profile();
		boolean isLocalDev = StartApplication.isLocalDev();
		Properties props = System.getProperties();
		props.setProperty("snail.env", profile);
		props.setProperty("snail.name", appName);
		props.setProperty("snail.is-local", String.valueOf(isLocalDev));
		props.setProperty("snail.dev-mode", profile.equals(AppConstant.PROD_CODE) ? "false" : "true");
		props.setProperty("snail.service.version", AppConstant.APPLICATION_VERSION);
		props.setProperty("spring.application.name", appName);
		props.setProperty("spring.profiles.active", profile);
		props.setProperty("info.version", AppConstant.APPLICATION_VERSION);
		props.setProperty("info.desc", appName);
		props.setProperty("spring.cloud.nacos.discovery.server-addr", NacosConstant.NACOS_ADDR);
		props.setProperty("spring.cloud.nacos.config.server-addr", NacosConstant.NACOS_ADDR);
		props.setProperty("spring.cloud.nacos.config.file-extension", NacosConstant.NACOS_CONFIG_FORMAT);
		props.setProperty("spring.cloud.sentinel.transport.dashboard", SentinelConstant.SENTINEL_ADDR);
		props.setProperty("spring.main.allow-bean-definition-overriding", "true");
		setSharedConfig(props,profile);
		// 加载自定义组件
		if (snailBootTest.enableLoader()) {
			List<LauncherService> launcherList = new ArrayList<>();
			SpringApplicationBuilder builder = new SpringApplicationBuilder(clazz);
			ServiceLoader.load(LauncherService.class).forEach(launcherList::add);
			launcherList.stream().sorted(Comparator.comparing(LauncherService::getOrder)).collect(Collectors.toList())
				.forEach(launcherService -> launcherService.launcher(builder, appName, profile));
		}
		System.err.println(String.format("---[junit.test]:[%s]---启动中，读取到的环境变量:[%s]", appName, profile));
	}

	private static void setSharedConfig(Properties props,String profile){
		props.setProperty("spring.cloud.nacos.config.shared-configs[0].data-id", NacosConstant.NACOS_CONFIG_COMMON + "." +NacosConstant.NACOS_CONFIG_FORMAT);
		props.setProperty("spring.cloud.nacos.config.shared-configs[0].refresh", "true");
		props.setProperty("spring.cloud.nacos.config.shared-configs[1].data-id", NacosConstant.NACOS_CONFIG_COMMON + "-" + profile + "." +NacosConstant.NACOS_CONFIG_FORMAT);
		props.setProperty("spring.cloud.nacos.config.shared-configs[1].refresh", "true");
	}


}
