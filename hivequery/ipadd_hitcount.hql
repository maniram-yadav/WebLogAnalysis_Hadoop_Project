create external table ipcount (

ipaddress string,

hitcount int
)

ROW FORMAT SERDE 'org.apache.hive.hcatalog.data.JsonSerDe'

STORED AS TEXTFILE

LOCATION '/ipcount';

