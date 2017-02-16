/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2017 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.qa.galenium.reporting;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * Handles closing of reports a little more gracefully than the original.
 */
class GaleniumExtentReports extends ExtentReports {

  private boolean closed;
  private Map<String, ExtentTest> map = new HashMap<String, ExtentTest>();
  private String pathToReport;

  // Logger
  private static final Logger log = LoggerFactory.getLogger(GaleniumExtentReports.class);

  GaleniumExtentReports(String pathExtentReportsReport) {
    pathToReport = pathExtentReportsReport;
    ExtentHtmlReporter reporter = new ExtentHtmlReporter(pathToReport);
    attach(reporter);
  }

  @Override
  public synchronized void end() {
    if (isClosed()) {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      StringBuilder stacktraceString = new StringBuilder();
      for (StackTraceElement stackTraceElement : stackTrace) {
        stacktraceString.append(stackTraceElement.toString());
        stacktraceString.append("\n");
      }
      log.error("attempting to close closed ExtentReports:\n" + stacktraceString.toString());
    }
    //    closeAllTests();
    map.clear();
    super.end();
    setClosed(true);
  }

  private ExtentTest addExtentTest(ExtentTest extentTest) {
    return map.put(extentTest.getModel().getName(), extentTest);
  }

  @Override
  public synchronized ExtentTest createTest(String testName, String description) {
    ExtentTest extentTest = super.createTest(testName, description);
    addExtentTest(extentTest);
    return extentTest;
  }

  public ExtentTest getExtentTest(String name) {
    if (map.containsKey(name)) {
      return map.get(name);
    }
    return createTest(name, "");
  }

  private boolean isClosed() {
    return closed;
  }

  private void setClosed(boolean closed) {
    this.closed = closed;
  }

  @Override
  public synchronized void flush() {
    new File(pathToReport).getParentFile().mkdirs();
    super.flush();
  }
}
