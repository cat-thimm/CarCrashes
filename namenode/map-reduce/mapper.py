#!/usr/bin/env python
# -*-coding:utf-8 -*

import sys
import csv
import json

# Read CSV rows from stdin and emit JSON objects
csv_reader = csv.DictReader(sys.stdin)
for row in csv_reader:
    # Transform CSV row to JSON object
    json_obj = {
        'COLLISION_ID': row['COLLISION_ID'],
        'EMOTIONAL_STATUS': row['EMOTIONAL_STATUS'],
        'BODILY_INJURY': row['BODILY_INJURY'],
        'PERSON_TYPE': row['PERSON_TYPE'],
        'PERSON_AGE': row['PERSON_AGE'],
        'PERSON_SEX': row['PERSON_SEX'],
        'SAFETY_EQUIPMENT': row['SAFETY_EQUIPMENT']
    }
    # Emit JSON object
    print(json.dumps(json_obj))