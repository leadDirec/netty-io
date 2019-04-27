package com.study.dispatcher.action.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiangdao
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionMapping {

    /**
     * 协议ID
     *
     * @return protocolId
     */
    int protocolId() default 0;

    String uri();

}
