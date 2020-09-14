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
package com.snailscoder.core.boot.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.snailscoder.core.launch.props.AppProperties;
import com.snailscoder.core.tool.constant.SystemConstant;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置类
 *
 * @author snailscoder
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
	AppProperties.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@AllArgsConstructor
public class SnailBootAutoConfiguration {

	private AppProperties appProperties;

	/**
	 * 全局变量定义
	 *
	 * @return SystemConstant
	 */
	@Bean
	public SystemConstant fileConst() {
		SystemConstant me = SystemConstant.me();

		//设定开发模式
		me.setDevMode(("dev".equals(appProperties.getEnv())));

		//设定文件上传远程地址
		me.setDomain(appProperties.get("upload-domain", "http://localhost:8888"));

		//设定文件上传是否为远程模式
		me.setRemoteMode(appProperties.getBoolean("remote-mode", true));

		//远程上传地址
		me.setRemotePath(appProperties.get("remote-path", System.getProperty("user.dir") + "/work/snail"));

		//设定文件上传头文件夹
		me.setUploadPath(appProperties.get("upload-path", "/upload"));

		//设定文件下载头文件夹
		me.setDownloadPath(appProperties.get("download-path", "/download"));

		//设定上传图片是否压缩
		me.setCompress(appProperties.getBoolean("compress", false));

		//设定上传图片压缩比例
		me.setCompressScale(appProperties.getDouble("compress-scale", 2.00));

		//设定上传图片缩放选择:true放大;false缩小
		me.setCompressFlag(appProperties.getBoolean("compress-flag", false));

		return me;
	}

}
