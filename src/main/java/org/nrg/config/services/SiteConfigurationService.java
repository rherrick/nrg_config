/*
 * SiteConfigurationService
 * (C) 2016 Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD License
 */
package org.nrg.config.services;

import org.nrg.config.exceptions.SiteConfigurationException;
import org.nrg.framework.services.NrgService;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public interface SiteConfigurationService extends NrgService {

    /**
     * Initialized the site configuration from whatever persistent stores the service implementation uses.
     *
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    void initSiteConfiguration() throws SiteConfigurationException;

    /**
     * Sets the {@link #getConfigFilesLocationsRoot() configuration file root} to the submitted location,  {@link
     * #resetSiteConfiguration() clears all cached properties}, and {@link #initSiteConfiguration() reloads properties
     * from any properties files} found in the submitted list.
     *
     * @param configFilesLocationsRoot The root location to search for configuration files.
     * @return The resulting site configuration properties.
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    Properties updateSiteConfiguration(final String configFilesLocationsRoot) throws SiteConfigurationException;

    /**
     * Sets the {@link #getConfigFilesLocations() list of configuration file locations} to the submitted list, {@link
     * #resetSiteConfiguration() clears all cached properties}, and {@link #initSiteConfiguration() reloads properties
     * from any properties files} found in the submitted list.
     *
     * @param configFilesLocations The list of locations where configuration files can be found.
     * @return The resulting site configuration properties.
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    Properties updateSiteConfiguration(final List<String> configFilesLocations) throws SiteConfigurationException;

    /**
     * Sets the {@link #getConfigFilesLocationsRoot() configuration file root} to the submitted location, sets the
     * {@link #getConfigFilesLocations() list of configuration file locations} to the submitted list, {@link
     * #resetSiteConfiguration() clears all cached properties}, and {@link #initSiteConfiguration() reloads properties
     * from any properties files} found in the submitted list.
     *
     * @param configFilesLocationsRoot The root location to search for configuration files.
     * @param configFilesLocations     The list of locations where configuration files can be found.
     * @return The resulting site configuration properties.
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    Properties updateSiteConfiguration(final String configFilesLocationsRoot, final List<String> configFilesLocations) throws SiteConfigurationException;

    /**
     * Resets the site configuration. This returns the service to its uninitialized state, discarding any cached or
     * persistent data. The site can then be {@link #initSiteConfiguration() initialized} again.
     */
    void resetSiteConfiguration();

    /**
     * Gets the site configuration as a Java {@link Properties} object.
     *
     * @return The initialized Java {@link Properties} object.
     * @throws SiteConfigurationException Thrown when an error occurs resolving or accessing the configuration service.
     */
    Properties getSiteConfiguration() throws SiteConfigurationException;

    /**
     * Gets the value of the indicated property from the site configuration.
     *
     * @param property The name of the property to be retrieved.
     * @return The value of the property.
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    String getSiteConfigurationProperty(final String property) throws SiteConfigurationException;

    /**
     * Sets the value of the indicated property to the submitted value.
     *
     * @param username The name of the user setting the property.
     * @param property The name of the property to be set.
     * @param value    The value to set for the property.
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    void setSiteConfigurationProperty(String username, String property, String value) throws SiteConfigurationException;

    /**
     * Gets the boolean value of the indicated property. If the property isn't found, the indicated default value is
     * returned instead.
     *
     * @param property     The name of the property to be retrieved.
     * @param defaultValue The default value to be returned if the property isn't found.
     * @return The boolean value requested.
     */
    boolean getBoolSiteConfigurationProperty(final String property, final boolean defaultValue);

    /**
     * Gets the integer value of the indicated property. If the property isn't found, null is returned instead.
     *
     * @param property The name of the property to be retrieved.
     * @return The integer value requested.
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    Integer getIntegerSiteConfigurationProperty(final String property) throws SiteConfigurationException;

    /**
     * Gets the long value of the indicated property. If the property isn't found, null is returned instead.
     *
     * @param property The name of the property to be retrieved.
     * @return The long value requested.
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    Long getLongSiteConfigurationProperty(final String property) throws SiteConfigurationException;

    /**
     * Gets the float value of the indicated property. If the property isn't found, null is returned instead.
     *
     * @param property The name of the property to be retrieved.
     * @return The float value requested.
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    Float getFloatSiteConfigurationProperty(final String property) throws SiteConfigurationException;

    /**
     * Gets the double value of the indicated property. If the property isn't found, null is returned instead.
     *
     * @param property The name of the property to be retrieved.
     * @return The double value requested.
     * @throws SiteConfigurationException When an error occurs accessing or updating the site configuration.
     */
    Double getDoubleSiteConfigurationProperty(final String property) throws SiteConfigurationException;

    /**
     * Gets the list of locations (relative to the {@link #getConfigFilesLocationsRoot() file location root} where
     * config files can be found.
     *
     * @return The list of paths (as strings) to be searched for config files.
     */
    List<String> getConfigFilesLocations();

    /**
     * Sets the list of locations (relative to the {@link #getConfigFilesLocationsRoot() file location root} where
     * config files can be found. Note that this does <i>not</i> re-run the properties processing step and load any new
     * properties, but only updates the stored list of file locations. To reload the site configuration completely, you
     * need to also call {@link #resetSiteConfiguration()} and {@link #initSiteConfiguration()}.  Alternatively, you can
     * call {@link #updateSiteConfiguration(List)} or {@link #updateSiteConfiguration(String, List)}, which both perform
     * all of the steps necessary to re-process the site configuration from as close to scratch as possible.
     *
     * @param configFilesLocations The list of paths (as strings) to be searched for config files.
     */
    void setConfigFilesLocations(final List<String> configFilesLocations);

    /**
     * The absolute path to prepend to any paths in the injected configFilesLocations that are relative.
     *
     * @return The root location for configuration files.
     */
    String getConfigFilesLocationsRoot();

    /**
     * Sets the absolute path to prepend to any paths in the {@link #getConfigFilesLocations() list of file locations}.
     * Note that this does <i>not</i> re-run the properties processing step and load any new properties, but only
     * updates the stored file location root. To reload the site configuration completely, you need to also call {@link
     * #resetSiteConfiguration()} and {@link #initSiteConfiguration()}.  Alternatively, you can call {@link
     * #updateSiteConfiguration(String, List)}, which performs all of the steps necessary to re-process the site
     * configuration from as close to scratch as possible.
     *
     * @param configFilesLocationRoot The root location for configuration files.
     */
    void setConfigFilesLocationsRoot(final String configFilesLocationRoot);

    /**
     * Gets the pattern for matching file names for custom properties files.
     *
     * @return The pattern used for matching file names.
     */
    String getCustomPropertiesNamePattern();

    /**
     * Sets the pattern for matching file names for custom properties files. By default, this is set to {@link
     * #CUSTOM_PROPERTIES_NAME}.
     *
     * @param pattern The pattern to use when locating custom properties files.
     */
    void setCustomPropertiesNamePattern(final String pattern);

    Pattern    CUSTOM_PROPERTIES_NAME   = Pattern.compile("^.*-config\\.properties");
    FileFilter CUSTOM_PROPERTIES_FILTER = new FileFilter() {
        public boolean accept(final File file) {
            return file.exists() && file.isFile() && CUSTOM_PROPERTIES_NAME.matcher(file.getName()).matches();
        }
    };
}

