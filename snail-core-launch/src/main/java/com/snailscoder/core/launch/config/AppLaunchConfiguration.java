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
package com.snailscoder.core.launch.config;

import lombok.AllArgsConstructor;
import com.snailscoder.core.launch.props.AppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 配置类
 *
 * @author snailscoder
 */
@Configuration
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties({
	AppProperties.class
})
public class AppLaunchConfiguration {

}
