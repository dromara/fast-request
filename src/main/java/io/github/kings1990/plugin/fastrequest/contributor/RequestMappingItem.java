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
