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
 * Verb reduction rules for RSLP stemmer.
 * 
 * @author anaelcarvalho
 */
public enum RSLPVerbRules {
	VERB_RULE_1(new char[]{'a','r','í','a','m','o'},8,null,null),
	VERB_RULE_2(new char[]{'á','s','s','e','m','o'},8,null,null),
	VERB_RULE_3(new char[]{'e','r','í','a','m','o'},8,null,null),
	VERB_RULE_4(new char[]{'ê','s','s','e','m','o'},8,null,null),
	VERB_RULE_5(new char[]{'i','r','í','a','m','o'},9,null,null),
	VERB_RULE_6(new char[]{'í','s','s','e','m','o'},9,null,null),
	VERB_RULE_7(new char[]{'á','r','a','m','o'},7,null,null),
	VERB_RULE_8(new char[]{'á','r','e','i'},6,null,null),
	VERB_RULE_9(new char[]{'a','r','e','m','o'},7,null,null),
	VERB_RULE_10(new char[]{'a','r','i','a','m'},7,null,null),
	VERB_RULE_11(new char[]{'a','r','í','e','i'},7,null,null),
	VERB_RULE_12(new char[]{'á','s','s','e','i'},7,null,null),
	VERB_RULE_13(new char[]{'a','s','s','e','m'},7,null,null),
	VERB_RULE_14(new char[]{'á','v','a','m','o'},7,null,null),
	VERB_RULE_15(new char[]{'ê','r','a','m','o'},8,null,null),
	VERB_RULE_16(new char[]{'e','r','e','m','o'},8,null,null),
	VERB_RULE_17(new char[]{'e','r','i','a','m'},8,null,null),
	VERB_RULE_18(new char[]{'e','r','í','e','i'},8,null,null),
	VERB_RULE_19(new char[]{'ê','s','s','e','i'},8,null,null),
	VERB_RULE_20(new char[]{'e','s','s','e','m'},8,null,null),
	VERB_RULE_21(new char[]{'í','r','a','m','o'},8,null,null),
	VERB_RULE_22(new char[]{'i','r','e','m','o'},8,null,null),
	VERB_RULE_23(new char[]{'i','r','i','a','m'},8,null,null),
	VERB_RULE_24(new char[]{'i','r','í','e','i'},8,null,null),
	VERB_RULE_25(new char[]{'í','s','s','e','i'},8,null,null),
	VERB_RULE_26(new char[]{'i','s','s','e','m'},8,null,null),
	VERB_RULE_27(new char[]{'a','n','d','o'},6,null,null),
	VERB_RULE_28(new char[]{'e','n','d','o'},7,null,null),
	VERB_RULE_29(new char[]{'i','n','d','o'},7,null,null),
	VERB_RULE_30(new char[]{'o','n','d','o'},7,null,null),
	VERB_RULE_31(new char[]{'a','r','a','m'},6,null,null),
	VERB_RULE_32(new char[]{'a','r','ã','o'},6,null,null),
	VERB_RULE_33(new char[]{'a','r','d','e'},6,null,null),
	VERB_RULE_34(new char[]{'a','r','e','i'},6,null,null),
	VERB_RULE_35(new char[]{'a','r','e','m'},6,null,null),
	VERB_RULE_36(new char[]{'a','r','i','a'},6,null,null),
	VERB_RULE_37(new char[]{'a','r','m','o'},6,null,null),
	VERB_RULE_38(new char[]{'a','s','s','e'},6,null,null),
	VERB_RULE_39(new char[]{'a','s','t','e'},6,null,null),
	VERB_RULE_40(new char[]{'a','v','a','m'},6,null,new String[]{"agravam"}),
	VERB_RULE_41(new char[]{'á','v','e','i'},6,null,null),
	VERB_RULE_42(new char[]{'e','r','a','m'},7,null,null),
	VERB_RULE_43(new char[]{'e','r','ã','o'},7,null,null),
	VERB_RULE_44(new char[]{'e','r','d','e'},7,null,null),
	VERB_RULE_45(new char[]{'e','r','e','i'},7,null,null),
	VERB_RULE_46(new char[]{'ê','r','e','i'},7,null,null),
	VERB_RULE_47(new char[]{'e','r','e','m'},7,null,null),
	VERB_RULE_48(new char[]{'e','r','i','a'},7,null,null),
	VERB_RULE_49(new char[]{'e','r','m','o'},7,null,null),
	VERB_RULE_50(new char[]{'e','s','s','e'},7,null,null),
	VERB_RULE_51(new char[]{'e','s','t','e'},7,null,new String[]{"agreste","faroeste"}),
	VERB_RULE_52(new char[]{'í','a','m','o'},7,null,null),
	VERB_RULE_53(new char[]{'i','r','a','m'},7,null,null),
	VERB_RULE_54(new char[]{'í','r','a','m'},7,null,null),
	VERB_RULE_55(new char[]{'i','r','ã','o'},6,null,null),
	VERB_RULE_56(new char[]{'i','r','d','e'},6,null,null),
	VERB_RULE_57(new char[]{'i','r','e','i'},7,null,new String[]{"admirei"}),
	VERB_RULE_58(new char[]{'i','r','e','m'},7,null,new String[]{"adquirem","admirem"}),
	VERB_RULE_59(new char[]{'i','r','i','a'},7,null,null),
	VERB_RULE_60(new char[]{'i','r','m','o'},7,null,null),
	VERB_RULE_61(new char[]{'i','s','s','e'},7,null,null),
	VERB_RULE_62(new char[]{'i','s','t','e'},8,null,null),
	VERB_RULE_63(new char[]{'i','a','v','a'},8,null,new String[]{"ampliava"}),
	VERB_RULE_64(new char[]{'a','m','o'},5,null,null),
	VERB_RULE_65(new char[]{'i','o','n','a'},7,null,null),
	VERB_RULE_66(new char[]{'a','r','a'},5,null,new String[]{"arara","prepara"}),
	VERB_RULE_67(new char[]{'a','r','á'},5,null,new String[]{"alvará"}),
	VERB_RULE_68(new char[]{'a','r','e'},5,null,new String[]{"prepare"}),
	VERB_RULE_69(new char[]{'a','v','a'},5,null,new String[]{"agrava","iava"}),
	VERB_RULE_70(new char[]{'e','m','o'},5,null,null),
	VERB_RULE_71(new char[]{'e','r','a'},6,null,new String[]{"acelera","espera"}),
	VERB_RULE_72(new char[]{'e','r','á'},6,null,null),
	VERB_RULE_73(new char[]{'e','r','e'},6,null,new String[]{"espere"}),
	VERB_RULE_74(new char[]{'i','a','m'},6,null,new String[]{"ampliam","elogiam","enfiam","ensaiam"}),
	VERB_RULE_75(new char[]{'í','e','i'},6,null,null),
	VERB_RULE_76(new char[]{'i','m','o'},6,null,new String[]{"intimo","nimo","queimo","reprimo",
			"ximo","íntimo"}),
	VERB_RULE_77(new char[]{'i','r','a'},6,null,new String[]{"fronteira","sátira"}),
	VERB_RULE_78(new char[]{'í','d','o'},6,null,null),
	VERB_RULE_79(new char[]{'i','r','á'},6,null,null),
	VERB_RULE_80(new char[]{'t','i','z','a','r'},9,null,new String[]{"alfabetizar"}),
	VERB_RULE_81(new char[]{'i','z','a','r'},9,null,new String[]{"organizar"}),
	VERB_RULE_82(new char[]{'i','t','a','r'},9,null,new String[]{"acreditar","estreitar","explicitar"}),
	VERB_RULE_83(new char[]{'i','r','e'},6,null,new String[]{"adquire","admire"}),
	VERB_RULE_84(new char[]{'o','m','o'},6,null,null),
	VERB_RULE_85(new char[]{'a','i'},4,null,null),
	VERB_RULE_86(new char[]{'a','m'},4,null,null),
	VERB_RULE_87(new char[]{'e','a','r'},7,null,new String[]{"alardear","nuclear"}),
	VERB_RULE_88(new char[]{'a','r'},4,null,new String[]{"azar","bazaar","patamar"}),
	VERB_RULE_89(new char[]{'u','e','i'},6,null,null),
	VERB_RULE_90(new char[]{'u','í','a'},8,new char[]{'u'},null),
	VERB_RULE_91(new char[]{'e','i'},5,null,null),
	VERB_RULE_92(new char[]{'g','u','e','m'},7,new char[]{'g'},null),
	VERB_RULE_93(new char[]{'e','m'},4,null,new String[]{"alem","virgem"}),
	VERB_RULE_94(new char[]{'e','r'},4,null,new String[]{"pier","éter"}),
	VERB_RULE_95(new char[]{'e','u'},5,null,new String[]{"chapeu"}),
	VERB_RULE_96(new char[]{'i','a'},5,null,new String[]{"acia","aprecia","arredia","cheia","elogia",
			"estória","fatia","lábia","mania","polícia","praia","ásia"}),
	VERB_RULE_97(new char[]{'i','r'},5,null,new String[]{"freir"}),
	VERB_RULE_98(new char[]{'i','u'},5,null,null),
	VERB_RULE_99(new char[]{'e','o','u'},8,null,null),
	VERB_RULE_100(new char[]{'o','u'},5,null,null),
	VERB_RULE_101(new char[]{'i'},4,null,null);
	
	private final char[] suffix;
	private final int minLength;
	private final char[] replacement;
	private final String[] exceptions;

	RSLPVerbRules(char[] suffix, int minLength, char[] replacement, String[] exceptions) {
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
