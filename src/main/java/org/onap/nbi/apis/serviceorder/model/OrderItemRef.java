/**
 *
 *     Copyright (c) 2017 Orange.  All rights reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
/*
 * API ServiceOrder serviceOrder API designed for ONAP Beijing Release. This API is build from TMF
 * open API16.5 + applied TMF guideline 3.0
 *
 * OpenAPI spec version: 0.1.1_inProgress
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git Do not edit the class manually.
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


package org.onap.nbi.apis.serviceorder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Reference of an order item
 */
@ApiModel(description = "Reference of an order item")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen",
        date = "2018-02-19T14:00:30.767Z")
public class OrderItemRef {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("href")
    private String href = null;

    public OrderItemRef id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Unique identifier of the order irtem
     *
     * @return id
     **/
    @JsonProperty("id")
    @ApiModelProperty(required = true, value = "Unique identifier of the order irtem")
    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderItemRef href(String href) {
        this.href = href;
        return this;
    }

    /**
     * Unique reference of the order item
     *
     * @return href
     **/
    @JsonProperty("href")
    @ApiModelProperty(value = "Unique reference of the order item")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderItemRef orderItemRef = (OrderItemRef) o;
        return Objects.equals(this.id, orderItemRef.id) && Objects.equals(this.href, orderItemRef.href);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, href);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderItemRef {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    href: ").append(toIndentedString(href)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

