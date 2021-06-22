package com.trainingorg.demo.bean.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class MenuEntity {
    @Entity
    @Table(name = "system_menu", schema = "trainingOrg")
    @DynamicInsert
    @DynamicUpdate
    public class SysMenu implements Serializable {

        private static final long serialVersionUID = 5421757630121636006L;

        /**
         * 复合主键要用这个注解
         */
        @EmbeddedId
        private MenuKey key;


        /**
         * 父ID
         */
        @Column(name = "pid")
        private Long pid;


        /**
         * 菜单图标
         */
        @Column(name = "icon")
        private String icon;

        /**
         * 链接打开方式
         */
        @Column(name = "target", columnDefinition = "_self")
        private String target;
        /**
         * 菜单排序
         */
        @Column(name = "sort")
        private Long sort;

        /**
         * 状态(0:禁用,1:启用)
         */
        @Column(name = "status", columnDefinition = "tinyint DEFAULT 1")
        private Integer status;

        /**
         * 备注信息
         */
        @Column(name = "remark")
        private String remark;

        /**
         * 创建时间
         */
        @Column(name = "create_at")
        private Date createAt;

        /**
         * 更新时间
         */
        @Column(name = "update_at")
        private Date updateAt;

        /**
         * 删除时间
         */
        @Column(name = "delete_at")
        private Date deleteAt;

/*    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

        public Long getPid() {
            return this.pid;
        }

        public void setPid(Long pid) {
            this.pid = pid;
        }


        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }


        public String getTarget() {
            return this.target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public Long getSort() {
            return this.sort;
        }

        public void setSort(Long sort) {
            this.sort = sort;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Date getCreateAt() {
            return this.createAt;
        }

        public void setCreateAt(Date createAt) {
            this.createAt = createAt;
        }

        public Date getUpdateAt() {
            return this.updateAt;
        }

        public void setUpdateAt(Date updateAt) {
            this.updateAt = updateAt;
        }

        public Date getDeleteAt() {
            return this.deleteAt;
        }

        public void setDeleteAt(Date deleteAt) {
            this.deleteAt = deleteAt;
        }

        public MenuKey getKey() {
            return key;
        }

        public void setKey(MenuKey key) {
            this.key = key;
        }

    }


    @Embeddable
    public class MenuKey implements Serializable {


        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        /**
         * 名称
         */
        @Column(name = "title")
        private String title;
        /**
         * 链接
         */
        @Column(name = "href")
        private String href;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MenuVo {

        private Long id;

        private Long pid;

        private String title;

        private String icon;

        private String href;

        private String target;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getPid() {
            return pid;
        }

        public void setPid(Long pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public List<MenuVo> getChild() {
            return child;
        }

        public void setChild(List<MenuVo> child) {
            this.child = child;
        }

        private List<MenuVo> child;
    }
}