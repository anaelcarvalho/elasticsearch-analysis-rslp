RSLP Stemmer Plugin for Elasticsearch
========================================

RSLP stemmer plugin integrates the RSLP stemming algorithm for Brazilian Portuguese into Elasticsearch.

## Installing

```sh
./elasticsearch-plugin install https://github.com/anaelcarvalho/elasticsearch-analysis-rslp/blob/master/dist/elasticsearch-analysis-rslp-2.0.0.zip?raw=true
```

## Building from source

```bash
mvn clean package
./elasticsearch-plugin install file:target/releases/elasticsearch-analysis-rslp-2.0.0.zip
```

## Compatibility

|RSLP Stemmer Plugin|Elasticsearch|JDK
|---|---|---|
| 2.0.0|5.0+|1.8+|
| 1.0.1|2.x|1.7+|
| 1.0.0|2.x|1.7+|

## Usage

This plugin includes the 'br_rslp' token filter. 

Example usage:

```javascript
{
  "settings": {
    "analysis": {
      "analyzer": {
        "myAnalyzer": {
          "tokenizer":  "standard",
          "filter": [
            "br_rslp"
          ]
        }
      }
    }
  }
}
```
