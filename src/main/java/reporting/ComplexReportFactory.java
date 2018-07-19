package reporting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ComplexReportFactory {

	private static ExtentReports reporter;
	private static Map<Long, String> threadToExtentTestMap = new HashMap<Long, String>();
	private static Map<String, ExtentTest> nameToTestMap = new HashMap<String, ExtentTest>();

	private synchronized static ExtentReports getExtentReport() {
		String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

		if (reporter == null) {
			// you can get the file name and other parameters here from a
			// config file or global variables
			reporter = new ExtentReports("Results/AutomationReport"+fileName+".html", true, NetworkMode.OFFLINE);
		}
		return reporter;
	}

	public synchronized static ExtentTest getTest(String testName, String testDescription) {

		// if this test has already been created return
		if (!nameToTestMap.containsKey(testName)) {
			Long threadID = Thread.currentThread().getId();
            ExtentTest test = getExtentReport().startTest(testName, testDescription);
			nameToTestMap.put(testName, test);
			threadToExtentTestMap.put(threadID, testName);
		}
		return nameToTestMap.get(testName);
	}

	public synchronized static ExtentTest getTest(String testName) {
		return getTest(testName, "");
	}


	public synchronized static ExtentTest getTest() {
		Long threadID = Thread.currentThread().getId();

		if (threadToExtentTestMap.containsKey(threadID)) {
			String testName = threadToExtentTestMap.get(threadID);
			return nameToTestMap.get(testName);
		}
		return null;
	}

  	public synchronized static void closeTest(ExtentTest test) {
		if (test != null) {
			getExtentReport().endTest(test);
		}
	}

	public synchronized static void closeTest()  {
		ExtentTest test = getTest();
		closeTest(test);
	}

	public synchronized static void closeReport() {
		if (reporter != null) {
			reporter.flush();
			reporter.close();
		}
	}

}
