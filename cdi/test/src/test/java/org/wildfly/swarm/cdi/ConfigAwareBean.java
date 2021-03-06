/**
 * Copyright 2015-2016 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wildfly.swarm.cdi;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.wildfly.swarm.spi.api.StageConfig.Resolver;
import org.wildfly.swarm.spi.api.SwarmProperties;

/**
 *
 * @author Martin Kouba
 */
@Dependent
public class ConfigAwareBean {

    @Inject
    @ConfigValue(SwarmProperties.PORT_OFFSET)
    private Integer portOffset;

    private String logLevel;

    private Resolver<String> portOffsetResolver;

    @Inject
    ConfigAwareBean(@ConfigValue("logger.level") String logLevel,
            @ConfigValue(SwarmProperties.PORT_OFFSET) Resolver<String> resolver) {
        this.logLevel = logLevel;
        this.portOffsetResolver = resolver;
    }

    @Inject
    void init(@ConfigValue("logger.level") String logLevel) {
        // Initializers are called after constructor
        if (!this.logLevel.equals(logLevel)) {
            this.logLevel = "FAIL";
        }
    }

    Integer getPortOffset() {
        return portOffset;
    }

    String getLogLevel() {
        return logLevel;
    }

    Resolver<String> getPortOffsetResolver() {
        return portOffsetResolver;
    }

}
