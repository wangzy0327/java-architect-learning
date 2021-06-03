package com.imooc.enums;

/**
 * 商品类别 枚举
 */
public enum ItemCategory {

    rootCat(1,"根分类"),
    secondCat(2,"二级分类"),
    thirdCat(3,"三级分类");

    public final Integer type;
    public final String value;

    ItemCategory(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
