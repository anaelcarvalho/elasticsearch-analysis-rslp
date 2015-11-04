/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.elasticsearch.index.analysis;

import static org.elasticsearch.common.settings.Settings.settingsBuilder;
import static org.elasticsearch.common.settings.Settings.Builder.EMPTY_SETTINGS;
import static org.hamcrest.Matchers.instanceOf;

import org.elasticsearch.Version;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.inject.Injector;
import org.elasticsearch.common.inject.ModulesBuilder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsModule;
import org.elasticsearch.env.Environment;
import org.elasticsearch.env.EnvironmentModule;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.IndexNameModule;
import org.elasticsearch.index.settings.IndexSettingsModule;
import org.elasticsearch.indices.analysis.IndicesAnalysisService;
import org.elasticsearch.test.ESTestCase;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * @author anaelcarvalho
 */
public class RSLPAnalysisTests extends ESTestCase {

    @Test
    public void testDefaultsRSLPAnalysis() {
        Index index = new Index("test");
        Settings settings = settingsBuilder()
                .put("path.home", createTempDir())
                .put(IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT)
                .build();
        Injector parentInjector = new ModulesBuilder().add(new SettingsModule(EMPTY_SETTINGS), 
        		new EnvironmentModule(new Environment(settings))).createInjector();
        Injector injector = new ModulesBuilder().add(
                new IndexSettingsModule(index, settings),
                new IndexNameModule(index),
                new AnalysisModule(EMPTY_SETTINGS, parentInjector.getInstance(IndicesAnalysisService.class))
                	.addProcessor(new RSLPAnalysisBinderProcessor()))
                	.createChildInjector(parentInjector);

        AnalysisService analysisService = injector.getInstance(AnalysisService.class);

        TokenFilterFactory tokenFilterFactory = analysisService.tokenFilter("br_rslp");
        MatcherAssert.assertThat(tokenFilterFactory, instanceOf(RSLPTokenFilterFactory.class));
    }
}
