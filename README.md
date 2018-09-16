# WebLogAnalysis_Hadoop_Project

## Objective:

The objective is to enable participants to get a feel of working with production datasets and analyzing data using tools in Hadoop ecosystem. 


This POC requires 

a. Extracting required columns from Web Server logs, processing data using Map Reduce, output result as JSON files for use by other applications

b. Query the JSON file from Hive using a JSON SerDe 


Input Dataset:
Use the weblog file provided with the input directory.

Input dataset is a Web Server log which has the following details:

`IP Address,`

`Timestamp,`

`Request Type,`

`Resource Requested,`

`Request Status,`

`Size of output object in bytes`



**Sample Record in Log file:**

**10.211.145.145 - - [15/Apr/2008:13:40:25 -0800] "GET /assets/js/listprod.js HTTP/1.1" 200 12460**

10.211.145.145   –   IP Address of Client
 
_          –   UserID (Masked)
 
_           –   Password (Masked)   

[15/Apr/2008:13:40:25 -0800] 	         – 	Date and Time of request

"GET /assets/js/listprod.js HTTP/1.1" – 	Client Request (contains method, path of the page accessed and protocol)

200             – 	Request Status Code (200–success, 404-Not Found)  

12460          – 	Size of output in Bytes 

>



**Requirements:**

a. Find the total number of hits by IP Address -  Output Columns: IP Address, Number of Hits

b. Find the Web Pages by number of hits by rank - Output Columns: Web Page Name, Number of Hits, Rank


**Steps Involved:**

a. Extract Web Server log files and copy it to HDFS

b. Create a Map Reduce Job using Java  for each of the above requirements to extract required columns from Log File using Regular Expressions and save the output as JSON format

c. Create an external Hive table to read the JSON file and query the data from Hive.


**Required Output:**

a. JSON output files from both the requirements

b. Output of Hive Select Query (select * from <tablename>)  with Column headings
 
 
 # :point_right: [Problem Solutions](https://github.com/maniram-yadav/WebLogAnalysis_Hadoop_Project/tree/master/src/org/weblog)
