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
package com.snailscoder.core.boot.ctrl;

import com.snailscoder.core.boot.file.SnailFile;
import com.snailscoder.core.boot.file.SnailFileUtil;
import com.snailscoder.core.secure.LoginUser;
import com.snailscoder.core.secure.utils.SecureUtil;
import com.snailscoder.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Snail控制器封装类
 *
 * @author snailscoder
 */
public class SnailController {

	/**
	 * ============================     REQUEST    =================================================
	 */

	@Autowired
	private HttpServletRequest request;

	/**
	 * 获取request
	 *
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		return this.request;
	}

	/**
	 * 获取当前用户
	 *
	 * @return SnailUser
	 */
	public LoginUser getUser() {
		return SecureUtil.getUser();
	}

	/** ============================     API_RESULT    =================================================  */

	/**
	 * 返回ApiResult
	 *
	 * @param data 数据
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public <T> R<T> data(T data) {
		return R.data(data);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param data 数据
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public <T> R<T> data(T data, String msg) {
		return R.data(data, msg);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param data 数据
	 * @param msg  消息
	 * @param code 状态码
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public <T> R<T> data(T data, String msg, int code) {
		return R.data(code, data, msg);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param msg 消息
	 * @return R
	 */
	public R success(String msg) {
		return R.success(msg);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param msg 消息
	 * @return R
	 */
	public R fail(String msg) {
		return R.fail(msg);
	}

	/**
	 * 返回ApiResult
	 *
	 * @param flag 是否成功
	 * @return R
	 */
	public R status(boolean flag) {
		return R.status(flag);
	}


	/**============================     FILE    =================================================  */

	/**
	 * 获取SnailFile封装类
	 *
	 * @param file 文件
	 * @return SnailFile
	 */
	public SnailFile getFile(MultipartFile file) {
		return SnailFileUtil.getFile(file);
	}

	/**
	 * 获取SnailFile封装类
	 *
	 * @param file 文件
	 * @param dir  目录
	 * @return SnailFile
	 */
	public SnailFile getFile(MultipartFile file, String dir) {
		return SnailFileUtil.getFile(file, dir);
	}

	/**
	 * 获取SnailFile封装类
	 *
	 * @param file        文件
	 * @param dir         目录
	 * @param path        路径
	 * @param virtualPath 虚拟路径
	 * @return SnailFile
	 */
	public SnailFile getFile(MultipartFile file, String dir, String path, String virtualPath) {
		return SnailFileUtil.getFile(file, dir, path, virtualPath);
	}

	/**
	 * 获取SnailFile封装类
	 *
	 * @param files 文件集合
	 * @return SnailFile
	 */
	public List<SnailFile> getFiles(List<MultipartFile> files) {
		return SnailFileUtil.getFiles(files);
	}

	/**
	 * 获取SnailFile封装类
	 *
	 * @param files 文件集合
	 * @param dir   目录
	 * @return SnailFile
	 */
	public List<SnailFile> getFiles(List<MultipartFile> files, String dir) {
		return SnailFileUtil.getFiles(files, dir);
	}

	/**
	 * 获取SnailFile封装类
	 *
	 * @param files       文件集合
	 * @param dir         目录
	 * @param path        目录
	 * @param virtualPath 虚拟路径
	 * @return SnailFile
	 */
	public List<SnailFile> getFiles(List<MultipartFile> files, String dir, String path, String virtualPath) {
		return SnailFileUtil.getFiles(files, dir, path, virtualPath);
	}


}
