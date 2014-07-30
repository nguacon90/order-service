package vn.com.vndirect.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class LogReportServiceTest {

	private String fileURL;
	private ReadingLogService logReportService;
	private OrderMemoryCache orderMemoryCache;
	@Before
	public void setUp() {
		orderMemoryCache = new OrderMemoryCacheImpl();
		fileURL="C:/var/log/reports/orderReport.log";
	}
	
	@Test
	public void testReadLogFile() {
		logReportService = new ReadingLogServiceImpl(fileURL, orderMemoryCache);
		logReportService.readLogFile();
		assertNotNull(orderMemoryCache.getTopOrder(1));
	}

}
