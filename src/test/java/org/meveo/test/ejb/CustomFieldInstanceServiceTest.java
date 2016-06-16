package org.meveo.test.ejb;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meveo.model.ICustomFieldEntity;
import org.meveo.model.admin.User;
import org.meveo.model.billing.Subscription;
import org.meveo.model.catalog.OfferTemplate;
import org.meveo.model.crm.Provider;
import org.meveo.service.admin.impl.UserService;
import org.meveo.service.billing.impl.SubscriptionService;
import org.meveo.service.catalog.impl.OfferTemplateService;
import org.meveo.service.crm.impl.CustomFieldInstanceService;
import org.meveo.service.crm.impl.ProviderService;
import org.slf4j.Logger;

@RunWith(Arquillian.class)
public class CustomFieldInstanceServiceTest {

	private static final String webwar = "/home/tech/development/seb/workspace/meveo/meveo-admin/web/target/meveo.war";

	@Inject
	private Logger log;
	
	@Inject
	private UserService userService;
	@Inject
	private SubscriptionService subscriptionService;
	@Inject
	private ProviderService providerService;
	@Inject
	private CustomFieldInstanceService customFieldInstanceService;
	@Inject
	private OfferTemplateService offerTemplateService;
	
	private User currentUser;
	private Subscription subscription;
	private Provider currentProvider;
	private OfferTemplate currentOffer;

	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive result = ShrinkWrap.createFromZipFile(WebArchive.class,
				new File(webwar));
		return result;
	}

	@Before
	public void init() throws Exception {
		currentProvider=providerService.findByCode("DEMO");
		currentUser=userService.findByUsername("meveo.admin");
		subscription=subscriptionService.findByCode("subscription100", currentProvider);
		currentOffer=offerTemplateService.findByCode("offer100", currentProvider);
	}

//	@Test()
//	public void testSubscriptionCFE() throws Exception {
//		ICustomFieldEntity[] cfes=customFieldInstanceService.getHierarchyParentCFEntities(subscription);
//		Assert.assertEquals(8, cfes.length);
//	}
//	@Test
//	public void testOfferCfe() throws Exception{
//		ICustomFieldEntity[] cfes=customFieldInstanceService.getHierarchyParentCFEntities(currentOffer);
//		Assert.assertNull(cfes);
//	}
	@Test
	public void testSubscriptionInheritedOnlyCFValueCumulative() throws Exception{
		Object value=customFieldInstanceService.getInheritedOnlyCFValueCumulative(subscription, "sellercfe", currentUser);
		Assert.assertEquals(111L, value);
	}
	@Test
	public void testSubscriptionInheritedOnlyCFValue() throws Exception{
		Object value=customFieldInstanceService.getInheritedOnlyCFValue(subscription, "sellercfe", currentUser);
		Assert.assertEquals(111L, value);
	}
}
