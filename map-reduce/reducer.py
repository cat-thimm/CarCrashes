#!/usr/bin/env python
# -*-coding:utf-8 -*

import sys
import json

# Read JSON objects from stdin and combine them into a single JSON array
json_array = []
for line in sys.stdin:
    json_obj = json.loads(line)
    json_array.append(json_obj)

# Output the combined JSON array
print(json.dumps(json_array))
