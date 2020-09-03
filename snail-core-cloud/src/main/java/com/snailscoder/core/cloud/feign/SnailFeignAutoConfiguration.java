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

package com.snailscoder.core.cloud.feign;

import com.netflix.hystrix.HystrixCommand;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.hystrix.HystrixFeign;
import com.snailscoder.core.tool.convert.EnumToStringConverter;
import com.snailscoder.core.tool.convert.StringToEnumConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.SnailFeignClientsRegistrar;
import org.springframework.cloud.openfeign.SnailHystrixTargeter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.Targeter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

import java.util.ArrayList;


/**
 * snail feign 增强配置
 *
 * @author L.cm
 */
@Configuration
@ConditionalOnClass(Feign.class)
@Import(SnailFeignClientsRegistrar.class)
@AutoConfigureAfter(EnableFeignClients.class)
public class SnailFeignAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(Targeter.class)
	public Targeter snailFeignTargeter() {
		return new SnailHystrixTargeter();
	}

	@Configuration("hystrixFeignConfiguration")
	@ConditionalOnClass({ HystrixCommand.class, HystrixFeign.class })
	protected static class HystrixFeignConfiguration {
		@Bean
		@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
		@ConditionalOnProperty("feign.hystrix.enabled")
		public Feign.Builder feignHystrixBuilder(
			RequestInterceptor requestInterceptor, Contract feignContract) {
			return HystrixFeign.builder()
				.contract(feignContract)
				.decode404()
				.requestInterceptor(requestInterceptor);
		}

		@Bean
		@ConditionalOnMissingBean
		public RequestInterceptor requestInterceptor() {
			return new SnailFeignRequestHeaderInterceptor();
		}
	}

	/**
	 * snail enum 《-》 String 转换配置
	 * @param conversionService ConversionService
	 * @return SpringMvcContract
	 */
	@Bean
	public Contract feignContract(@Qualifier("mvcConversionService") ConversionService conversionService) {
		ConverterRegistry converterRegistry =  ((ConverterRegistry) conversionService);
		converterRegistry.addConverter(new EnumToStringConverter());
		converterRegistry.addConverter(new StringToEnumConverter());
		return new SnailSpringMvcContract(new ArrayList<>(), conversionService);
	}
}
