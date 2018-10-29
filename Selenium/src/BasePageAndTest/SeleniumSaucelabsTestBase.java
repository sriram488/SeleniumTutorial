package BasePageAndTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

public class SeleniumSaucelabsTestBase implements SauceOnDemandSessionIdProvider {

	private static final Logger LOGGER = Logger.getLogger(SeleniumSaucelabsTestBase.class);
	private static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
	private static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	private static final String SAUCE_ONDEMAND_BROWSERS = System.getenv("SAUCE_ONDEMAND_BROWSERS");
	private static final String JOB_NAME = System.getenv("JOB_NAME");
	private static final String RANDOM_CHARACTERS_FOR_JENKINS = RandomStringUtils.randomAlphabetic(2);
	private static final String BUILD_NUMBER = System.getenv("BUILD_NUMBER");
	private static final String SAUCE_URI = "@ondemand.saucelabs.com:80/wd/hub"; // the URI may change
	private final SauceOnDemandAuthentication authentication;
	@Rule
	public SauceOnDemandTestWatcher resultReportingWatcher;
	@Rule
	public final TestName name;
	private String sessionId;
	protected WebDriver driver;
	protected String os;
	protected String browser;
	protected String version;
	protected String applicationUrl;
	protected String testDescription;
	protected boolean autoLogin;
	protected boolean extendedDebugging;
	protected String testUsername;
	protected String testPassword;

	public SeleniumSaucelabsTestBase() {
		this.authentication = new SauceOnDemandAuthentication(SAUCE_USERNAME, SAUCE_ACCESS_KEY);
		this.resultReportingWatcher = new SauceOnDemandTestWatcher(this, this.authentication);
		this.name = new TestName() {
			public String getMethodName() {
				return String.format("%s : %s", SeleniumSaucelabsTestBase.this.testDescription, super.getMethodName());
			}
		};
		this.testDescription = "";
		this.testUsername = "";
		this.testPassword = "";
	}

	@Override
	public String getSessionId() {
		return this.sessionId;
	}

	/*
	 * @return a LinkedList containing string arrays representing the browser
	 * combinations the test should be run against. The values in the string array
	 * are used as part of the invocation of the test constructor.
	 */
	@ConcurrentParameterized.Parameters
	public static LinkedList<String[]> browserStrings() {
		LinkedList<String[]> browsers = new LinkedList<String[]>();
		try {
			JSONArray browserData = new JSONArray(SAUCE_ONDEMAND_BROWSERS);
			for (int i = 0; i < browserData.length(); i++) {
				JSONObject browserObject = browserData.getJSONObject(i);
				browsers.add(new String[] { browserObject.getString("os"), browserObject.getString("browser-version"),
						browserObject.getString("browser") });
			}
		} catch (JSONException ex) {
			LOGGER.fatal(ex);
		}
		return browsers;

	}

	@Before
	public void setUp() throws MalformedURLException {
		final String method = name.getMethodName();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
		capabilities.setCapability(CapabilityType.VERSION, version);
		capabilities.setCapability(CapabilityType.PLATFORM_NAME, os);
		capabilities.setCapability("name", method);
		capabilities.setCapability("build", JOB_NAME + "_" + BUILD_NUMBER);

		if (os.equals(BrowserType.IE)) {
			capabilities.setCapability("ie.ensureCleanSession", true);
			capabilities.setCapability("ignoreProtectedModeSettings", true);
		}

		// Chrome Set up
		if (os.equals(BrowserType.CHROME) || os.equals(BrowserType.GOOGLECHROME)) {
			ChromeOptions options = new ChromeOptions();
			// disable automation info bar
			options.addArguments("start-maximized", "disabled-webgl", "blacklist-webgl",
					"blacklist-accelerated-compositing", "disable-accelerated-2d-canvas",
					"disable-accelerated-compositing", "disable-accelerated-layers", "disable-accelerated-plugins",
					"disable-accelerated-video", "disable-accelerated-video-code", "disable-gpu", "disable-infobars",
					"disble-web-security", "no-proxy-server", "test-type");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		}

		// create remote webdriver
		driver = new RemoteWebDriver(new URL("http://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + SAUCE_URI),
				capabilities);
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());

		// create session ID
		sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

		String saucelabsMesageForJenkins = String.format("SauceOnDemandSessionId=%s job-name=%s_%s", sessionId,
				JOB_NAME, RANDOM_CHARACTERS_FOR_JENKINS);
		System.out.println(saucelabsMesageForJenkins);
		LOGGER.debug(method + " : Navigating to" + applicationUrl);
		Assert.assertTrue("Test Abort - missing URL", applicationUrl != null);
		getApplicationUrl();

		if (autoLogin) {
			// new LoginPage(driver).submitLogin(testUsername, testPassword); created login
			// page and submit the credentials

		}
	}

	private boolean getApplicationUrl() {
		int attempts = 0;
		boolean result = false;

		while (attempts < 5) {
			try {
				driver.get(applicationUrl);
				result = true;
				break;
			} catch (Exception ex2) {
				LOGGER.info("Unexpected driver exception " + applicationUrl + "never loaded for attempt" + attempts);
				attempts++;

			}
		}
		return result;
	}

	@After
	public void tearDown() {
		String sauceLabsMessageForJenkins = String.format("SauceOnDemandSessionID=%s job-name=%s_%s", sessionId,
				JOB_NAME, RANDOM_CHARACTERS_FOR_JENKINS);
		System.out.println(sauceLabsMessageForJenkins);

		if (driver != null) {
			String message = String.format("https://saucelabs.conm/tests/%s", sessionId);
			String output = String.format("%s - %s", name.getMethodName(), message);
			LOGGER.info(output);
			driver.quit();
		}
	}

}
