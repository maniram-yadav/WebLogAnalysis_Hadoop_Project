create external table pagehit (

weburl string,

hitcount int,

rank int
)

ROW FORMAT SERDE 'org.apache.hive.hcatalog.data.JsonSerDe'

STORED AS TEXTFILE

LOCATION '/webhit';

