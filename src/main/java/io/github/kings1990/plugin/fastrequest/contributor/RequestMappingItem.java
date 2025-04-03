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

package io.github.kings1990.plugin.fastrequest.contributor;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import io.github.kings1990.plugin.fastrequest.util.FrIconUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public  class RequestMappingItem implements NavigationItem {
   private final Navigatable navigationElement;
   @NotNull
   private final PsiElement psiElement;
   private final String urlPath;
   private final String requestMethod;

   @NotNull
   public String getName() {
      return this.requestMethod + " " +this.urlPath;
   }

   @NotNull
   public ItemPresentation getPresentation() {
      return (ItemPresentation)(new RequestMappingItem.RequestMappingItemPresentation());
   }

   public void navigate(boolean requestFocus) {
      if (navigationElement != null) {
         navigationElement.navigate(requestFocus);
      }
   }



   public boolean canNavigate() {
      return this.navigationElement != null;
   }

   public boolean canNavigateToSource() {
      return true;
   }

   @NotNull
   public String toString() {
      return "RequestMappingItem(psiElement=" + this.psiElement + ", urlPath='" + this.urlPath + "', requestMethod='" + this.requestMethod + "', navigationElement=" + this.navigationElement + ')';
   }

   @NotNull
   public final PsiElement getPsiElement() {
      return this.psiElement;
   }

   public RequestMappingItem(@NotNull PsiElement psiElement, @NotNull String urlPath, @NotNull String requestMethod) {
      this.psiElement = psiElement;
      this.urlPath = urlPath;
      this.requestMethod = requestMethod;
      PsiElement var10001 = this.psiElement.getNavigationElement();
      if (!(var10001 instanceof Navigatable)) {
         var10001 = null;
      }

      this.navigationElement = (Navigatable)var10001;
   }

   public final class RequestMappingItemPresentation implements ItemPresentation {
      @NotNull
      public String getPresentableText() {
         return RequestMappingItem.this.urlPath;
      }

      @NotNull
      public String getLocationString() {
         PsiElement psiElement = RequestMappingItem.this.getPsiElement();
         PsiFile psiFile = psiElement.getContainingFile();
         String fileName = psiFile != null ? psiFile.getName() : null;
         if (psiElement instanceof PsiMethod) {
            return fileName != null ? fileName : "unknownLocation" +  "." + ((PsiMethod) psiElement).getName();
         } else if (psiElement instanceof PsiClass){
            return fileName != null ? fileName : "unknownLocation";
         } else {
            return "unknownLocation";
         }
      }

      @NotNull
      public Icon getIcon(boolean b) {
         return FrIconUtil.getIconByMethodType(requestMethod);
      }
   }
}
