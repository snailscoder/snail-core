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

package com.snailscoder.core.log.config;

import com.snailscoder.core.log.aspect.ApiLogAspect;
import com.snailscoder.core.log.event.ApiLogListener;
import com.snailscoder.core.log.event.ErrorLogListener;
import com.snailscoder.core.log.event.UsualLogListener;
import com.snailscoder.core.log.feign.ILogClient;
import lombok.AllArgsConstructor;
import com.snailscoder.core.log.logger.SnailLogger;
import com.snailscoder.core.launch.props.SnailProperties;
import com.snailscoder.core.launch.server.ServerInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志工具自动配置
 *
 * @author snailscoder
 */
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class SnailLogToolAutoConfiguration {

	private final ILogClient logService;
	private final ServerInfo serverInfo;
	private final SnailProperties snailProperties;

	@Bean
	public ApiLogAspect apiLogAspect() {
		return new ApiLogAspect();
	}

	@Bean
	public SnailLogger snailLogger() {
		return new SnailLogger();
	}

	@Bean
	public ApiLogListener apiLogListener() {
		return new ApiLogListener(logService, serverInfo, snailProperties);
	}

	@Bean
	public ErrorLogListener errorEventListener() {
		return new ErrorLogListener(logService, serverInfo, snailProperties);
	}

	@Bean
	public UsualLogListener snailEventListener() {
		return new UsualLogListener(logService, serverInfo, snailProperties);
	}

}
