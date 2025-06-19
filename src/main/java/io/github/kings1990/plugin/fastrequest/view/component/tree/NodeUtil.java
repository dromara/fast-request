/*
 *
 *  * Copyright (C) 2021 WangSheng darkings1990@gmail.com
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Affero General Public License as published
 *  * by the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU Affero General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Affero General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *  
 */

package io.github.kings1990.plugin.fastrequest.view.component.tree;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.generator.impl.JaxRsGenerator;
import io.github.kings1990.plugin.fastrequest.generator.impl.SpringMethodUrlGenerator;
import io.github.kings1990.plugin.fastrequest.model.ApiService;
import io.github.kings1990.plugin.fastrequest.util.FrPsiUtil;
import org.apache.commons.collections.IteratorUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.stream.Collectors;

public class NodeUtil {


    public static List<ApiService> getAllApiList(Collection<PsiClass> controller) {
        SpringMethodUrlGenerator springMethodUrlGenerator = ApplicationManager.getApplication().getService(SpringMethodUrlGenerator.class);
        JaxRsGenerator jaxRsGenerator = ApplicationManager.getApplication().getService(JaxRsGenerator.class);

        List<ApiService> apiServiceList = new ArrayList<>();
        for (PsiClass psiClass : controller) {
            Module module = ModuleUtil.findModuleForPsiElement(psiClass);
            if (module == null) {
                continue;
            }
            String moduleName = module.getName();
            String className = psiClass.getName();
            PsiMethod[] methods = psiClass.getMethods();
            List<ApiService.ApiMethod> apiMethodList = new ArrayList<>();
            for (PsiMethod method : methods) {
                Constant.FrameworkType frameworkType = FrPsiUtil.calcFrameworkType(method);
                if (frameworkType.equals(Constant.FrameworkType.SPRING)) {
                    for (Constant.SpringMappingConfig value : Constant.SpringMappingConfig.values()) {
                        if (method.getAnnotation(value.getCode()) != null) {
                            String methodDescription = springMethodUrlGenerator.getMethodDescription(method);
                            String name = method.getName();
                            String methodUrl = springMethodUrlGenerator.getMethodRequestMappingUrl(method);
                            String classUrl = springMethodUrlGenerator.getClassRequestMappingUrl(method);
                            String originUrl = classUrl + "/" + methodUrl;
                            originUrl = (originUrl.startsWith("/") ? "" : "/") + originUrl.replace("//", "/");
                            String methodType = springMethodUrlGenerator.getMethodType(method);
                            ApiService.ApiMethod apiMethod = new ApiService.ApiMethod(method, originUrl, methodDescription, name, methodType);
                            apiMethodList.add(apiMethod);
                            break;
                        }
                    }
                } else {
                    for (Constant.JaxRsMappingConfig value : Constant.JaxRsMappingConfig.values()) {
                        if (method.getAnnotation(value.getCode()) != null) {
                            String methodDescription = jaxRsGenerator.getMethodDescription(method);
                            String name = method.getName();
                            String methodUrl = jaxRsGenerator.getMethodRequestMappingUrl(method);
                            String classUrl = jaxRsGenerator.getClassRequestMappingUrl(method);
                            String originUrl = classUrl + "/" + methodUrl;
                            originUrl = (originUrl.startsWith("/") ? "" : "/") + originUrl.replace("//", "/");
                            String methodType = jaxRsGenerator.getMethodType(method);
                            ApiService.ApiMethod apiMethod = new ApiService.ApiMethod(method, originUrl, methodDescription, name, methodType);
                            apiMethodList.add(apiMethod);
                            break;
                        }
                    }
                }
            }

            String packageName = getPackageName(psiClass);
            if (packageName == null) {
                packageName = "";
            }

            ApiService apiService = new ApiService(moduleName, packageName, className, apiMethodList);
            apiServiceList.add(apiService);
        }
        return apiServiceList;
    }

    public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<ApiService>>>> convertToMap(List<ApiService> apiServiceList) {
        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<ApiService>>>> dataMap = apiServiceList.stream().collect(Collectors.groupingBy(ApiService::getModuleName,
                LinkedHashMap::new,
                Collectors.groupingBy(ApiService::getPackageName,
                        LinkedHashMap::new,
                        Collectors.groupingBy(ApiService::getClassName,
                                LinkedHashMap::new,
                                Collectors.toList())
                )));
        return dataMap;
    }

    private static List<PackageNode> findChildren(@NotNull PackageNode node) {
        List<PackageNode> children = new ArrayList<>();
        Enumeration<TreeNode> enumeration = node.children();
        while (enumeration.hasMoreElements()) {
            TreeNode ele = enumeration.nextElement();
            if (ele instanceof PackageNode) {
                sortChildNode((BaseNode) ele);
                children.add((PackageNode) ele);
            }
        }
        return children;
    }

