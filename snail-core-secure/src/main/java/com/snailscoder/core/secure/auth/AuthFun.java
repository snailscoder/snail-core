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
package com.snailscoder.core.secure.auth;

import com.snailscoder.core.secure.utils.SecureUtil;
import com.snailscoder.core.tool.constant.RoleConstant;
import com.snailscoder.core.tool.utils.CollectionUtil;
import com.snailscoder.core.tool.utils.Func;
import com.snailscoder.core.tool.utils.StringUtil;

/**
 * 权限判断
 *
 * @author snailscoder
 */
public class AuthFun {

	/**
	 * 放行所有请求
	 *
	 * @return {boolean}
	 */
	public boolean permitAll() {
		return true;
	}

	/**
	 * 只有超管角色才可访问
	 *
	 * @return {boolean}
	 */
	public boolean denyAll() {
		return hasRole(RoleConstant.ADMIN);
	}

	/**
	 * 判断是否有该角色权限
	 *
	 * @param role 单角色
	 * @return {boolean}
	 */
	public boolean hasRole(String role) {
		return hasAnyRole(role);
	}

	/**
	 * 判断是否有该角色权限
	 *
	 * @param role 角色集合
	 * @return {boolean}
	 */
	public boolean hasAnyRole(String... role) {
		String userRole = SecureUtil.getUser().getRoleName();
		if (StringUtil.isBlank(userRole)) {
			return false;
		}
		String[] roles = Func.toStrArray(userRole);
		for (String r : role) {
			if (CollectionUtil.contains(roles, r)) {
				return true;
			}
		}
		return false;
	}

}
