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
 * Noun reduction rules for RSLP stemmer.
 * 
 * @author anaelcarvalho
 */
public enum RSLPNounRules {
	NOUN_RULE_1(new char[]{'e','n','c','i','a','l','i','s','t','a'},18,null,null),
	NOUN_RULE_2(new char[]{'a','l','i','s','t','a'},11,null,null),
	NOUN_RULE_3(new char[]{'a','g','e','m'},7,null,new String[]{"carruagem","chantagem","coragem","vantagem"}),
	NOUN_RULE_4(new char[]{'i','a','m','e','n','t','o'},11,null,null),
	NOUN_RULE_5(new char[]{'a','m','e','n','t','o'},9,null,new String[]{"departamento","firmamento","fundamento"}),
	NOUN_RULE_6(new char[]{'i','m','e','n','t','o'},9,null,null),
	NOUN_RULE_7(new char[]{'m','e','n','t','o'},11,null,new String[]{"complemento","departamento","elemento",
			"firmamento","instrumento"}),
	NOUN_RULE_8(new char[]{'a','l','i','z','a','d','o'},11,null,null),
	NOUN_RULE_9(new char[]{'a','t','i','z','a','d','o'},11,null,null),
	NOUN_RULE_10(new char[]{'t','i','z','a','d','o'},10,null,new String[]{"alfabetizado"}),
	NOUN_RULE_11(new char[]{'i','z','a','d','o'},10,null,new String[]{"organizado","pulverizado"}),
	NOUN_RULE_12(new char[]{'a','t','i','v','o'},9,null,new String[]{"pejorativo","relativo"}),
	NOUN_RULE_13(new char[]{'t','i','v','o'},8,null,new String[]{"relativo"}),
	NOUN_RULE_14(new char[]{'i','v','o'},7,null,new String[]{"passivo","pejorativo","positivo","possessivo"}),
	NOUN_RULE_15(new char[]{'a','d','o'},5,null,new String[]{"grado"}),
	NOUN_RULE_16(new char[]{'i','d','o'},6,null,new String[]{"consolido","cândido","decido","duvido","marido",
			"rápido","tímido"}),
	NOUN_RULE_17(new char[]{'a','d','o','r'},7,null,null),
	NOUN_RULE_18(new char[]{'e','d','o','r'},7,null,null),
	NOUN_RULE_19(new char[]{'i','d','o','r'},8,null,new String[]{"ouvidor"}),
	NOUN_RULE_20(new char[]{'d','o','r'},7,null,new String[]{"ouvidor"}),
	NOUN_RULE_21(new char[]{'s','o','r'},7,null,new String[]{"assessor"}),
	NOUN_RULE_22(new char[]{'a','t','o','r','i','a'},11,null,null),
	NOUN_RULE_23(new char[]{'t','o','r'},6,null,new String[]{"benfeitor","consultor","editor","leitor",
			"pastor","produtor","promotor"}),
	NOUN_RULE_24(new char[]{'o','r'},3,null,new String[]{"assessor","autor","benfeitor","favor","melhor",
			"motor","pastor","redor","rigor","sensor","tambor","terior","tumor"}),
	NOUN_RULE_25(new char[]{'a','b','i','l','i','d','a','d','e'},14,null,null),
	NOUN_RULE_26(new char[]{'i','c','i','o','n','i','s','t','a'},13,null,null),
	NOUN_RULE_27(new char[]{'c','i','o','n','i','s','t','a'},13,null,null),
	NOUN_RULE_28(new char[]{'i','o','n','i','s','t','a'},12,null,null),
	NOUN_RULE_29(new char[]{'i','o','n','a','r'},10,null,null),
	NOUN_RULE_30(new char[]{'i','o','n','a','l'},9,null,null),
	NOUN_RULE_31(new char[]{'ê','n','c','i','a'},8,null,null),
	NOUN_RULE_32(new char[]{'â','n','c','i','a'},9,null,new String[]{"ambulância"}),
	NOUN_RULE_33(new char[]{'e','d','o','u','r','o'},9,null,null),
	NOUN_RULE_34(new char[]{'q','u','e','i','r','o'},9,new char[]{'c'},null),
	NOUN_RULE_35(new char[]{'a','d','e','i','r','o'},10,null,new String[]{"desfiladeiro"}),
	NOUN_RULE_36(new char[]{'e','i','r','o'},7,null,new String[]{"desfiladeiro","mosteiro","pioneiro"}),
	NOUN_RULE_37(new char[]{'u','o','s','o'},7,null,null),
	NOUN_RULE_38(new char[]{'o','s','o'},6,null,new String[]{"precioso"}),
	NOUN_RULE_39(new char[]{'a','l','i','z','a','ç'},11,null,null),
	NOUN_RULE_40(new char[]{'a','t','i','z','a','ç'},11,null,null),
	NOUN_RULE_41(new char[]{'t','i','z','a','ç'},10,null,null),
	NOUN_RULE_42(new char[]{'i','z','a','ç'},9,null,new String[]{"organizaç"}),
	NOUN_RULE_43(new char[]{'a','ç'},5,null,new String[]{"equaç","relaç"}),
	NOUN_RULE_44(new char[]{'i','ç'},5,null,new String[]{"eleição"}),
	NOUN_RULE_45(new char[]{'á','r','i','o'},7,null,new String[]{"aniversário","armário","diário","lionário",
			"salário","voluntário"}),
	NOUN_RULE_46(new char[]{'a','t','ó','r','i','o'},9,null,null),
	NOUN_RULE_47(new char[]{'r','i','o'},8,null,new String[]{"aniversário","armário","compulsório","diário",
			"lionário","próprio","salário","stério","voluntário"}),
	NOUN_RULE_48(new char[]{'é','r','i','o'},10,null,null),
	NOUN_RULE_49(new char[]{'ê','s'},6,null,null),
	NOUN_RULE_50(new char[]{'e','z','a'},6,null,null),
	NOUN_RULE_51(new char[]{'e','z'},6,null,null),
	NOUN_RULE_52(new char[]{'e','s','c','o'},8,null,null),
	NOUN_RULE_53(new char[]{'a','n','t','e'},6,null,new String[]{"adiante","elefante","gigante","instante",
			"possante","restaurante"}),
	NOUN_RULE_54(new char[]{'á','s','t','i','c','o'},10,null,new String[]{"eclesiástico"}),
	NOUN_RULE_55(new char[]{'a','l','í','s','t','i','c','o'},11,null,null),
	NOUN_RULE_56(new char[]{'á','u','t','i','c','o'},10,null,null),
	NOUN_RULE_57(new char[]{'ê','u','t','i','c','o'},10,null,null),
	NOUN_RULE_58(new char[]{'t','i','c','o'},7,null,new String[]{"alopático","artístico","autêntico","critico",
			"crítico","diagnostico","diagnóstico","doméstico","eclesiástico","eclético","idêntico","político",
			"prático"}),
	NOUN_RULE_59(new char[]{'i','c','o'},7,null,new String[]{"explico","público","tico"}),
	NOUN_RULE_60(new char[]{'i','v','i','d','a','d','e'},12,null,null),
	NOUN_RULE_61(new char[]{'i','d','a','d','e'},9,null,new String[]{"autoridade","comunidade"}),			
	NOUN_RULE_62(new char[]{'o','r','i','a'},8,null,new String[]{"categoria"}),
	NOUN_RULE_63(new char[]{'e','n','c','i','a','l'},11,null,null),
	NOUN_RULE_64(new char[]{'i','s','t','a'},8,null,null),
	NOUN_RULE_65(new char[]{'a','u','t','a'},9,null,null),
	NOUN_RULE_66(new char[]{'q','u','i','c','e'},9,new char[]{'c'},null),
	NOUN_RULE_67(new char[]{'i','c','e'},7,null,new String[]{"cúmplice"}),
	NOUN_RULE_68(new char[]{'í','a','c','o'},7,null,null),
	NOUN_RULE_69(new char[]{'e','n','t','e'},8,null,new String[]{"acrescente","alimente","aparente","freqüente",
			"oriente","permanente"}),
	NOUN_RULE_70(new char[]{'e','n','s','e'},9,null,null),
	NOUN_RULE_71(new char[]{'i','n','a','l'},7,null,null),
	NOUN_RULE_72(new char[]{'a','n','o'},7,null,null),
	NOUN_RULE_73(new char[]{'á','v','e','l'},6,null,new String[]{"afável","potável","razoável","vulnerável"}),
	NOUN_RULE_74(new char[]{'í','v','e','l'},7,null,new String[]{"possível"}),
	NOUN_RULE_75(new char[]{'v','e','l'},8,null,new String[]{"possível","solúvel","vulnerável"}),
	NOUN_RULE_76(new char[]{'b','i','l'},6,new char[]{'v','e','l'},null),
	NOUN_RULE_77(new char[]{'u','r','a'},7,null,new String[]{"acupuntura","costura","imatura"}),
	NOUN_RULE_78(new char[]{'u','r','a','l'},8,null,null),
	NOUN_RULE_79(new char[]{'u','a','l'},6,null,new String[]{"bissexual","pontual","virtual","visual"}),
	NOUN_RULE_80(new char[]{'i','a','l'},6,null,null),
	NOUN_RULE_81(new char[]{'a','l'},6,null,new String[]{"afinal","animal","bissexual","desleal","estatal",
			"fiscal","formal","liberal","pessoal","pontual","postal","sideral","sucursal","virtual","visual"}),
	NOUN_RULE_82(new char[]{'a','l','i','s','m','o'},10,null,null),
	NOUN_RULE_83(new char[]{'i','v','i','s','m','o'},10,null,null),
	NOUN_RULE_84(new char[]{'i','s','m','o'},7,null,new String[]{"cinismo"});
	
	private final char[] suffix;
	private final int minLength;
	private final char[] replacement;
	private final String[] exceptions;

	RSLPNounRules(char[] suffix, int minLength, char[] replacement, String[] exceptions) {
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
