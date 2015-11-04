RSLP Stemmer Plugin for Elasticsearch
========================================

RSLP stemmer plugin integrates the RSLP stemming algorithm for Brazilian Portuguese into Elasticsearch.

## Installing

```sh
bin/plugin install https://github.com/anaelcarvalho/elasticsearch-analysis-rslp/blob/master/dist/elasticsearch-analysis-rslp-1.0.0.zip?raw=true
```

## Building from source

```bash
mvn clean package
bin/plugin install file:target/releases/elasticsearch-analysis-rslp-1.0.0.zip
```

## Compatibility

|RSLP Stemmer Plugin|Elasticsearch|
|---|---|
| 1.0.0|2.0+|

## Usage

This plugin includes the 'br_rslp' token filter. 

Example usage:

	index:
	  analysis:
	    filter:
	      my_stemmer:
	        type: br_rslp
