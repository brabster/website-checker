website-checker
===============

Checks whether websites of interest meet expectations around availability, performance and security.

quick-start
===========

Define your website URLs and expectations using JSON and then run the checker programmatically
or as an executable JAR using the Maven assembly:single goal.

An example of URL and expectation definitions follows.
We expect all URLs in the "Google" group to return an HTTP 200 response in less than 2000ms,
and where a secure URL is specified, it must present a valid certificate.
We further specify that the "Secure Google Homepage"
URL must present a certificate valid for at least the next 7 days.

```
[
   {
     "label": "Google",
     "expect": {
         "faster_than_millis": 2000,
         "status_code": 200,
         "accept_untrusted_certificates": false
     },
     "urls": [
         {
             "url": "http://google.com",
             "label": "Google Homepage"
         },
         {
             "url": "https://www.google.co.uk/webhp",
             "label": "Secure Google Homepage",
             "expect": {
                 "certificate_valid_for_days": 7
             }
         }
      ]
   }
]
```

Running a jar-with-dependencies produces output such as:

```
[paul@paul-laptop java-website-check]$ java -jar target/java-website-check-1.0-SNAPSHOT-jar-with-dependencies.jar urls.json
Starting Check
OK  My Blog (http://blog.crossedstreams.com) status 200 in 280ms
OK  My Domain (http://crossedstreams.com) status 200 in 272ms
OK  My Twitter Profile (http://twitter.com/brabster) status 301 in 206ms
OK  Google Homepage (http://google.com) status 200 in 31ms
ERR Secure Google Homepage (https://google.com) SSLException java.lang.RuntimeException: Certificate not valid for 365 days
ERR NoSuchDomain.none (http://nosuchdomain.none) UnknownHostException nosuchdomain.none
ERR No Page Here (http://blog.crossedstreams.com/nopagehere) expected HTTP 200 but received 404
Check Complete
```


license
=======

   Copyright 2013 Paul J Brabban

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
