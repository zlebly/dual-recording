package com.georsoft.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.georsoft.common.constant.Constants;
import com.georsoft.common.constant.UserConstants;
import com.georsoft.common.core.domain.TreeSelect;
import com.georsoft.common.core.domain.entity.UsrFunctionTree;
import com.georsoft.common.core.domain.entity.UsrUsers;
import com.georsoft.common.utils.SecurityUtils;
import com.georsoft.common.utils.StringUtils;
import com.georsoft.system.domain.vo.MetaVo;
import com.georsoft.system.domain.vo.RouterVo;
import com.georsoft.system.mapper.UsrFunctionTreeMapper;
import com.georsoft.system.mapper.UsrRoleMapper;
import com.georsoft.system.mapper.UsrRoleFunctionMapper;
import com.georsoft.system.service.IUsrFunctionTreeService;

/**
 * 菜单 业务层处理
 * 
 * @author douwenjie
 */
@Service
public class UsrFunctionTreeServiceImpl implements IUsrFunctionTreeService
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private UsrFunctionTreeMapper functionMapper;

    @Autowired
    private UsrRoleMapper roleMapper;

    @Autowired
    private UsrRoleFunctionMapper roleFunctionMapper;

    /**
     * 根据用户查询系统菜单列表
     * 
     * @param id 用户ID
     * @return 菜单列表
     */
    @Override
    public List<UsrFunctionTree> selectFunctionList(Long id)
    {
        return selectFunctionList(new UsrFunctionTree(), id);
    }

    /**
     * 查询系统菜单列表
     * 
     * @param functionTree 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<UsrFunctionTree> selectFunctionList(UsrFunctionTree functionTree, Long id)
    {
        List<UsrFunctionTree> functionTrees = null;
        // 管理员显示所有菜单信息
        if (UsrUsers.isAdmin(SecurityUtils.getUsername()))
        {
            functionTrees = functionMapper.selectFunctionList(functionTree);
        }
        else
        {
            functionTree.getParams().put("loginName", SecurityUtils.getLoginUser().getUser().getLoginName());
            functionTrees = functionMapper.selectFunctionListByUserId(functionTree);
        }
        return functionTrees;
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param loginName 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectFunctionPermsByUserId(String loginName)
    {
        List<String> perms = functionMapper.selectFunctionPermsByLoginName(loginName);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询权限
     * 
     * @param roleCode 角色ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectFunctionPermsByRoleCode(String roleCode)
    {
        List<String> perms = functionMapper.selectFunctionPermsByRoleCode(roleCode);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询菜单
     * 
     * @param loginName 用户名称
     * @return 菜单列表
     */
    @Override
    public List<UsrFunctionTree> selectFunctionTreeByUserId(String loginName)
    {
        List<UsrFunctionTree> functionTrees = null;
        if ("admin".equals(loginName))
        {
            functionTrees = functionMapper.selectFunctionTreeAll();
        }
        else
        {
            functionTrees = functionMapper.selectFunctionTreeByLoginName(loginName);
        }
        return getChildPerms(functionTrees,  "0");
    }

    /**
     * 根据角色ID查询菜单树信息
     * 
     * @param roleCode 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectFunctionListByRoleCode(String roleCode)
    {
        return functionMapper.selectFunctionListByRoleCode(roleCode);
    }

    /**
     * 构建前端路由所需要的菜单
     * 
     * @param functionTrees 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildFunctionTrees(List<UsrFunctionTree> functionTrees)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (UsrFunctionTree functionTree : functionTrees)
        {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(functionTree.getViewFlag()));
            router.setName(getRouteName(functionTree));
            router.setPath(getRouterPath(functionTree));
            router.setComponent(getComponent(functionTree));
            router.setQuery(functionTree.getQuery());
            router.setMeta(new MetaVo(functionTree.getFunctionName(), functionTree.getIconResource(), StringUtils.equals("1", functionTree.getIsCache()), functionTree.getPath()));
            List<UsrFunctionTree> usrFunctionTrees = functionTree.getChildren();
            if (StringUtils.isNotEmpty(usrFunctionTrees) && UserConstants.TYPE_DIR.equals(functionTree.getFunctionType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildFunctionTrees(usrFunctionTrees));
            }
            else if (isFunctionFrame(functionTree))
            {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(functionTree.getPath());
                children.setComponent(functionTree.getComponent());
                children.setName(StringUtils.capitalize(functionTree.getPath()));
                children.setMeta(new MetaVo(functionTree.getFunctionName(), functionTree.getIconResource(), StringUtils.equals("1", functionTree.getIsCache()), functionTree.getPath()));
                children.setQuery(functionTree.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            else if (functionTree.getParentCode().equals("0") && isInnerLink(functionTree))
            {
                router.setMeta(new MetaVo(functionTree.getFunctionName(), functionTree.getIconResource()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(functionTree.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(functionTree.getFunctionName(), functionTree.getIconResource(), functionTree.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 构建前端所需要树结构
     * 
     * @param functionTrees 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<UsrFunctionTree> buildFunctionTree(List<UsrFunctionTree> functionTrees)
    {
        List<UsrFunctionTree> returnList = new ArrayList<UsrFunctionTree>();
        List<String> tempList = functionTrees.stream().map(UsrFunctionTree::getFunctionCode).collect(Collectors.toList());
        for (Iterator<UsrFunctionTree> iterator = functionTrees.iterator(); iterator.hasNext();)
        {
            UsrFunctionTree functionTree = (UsrFunctionTree) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(functionTree.getParentCode()))
            {
                recursionFn(functionTrees, functionTree);
                returnList.add(functionTree);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = functionTrees;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param functionTrees 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildFunctionTreeSelect(List<UsrFunctionTree> functionTrees)
    {
        List<UsrFunctionTree> usrFunctionTrees = buildFunctionTree(functionTrees);
        return usrFunctionTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据菜单ID查询信息
     * 
     * @param functionCode 菜单ID
     * @return 菜单信息
     */
    @Override
    public UsrFunctionTree selectFunctionById(String functionCode)
    {
        return functionMapper.selectFunctionById(functionCode);
    }

    /**
     * 是否存在菜单子节点
     * 
     * @param functionCode 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByFunctionCode(String functionCode)
    {
        int result = functionMapper.hasChildByFunctionCode(functionCode);
        return result > 0;
    }

    /**
     * 查询菜单使用数量
     * 
     * @param functionCode 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkFunctionExistRole(String functionCode)
    {
        int result = roleFunctionMapper.checkFunctionExistRole(functionCode);
        return result > 0;
    }

    /**
     * 新增保存菜单信息
     * 
     * @param functionTree 菜单信息
     * @return 结果
     */
    @Override
    public int insertFunction(UsrFunctionTree functionTree)
    {
        return functionMapper.insertFunction(functionTree);
    }

    /**
     * 修改保存菜单信息
     * 
     * @param functionTree 菜单信息
     * @return 结果
     */
    @Override
    public int updateFunction(UsrFunctionTree functionTree)
    {
        return functionMapper.updateFunction(functionTree);
    }

    /**
     * 删除菜单管理信息
     * 
     * @param functionCode 菜单ID
     * @return 结果
     */
    @Override
    public int deleteFunctionById(String functionCode)
    {
        return functionMapper.deleteFunctionById(functionCode);
    }

    /**
     * 校验菜单名称是否唯一
     * 
     * @param functionTree 菜单信息
     * @return 结果
     */
    @Override
    public boolean checkFunctionNameUnique(UsrFunctionTree functionTree)
    {
        String functionCode = StringUtils.isNull(functionTree.getFunctionCode()) ? "1" : functionTree.getFunctionCode();
        UsrFunctionTree info = functionMapper.checkFunctionNameUnique(functionTree.getFunctionName(), functionTree.getParentCode());
        if (StringUtils.isNotNull(info) && !info.getFunctionCode().equals(functionCode))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 获取路由名称
     * 
     * @param function 菜单信息
     * @return 路由名称
     */
    public String getRouteName(UsrFunctionTree function)
    {
        String routerName = StringUtils.capitalize(function.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isFunctionFrame(function))
        {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     * 
     * @param function 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(UsrFunctionTree function)
    {
        String routerPath = function.getPath();
        // 内链打开外网方式
        if (!function.getParentCode().equals("0") && isInnerLink(function))
        {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if ("0".equals(function.getParentCode()) && UserConstants.TYPE_DIR.equals(function.getFunctionType())
                && UserConstants.NO_FRAME.equals(function.getIsFrame()))
        {
            routerPath = "/" + function.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isFunctionFrame(function))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     * 
     * @param function 菜单信息
     * @return 组件信息
     */
    public String getComponent(UsrFunctionTree function)
    {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(function.getComponent()) && !isFunctionFrame(function))
        {
            component = function.getComponent();
        }
        else if (StringUtils.isEmpty(function.getComponent()) && !function.getParentCode().equals("0") && isInnerLink(function))
        {
            component = UserConstants.INNER_LINK;
        }
        else if (StringUtils.isEmpty(function.getComponent()) && isParentView(function))
        {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     * 
     * @param function 菜单信息
     * @return 结果
     */
    public boolean isFunctionFrame(UsrFunctionTree function)
    {
        return function.getParentCode().equals("0") && UserConstants.TYPE_MENU.equals(function.getFunctionType())
                && function.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 是否为内链组件
     * 
     * @param function 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(UsrFunctionTree function)
    {
        return function.getIsFrame().equals(UserConstants.NO_FRAME) && StringUtils.ishttp(function.getPath());
    }

    /**
     * 是否为parent_view组件
     * 
     * @param function 菜单信息
     * @return 结果
     */
    public boolean isParentView(UsrFunctionTree function)
    {
        return !function.getParentCode().equals("0") && UserConstants.TYPE_DIR.equals(function.getFunctionType());
    }

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param parentCode 传入的父节点ID
     * @return String
     */
    public List<UsrFunctionTree> getChildPerms(List<UsrFunctionTree> list, String parentCode)
    {
        List<UsrFunctionTree> returnList = new ArrayList<UsrFunctionTree>();
        for (Iterator<UsrFunctionTree> iterator = list.iterator(); iterator.hasNext();)
        {
            UsrFunctionTree t = (UsrFunctionTree) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentCode().equals(parentCode))
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * 
     * @param list 分类表
     * @param t 子节点
     */
    private void recursionFn(List<UsrFunctionTree> list, UsrFunctionTree t)
    {
        // 得到子节点列表
        List<UsrFunctionTree> childList = getChildList(list, t);
        t.setChildren(childList);
        for (UsrFunctionTree tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<UsrFunctionTree> getChildList(List<UsrFunctionTree> list, UsrFunctionTree t)
    {
        List<UsrFunctionTree> tlist = new ArrayList<UsrFunctionTree>();
        Iterator<UsrFunctionTree> it = list.iterator();
        while (it.hasNext())
        {
            UsrFunctionTree n = (UsrFunctionTree) it.next();
            if (n.getParentCode().equals(t.getFunctionCode()))
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<UsrFunctionTree> list, UsrFunctionTree t)
    {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 内链域名特殊字符替换
     * 
     * @return 替换后的内链域名
     */
    public String innerLinkReplaceEach(String path)
    {
        return StringUtils.replaceEach(path, new String[] { Constants.HTTP, Constants.HTTPS, Constants.WWW, ".", ":" },
                new String[] { "", "", "", "/", "/" });
    }
}
