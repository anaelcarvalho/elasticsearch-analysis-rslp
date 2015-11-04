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
 * Plural reduction rules for RSLP stemmer.
 * 
 * @author anaelcarvalho
 */
public enum RSLPPluralRules {
	PLURAL_RULE_1(new char[]{'n','s'},3,new char[]{'m'},null),
	PLURAL_RULE_2(new char[]{'õ','e','s'},6,new char[]{'ã','o'},null),
	PLURAL_RULE_3(new char[]{'ã','e','s'},4,new char[]{'ã','o'},new String[]{"mãe"}),
	PLURAL_RULE_4(new char[]{'a','i','s'},4,new char[]{'a','l'},new String[]{"cais","mais"}),
	PLURAL_RULE_5(new char[]{'é','i','s'},5,new char[]{'e','l'},null),
	PLURAL_RULE_6(new char[]{'e','i','s'},5,new char[]{'e','l'},null),
	PLURAL_RULE_7(new char[]{'ó','i','s'},5,new char[]{'o','l'},null),
	PLURAL_RULE_8(new char[]{'i','s'},4,new char[]{'i','l'},new String[]{"biquínis","cais","crúcis",
			"depois","dois","leis","lápis","mais","pois"}),
	PLURAL_RULE_9(new char[]{'l','e','s'},6,new char[]{'l'},null),
	PLURAL_RULE_10(new char[]{'r','e','s'},6,new char[]{'r'},null),
	PLURAL_RULE_11(new char[]{'s'},3,null,new String[]{"aliás","ambas","ambos","após","através",
			"atrás","cais","convés","crúcis","fezes","férias","gás","lápis","mais","mas","menos",
			"messias","moisés","país","pires","pêsames","ês"});
	
	private final char[] suffix;
	private final int minLength;
	private final char[] replacement;
	private final String[] exceptions;
	
	RSLPPluralRules(char[] suffix, int minLength, char[] replacement, String[] exceptions) {
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
