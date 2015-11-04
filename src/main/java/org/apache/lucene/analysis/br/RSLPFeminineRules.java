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
 * Feminine reduction rules for RSLP stemmer.
 * 
 * @author anaelcarvalho
 */
public enum RSLPFeminineRules {
	FEMININE_RULE_1(new char[]{'o','n','a'},6,new char[]{'ã','o'},new String[]{"abandona","acetona",
			"carona","cortisona","detona","iona","lona","maratona","monótona"}),
	FEMININE_RULE_2(new char[]{'o','r','a'},6,new char[]{'o','r'},null),
	FEMININE_RULE_3(new char[]{'n','a'},6,new char[]{'n','o'},new String[]{"abandona","acetona","banana",
			"campana","caravana","carona","cortisona","detona","grana","guiana","iona","lona","maratona",
			"monótona","paisana"}),
	FEMININE_RULE_4(new char[]{'i','n','h','a'},7,new char[]{'i','n','h','o'},new String[]{"linha","minha",
			"rainha"}),
	FEMININE_RULE_5(new char[]{'e','s','a'},6,new char[]{'ê','s'},new String[]{"ilesa","mesa","obesa",
			"pesa","presa","princesa","turquesa"}),
	FEMININE_RULE_6(new char[]{'o','s','a'},6,new char[]{'o','s','o'},new String[]{"mucosa","prosa"}),
	FEMININE_RULE_7(new char[]{'í','a','c','a'},7,new char[]{'í','a','c','o'},null),
	FEMININE_RULE_8(new char[]{'i','c','a'},6,new char[]{'i','c','o'},new String[]{"dica"}),
	FEMININE_RULE_9(new char[]{'a','d','a'},5,new char[]{'a','d','o'},new String[]{"pitada"}),
	FEMININE_RULE_10(new char[]{'i','d','a'},6,new char[]{'i','d','o'},new String[]{"dúvida","vida"}),
	FEMININE_RULE_11(new char[]{'í','d','a'},6,new char[]{'i','d','o'},new String[]{"recaída","saída"}),
	FEMININE_RULE_12(new char[]{'i','m','a'},6,new char[]{'i','m','o'},new String[]{"vítima"}),
	FEMININE_RULE_13(new char[]{'i','v','a'},6,new char[]{'i','v','o'},new String[]{"oliva","saliva"}),
	FEMININE_RULE_14(new char[]{'e','i','r','a'},7,new char[]{'e','i','r','o'},new String[]{"bandeira",
			"barreira","beira","besteira","cadeira","capoeira","feira","frigideira","fronteira","poeira"}),
	FEMININE_RULE_15(new char[]{'ã'},3,new char[]{'ã','o'},new String[]{"amanhã","arapuã","divã","fã"});
	
	private final char[] suffix;
	private final int minLength;
	private final char[] replacement;
	private final String[] exceptions;

	RSLPFeminineRules(char[] suffix, int minLength, char[] replacement, String[] exceptions) {
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
