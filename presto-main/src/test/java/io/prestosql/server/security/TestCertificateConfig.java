/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.prestosql.server.security;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.assertFullMapping;
import static io.airlift.configuration.testing.ConfigAssertions.assertRecordedDefaults;
import static io.airlift.configuration.testing.ConfigAssertions.recordDefaults;

public class TestCertificateConfig
{
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(CertificateConfig.class)
                .setUserExtractionPattern(null)
                .setUserExtractionFile(null));
    }

    @Test
    public void testExplicitPropertyMappings()
    {
        Map<String, String> properties = new ImmutableMap.Builder<String, String>()
                .put("http-server.authentication.certificate.user-extraction.pattern", "(.*)@something")
                .put("http-server.authentication.certificate.user-extraction.file", "some-file")
                .build();

        CertificateConfig expected = new CertificateConfig()
                .setUserExtractionPattern("(.*)@something")
                .setUserExtractionFile(new File("some-file"));

        assertFullMapping(properties, expected);
    }
}