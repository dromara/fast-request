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

package io.github.kings1990.plugin.fastrequest.util;

import io.github.kings1990.plugin.fastrequest.view.component.Strings;

public class MyStringUtil {

    public static String getFileName0() {
        return "t";
    }

    public static String get() {
        return "get";
    }

    public static String set() {
        return "set";
    }

    public static String getConflictingPlugin() {
        String[] split = Strings.letter.split("");
        String[] splitUp = Strings.letterUp.split("");
        return split[8] + split[14] + split[26] + split[6] + split[8] + split[19] + split[7] + split[20] + split[1] + split[26] + split[8] + split[18] + split[11] + split[8] + split[20] + split[3] + split[14] + split[13] + split[6] + split[26] + splitUp[4] + split[0] + split[18] + split[24] + splitUp[17] + split[4] + split[16] + split[20] + split[4] + split[18] + split[19];
    }

    public static String getSet() {
        String[] split = Strings.letter.split("");
        String[] splitUp = Strings.letterUp.split("");
        return split[8] + split[16] + split[20] + split[4] + split[18] + split[19];
    }



}
