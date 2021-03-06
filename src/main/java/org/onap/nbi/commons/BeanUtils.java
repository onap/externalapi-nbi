/**
 * Copyright (c) 2018 Orange
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.onap.nbi.commons;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class BeanUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    private static final PropertyUtilsBean PUB = new PropertyUtilsBean();

    private BeanUtils() {
    }

    /**
     *
     * @param bean
     * @param name
     * @return
     */
    public static Object getNestedProperty(Object bean, String name) {
        try {
            return BeanUtils.PUB.getNestedProperty(bean, name);
        } catch (Exception e) {
            LOGGER.warn("Enable to retrieve nested property name=" + name, e);
            return null;
        }
    }

    /**
     *
     * @param bean
     * @param name
     * @param value
     */
    public static void setNestedProperty(Object bean, String name, Object value) {
        try {
            BeanUtils.PUB.setNestedProperty(bean, name, value);
        } catch (Exception ex) {
            LOGGER.warn("Enable to set nested property name=" + name + " value=" + value, ex);
        }
    }

}
