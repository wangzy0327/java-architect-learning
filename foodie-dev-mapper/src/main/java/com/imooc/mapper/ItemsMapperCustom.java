package com.imooc.mapper;

import com.imooc.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom{
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String,Object> map);
}