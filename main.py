from pykson import JsonObject, IntegerField, StringField, ObjectListField, BooleanField, TimestampMillisecondsField, FloatField, Pykson, ListField
import requests
import os
import psutil
import time as tm
import calendar
import datetime
import json


URL = "http://localhost:8080/"

class Clients(JsonObject):
    id = IntegerField()
    key = StringField()

class Tasks(JsonObject):
    id = IntegerField()
    command = StringField()
    status = BooleanField()
    clients_id = Clients()

class Records(JsonObject):
    id = IntegerField()
    time = StringField()
    cpu = FloatField()
    ram = FloatField()
    ip = StringField()
    ssd = FloatField()
    clients_id = Clients()

class Foo(JsonObject):
    id = IntegerField()
    name = StringField()

def myconverter(o):
    if isinstance(o, datetime.datetime):
        return o.__str__()

def getParam(c):
    cpu = (psutil.cpu_percent())
    ram = (psutil.virtual_memory().percent)
    ssd = (psutil.disk_usage('/').percent)
    ip = requests.get("https://api.ipify.org").text
    presentDate = datetime.datetime.now()
    unix_timestamp = datetime.datetime.timestamp(presentDate) * 1000
    r = Records()
    r.time = str(int(unix_timestamp))
    r.cpu = cpu
    r.ip = ip
    r.ssd = ssd
    r.ram = ram
    r.clients_id = c 
    requests.post(URL + "records", json = {
        "timestamp": r.time,
        "cpu": cpu,
        "ssd": r.ssd,
        "ip": r.ip,
        "ram": r.ram,
        "clients_id": {
            "key": c.key,
            },
        }).text

def checkInputCommands(c):
    t = requests.get(URL + "tasks/getTasksByClient/" + c.key).text
    tasks = Pykson().from_json(t, Tasks)
    for i in range(len(tasks)):
        task = tasks[i]
        print("[НОВАЯ КОМАНДА] ~ " + task.command)
        
        #execute command
        '''
        if task.command == "photo":
        elif task.command == "reboot":
        else:
            #subprocess call to CMD
        '''

        responce = requests.put(URL + "tasks/changeStatus/", json = {
            "id": task.id,
            "command": task.command,
            "clients_id": {
                "key": c.key,
            },
        }).text

        print(responce)

if __name__ == "__main__":
    c = None
    if os.path.exists(os.getcwd()+ "/settings.ini") == False:
        t = requests.get(URL + "clients/newKey").text
        file = open("settings.ini", "w", encoding = "utf-8", errors = "ignore")
        file.write(t)
        file.close()
        c = Pykson().from_json(t, Clients)
    else:
        file = open("settings.ini", "r", encoding = "utf-8", errors = "ignore")
        t = file.read()
        c = Pykson().from_json(t, Clients)
        file.close()

    while True:    
        getParam(c)
        checkInputCommands(c)
        tm.sleep(5)

        
