package org.springblade.core.mp.support;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * @author: snailscoder
 * @date: 2020/6/27 22:17
 */
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Long nextId(Object entity) {
        return CustomIdUtil.nextId();
    }
}
