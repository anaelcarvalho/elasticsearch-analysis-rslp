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

import static org.hamcrest.Matchers.equalTo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
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
import org.junit.Test;

/**
 * @author anaelcarvalho
 */
public class RSLPTokenFilterTests extends ESTestCase {
	
    @Test
    public void testRSLPRules() throws Exception {
        Index index = new Index("test");
        Settings settings = Settings.settingsBuilder()
                .put(IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT)
                .put("path.home", createTempDir())
                .put("index.analysis.filter.myStemmer.type", "br_rslp")
                .build();
        AnalysisService analysisService = createAnalysisService(index, settings);

        TokenFilterFactory filterFactory = analysisService.tokenFilter("myStemmer");

        Tokenizer tokenizer = new KeywordTokenizer();
        
        Map<String,String> words = buildWordList();
        
        Set<String> inputWords = words.keySet();
        for(String word : inputWords) {
            tokenizer.setReader(new StringReader(word));
            TokenStream ts = filterFactory.create(tokenizer);

            CharTermAttribute term1 = ts.addAttribute(CharTermAttribute.class);
            ts.reset();
            assertThat(ts.incrementToken(), equalTo(true));
            assertThat(term1.toString(), equalTo(words.get(word)));
            ts.close();
        }
    }
    
    @Test
    public void testRSLPPhrases() throws Exception {
        Index index = new Index("test");
        Settings settings = Settings.settingsBuilder()
                .put(IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT)
                .put("path.home", createTempDir())
                .put("index.analysis.analyzer.myAnalyzer.type", "custom")
                .put("index.analysis.analyzer.myAnalyzer.tokenizer", "standard")
                .put("index.analysis.analyzer.myAnalyzer.filter", "br_rslp")
                .build();
        AnalysisService analysisService = createAnalysisService(index, settings);

        Analyzer analyzer = analysisService.analyzer("myAnalyzer");
        
        Map<String,List<String>> phrases = buildPhraseList();
        
        for(String phrase : phrases.keySet()) {
	        List<String> outputWords = phrases.get(phrase);
	        
	        TokenStream ts = analyzer.tokenStream("test", phrase);
	
	        CharTermAttribute term1 = ts.addAttribute(CharTermAttribute.class);
	        ts.reset();
	
	        for (String expected : outputWords) {
	            assertThat(ts.incrementToken(), equalTo(true));
	            assertThat(term1.toString(), equalTo(expected));
	        }
	        ts.close();
        
        }
    }

    private AnalysisService createAnalysisService(Index index, Settings settings) {
        Injector parentInjector = new ModulesBuilder().add(new SettingsModule(settings), 
        		new EnvironmentModule(new Environment(settings))).createInjector();
        Injector injector = new ModulesBuilder().add(
                new IndexSettingsModule(index, settings),
                new IndexNameModule(index),
                new AnalysisModule(settings, parentInjector.getInstance(IndicesAnalysisService.class))
                	.addProcessor(new RSLPAnalysisBinderProcessor()))
                	.createChildInjector(parentInjector);

        return injector.getInstance(AnalysisService.class);
    }
    