    public static void convertToRoot(DefaultMutableTreeNode root, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<ApiService>>>> dataMap, List<String> selectMethodType) {
        List<ModuleNode> moduleNodeList = new ArrayList<>();
        for (Map.Entry<String, LinkedHashMap<String, LinkedHashMap<String, List<ApiService>>>> moduleEntry : dataMap.entrySet()) {
            String moduleName = moduleEntry.getKey();
            ModuleNode moduleNode = new ModuleNode(moduleName);
            LinkedHashMap<String, LinkedHashMap<String, List<ApiService>>> packageMap = moduleEntry.getValue();
            Map<String, PackageNode> packageNodeMap = new LinkedHashMap<>();
            for (Map.Entry<String, LinkedHashMap<String, List<ApiService>>> packageEntry : packageMap.entrySet()) {
                String packageName = packageEntry.getKey();
                LinkedHashMap<String, List<ApiService>> classMap = packageEntry.getValue();
                for (Map.Entry<String, List<ApiService>> classEntry : classMap.entrySet()) {
                    String className = classEntry.getKey();
                    ClassNode classNode = new ClassNode(className);
                    for (ApiService apiService : classEntry.getValue()) {
                        List<ApiService.ApiMethod> apiMethodList = apiService.getApiMethodList();
                        List<ApiService.ApiMethod> filterMethodList = apiMethodList.stream().filter(q -> selectMethodType.contains(q.getMethodType())).collect(Collectors.toList());
                        filterMethodList.forEach(apiMethod -> classNode.add(new MethodNode(apiMethod)));
                    }
                    customPending(packageNodeMap, packageName).add(classNode);
                }
            }
            List<PackageNode> nodes = new ArrayList<>();
            packageNodeMap.forEach((key, rootNode) -> {
                if ("".equals(key)) {
                    return;
                }
                while (true) {
                    List<PackageNode> list = findChildren(rootNode);
                    if (list.size() == 1) {
                        PackageNode newEle = list.get(0);
                        rootNode.remove(newEle);
                        String value = rootNode.getSource() + "." + newEle.getSource();
                        newEle.setSource(value);
                        newEle.setUserObject(value);
                        rootNode = newEle;
                    } else {
                        break;
                    }
                }
                sortChildNode(rootNode);
                nodes.add(rootNode);
            });
            nodes.forEach(moduleNode::add);
            PackageNode noPackageNode = packageNodeMap.get("");
            if (noPackageNode != null) {
                ArrayList<ClassNode> nodeList = (ArrayList<ClassNode>) IteratorUtils.toList(noPackageNode.children().asIterator());
                nodeList.sort(Comparator.comparing(ClassNode::toString));
                nodeList.forEach(moduleNode::add);
            }
            moduleNodeList.add(moduleNode);
        }
        moduleNodeList.sort(Comparator.comparing(ModuleNode::toString));
        moduleNodeList.forEach(root::add);
    }

    private static void sortChildNode(BaseNode rootNode) {
        ArrayList<BaseNode> nodeList = (ArrayList<BaseNode>) IteratorUtils.toList(rootNode.children().asIterator());
        nodeList.sort((n1, n2) -> {
            String prefix1 = n1 instanceof ClassNode ? "999999" : "000000";
            String prefix2 = n2 instanceof ClassNode ? "999999" : "000000";
            String name1 = n1.toString();
            String name2 = n2.toString();
            return (prefix1 + name1).compareTo(prefix2 + name2);
        });
        rootNode.removeAllChildren();
        nodeList.forEach(rootNode::add);
    }

    private static void sortPackage(List<PackageNode> list) {
        list.sort((p1, p2) -> {
            //有package优先,无package排最后 再按name排序
            int childCount1 = p1.getChildCount();
            int childCount2 = p2.getChildCount();
            String prefix1 = childCount1 == 0 ? "999999" : "000000";
            String prefix2 = childCount2 == 0 ? "999999" : "000000";
            String name1 = p1.toString();
            String name2 = p2.toString();
            return (prefix1 + name1).compareTo(prefix2 + name2);
        });
    }

    private static PackageNode customPending(@NotNull Map<String, PackageNode> data, @NotNull String packageName) {
        String[] names = packageName.split("\\.");

        PackageNode node = data.computeIfAbsent(names[0], PackageNode::new);

        if (names.length == 1) {
            return node;
        }

        PackageNode curr = node;
        int fex = 1;
        while (fex < names.length) {
            String name = names[fex++];
            curr = findChild(curr, name);
        }
        return curr;
    }

    @NotNull
    private static PackageNode findChild(@NotNull PackageNode node, @NotNull String name) {
        Enumeration<TreeNode> children = node.children();
        while (children.hasMoreElements()) {
            TreeNode child = children.nextElement();
            if (!(child instanceof PackageNode)) {
                continue;
            }
            PackageNode packageNode = (PackageNode) child;
            if (name.equals(packageNode.getSource())) {
                return packageNode;
            }
        }
        PackageNode packageNode = new PackageNode(name);
        node.add(packageNode);
        return packageNode;
    }


    private static String getPackageName(@NotNull PsiClass psiClass) {
        String qualifiedName = psiClass.getQualifiedName();
        if (qualifiedName == null) {
            return null;
        }

        String fileName = psiClass.getName();
        if (fileName == null) {
            return null;
        }

        if (!qualifiedName.equals(fileName)) {
            return qualifiedName.substring(0, qualifiedName.lastIndexOf('.'));
        }

        return null;
    }
}
