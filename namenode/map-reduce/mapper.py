#!/usr/bin/env python3

import sys
import json

for line in sys.stdin:
    def split_csv_line(line):
        fields = []
        field = ''
        in_quotes = False

        for char in line:
            if char == ',' and not in_quotes:
                fields.append(field)
                field = ''
            elif char == '"':
                in_quotes = not in_quotes
            else:
                field += char

        fields.append(field)

        return fields


    fields = split_csv_line(line.strip())

    if len(fields) > 0:
        unique_id = fields[0]
        collision_id = fields[1]
        crash_date = fields[2]
        crash_time = fields[3]
        person_id = fields[4]
        person_type = fields[5]
        person_injury = fields[6]
        vehicle_id = fields[7]
        person_age = fields[8]
        ejection = fields[9]
        emotional_status = fields[10]
        bodily_injury = fields[11]
        position_in_vehicle = fields[12]
        safety_equipment = fields[13]
        ped_location = fields[14]
        ped_action = fields[15]
        complaint = fields[16]
        ped_role = fields[17]
        contributing_factor_1 = fields[18]
        contributing_factor_2 = fields[19]
        person_sex = fields[20]

        json_object = {
            "unique_id": unique_id,
            "collision_id": collision_id,
            "crash_date": crash_date,
            "crash_time": crash_time,
            "person_id": person_id,
            "person_type": person_type,
            "person_injury": person_injury,
            "vehicle_id": vehicle_id,
            "person_age": person_age,
            "ejection": ejection,
            "emotional_status": emotional_status,
            "bodily_injury": bodily_injury,
            "position_in_vehicle": position_in_vehicle,
            "safety_equipment": safety_equipment,
            "ped_location": ped_location,
            "ped_action": ped_action,
            "complaint": complaint,
            "ped_role": ped_role,
            "contributing_factor_1": contributing_factor_1,
            "contributing_factor_2": contributing_factor_2,
            "person_sex": person_sex
        }
        print(json.dumps(json_object))

