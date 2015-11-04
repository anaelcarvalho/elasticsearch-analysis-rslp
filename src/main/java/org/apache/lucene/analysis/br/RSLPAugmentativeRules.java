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
 * Augmentative reduction rules for RSLP stemmer.
 * 
 * @author anaelcarvalho
 */
public enum RSLPAugmentativeRules {
	AUGMENTATIVE_RULE_1(new char[]{'d','í','s','s','i','m','o'},12,null,null),
	AUGMENTATIVE_RULE_2(new char[]{'a','b','i','l','í','s','s','i','m','o'},15,null,null),
	AUGMENTATIVE_RULE_3(new char[]{'í','s','s','i','m','o'},9,null,null),
	AUGMENTATIVE_RULE_4(new char[]{'é','s','i','m','o'},8,null,null),
	AUGMENTATIVE_RULE_5(new char[]{'é','r','r','i','m','o'},10,null,null),
	AUGMENTATIVE_RULE_6(new char[]{'z','i','n','h','o'},7,null,null),
	AUGMENTATIVE_RULE_7(new char[]{'q','u','i','n','h','o'},10,new char[]{'c'},null),
	AUGMENTATIVE_RULE_8(new char[]{'u','i','n','h','o'},9,null,null),
	AUGMENTATIVE_RULE_9(new char[]{'a','d','i','n','h','o'},9,null,null),
	AUGMENTATIVE_RULE_10(new char[]{'i','n','h','o'},7,null,new String[]{"caminho","carinho","cominho"}),
	AUGMENTATIVE_RULE_11(new char[]{'a','l','h','ã','o'},9,null,null),
	AUGMENTATIVE_RULE_12(new char[]{'u','ç','a'},7,null,null),
	AUGMENTATIVE_RULE_13(new char[]{'a','ç','o'},7,null,new String[]{"antebraço"}),
	AUGMENTATIVE_RULE_14(new char[]{'a','ç','a'},7,null,null),
	AUGMENTATIVE_RULE_15(new char[]{'a','d','ã','o'},8,null,null),
	AUGMENTATIVE_RULE_16(new char[]{'i','d','ã','o'},8,null,null),
	AUGMENTATIVE_RULE_17(new char[]{'á','z','i','o'},7,null,new String[]{"topázio"}),
	AUGMENTATIVE_RULE_18(new char[]{'a','r','r','a','z'},9,null,null),
	AUGMENTATIVE_RULE_19(new char[]{'z','a','r','r','ã','o'},9,null,null),
	AUGMENTATIVE_RULE_20(new char[]{'a','r','r','ã','o'},9,null,null),
	AUGMENTATIVE_RULE_21(new char[]{'a','r','r','ã','o'},8,null,null),
	AUGMENTATIVE_RULE_22(new char[]{'z','ã','o'},5,null,new String[]{"coalizão"}),
	AUGMENTATIVE_RULE_23(new char[]{'ã','o'},5,null,new String[]{"aptidão","barão","bilhão","camarão",
			"campeão","canção","capitão","chimarrão","colchão","coração","cordão","cristão","embrião",
			"espião","estação","falcão","feição","ficção","fogão","folião","furacão","fusão","gamão",
			"glutão","grotão","ilusão","lampião","leilão","leão","limão","macacão","mamão","melão",
			"milhão","nação","orgão","patrão","portão","quinhão","rincão","senão","tração","órfão"});
	
	private final char[] suffix;
	private final int minLength;
	private final char[] replacement;
	private final String[] exceptions;

	RSLPAugmentativeRules(char[] suffix, int minLength, char[] replacement, String[] exceptions) {
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
