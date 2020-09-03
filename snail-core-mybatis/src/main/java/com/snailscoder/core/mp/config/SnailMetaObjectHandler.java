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

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * mybatis-plus自动填充
 *
 * @author snailscoder
 * @date 2020/4/5
 */
@Slf4j
public class SnailMetaObjectHandler implements MetaObjectHandler {

	private MybatisPlusProperties properties;

	public SnailMetaObjectHandler(MybatisPlusProperties autoFillProperties) {
		this.properties = autoFillProperties;
	}

	/**
	 * 是否开启了插入填充
	 */
	@Override
	public boolean openInsertFill() {
		return properties.getAutoFill().getEnableInsertFill();
	}

	/**
	 * 是否开启了更新填充
	 */
	@Override
	public boolean openUpdateFill() {
		return properties.getAutoFill().getEnableUpdateFill();
	}

	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("start insert fill ....");
		LocalDateTime date = LocalDateTime.now();
		this.strictInsertFill(metaObject, properties.getAutoFill().getCreateTimeField(), LocalDateTime.class, date);
		this.strictInsertFill(metaObject, properties.getAutoFill().getUpdateTimeField(), LocalDateTime.class, date);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start update fill ....");
		this.strictInsertFill(metaObject, properties.getAutoFill().getUpdateTimeField(), LocalDateTime.class, LocalDateTime.now());
	}

}
