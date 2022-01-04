/*
 * Copyright 2021 kings1990(darkings1990@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kings1990.plugin.fastrequest.contributor;

import com.intellij.ide.actions.searcheverywhere.AbstractEqualityProvider;
import com.intellij.ide.actions.searcheverywhere.SearchEverywhereFoundElementInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Computable;
import org.jetbrains.annotations.NotNull;

public class FastRequestEqualityProvider extends AbstractEqualityProvider {
    @Override
    protected boolean areEqual(@NotNull SearchEverywhereFoundElementInfo newItem, @NotNull SearchEverywhereFoundElementInfo alreadyFoundItem) {
        Object newItemElement = newItem.getElement();
        Object alreadyFoundItemElement = alreadyFoundItem.getElement();
        String newItemElementString = getString(newItemElement);
        String alreadyFoundItemElementString = getString(alreadyFoundItemElement);
        return newItemElementString != null && newItemElementString.equals(alreadyFoundItemElementString);
//        return Objects.equals(newItem,alreadyFoundItem);
    }

    private static String getString(Object item) {

        if (item == null) return null;

        if (item instanceof RequestMappingItem) {
            return ApplicationManager.getApplication().runReadAction((Computable<String>) item::toString);
        }
        return null;
    }
}
