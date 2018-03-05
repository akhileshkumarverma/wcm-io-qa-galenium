/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2018 wcm.io
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
package io.wcm.qa.galenium.cookies;

import static io.wcm.qa.galenium.reporting.GaleniumReportUtil.getLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Cookie;

import io.wcm.qa.galenium.util.GaleniumContext;

/**
 * Combines multiple {@link CookieFetcher} into one profile that can be easily selected on a per test case basis.
 */
public class CookieProfile {

  private Collection<CookieFetcher> cookieFetchers = new ArrayList<CookieFetcher>();
  private String profileName;
  private boolean initialized;
  private Collection<Cookie> fetchedCookies = new ArrayList<>();

  /**
   * Constructor.
   * @param profileName identifies profile, should be unique per test run
   */
  public CookieProfile(String profileName) {
    setProfileName(profileName);
  }

  /**
   * Constructor.
   * @param profileName identifies profile, should be unique per test run
   * @param cookieFetchers fetcher(s) to add right away
   */
  public CookieProfile(String profileName, CookieFetcher... cookieFetchers) {
    this(profileName);
    for (CookieFetcher cookieFetcher : cookieFetchers) {
      add(cookieFetcher);
    }
  }

  /**
   * @param fetcherToAdd
   * @return whether fetcher collection changed as a result of the call
   * @see java.util.Collection#add(java.lang.Object)
   */
  public boolean add(CookieFetcher fetcherToAdd) {
    return this.cookieFetchers.add(fetcherToAdd);
  }

  /**
   * @param collectionOfFetchersToAdd
   * @return whether fetcher collection changed as a result of the call
   * @see java.util.Collection#addAll(java.util.Collection)
   */
  public boolean addAll(Collection<? extends CookieFetcher> collectionOfFetchersToAdd) {
    return this.cookieFetchers.addAll(collectionOfFetchersToAdd);
  }

  /**
   * @see java.util.Collection#clear()
   */
  public void clear() {
    this.cookieFetchers.clear();
  }

  /**
   * @param fetcher
   * @return whether this profile contains the specified fetcher
   * @see java.util.Collection#contains(java.lang.Object)
   */
  public boolean contains(Object fetcher) {
    return this.cookieFetchers.contains(fetcher);
  }

  public String getProfileName() {
    return profileName;
  }

  /**
   * @param fetcherToRemove
   * @return whether an element was removed as a result of this call
   * @see java.util.Collection#remove(java.lang.Object)
   */
  public boolean remove(Object fetcherToRemove) {
    return this.cookieFetchers.remove(fetcherToRemove);
  }

  private void setProfileName(String profileName) {
    this.profileName = profileName;
  }

  private boolean isInitialized() {
    return initialized;
  }

  private void setInitialized() {
    this.initialized = true;
  }

  private void fetchCookies() {
    for (CookieFetcher cookieFetcher : cookieFetchers) {
      getLogger().debug("fetching cookies for profile '" + getProfileName() + "': " + cookieFetcher);
      if (cookieFetcher.fetchCookies()) {
        Set<Cookie> cookies = GaleniumContext.getDriver().manage().getCookies();
        Collection<String> cookieNames = cookieFetcher.getCookieNames();
        for (String cookieName : cookieNames) {
          for (Cookie cookie : cookies) {
            if (StringUtils.equals(cookieName, cookie.getName())) {
              getLogger().trace("adding cookie: '" + cookieName + "'");
              getFetchedCookies().add(cookie);
            }
          }
        }
      }
      else {
        getLogger().warn("fetching cookies failed in profile '" + getProfileName() + "': " + cookieFetcher);
      }
    }
  }

  /**
   * @return fetched cookies
   */
  public Collection<Cookie> getFetchedCookies() {
    if (!isInitialized()) {
      fetchCookies();
      setInitialized();
    }
    return fetchedCookies;
  }

}
