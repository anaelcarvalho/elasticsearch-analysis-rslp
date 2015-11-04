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

/*
 * This is an implementation of the RSLP stemmer for Brazilian Portuguese as
 * described in the original paper:
 * 
 *  Orengo, V.M. and C.R. Huyck, A Stemming Algorithm for the Portuguese Language, 
 *  in 8th International Symposium on String Processing and Information Retrieval (SPIRE). 
 *  2001: Laguna de San Raphael, Chile. p. 183-193.
 *  Link: http://doi.ieeecomputersociety.org/10.1109/SPIRE.2001.10024
 * 
 * It also contains further improvements and exception lists as described in the later paper:
 * 
 *  Orengo, V.M. (2004). Assessing relevance using automatically translated documents for 
 *  cross-language information retrieval. Tese de Doutorado, School of Computing Science, 
 *  Middlesex University, London.
 * 
 * In addition, the reference C implementation was also used for further refinements.
 * 
 *  RSLP Stemmer by Alexandre Ramos Coelho
 *  http://www.inf.ufrgs.br/~arcoelho/rslp/integrando_rslp.html
 * 
 */
package org.apache.lucene.analysis.br;

/**
 * RSLP stemmer for Brazilian Portuguese.
 * 
 * @author anaelcarvalho
 */
public class RSLPStemmer {
	private static final String tab00c0 = 
			"AAAAAAACEEEEIIII" +
		    "DNOOOOO\u00d7\u00d8UUUUYI\u00df" +
		    "aaaaaaaceeeeiiii" +
		    "\u00f0nooooo\u00f7\u00f8uuuuy\u00fey" +
		    "AaAaAaCcCcCcCcDd" +
		    "DdEeEeEeEeEeGgGg" +
		    "GgGgHhHhIiIiIiIi" +
		    "IiJjJjKkkLlLlLlL" +
		    "lLlNnNnNnnNnOoOo" +
		    "OoOoRrRrRrSsSsSs" +
		    "SsTtTtTtUuUuUuUu" +
		    "UuUuWwYyYZzZzZzF";
	
	public int stem(char[] term, int length) {
		//Step 1: Plural Reduction. For optimization, we check whether word ends with 's'.
		//Minimum word length is 3. 
		if(length >= 3 && term[length-1] == 's') {
			for(RSLPPluralRules r : RSLPPluralRules.values()) {
				int[] result = r.processRule(term, length);
				if(result[0] == 1) { //rule was applied
					length = result[1]; //get new word length
					break; //go to next step
				}
			}
		}
		//Step 2: Feminine Reduction. For optimization, we check whether word ends with 'a'
		//or 'ã'. Minimum word length is 3.
		if(length >= 3 && term[length-1] == 'a' || term[length-1] == 'ã') {
			for(RSLPFeminineRules r : RSLPFeminineRules.values()) {
				int[] result = r.processRule(term, length);
				if(result[0] == 1) {
					length = result[1];
					break;
				}
			}			
		}
		//Step 3: Adverb Reduction. Minimum word length is 9.
		if(length >= 9) {
			for(RSLPAdverbRules r : RSLPAdverbRules.values()) {
				int[] result = r.processRule(term, length);
				if(result[0] == 1) {
					length = result[1];
					break;
				}
			}	
		}
		//Step 4: Augmentative/Diminutive Reduction. Minimum word length is 5.
		if(length >= 5) {
			for(RSLPAugmentativeRules r : RSLPAugmentativeRules.values()) {
				int[] result = r.processRule(term, length);
				if(result[0] == 1) {
					length = result[1];
					break;
				}
			}
		}
		boolean changedTerm = false;
		//Step 5: Noun Suffix Reduction. Minimum word length is 3.
		if(length >= 3) {
			for(RSLPNounRules r : RSLPNounRules.values()) {
				int[] result = r.processRule(term, length);
				if(result[0] == 1) {
					length = result[1];
					changedTerm = true; //store flag for next step
					break;
				}
			}
		}
		//Step 6: Verb Suffix Reduction. Will process only if Step 5 was not applied.
		//Minimum word length is 4.
		if(!changedTerm && length >= 4) {
			changedTerm = false; //reset flag
			for(RSLPVerbRules r : RSLPVerbRules.values()) {
				int[] result = r.processRule(term, length);
				if(result[0] == 1) {
					length = result[1];
					changedTerm = true; //store flag for next step
					break;
				}
			}			
		}
		//Step 7: Vowel Removal. Will process only if Step 6 (and by extension, 5) 
		//was not applied. Minimum word length is 4.
		if(!changedTerm && length >= 4) {
			for(RSLPVowelRules r : RSLPVowelRules.values()) {
				int[] result = r.processRule(term, length);
				if(result[0] == 1) {
					length = result[1];
					break;
				}
			}				
		}
		//Step 8: Accents Removal.
	    for (int i = 0; i < term.length; i++) {
	        if (term[i] >= '\u00c0' && term[i] <= '\u017f') {
	        	term[i] = tab00c0.charAt((int) term[i] - '\u00c0');
	        }
	    }
	    return length;
	}
}
