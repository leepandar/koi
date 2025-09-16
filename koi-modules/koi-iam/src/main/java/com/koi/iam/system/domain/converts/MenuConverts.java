package com.koi.iam.system.domain.converts;

import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import com.koi.common.core.entity.BaseConverts;
import com.koi.iam.system.domain.dto.resp.VisibleResourceResp;
import com.koi.iam.system.domain.enums.ResourceType;

import java.util.Map;

/**
 * @author lida
 */
public class MenuConverts {

    public static final VueRouter2TreeNodeConverts VUE_ROUTER_2_TREE_NODE_CONVERTS = new VueRouter2TreeNodeConverts();

    public static class VueRouter2TreeNodeConverts implements BaseConverts<VisibleResourceResp, TreeNode<Long>> {

        private static Map<String, Object> buildRouteMeta(VisibleResourceResp route) {
            Map<String, Object> meta = Maps.newHashMap();
            if (route.getVisible() != null && !route.getVisible()) {
                meta.put("hideInMenu", true);
                meta.put("activePath", StrUtil.subBefore(route.getPath(), "/", true));
            }
            meta.put("icon", route.getIcon());
            meta.put("title", route.getTitle());
            if (route.getKeepAlive() != null) {
                meta.put("keepAlive", route.getKeepAlive());
            }
            if (route.getType() == ResourceType.LINK) {
                meta.put("link", route.getComponent());
            }
            if (route.getType() == ResourceType.IFRAME) {
                meta.put("iframeSrc", route.getComponent());
            }
            if (StrUtil.isNotBlank(route.getMeta())) {
                meta.putAll(JSON.parseObject(route.getMeta()));
            }
            return meta;
        }

        @Override
        public TreeNode<Long> convert(VisibleResourceResp route) {
            TreeNode<Long> node = new TreeNode<>(route.getId(), route.getParentId(), route.getTitle(), route.getSequence());
            Map<String, Object> extra = Maps.newHashMap();
            extra.put("path", route.getPath());
            // TODO VBen5.x Name 如果为中文部分情况会 404
            extra.put("name", route.getPath());
            extra.put("title", route.getTitle());
            extra.put("type", route.getType().getValue());
            if (route.getType() == ResourceType.DIRECTORY) {
                extra.put("component", "BasicLayout");
            } else if (route.getType() == ResourceType.IFRAME || route.getType() == ResourceType.LINK) {
                extra.put("component", "IFrameView");
                extra.put("url", route.getComponent());
            } else {
                if (StrUtil.isNotBlank(route.getComponent())) {
                    extra.put("component", route.getComponent());
                }
            }
            extra.put("icon", route.getIcon());
            extra.put("permission", route.getPermission());
            extra.put("meta", buildRouteMeta(route));
            node.setExtra(extra);
            return node;
        }
    }

}
