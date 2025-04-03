/*
 *
 *  * End-User License Agreement (EULA) of Restful Fast Request
 *  * This End-User License Agreement ("EULA") is a legal agreement between you and Kings(darkings1990@gmail.com). Our EULA was created by EULA Template for Restful Fast Request.
 *  *
 *  * This EULA agreement governs your acquisition and use of our Restful Fast Request software ("Software") directly from Kings(darkings1990@gmail.com) or indirectly through a Kings(darkings1990@gmail.com) authorized reseller or distributor (a "Reseller").
 *  *
 *  * Please read this EULA agreement carefully before completing the installation process and using the Restful Fast Request software. It provides a license to use the Restful Fast Request software and contains warranty information and liability disclaimers.
 *  *
 *  * If you register for a free trial of the Restful Fast Request software, this EULA agreement will also govern that trial. By clicking "accept" or installing and/or using the Restful Fast Request software, you are confirming your acceptance of the Software and agreeing to become bound by the terms of this EULA agreement.
 *  *
 *  * If you are entering into this EULA agreement on behalf of a company or other legal entity, you represent that you have the authority to bind such entity and its affiliates to these terms and conditions. If you do not have such authority or if you do not agree with the terms and conditions of this EULA agreement, do not install or use the Software, and you must not accept this EULA agreement.
 *  *
 *  * This EULA agreement shall apply only to the Software supplied by Kings(darkings1990@gmail.com) herewith regardless of whether other software is referred to or described herein. The terms also apply to any Kings(darkings1990@gmail.com) updates, supplements, Internet-based services, and support services for the Software, unless other terms accompany those items on delivery. If so, those terms apply.
 *  *
 *  * License Grant
 *  * Kings(darkings1990@gmail.com) hereby grants you a personal, non-transferable, non-exclusive licence to use the Restful Fast Request software on your devices in accordance with the terms of this EULA agreement.
 *  *
 *  * You are permitted to load the Restful Fast Request software (for example a PC, laptop, mobile or tablet) under your control. You are responsible for ensuring your device meets the minimum requirements of the Restful Fast Request software.
 *  *
 *  * You are not permitted to:
 *  *
 *  * Edit, alter, modify, adapt, translate or otherwise change the whole or any part of the Software nor permit the whole or any part of the Software to be combined with or become incorporated in any other software, nor decompile, disassemble or reverse engineer the Software or attempt to do any such things
 *  * Copy this project and republish a new plugin in JetBrains Marketplace
 *  * Reproduce, copy, distribute, resell or otherwise use the Software for any commercial purpose
 *  * Allow any third party to use the Software on behalf of or for the benefit of any third party
 *  * Use the Software in any way which breaches any applicable local, national or international law
 *  * use the Software for any purpose that Kings(darkings1990@gmail.com) considers is a breach of this EULA agreement
 *  * Intellectual Property and Ownership
 *  * Kings(darkings1990@gmail.com) shall at all times retain ownership of the Software as originally downloaded by you and all subsequent downloads of the Software by you. The Software (and the copyright, and other intellectual property rights of whatever nature in the Software, including any modifications made thereto) are and shall remain the property of Kings(darkings1990@gmail.com).
 *  *
 *  * Kings(darkings1990@gmail.com) reserves the right to grant licences to use the Software to third parties.
 *  *
 *  * Termination
 *  * This EULA agreement is effective from the date you first use the Software and shall continue until terminated. You may terminate it at any time upon written notice to Kings(darkings1990@gmail.com).
 *  *
 *  * It will also terminate immediately if you fail to comply with any term of this EULA agreement. Upon such termination, the licenses granted by this EULA agreement will immediately terminate and you agree to stop all access and use of the Software. The provisions that by their nature continue and survive will survive any termination of this EULA agreement.
 *  *
 *  * Governing Law
 *  * This EULA agreement, and any dispute arising out of or in connection with this EULA agreement, shall be governed by and construed in accordance with the laws of cn.
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
