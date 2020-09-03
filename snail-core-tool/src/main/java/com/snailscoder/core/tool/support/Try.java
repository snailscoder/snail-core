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

package com.snailscoder.core.tool.support;

import com.snailscoder.core.tool.utils.Exceptions;

import java.util.Objects;
import java.util.function.Function;

/**
 * 当 Lambda 遇上受检异常
 * https://segmentfault.com/a/1190000007832130
 *
 * @author snailscoder
 */
public class Try {

	public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper) {
		Objects.requireNonNull(mapper);
		return t -> {
			try {
				return mapper.apply(t);
			} catch (Exception e) {
				throw Exceptions.unchecked(e);
			}
		};
	}

	@FunctionalInterface
	public interface UncheckedFunction<T, R> {
		/**
		 * 调用
		 * @param t t
		 * @return R
		 * @throws Exception Exception
		 */
		R apply(T t) throws Exception;
	}
}
