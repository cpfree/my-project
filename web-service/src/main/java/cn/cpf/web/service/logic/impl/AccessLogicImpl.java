package cn.cpf.web.service.logic.impl;

import cn.cpf.web.base.constant.dic.DicCommon;
import cn.cpf.web.base.model.entity.AccLinkRolePerm;
import cn.cpf.web.service.combine.api.IAccessCombine;
import cn.cpf.web.service.logic.api.IAccessLogic;
import cn.cpf.web.service.mod.system.shiro.AccessBean;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/27 10:06
 **/
@Service
@Slf4j
public class AccessLogicImpl implements IAccessLogic {

    @Autowired
    IAccessCombine iAccessCombine;

    /**
     * 通过CacheBuilder构建一个缓存实例, 存放角色 : 角色权限
     */
    private Cache<String, List<PermInfo>> rolePermCache = CacheBuilder.newBuilder()
            // 设置缓存的最大容量
            .maximumSize(100)
            // 设置缓存在写入一小时后失效
            .expireAfterWrite(1, TimeUnit.HOURS)
            // 设置并发级别为10
            .concurrencyLevel(10)
            // 开启缓存统计
            .recordStats()
            .build();


    private class PermInfo{

        @Getter
        private final int priority;

        @Getter
        private final String permKey;

        @Getter
        private final boolean use;

        public PermInfo(int priority, String permKey, boolean use) {
            this.priority = priority;
            this.permKey = permKey;
            this.use = use;
        }

        public PermInfo(@NonNull AccLinkRolePerm perm) {
            this.priority = perm.getPriority();
            this.permKey = perm.getPermKey();
            this.use = DicCommon.State.enable.isValue(perm.getType());
        }

    }

    /**
     * 通过用户标识获取所有有效权限
     *
     * @param userGuid 角色名称
     * @return AccessBean
     */
    @Override
    public AccessBean selectAccessBeanByUserGuid(String userGuid) {
        AccessBean bean = new AccessBean();
        final Set<String> roleNames = iAccessCombine.selectAllRoleByUserGuid(userGuid);
        final Set<String> permSetByRoleKey = getPermSetByRoleKey(roleNames);
        bean.setRoles(roleNames);
        bean.setPerms(permSetByRoleKey);
        return null;
    }


    /**
     * 通过角色, 获取有用的权限
     *
     * @param roleKeys 角色Set
     * @return 有用的权限Set
     */
    @Override
    public Set<String> getPermSetByRoleKey(@NonNull Set<String> roleKeys) {
        final List<PermInfo> permInfoList = new ArrayList<>();
        // 集合所有对象的权限
        for (String roleKey : roleKeys) {
            List<PermInfo> permInfos = rolePermCache.getIfPresent(roleKey);
            // 如果缓存中找不到
            if (permInfos == null) {
                final List<AccLinkRolePerm> accLinkRolePerms = iAccessCombine.selectLinkRolePerm(DicCommon.State.enable, roleKey);
                if (accLinkRolePerms.isEmpty()) {
                    log.warn("未配置角色 {}", roleKey);
                }
                permInfos = accLinkRolePerms.stream().map(PermInfo::new).collect(Collectors.toList());
                // 加入缓存
                rolePermCache.put(roleKey, permInfos);
            }
            permInfoList.addAll(permInfos);
        }
        // 根据优先级获取有效的权限
        Map<String, PermInfo> map = Maps.newHashMap();
        permInfoList.forEach(it -> {
            final PermInfo permInfo = map.get(it.permKey);
            if (permInfo == null || it.priority > permInfo.priority) {
                map.put(it.permKey, it);
            }
        });
        // 过滤掉禁用的权限
        return map.values().stream().filter(PermInfo::isUse).map(PermInfo::getPermKey).collect(Collectors.toSet());
    }


}
