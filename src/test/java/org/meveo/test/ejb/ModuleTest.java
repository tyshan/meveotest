package org.meveo.test.ejb;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meveo.model.admin.MeveoModule;
import org.meveo.model.admin.User;
import org.meveo.model.communication.MeveoInstance;
import org.meveo.model.crm.Provider;
import org.meveo.service.admin.impl.MeveoModuleService;
import org.meveo.service.admin.impl.UserService;
import org.meveo.service.communication.impl.MeveoInstanceService;
import org.meveo.service.crm.impl.ProviderService;
import org.slf4j.Logger;

/**
 * ejb3 tier test
 * 
 * @author Tyshan Shi(tyshan@manaty.net)
 *
 */

@RunWith(Arquillian.class)
public class ModuleTest {

	private static final String webwar = "/home/tech/development/seb/workspace/meveo1/meveo-admin/web/target/meveo.war";

	@Inject
	private Logger log;
	@Inject
	private MeveoModuleService meveoModuleService;
	@Inject
	private MeveoInstanceService meveoInstanceService;
	@Inject
	private ProviderService providerService;
	@Inject
	private UserService userService;

	private MeveoInstance meveoInstance;
	private Provider provider;
	private MeveoModule meveoModule;
	private User currentUser;
	
	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive result = ShrinkWrap.createFromZipFile(WebArchive.class, new File(webwar));
		return result;
	}

	@Before
	public void init() throws Exception {
		provider=providerService.findByCode("DEMO");
		meveoInstance = meveoInstanceService.findByCode("localhost");
		meveoModule=meveoModuleService.findByCode("module100", provider);
		currentUser=userService.findByUsername("meveo.admin");
	}

	@Test()
	public void testExportModule() throws Exception {
		log.debug("start module export test");
		meveoModuleService.exportModule2MeveoInstance(meveoModule, meveoInstance,currentUser);
	}

}
