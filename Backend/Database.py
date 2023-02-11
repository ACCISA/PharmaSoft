from config import url
import sqlite3

class Database:
    def __init__(self):
        conn = sqlite3.connect(url)
        conn.execute('''
        
            CREATE TABLE IF NOT EXISTS EMPLOYEE 
            ([entry_id] INTEGER PRIMARY KEY,
            [first_name] VARCHAR(60) NOT NULL,
            [last_name] VARCHAR(60) NOT NULL,
            [employee_id] VARCHAR(70) NOT NULL,
            [password] VARCHAR(180) NOT NULL           
            )

        ''')
        # conn.execute('''''')

def Initialize():
    Database()
    print('[API] Database Initialized')