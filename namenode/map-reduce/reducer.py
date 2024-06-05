#!/usr/bin/env python3
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

# print(sys.stdin)
# #!/usr/bin/env python
# import sys
# for line in sys.stdin:
#     line = line.strip()
#     ck_others = line.split('\t')          # parsing mapper o/p
#     ck = ck_others[0]
#     others = ck_others[1]
#
#     other_parsed = others.split('\001')