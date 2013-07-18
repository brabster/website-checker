website-checker
===============

Checks whether websites of interest meet expectations around availability, performance and security.

quick-start
===========

Define your website URLs and expectations using JSON and then run the checker programmatically
or as an executable JAR using the Maven assembly:single goal.

URL and expectation definitions look like the following, with working examples in the project.

```
[
   {
     "label": "Google",
     "expect": {
         "faster_than_millis": 2000
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
                 "certificate_valid_for_days": 365
             }
         }
      ]
   }
]
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
