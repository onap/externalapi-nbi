package org.onap.nbi;

/**
 * Contains ONAP SDC and AAI urlPaths
 *
 */
public final class OnapComponentsUrlPaths {

    private OnapComponentsUrlPaths() {}

    // SDC
    public static final String SDC_ROOT_URL = "/sdc/v1/catalog/services/";
    public static final String SDC_GET_PATH = "/metadata";
    public static final String SDC_TOSCA_PATH = "/toscaModel";

    // AAI
    public static final String AAI_GET_TENANTS_PATH =
            "/aai/v11/cloud-infrastructure/cloud-regions/cloud-region/$onap.cloudOwner/$onap.lcpCloudRegionId/tenants";
    public static final String AAI_GET_CUSTOMER_PATH = "/aai/v11/business/customers/customer/";
    public static final String AAI_GET_SERVICES_FOR_CUSTOMER_PATH =
            "/aai/v11/business/customers/customer/$customerId/service-subscriptions";
    public static final String AAI_PUT_SERVICE_FOR_CUSTOMER_PATH =
            "/aai/v11/business/customers/customer/$customerId/service-subscriptions/service-subscription/";
    public static final String AAI_GET_SERVICE_FOR_CUSTOMER_PATH =
            "/aai/v11/business/customers/customer/$customerId/service-subscriptions/service-subscription/$serviceSpecName/service-instances/service-instance/$serviceId";
    public static final String AAI_GET_SERVICE_INSTANCES_PATH =
            "/aai/v11/business/customers/customer/$customerId/service-subscriptions/service-subscription/$serviceSpecName/service-instances/";

    // MSO
    public static final String MSO_CREATE_SERVICE_INSTANCE_PATH = "/ecomp/mso/infra/serviceInstance/v4";
    public static final String MSO_GET_REQUEST_STATUS_PATH = "/ecomp/mso/infra/orchestrationRequests/v4/";
    public static final String MSO_DELETE_REQUEST_STATUS_PATH = "/ecomp/mso/infra/serviceInstances/";
}
