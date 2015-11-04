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

package org.elasticsearch.plugin.analysis.rslp;

import java.util.Collection;
import java.util.Collections;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.RSLPAnalysisBinderProcessor;
import org.elasticsearch.indices.analysis.RSLPIndicesAnalysisModule;
import org.elasticsearch.plugins.Plugin;

/**
 * @author anaelcarvalho
 */
public class AnalysisRSLPPlugin extends Plugin {

	@Override
	public String name() {
		return "analysis-rslp";
	}

	@Override
	public String description() {
		return "Brazilian Portuguese RSLP stemmer plugin";
	}

	@Override
	public Collection<Module> nodeModules() {
		return Collections.<Module>singletonList(new RSLPIndicesAnalysisModule());
	}

	public void onModule(AnalysisModule module) {
        module.addProcessor(new RSLPAnalysisBinderProcessor());
    }
}
