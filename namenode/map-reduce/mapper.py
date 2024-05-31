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
        'COLLISION_ID': row['COLLISION_ID'] if 'COLLISION_ID' in row else '',
        'EMOTIONAL_STATUS': row['EMOTIONAL_STATUS'] if 'EMOTIONAL_STATUS' in row else '',
        'BODILY_INJURY': row['BODILY_INJURY'] if 'BODILY_INJURY' in row else '',
        'PERSON_TYPE': row['PERSON_TYPE'] if 'PERSON_TYPE' in row else '',
        'PERSON_AGE': row['PERSON_AGE'] if 'PERSON_AGE' in row else '',
        'PERSON_SEX': row['PERSON_SEX'] if 'PERSON_SEX' in row else '',
        'SAFETY_EQUIPMENT': row['SAFETY_EQUIPMENT'] if 'SAFETY_EQUIPMENT' in row else ''
    }
    # Emit JSON object
    print(json.dumps(json_obj))


