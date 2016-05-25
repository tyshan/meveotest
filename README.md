# meveotest
<h3>Meveo test</h3>

<p>This unit test is for <a href="https://www.assembla.com/spaces/meveo/wiki">MEVEO</a></p>

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
<blockquote>
@RunWith(Arquillian.class)<br/>
public class ModuleTest {<br/>

	private static final String webwar = "/home/tech/development/seb/workspace/meveo/meveo-admin/web/target/meveo.war";<br/>

	@Inject<br/>
	private Logger log;<br/>
	@Inject<br/>
	private MeveoModuleService meveoModuleService;<br/>
	...<br/>
	
	@Deployment<br/>
	public static Archive<?> createTestArchive() {<br/>
		WebArchive result = ShrinkWrap.createFromZipFile(WebArchive.class, new File(webwar));<br/>
		return result;<br/>
	}<br/>

	@Before<br/>
	public void init() throws Exception {<br/>
		meveoModule=meveoModuleService.findByCode("module100", provider);<br/>
		...<br/>
	}<br/>

	@Test<br/>
	public void testExportModule() throws Exception {<br/>
		log.debug("start module export test");<br/>
		meveoModuleService.exportModule2MeveoInstance(meveoModule, meveoInstance,currentUser);<br/>
	}<br/>
}<br/>
</blockquote>
</p>
</li>
<li>
<p>Open the project from eclipse and run the test, ejb3 stateless test is ok</p>
</li>
<p>Attention: install jdk8, I found issues in jdk7 like OutofPerm</p>
</ul>


