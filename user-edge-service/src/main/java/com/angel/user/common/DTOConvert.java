package com.angel.user.common;

/**
 * @author JingXiang Bi
 * @date 2019/1/21
 */
public interface DTOConvert<S, T> {
    //T convert(S s);

    /**
     * S -> T
     * @param s 实体类
     * @return T
     */
    T doForward(S s);

    /**
     * T -> S
     * @param t 实体类
     * @return S
     */
    S doBackward(T t);
}
