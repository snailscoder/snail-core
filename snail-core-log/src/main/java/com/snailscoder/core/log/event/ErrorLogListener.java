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
package com.snailscoder.core.log.event;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.snailscoder.core.launch.props.AppProperties;
import com.snailscoder.core.launch.server.ServerInfo;
import com.snailscoder.core.log.constant.EventConstant;
import com.snailscoder.core.log.feign.ILogClient;
import com.snailscoder.core.log.model.LogError;
import com.snailscoder.core.log.utils.LogAbstractUtil;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

/**
 * 异步监听错误日志事件
 *
 * @author snailscoder
 */
@Slf4j
@AllArgsConstructor
public class ErrorLogListener {

	private final ILogClient logService;
	private final ServerInfo serverInfo;
	private final AppProperties appProperties;

	@Async
	@Order
	@EventListener(ErrorLogEvent.class)
	public void saveErrorLog(ErrorLogEvent event) {
		Map<String, Object> source = (Map<String, Object>) event.getSource();
		LogError logError = (LogError) source.get(EventConstant.EVENT_LOG);
		LogAbstractUtil.addOtherInfoToLog(logError, appProperties, serverInfo);
		logService.saveErrorLog(logError);
	}

}