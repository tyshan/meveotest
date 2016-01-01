# meveotest
<h3>Meveo test</h3>

<p>This unit test is for <a href="https://www.assembla.com/spaces/meveo/wiki">MEVEO</a></p>
<p>Please create the test environment from the existed meveo's subproject <a href="https://www.assembla.com/spaces/meveo/git/source/master/meveo-tests">meveo-tests</a>. 
<ul>
<li>
<p>In project root folder, run maven in a console</p>
<p>mvn clean package install</p>
</li>
<li>
<p>find ${jboss-eap-home}/standalone/deployments/ and clean meveo deployed war from the folder</p>
</li>
<li>
<p>create a test</p>
<code>
@RunWith(Arquillian.class)
public class ModuleTest {

	private static final String webwar = "/home/tech/development/seb/workspace/meveo1/meveo-admin/web/target/meveo.war";

	@Inject
	private Logger log;
	@Inject
	private MeveoModuleService meveoModuleService;
	
	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive result = ShrinkWrap.createFromZipFile(WebArchive.class, new File(webwar));
		return result;
	}

	@Before
	public void init() throws Exception {
		meveoModule=meveoModuleService.findByCode("module100", provider);
	}

	@Test
	public void testExportModule() throws Exception {
		log.debug("start module export test");
		meveoModuleService.exportModule2MeveoInstance(meveoModule, meveoInstance,currentUser);
	}
}
</code>
</p>
</li>
<li>
<p>Open the project from eclipse and run the test, ejb3 stateless test is ok</p>
</li>
</ul>


