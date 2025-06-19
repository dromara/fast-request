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