    private Map<String,String> buildWordList() {
    	Map<String,String> testWords = new HashMap<String,String>();
    	testWords.put("bons", "bom");
    	testWords.put("balão", "bal");
    	testWords.put("capitães", "capita");
    	testWords.put("normais", "norm");
    	testWords.put("papéis", "papel");
    	testWords.put("amáveis", "am");
    	testWords.put("lençóis", "lencol");
    	testWords.put("barris", "barril");
    	testWords.put("males", "mal");
    	testWords.put("mares", "mar");
    	testWords.put("casas", "cas");
    	testWords.put("mãe", "mae");
    	testWords.put("mais", "mais");
    	testWords.put("lápis", "lapis");
    	testWords.put("pires", "pires");
    	testWords.put("bonachona", "bonach");
    	testWords.put("anã", "ana");
    	testWords.put("cortisona", "cortison");
    	testWords.put("vilã", "vil");
    	testWords.put("amanhã", "amanha");
    	testWords.put("professora", "profes");
    	testWords.put("americana", "americ");
    	testWords.put("guiana", "guian");
    	testWords.put("sozinha", "so");
    	testWords.put("rainha", "rainh");
    	testWords.put("inglesa", "ingl");
    	testWords.put("famosa", "fam");
    	testWords.put("mucosa", "mucos");
    	testWords.put("maníaca", "man");
    	testWords.put("prática", "prat");
    	testWords.put("dica", "dic");
    	testWords.put("coitada", "coit");
    	testWords.put("pitada", "pitad");
    	testWords.put("mantida", "mant");
    	testWords.put("vida", "vid");
    	testWords.put("caída", "caid");
    	testWords.put("saída", "said");
    	testWords.put("prima", "prim");
    	testWords.put("vítima", "vitim");
    	testWords.put("passiva", "passiv");
    	testWords.put("oliva", "oliv");
    	testWords.put("primeira", "prim");
    	testWords.put("frigideira", "frigide");
    	testWords.put("felizmente", "feliz");
    	testWords.put("experimente", "experim");
    	testWords.put("cansadíssimo", "cans");
    	testWords.put("amabilíssimo", "amavel");
    	testWords.put("amabilíssimo", "amavel");
    	testWords.put("fortíssimo", "fort");
    	testWords.put("chiquérrimo", "chiqu");
    	testWords.put("pezinho", "pe");
    	testWords.put("maluquinho", "maluc");
    	testWords.put("amiguinho", "amig");
    	testWords.put("cansadinho", "cans");
    	testWords.put("carrinho", "carr");
    	testWords.put("carinho", "carinh");
    	testWords.put("grandalhão", "grand");
    	testWords.put("dentuça", "dent");
    	testWords.put("ricaço", "ricac");
    	testWords.put("antebraço", "antebrac");
    	testWords.put("cansadão", "cans");
    	testWords.put("corpázio", "corp");
    	testWords.put("topázio", "topazi");
    	testWords.put("pratarraz", "prat");
    	testWords.put("bocarra", "bocarr");
    	testWords.put("calorzão", "cal");
    	testWords.put("coalizão", "coaliz");
    	testWords.put("meninão", "menin");
    	testWords.put("patrão", "patra");
    	testWords.put("existencialista", "existenci");
    	testWords.put("minimalista", "minim");
    	testWords.put("contagem", "cont");
    	testWords.put("coragem", "corag");
    	testWords.put("gerenciamento", "gerenc");
    	testWords.put("monitoramento", "monitor");
    	testWords.put("firmamento", "firmament");
    	testWords.put("nascimento", "nasc");
    	testWords.put("comercializado", "comerci");
    	testWords.put("traumatizado", "traum");
    	testWords.put("alfabetizado", "alfabet");
    	testWords.put("organizado", "organiz");
    	testWords.put("associativo", "associ");
    	testWords.put("pejorativo", "pejora");
    	testWords.put("contraceptivo", "contracep");
    	testWords.put("relativo", "relat");
    	testWords.put("esportivo", "espor");
    	testWords.put("abalado", "abal");
    	testWords.put("grado", "grad");
    	testWords.put("impedido", "imped");
    	testWords.put("consolido", "consolid");
    	testWords.put("ralador", "ral");
    	testWords.put("entendedor", "entend");
    	testWords.put("cumpridor", "cumpr");
    	testWords.put("obrigatória", "obrigator");
    	testWords.put("produtor", "produt");
    	testWords.put("benfeitor", "benfeitor");
    	testWords.put("comparabilidade", "compar");
    	testWords.put("abolicionista", "abol");
    	testWords.put("intervencionista", "interven");
    	testWords.put("profissional", "profiss");
    	testWords.put("referência", "refer");
    	testWords.put("repugnância", "repugn");
    	testWords.put("ambulância", "ambulanc");
    	testWords.put("abatedouro", "abat");
    	testWords.put("fofoqueiro", "fofoc");
    	testWords.put("brasileiro", "brasil");
    	testWords.put("gostoso", "gost");
    	testWords.put("precioso", "precios");
    	testWords.put("comercialização", "comerci");
    	testWords.put("consumismo", "consum");
    	testWords.put("concretização", "concre");
    	testWords.put("organização", "organiz");
    	testWords.put("alegação", "aleg");
    	testWords.put("equação", "equac");
    	testWords.put("abolição", "abol");
    	testWords.put("eleição", "ele");
    	testWords.put("anedotário", "anedot");
    	testWords.put("voluntário", "voluntari");
    	testWords.put("ministério", "ministe");
    	testWords.put("chinês", "chin");
    	testWords.put("beleza", "bel");
    	testWords.put("rigidez", "rigid");
    	testWords.put("parentesco", "parent");
    	testWords.put("ocupante", "ocup");
    	testWords.put("elefante", "elefant");
    	testWords.put("bombástico", "bomb");
    	testWords.put("eclesiástico", "eclesiast");
    	testWords.put("problemático", "problema");
    	testWords.put("polêmico", "polem");
    	testWords.put("diagnóstico", "diagnost");
    	testWords.put("produtividade", "produt");
    	testWords.put("profundidade", "profund");
    	testWords.put("autoridade", "autoridad");
    	testWords.put("aposentadoria", "aposentad");
    	testWords.put("categoria", "categor");
    	testWords.put("existencial", "exist");
    	testWords.put("artista", "artist");
    	testWords.put("maluquice", "maluc");
    	testWords.put("chatice", "chat");
    	testWords.put("cúmplice", "cumplic");
    	testWords.put("demoníaco", "demon");
    	testWords.put("decorrente", "decorr");
    	testWords.put("permanente", "permanent");
    	testWords.put("criminal", "crim");
    	testWords.put("americano", "americ");
    	testWords.put("amável", "am");
    	testWords.put("vulnerável", "vulneravel");
    	testWords.put("combustível", "combust");
    	testWords.put("possível", "possivel");
    	testWords.put("cobertura", "cobert");
    	testWords.put("acupuntura", "acupuntur");
    	testWords.put("consensual", "consens");
    	testWords.put("mundial", "mund");
    	testWords.put("experimental", "experiment");
    	testWords.put("liberal", "liberal");
    	testWords.put("nativismo", "nativ");
    	testWords.put("cinismo", "cinism");
    	testWords.put("cantaríamos", "cant");
    	testWords.put("cantássemos", "cant");
    	testWords.put("beberíamos", "beb");
    	testWords.put("bebêssemos", "beb");
    	testWords.put("partiríamos", "part");
    	testWords.put("partíssemos", "part");
    	testWords.put("cantáramos", "cant");
    	testWords.put("cantáreis", "cantarel");
    	testWords.put("cantaremos", "cant");
    	testWords.put("cantariam", "cant");
    	testWords.put("cantaríeis", "cantariel");
    	testWords.put("cantásseis", "cantassel");
    	testWords.put("cantassem", "cant");
    	testWords.put("cantávamos", "cant");
    	testWords.put("bebêramos", "beb");
    	testWords.put("beberemos", "beb");
    	testWords.put("beberiam", "beb");
    	testWords.put("beberíeis", "beberiel");
    	testWords.put("bebêsseis", "bebessel");
    	testWords.put("bebessem", "beb");
    	testWords.put("partíramos", "part");
    	testWords.put("partiremos", "part");
    	testWords.put("partiriam", "part");
    	testWords.put("partiríeis", "partiriel");
    	testWords.put("partísseis", "partissel");
    	testWords.put("partissem", "part");
    	testWords.put("cantando", "cant");
    	testWords.put("bebendo", "beb");
    	testWords.put("partindo", "part");
    	testWords.put("propondo", "prop");
    	testWords.put("cantaram", "cant");
    	testWords.put("cantardes", "cant");
    	testWords.put("cantarei", "cant");
    	testWords.put("cantarem", "cant");
    	testWords.put("cantaria", "cant");
    	testWords.put("cantarmos", "cant");
    	testWords.put("cantasse", "cant");
    	testWords.put("cantaste", "cant");
    	testWords.put("cantavam", "cant");
    	testWords.put("agravam", "agrav");
    	testWords.put("cantáveis", "cant");
    	testWords.put("beberam", "beb");
    	testWords.put("beberdes", "beb");
    	testWords.put("beberei", "beb");
    	testWords.put("bebêreis", "beberel");
    	testWords.put("beberem", "beb");
    	testWords.put("beberia", "beb");
    	testWords.put("bebermos", "beb");
    	testWords.put("bebesse", "beb");
    	testWords.put("bebeste", "beb");
    	testWords.put("faroeste", "faroest");
    	testWords.put("bebíamos", "beb");
    	testWords.put("partiram", "part");
    	testWords.put("concluíram", "conclu");
    	testWords.put("partirdes", "part");
    	testWords.put("partirei", "part");
    	testWords.put("admirei", "admir");
    	testWords.put("partirem", "part");
    	testWords.put("adquirem", "adquir");
    	testWords.put("concretizar", "concre");
    	testWords.put("organizar", "organiz");
    	testWords.put("debilitar", "debil");
    	testWords.put("estreitar", "estreit");
    	testWords.put("partiria", "part");
    	testWords.put("partirmos", "part");
    	testWords.put("partisse", "part");
    	testWords.put("partiste", "part");
    	testWords.put("cantamos", "cant");
    	testWords.put("cantara", "cant");
    	testWords.put("arara", "arar");
    	testWords.put("cantará", "cant");
    	testWords.put("alvará", "alvar");
    	testWords.put("cantares", "cant");
    	testWords.put("cantavas", "cant");
    	testWords.put("agravas", "agrav");
    	testWords.put("cantemos", "cant");
    	testWords.put("bebera", "beb");
    	testWords.put("bebera", "beb");
    	testWords.put("acelera", "aceler");
    	testWords.put("beberá", "beb");
    	testWords.put("beberes", "beb");
    	testWords.put("espere", "esper");
    	testWords.put("bebiam", "beb");
    	testWords.put("enfiam", "enfi");
    	testWords.put("bebiéis", "bebiel");
    	testWords.put("partimos", "part");
    	testWords.put("queimo", "queim");
    	testWords.put("partira", "part");
    	testWords.put("fronteira", "fronteir");
    	testWords.put("partirá", "part");
    	testWords.put("partires", "part");
    	testWords.put("admire", "adm");
    	testWords.put("compomos", "comp");
    	testWords.put("cantais", "cant");
    	testWords.put("cantam", "cant");
    	testWords.put("barbear", "barb");
    	testWords.put("nuclear", "nucle");
    	testWords.put("cantar", "cant");
    	testWords.put("bazaar", "bazaar");
    	testWords.put("cheguei", "cheg");
    	testWords.put("cantei", "cant");
    	testWords.put("alaguem", "alag");
    	testWords.put("cantem", "cant");
    	testWords.put("virgem", "virgem");
    	testWords.put("beber", "beb");
    	testWords.put("pier", "pier");
    	testWords.put("bebeu", "beb");
    	testWords.put("chapeu", "chapeu");
    	testWords.put("bebia", "beb");
    	testWords.put("lábia", "labi");
    	testWords.put("polícia", "polici");
    	testWords.put("partir", "part");
    	testWords.put("freir", "freir");
    	testWords.put("partiu", "part");
    	testWords.put("chegou", "cheg");
    	testWords.put("bebi", "beb");
    	testWords.put("menina", "menin");
    	testWords.put("grande", "grand");
    	testWords.put("menino", "menin");
    	testWords.put("porquinho", "porc");
    	testWords.put("riquinho", "ric");
    	testWords.put("mesquinho", "mesq");
    	testWords.put("amesquinho", "amesq");

    	return testWords;
    }
    
    private Map<String,List<String>> buildPhraseList() {
    	Map<String,List<String>> testPhrases = new HashMap<String,List<String>>();
    	List<String> outputTokens = new ArrayList<String>();
    	outputTokens.add("Voc");
    	outputTokens.add("ja");
    	outputTokens.add("repar");
    	outputTokens.add("no");
    	outputTokens.add("olh");
    	outputTokens.add("del");
    	outputTokens.add("Sao");
    	outputTokens.add("assim");
    	outputTokens.add("de");
    	outputTokens.add("cigan");
    	outputTokens.add("obliqu");
    	outputTokens.add("e");
    	outputTokens.add("dissimul");
    	testPhrases.put("Você já reparou nos olhos dela? São assim de cigana oblíqua e dissimulada.", outputTokens);
    	
    	return testPhrases;
    }
}
