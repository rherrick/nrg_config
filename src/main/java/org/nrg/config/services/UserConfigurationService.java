/*
 * UseConfigurationService
 * Copyright (c) 2013. Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD License
 */

package org.nrg.config.services;

import org.nrg.config.exceptions.ConfigServiceException;
import org.nrg.framework.services.NrgService;

/**
 * UserConfigurationService
 *
 * @author rherri01
 * @since 1/31/13
 */
public interface UserConfigurationService extends NrgService {
    /**
     * Gets the user configuration
     * @param username    The username of the user associated with the desired configuration.
     * @param configId    The primary configuration identifier.
     * @param keys        Zero to N keys that can be used to drill into the retrieved configuration.
     * @return The content of the requested configuration for the indicated user.
     */
    public String getUserConfiguration(String username, String configId, String... keys);

    /**
     * Sets the user configuration.
     * @param username         The username of the user associated with the desired configuration.
     * @param configId         The primary configuration identifier.
     * @param configuration    The content to set for the requested configuration for the indicated user.
     * @param keys             Zero to N keys that can be used to drill into the retrieved configuration.
     * @throws ConfigServiceException
     */
    public void setUserConfiguration(String username, String configId, String configuration, String... keys) throws ConfigServiceException;
}