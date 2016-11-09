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

import java.util.Map;

import org.elasticsearch.index.analysis.RSLPTokenFilterFactory;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule.AnalysisProvider;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import static java.util.Collections.singletonMap;

/**
 * @author anaelcarvalho
 */
public class AnalysisRSLPPlugin extends Plugin implements AnalysisPlugin {

	@Override
	public Map<String, AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
		return singletonMap("br_rslp", RSLPTokenFilterFactory::new);
	}
}
