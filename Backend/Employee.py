import hashlib
import sqlite3
from config import url

class Employee:
    def __init__(self, first_name, last_name, employee_id, password):
        self.conn = sqlite3.connect(url)
        self.first_name = first_name
        self.last_name = last_name
        self.employee_id = employee_id
        self.password = hashlib.sha256(password.encode()).hexdigest()
    
    def remove(employee_id):
        print('removed')

    def verifyLogin(employee_id, password):
        sql = "SELECT password FROM EMPLOYEE WHERE employee_id=?"
        val = (employee_id,)
        conn = sqlite3.connect(url)
        c = conn.cursor()
        c.execute(sql, val)
        result = c.fetchone()
        if result == None:
            print('[API] Log in failed for employee_id = ' + str(employee_id))
            return False
        if not result[0] == password:
            return False
        print('[API] Log in successful for employee_id = ' + str(employee_id))
        return True
        
    def verifyEmployeeID(self):
        #check if the employee id doesnt already exist
        sql = "SELECT entry_id FROM EMPLOYEE WHERE employee_id =?"
        val = (self.employee_id,)
        c = self.conn.cursor()
        c.execute(sql,val)
        result = c.fetchone()
        if result == None:
            return False
        print('[API] Employee ID found at entry = ' + str(result[0]))
        return True

    def store(self):
        try:
            sql = '''
                INSERT INTO EMPLOYEE (first_name, last_name, employee_id, password) VALUES (?,?,?,?)
            '''
            val = (self.first_name, self.last_name, self.employee_id, self.password)
            c = self.conn.cursor()
            c.execute(sql, val)
            return True
        except Exception as e:
            raise Exception("[API] Error adding employee to database, verify that the information passed is not null and of the correct type")
  
    def add(self):
        if Employee.verifyEmployeeID(self):
            return False 
        if Employee.store(self):
            print('[API] Employee added to database')
# e = Employee("2",'2','32','4')
# e.add()

