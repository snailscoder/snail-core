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

package com.snailscoder.core.mp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * mybatis-plus 配置
 * @author snailscoder
 * @date 2020/4/5
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "snail.mybatis-plus")
public class MybatisPlusProperties {
	/**
	 * 自动填充配置
	 */
	private AutoFill autoFill;

	/**
	 * 自动填充配置信息
	 */
	@Setter
	@Getter
	public static class AutoFill{
		/**
		 * 是否开启自动填充字段
		 */
		private Boolean enabled = true;
		/**
		 * 是否开启了插入填充
		 */
		private Boolean enableInsertFill = true;
		/**
		 * 是否开启了更新填充
		 */
		private Boolean enableUpdateFill = true;
		/**
		 * 创建时间字段名
		 */
		private String createTimeField = "createTime";
		/**
		 * 更新时间字段名
		 */
		private String updateTimeField = "updateTime";
	}
}
