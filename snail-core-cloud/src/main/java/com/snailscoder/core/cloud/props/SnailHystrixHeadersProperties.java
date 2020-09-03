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
package com.snailscoder.core.cloud.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Hystrix Headers 配置
 *
 * @author L.cm
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties("snail.hystrix.headers")
public class SnailHystrixHeadersProperties {

	/**
	 * 用于 聚合层 向调用层传递用户信息 的请求头，默认：x-snail-account
	 */
	private String account = "X-Snail-Account";

	/**
	 * RestTemplate 和 Fegin 透传到下层的 Headers 名称表达式
	 */
	@Nullable
	private String pattern = "Snail*";

	/**
	 * RestTemplate 和 Fegin 透传到下层的 Headers 名称列表
	 */
	private List<String> allowed = Arrays.asList("Tenant-Id");

}
