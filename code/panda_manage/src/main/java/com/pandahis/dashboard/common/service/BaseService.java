package com.pandahis.dashboard.common.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

// JDK8函数式接口注解 仅能包含一个抽象方法
public interface BaseService<E, ID extends Serializable> {
    BaseMapper<E> getRepository();

    default E get(ID id) {
        return getRepository().selectById(id);
    }

    default List<E> geAll() {
        return getRepository().selectList(null);
    }

    default Integer getTotalCount() {
        return getRepository().selectCount(null);
    }

    default E insert(E entity) {
        getRepository().insert(entity);
        return entity;
    }

    default E update(E entity) {
        getRepository().updateById(entity);
        return entity;
    }

    default E insertOrUpdate(E entity) {
        try {
            Object getId = entity.getClass().getMethod("getId").invoke(entity);
            if (getId != null) {
                update(entity);
            } else {

                insert(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    default void delete(ID id) {
        getRepository().deleteById(id);
    }

    default void batchDelete(List<ID> ids) {
        getRepository().deleteBatchIds(ids);
    }

    default List<E> batchInsert(List<E> list) {
        list.forEach(each -> {
            getRepository().insert(each);
        });
        return list;
    }

    default List<E> findByBatchIds(List<ID> ids) {
        return getRepository().selectBatchIds(ids);
    }

    default List<E> findAll() {
        return getRepository().selectList(null);
    }

    default IPage<E> findByQueryWrapperPage(Page page, QueryWrapper<E> queryWrapper) {
        return getRepository().selectPage(page, queryWrapper);
    }

    default List<E> findQueryWrapper(QueryWrapper queryWrapper) {
        return getRepository().selectList(queryWrapper);
    }

    default Page<E> findALl(Page<E> page) {
        return (Page<E>) getRepository().selectPage(page, null);
    }
//    default QueryWrapper<E> getQueryWrapper(E e);

//    default Page<E> findAll(Page<E> page, QueryCondition<E> condition){
//        E data = condition.getData();
//        SearchVo searchVo = condition.getSearchVo();
//
//        //对自定字段进行查询
//        QueryWrapper<E> queryWrapper=new QueryWrapper<>(data);
//
//        if (searchVo != null) {
//            String content = searchVo.getContent();
//            String title = searchVo.getTitle();
//            String name = searchVo.getName();
//            String category=searchVo.getCategoryName();
//
////            queryWrapper.and(each ->each.like("desc_info",content).or()
//////            .like("title",title).or().like("name",name)
//////            );
//            if(StringUtils.isNotEmpty(category)){
//                queryWrapper.like("category_name",category);
//            }
//            if(StringUtils.isNotEmpty(title)){
//                queryWrapper.like("title",title);
//            }
//        }
//        return (Page<E>) getRepository().selectPage(page,queryWrapper);
//    }


    default Integer count(QueryWrapper<E> queryWrapper) {
        return getRepository().selectCount(queryWrapper);
    }


}
