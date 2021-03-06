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
package org.wildfly.swarm.batch.jberet;

import org.junit.Test;
import org.wildfly.swarm.container.Container;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class BatchInVmTest {

    @Test
    public void testSimple() throws Exception {
        Container container = new Container()
                .fraction(BatchFraction.createDefaultFraction());

        try {
            container.start().deploy(Deployments.createDefaultDeployment().addClass(StartBatchJob.class));
            // There's not much that can be tested here, a failure to start/deploy is enough for this
        } finally {
            container.stop();
        }
    }
}
