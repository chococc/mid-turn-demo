package com.trainingorg.demo.Util;

import com.trainingorg.demo.bean.Entity.MenuEntity;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    public static List<MenuEntity.MenuVo> toTree(List<MenuEntity.MenuVo> treeList, Long pid) {
        List<MenuEntity.MenuVo> retList = new ArrayList<>();
        for (MenuEntity.MenuVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }
    private static MenuEntity.MenuVo findChildren(MenuEntity.MenuVo parent, List<MenuEntity.MenuVo> treeList) {
        for (MenuEntity.MenuVo child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChild() == null) {
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(findChildren(child, treeList));
            }
        }
        return parent;
    }
}
