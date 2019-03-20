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

package org.onap.nbi.apis.hub;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.onap.nbi.OnapComponentsUrlPaths;
import org.onap.nbi.apis.hub.model.Subscriber;
import org.onap.nbi.apis.hub.model.Subscription;
import org.onap.nbi.apis.hub.service.CheckDMaaPEventsManager;
import org.onap.nbi.apis.hub.service.SubscriptionService;
import org.onap.nbi.commons.EWInterfaceUtils;
import org.onap.nbi.commons.JsonRepresentation;
import org.onap.nbi.commons.MultiCriteriaRequestBuilder;
import org.onap.nbi.commons.ResourceManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hub")
@EnableScheduling
public class HubResource extends ResourceManagement {

  Logger logger = LoggerFactory.getLogger(HubResource.class);

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  SubscriptionService subscriptionService;

  @Autowired
  MultiCriteriaRequestBuilder multiCriteriaRequestBuilder;

  @Autowired
  CheckDMaaPEventsManager checkDMaaPEventMAnager;

  @Autowired
  EWInterfaceUtils ewInterfaceUtils;

  @Value("${nbi.public.url}")
  private String nbiPublicUrl;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createEventSubscription(@RequestBody Subscription subscription,
      @RequestParam MultiValueMap<String, String> params,@RequestHeader(value="Target",required = false)String targetUrl) {
    if (targetUrl != null) {
      targetUrl = targetUrl + OnapComponentsUrlPaths.HUB_EXTERNALAPI_PATH;
      subscriptionService.createSubscription( subscription );
      subscription.setCallback( nbiPublicUrl + OnapComponentsUrlPaths.LISTENER_EXTERNALAPI_PATH );
      return ewInterfaceUtils.callPostRequestTarget( subscription, targetUrl );
    }
    else {
      logger.debug( "POST request for subscription : {}", subscription );
      Subscriber subscriber = subscriptionService.createSubscription( subscription );
      JsonRepresentation filter = new JsonRepresentation( params );
      return this.createResponse( Subscription.createFromSubscriber( subscriber ), filter );
    }
  }

  @GetMapping(value = "/{subscriptionId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Subscription> getSubscription(@PathVariable String subscriptionId,@RequestHeader(value="Target",required = false)String targetUrl) {
    if (targetUrl != null) {
      targetUrl = targetUrl + OnapComponentsUrlPaths.HUB_EXTERNALAPI_PATH + "/" + subscriptionId;
      return (ResponseEntity) ewInterfaceUtils.callGetRequestTarget( targetUrl );
    } else {
      Optional<Subscriber> optionalSubscriber =
              subscriptionService.findSubscriptionById( subscriptionId );
      if (!optionalSubscriber.isPresent()) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok( Subscription.createFromSubscriber( optionalSubscriber.get() ) );
    }
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> findSubscribers(
      @RequestParam MultiValueMap<String, String> params) {

    Query query = multiCriteriaRequestBuilder.buildRequest(params);
    List<Subscriber> subscribers = mongoTemplate.find(query, Subscriber.class);
    JsonRepresentation filter = new JsonRepresentation(params);
    long totalCount = subscriptionService.countSubscription();
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-Total-Count", String.valueOf(totalCount));
    headers.add("X-Result-Count", String.valueOf(subscribers.size()));
    List<Subscription> subscriptions =
        subscribers.stream().map(Subscription::createFromSubscriber).collect(Collectors.toList());

    return this.findResponse(subscriptions, filter, headers);

  }

  /*
   * Resource to test for DMaaP Integration for subscribing to AAI-EVENTs
   */
  @GetMapping("/testaaievents")
  @ResponseStatus(HttpStatus.OK)
  public void testAAIEventListener() {
    checkDMaaPEventMAnager.checkForDMaaPAAIEvents();
  }

  @DeleteMapping("/{subscriptionId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteSubscription(@PathVariable String subscriptionId) {
    logger.debug("DELETE request for subscription id #{}", subscriptionId);
    subscriptionService.deleteSubscription(subscriptionId);
  }

}
