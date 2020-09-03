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

package com.snailscoder.core.tool.convert;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.snailscoder.core.tool.support.Try;
import com.snailscoder.core.tool.utils.ClassUtil;
import com.snailscoder.core.tool.utils.ConvertUtil;
import com.snailscoder.core.tool.utils.ReflectUtil;
import org.springframework.cglib.core.Converter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 组合 spring cglib Converter 和 spring ConversionService
 *
 * @author L.cm
 */
@Slf4j
@AllArgsConstructor
public class SnailConverter implements Converter {
	private static final ConcurrentMap<String, TypeDescriptor> TYPE_CACHE = new ConcurrentHashMap<>();
	private final Class<?> targetClazz;

	/**
	 * cglib convert
	 * @param value 源对象属性
	 * @param target 目标对象属性类
	 * @param fieldName 目标的field名，原为 set 方法名，SnailBeanCopier 里做了更改
	 * @return {Object}
	 */
	@Override
	@Nullable
	public Object convert(Object value, Class target, final Object fieldName) {
		if (value == null) {
			return null;
		}
		// 类型一样，不需要转换
		if (ClassUtil.isAssignableValue(target, value)) {
			return value;
		}
		try {
			TypeDescriptor targetDescriptor = SnailConverter.getTypeDescriptor(targetClazz, (String) fieldName);
			return ConvertUtil.convert(value, targetDescriptor);
		} catch (Throwable e) {
			log.warn("SnailConverter error", e);
			return null;
		}
	}

	private static TypeDescriptor getTypeDescriptor(final Class<?> clazz, final String fieldName) {
		String srcCacheKey = clazz.getName() + fieldName;
		return TYPE_CACHE.computeIfAbsent(srcCacheKey, Try.of(k -> {
			// 这里 property 理论上不会为 null
			Field field = ReflectUtil.getField(clazz, fieldName);
			if (field == null) {
				throw new NoSuchFieldException(fieldName);
			}
			return new TypeDescriptor(field);
		}));
	}
}
