package org.restfulwhois.rdap.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * bean util.
 * @author jiashuo
 *
 */
public final class BeanUtil {
    /**
     * private constructor.
     */
    private BeanUtil(){
        super();
    }
    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(BeanUtil.class);

    /**
     * object copy.
     * @param source
     *     source object.
     * @param target
     *      target object.
     * @param ignoreProperties
     *     ignoreProperties.
     */
    public static void copyProperties(Object source, Object target,
            String... ignoreProperties) {
        try {
            BeanUtils.copyProperties(source, target, ignoreProperties);
        } catch (Exception e) {
            LOGGER.error("copyProperties error:{}", e);
        }
    }
}
