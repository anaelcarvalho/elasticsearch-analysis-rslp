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

package org.apache.lucene.analysis.br;

import static org.apache.lucene.analysis.util.StemmerUtil.endsWith;

import java.util.Arrays;

/**
 * Adverb reduction rules for RSLP stemmer.
 * 
 * @author anaelcarvalho
 */
public enum RSLPAdverbRules {
	ADVERB_RULE_1(new char[]{'m','e','n','t','e'},9,null,new String[]{"experimente"});
	
	private final char[] suffix;
	private final int minLength;
	private final char[] replacement;
	private final String[] exceptions;

	RSLPAdverbRules(char[] suffix, int minLength, char[] replacement, String[] exceptions) {
		this.suffix = suffix;
		this.minLength = minLength;
		this.replacement = replacement;
		this.exceptions = exceptions;
	}
	
	public int[] processRule(char[] term, int length) {
		if(length < minLength || !endsWith(term, length, suffix)
			|| (exceptions != null && Arrays.binarySearch(exceptions, new String(term, 0, length)) >= 0)){
			return new int[]{0};
		} else {
			int suffixIdx = length - suffix.length;
			int newLength = suffixIdx;
			if(replacement != null) {
				newLength += replacement.length;
			}
			if(newLength > term.length) {
				newLength = length;
			}
			for(int i=newLength-1; i>=suffixIdx; i--) {
				term[i] = replacement[i-suffixIdx];
			}
			return new int[]{1,newLength};
		}
	}
}
