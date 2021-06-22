package com.trainingorg.demo.repository;

import com.trainingorg.demo.bean.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysMenuRepository extends JpaRepository<MenuEntity.SysMenu, Long> {

    //这里我只查询页面转态为启用,可自行定义和写
    @Query(value = "select * from  system_menu where STATUS = 1  ORDER BY  sort ",nativeQuery = true)
    List<MenuEntity.SysMenu> getSystemMenuByStatusAndSort(Integer status, Long sort);

    List<MenuEntity.SysMenu> findAllByStatusOrderBySort(Boolean status);
}
