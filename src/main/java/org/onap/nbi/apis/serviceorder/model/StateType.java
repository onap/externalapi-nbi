/**
 *     Copyright (c) 2018 Orange
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


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public enum StateType {

    ACKNOWLEDGED("acknowledged"),

    REJECTED("rejected"),

    PENDING("pending"),

    HELD("held"),

    INPROGRESS("inProgress"),

    CANCELLED("cancelled"),

    COMPLETED("completed"),

    FAILED("failed"),

    PARTIAL("partial"),

    INPROGRESS_TASK_CREATED("inProgressTaskCreated"),

    INPROGRESS_MODIFY_REQUEST_DELETE_SEND("inProgressModifyRequestDeleteSend"),

    INPROGRESS_MODIFY_ITEM_TO_CREATE("inProgressModifyItemToCreate"),

    INPROGRESS_MODIFY_REQUEST_CREATE_SEND("inProgressModifyRequestCreateSend");


    private String value;

    StateType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static StateType fromValue(String text) {
        for (StateType b : StateType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static List<StateType> fromValueSearch(String text){
        List<StateType> values = new ArrayList<>();
        for (StateType b : StateType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                if(b.equals(StateType.INPROGRESS)) {
                    values.add(INPROGRESS_TASK_CREATED);
                    values.add(INPROGRESS_MODIFY_REQUEST_DELETE_SEND);
                    values.add(INPROGRESS_MODIFY_ITEM_TO_CREATE);
                    values.add(INPROGRESS_MODIFY_REQUEST_CREATE_SEND);
                }
                values.add(b);
            }
        }
        return values;
    }

    @JsonValue
    public String value()
    {
        if("inProgressModifyRequestDeleteSend".equalsIgnoreCase(this.value) || "inProgressModifyItemToCreate".equalsIgnoreCase(this.value)
            || "inProgressTaskCreated".equalsIgnoreCase(this.value)
            || "inProgressModifyRequestCreateSend".equalsIgnoreCase(this.value)) {
            return INPROGRESS.value;
        }
        return this.value;
    }

}

