package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

public interface CarouselService {

    /**
     * 加载轮播图
     * @param isShow
     * @return
     */
    List<Carousel> queryAll(Integer isShow);

}
