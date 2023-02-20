import datetime
import sqlite3
from config import url


class Log:
    def __init__(self, action, message, employee_id):
        self.action = action
        self.message = message
        self.time = datetime.datetime.now()
        self.employee_id = employee_id
        self.log()
    def __str__(self):
        return self.action +": " + self.message + "; At " + self.time
    def log(self):
        sql = 'INSERT INTO LOGS (action, message, caller, time) VALUES (?,?,?,?)'
        val = (self.action, self.message, self.employee_id, self.time)
        conn = sqlite3.connect(url)
        c = con.cursor()
        c.execute(sql,val)